package cz.muni.fi.raytracer;

import java.util.ArrayList;
import java.util.List;

import cz.muni.fi.raytracer.sceneobjects.IRenderable;

/**
 * Scene class that contains information about objects, lights and camera. It contains also the bacground color and abmient
 * light color information as well as method for detecting nearest intersections of rays.
 * 
 * @author Andrej Pancik
 */
public class Scene {

	private final List<IRenderable> objects = new ArrayList<IRenderable>();
	private final List<Light> lights = new ArrayList<Light>();
	private Camera camera;
	private Color background = new Color(0, 0, 0);
	private Color ambientLight = new Color(0, 0, 0);

	/**
	 * 
	 * @param camera
	 *            scene camera
	 */
	public Scene(final Camera camera) {
		this.camera = camera;
	}

	/**
	 * @return the ambient light color
	 */
	public Color getAmbientLight() {
		return this.ambientLight;
	}

	/**
	 * @return the background color
	 */
	public Color getBackground() {
		return this.background;
	}

	/**
	 * @return the camera
	 */
	public Camera getCamera() {
		return this.camera;
	}

	/**
	 * @return the lights of the scene
	 */
	public List<Light> getLights() {
		return this.lights;
	}

	/**
	 * Method that detects nearest intersection of the scene and the ray provided
	 * 
	 * @param ray
	 *            ray
	 * @return nearest intersection of ray provided with the scene
	 */
	public Intersection getNearestIntersection(final Ray ray) {

		double minimum = Double.MAX_VALUE;
		Intersection minimalIntersection = null;

		for (final IRenderable sceneObject : this.getObjects()) {
			final Intersection intersection = sceneObject.getIntersection(ray);
			if (intersection != null && intersection.getDistance() < minimum && intersection.getDistance() > 0) {
				minimalIntersection = intersection;
				minimum = intersection.getDistance();
			}
		}

		return minimalIntersection;
	}

	/**
	 * @return the objects of the scene
	 */
	public List<IRenderable> getObjects() {
		return this.objects;
	}

	/**
	 * @param ambientLight
	 *            the ambient light color to set
	 */
	public void setAmbientLight(final Color ambientLight) {
		this.ambientLight = ambientLight;
	}

	/**
	 * @param background
	 *            the background to set
	 */
	public void setBackground(final Color background) {
		this.background = background;
	}

	/**
	 * @param camera
	 *            the camera to set
	 */
	public void setCamera(final Camera camera) {
		this.camera = camera;
	}
}
