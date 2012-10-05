package cz.muni.fi.raytracer;

import java.awt.image.BufferedImage;

/**
 * 
 * @author Andrej Pancik
 */
public class RayTracer {

	private static int MAX_DEPTH = 6;
	private final int screenWidth;
	private final int screenHeight;
	private final Color backgroundColor;
	private final Scene scene;

	public RayTracer(final int screenWidth, final int screenHeight, final Color backgroundColor, final Scene scene) {
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		this.backgroundColor = backgroundColor;
		this.scene = scene;
	}

	private Color phongShade(final Intersection intersection, final int depth) {
		// http://en.wikipedia.org/wiki/Phong_shading
		final Vector3D intersectionPoint = intersection.getIntersectionPoint();
		final Vector3D negativeIntersectionDirection = intersection.getRay().getDirection().times(-1);
		final Vector3D intersectionNormal = intersection.getSurfaceNormal();

		Color resultShade = this.scene.getAmbientLight().times(intersection.getSceneObject().getSurface().getAmbientReflection(intersectionPoint));

		Intersection firstIntersectionfromLight;
		for (final Light light : this.scene.getLights()) {
			final Ray rayToLight = new Ray(light.getPosition(), intersectionPoint.minus(light.getPosition()));

			firstIntersectionfromLight = this.scene.getNearestIntersection(rayToLight);

			if (firstIntersectionfromLight != null && intersection.getSceneObject() == firstIntersectionfromLight.getSceneObject()) // simplified
																																	// way
																																	// not
																																	// considering
																																	// selfshadowing
																																	// in
																																	// favor
																																	// of
																																	// performance
			{
				light.setShadowCache(intersection.getSceneObject());
				final Vector3D lightDirection = light.getPosition().minus(intersectionPoint).normalize();

				final double diffusionAngle = intersectionNormal.dot(lightDirection);
				if (diffusionAngle > 0) {
					// Diffusion
					final double diffuseCoefficient = intersection.getSceneObject().getSurface().getDiffuseReflection(intersectionPoint) * diffusionAngle;
					resultShade = resultShade.plus(light.getDiffuse().times(diffuseCoefficient));

					// Specular
					final Vector3D reflectionDirection = intersectionNormal.times(diffusionAngle * 2.0).minus(lightDirection);
					final double reflectionAngle = reflectionDirection.dot(negativeIntersectionDirection);
					if (reflectionAngle > 0) {
						final double specularCoefficient = intersection.getSceneObject().getSurface().getSpecularReflection(intersectionPoint) * Math.pow(reflectionAngle, intersection.getSceneObject().getSurface().getShininess(intersectionPoint));
						resultShade = resultShade.plus(light.getSpecular().times(specularCoefficient));
					}
				}
			}
		}

		Color reflexedLight;

		if (intersection.getSceneObject().getSurface().getReflectance(intersectionPoint) > 0 && depth > 0) {
			final double angle = intersectionNormal.dot(negativeIntersectionDirection);

			reflexedLight = this.traceRay(new Ray(intersectionPoint, intersectionNormal.times(angle * 2.0).minus(negativeIntersectionDirection)), depth - 1);
		} else {
			reflexedLight = new Color(0, 0, 0);
		}

		return intersection.getSceneObject().getColor(intersectionPoint).times(resultShade).plus(reflexedLight.times(intersection.getSceneObject().getSurface().getReflectance(intersectionPoint)));
	}

	public void render(final BufferedImage result) {
		final Vector3D screenCenter = this.scene.getCamera().getPosition().plus(this.scene.getCamera().getLookAt());
		final Vector3D equator = this.scene.getCamera().getLookAt().cross(this.scene.getCamera().getUp()).normalize();
		final Vector3D up = equator.cross(this.scene.getCamera().getLookAt()).normalize().times((double) this.screenHeight / (double) this.screenWidth);

		for (int y = -this.screenHeight / 2; y < this.screenHeight / 2; y++) {
			for (int x = -this.screenWidth / 2; x < this.screenWidth / 2; x++) {
				final Vector3D pixelPosition = screenCenter.minus(equator.times(x / (double) this.screenWidth)).minus(up.times(y / (double) this.screenHeight));

				final Color color = this.traceRay(new Ray(this.scene.getCamera().getPosition(), pixelPosition.minus(this.scene.getCamera().getPosition())), MAX_DEPTH);

				result.setRGB(x + this.screenWidth / 2, y + this.screenHeight / 2, color.getDrawingColor().getRGB());
			}
		}
	}

	private Color traceRay(final Ray ray, final int depth) {
		final Intersection minimalIntersection = this.scene.getNearestIntersection(ray);

		if (minimalIntersection == null) {
			return this.backgroundColor;
		} else {
			return this.phongShade(minimalIntersection, depth);
		}
	}
}
