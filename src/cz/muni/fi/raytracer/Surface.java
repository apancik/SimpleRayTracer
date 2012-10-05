package cz.muni.fi.raytracer;

public class Surface {

	private final Color color;
	private final double shininess;
	private final double reflectance;
	private final double diffuseReflection;
	private final double ambientReflection;
	private final double specularReflection;

	public Surface(final Color color, final double shininess, final double reflectance) {
		this.color = color;
		this.reflectance = reflectance;
		this.shininess = shininess;
		this.diffuseReflection = 1;
		this.specularReflection = 1;
		this.ambientReflection = 1;
	}

	public Surface(final Color color, final double shininess, final double reflectance, final double diffuseReflectionConstant, final double specularReflectionConstant, final double ambientReflectionConstant) {
		this.color = color;
		this.shininess = shininess;
		this.reflectance = reflectance;
		this.diffuseReflection = diffuseReflectionConstant;
		this.specularReflection = specularReflectionConstant;
		this.ambientReflection = ambientReflectionConstant;
	}

	/**
	 * @return the ambientReflectionConstant
	 */
	public double getAmbientReflection() {
		return this.ambientReflection;
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return this.color;
	}

	/**
	 * @return the diffuseReflectionConstant
	 */
	public double getDiffuseReflection() {
		return this.diffuseReflection;
	}

	/**
	 * @return the reflectance
	 */
	public double getReflectance() {
		return this.reflectance;
	}

	/**
	 * @return the shininess
	 */
	public double getShininess() {
		return this.shininess;
	}

	/**
	 * @return the specularReflectionConstant
	 */
	public double getSpecularReflection() {
		return this.specularReflection;
	}
}
