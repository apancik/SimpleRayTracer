package cz.muni.fi.raytracer.sceneobjects;

import cz.muni.fi.raytracer.Color;
import cz.muni.fi.raytracer.Intersection;
import cz.muni.fi.raytracer.Ray;
import cz.muni.fi.raytracer.Vector3D;
import cz.muni.fi.raytracer.materials.IMaterial;

/**
 * 
 * @author Andrej Pancik
 */
public class Plane implements IRenderable {

	private final IMaterial surfaceMaterial;
	private final Vector3D normalVector;
	private final double offset;

	public Plane(final Vector3D normal, final double offset, final IMaterial surfaceMaterial) {
		this.normalVector = normal.normalize();
		this.offset = offset;
		this.surfaceMaterial = surfaceMaterial;
	}

	@Override
	public Color getColor(final Vector3D position) {
		return this.surfaceMaterial.getColor(position.getX() / 1000.0, position.getZ() / 1000.0);
	}

	@Override
	public Intersection getIntersection(final Ray ray) {
		final double denom = this.normalVector.dot(ray.getDirection());
		if (denom > 0) {
			return null;
		}

		return new Intersection(ray, this, (ray.getOrigin().dot(this.normalVector) + this.offset) / -denom);
	}

	@Override
	public Vector3D getNormal(final Vector3D pos) {
		return this.normalVector;
	}

	@Override
	public IMaterial getSurface() {
		return this.surfaceMaterial;
	}
}