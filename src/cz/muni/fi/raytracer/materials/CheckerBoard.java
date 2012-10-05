package cz.muni.fi.raytracer.materials;

import cz.muni.fi.raytracer.Color;
import cz.muni.fi.raytracer.Vector3D;

/**
 * 
 * @author Andrej Pancik
 */
public class CheckerBoard implements IMaterial {

	private final double shininess;
	private final double reflectance;
	private final double diffuseReflection;
	private final double ambientReflection;
	private final double specularReflection;
	private final int checkerSize;

	public CheckerBoard(final int checkerSize, final double shininess, final double reflectance) {
		this.checkerSize = checkerSize;
		this.reflectance = reflectance;
		this.shininess = shininess;
		this.diffuseReflection = 1;
		this.specularReflection = 1;
		this.ambientReflection = 1;
	}

	public CheckerBoard(final int checkerSize, final double shininess, final double reflectance, final double diffuseReflectionConstant, final double specularReflectionConstant, final double ambientReflectionConstant) {
		this.checkerSize = checkerSize;
		this.shininess = shininess;
		this.reflectance = reflectance;
		this.diffuseReflection = diffuseReflectionConstant;
		this.specularReflection = specularReflectionConstant;
		this.ambientReflection = ambientReflectionConstant;
	}

	/**
	 * @return the ambientReflectionConstant
	 */
	@Override
	public double getAmbientReflection(final Vector3D position) {
		return this.ambientReflection;
	}

	@Override
	public Vector3D getBumpMapColor(final double u, final double v) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	/**
	 * @return the color
	 */
	@Override
	public Color getColor(final double u, final double v) {

		if ((Math.floor(u * 1000.0 / this.checkerSize) + Math.floor(v * 1000.0 / this.checkerSize)) % 2 == 0) {
			// HACK not very nice
			return new Color(1, 1, 1);
		} else {
			return new Color(0, 0, 0);
		}
	}

	/**
	 * @return the diffuseReflectionConstant
	 */
	@Override
	public double getDiffuseReflection(final Vector3D position) {
		return this.diffuseReflection;
	}

	/**
	 * @return the reflectance
	 */
	@Override
	public double getReflectance(final Vector3D position) {
		return this.reflectance;
	}

	/**
	 * @return the shininess
	 */
	@Override
	public double getShininess(final Vector3D position) {
		return this.shininess;
	}

	/**
	 * @return the specularReflectionConstant
	 */
	@Override
	public double getSpecularReflection(final Vector3D position) {
		return this.specularReflection;
	}
}
