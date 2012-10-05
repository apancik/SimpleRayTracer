package cz.muni.fi.raytracer;

import cz.muni.fi.raytracer.sceneobjects.IRenderable;

/**
 * Light class containing information about light position, diffuse constant and specular constant
 * 
 * @author Andrej Pancik
 */
public class Light {

	private IRenderable shadowCache; // Optimalisation

	private final Vector3D position;

	private final Color diffuse;

	private final Color specular;

	/**
	 * Basic light constructor
	 * 
	 * @param position
	 *            light position
	 * @param diffuse
	 *            diffuse constant
	 * @param specular
	 *            specular constant
	 */
	public Light(final Vector3D position, final Color diffuse, final Color specular) {
		this.position = position;
		this.diffuse = diffuse;
		this.specular = specular;
	}

	/**
	 * @return the diffuse constant
	 */
	public Color getDiffuse() {
		return this.diffuse;
	}

	/**
	 * @return the position
	 */
	public Vector3D getPosition() {
		return this.position;
	}

	public IRenderable getShadowCache() {
		return this.shadowCache;
	}

	/**
	 * @return the specular constant
	 */
	public Color getSpecular() {
		return this.specular;
	}

	public void setShadowCache(final IRenderable shadowCache) {
		this.shadowCache = shadowCache;
	}
}
