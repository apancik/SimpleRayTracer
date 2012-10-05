package cz.muni.fi.raytracer.materials;

import cz.muni.fi.raytracer.*;

/**
 *
 * @author Andrej Pancik
 */
public class PerlinNoise implements IMaterial {

    private double shininess;
    private double reflectance;
    private double diffuseReflection;
    private double ambientReflection;
    private double specularReflection;
    private final double persistence;
    private final int octaves;

    public PerlinNoise(double persistence, int octaves, double shininess, double reflectance) {
        this.persistence = persistence;
        this.octaves = octaves;
        this.reflectance = reflectance;
        this.shininess = shininess;
        this.diffuseReflection = 1;
        this.specularReflection = 1;
        this.ambientReflection = 1;
    }

    public PerlinNoise(double persistence, int octaves, double shininess, double reflectance, double diffuseReflectionConstant, double specularReflectionConstant, double ambientReflectionConstant) {
        this.persistence = persistence;
        this.octaves = octaves;
        this.shininess = shininess;
        this.reflectance = reflectance;
        this.diffuseReflection = diffuseReflectionConstant;
        this.specularReflection = specularReflectionConstant;
        this.ambientReflection = ambientReflectionConstant;
    }

    //http://freespace.virgin.net/hugo.elias/models/m_perlin.htm
    double noise2D(double x, double y) {
        int n = (int) x + (int) y * 57;
        n = (n << 13) ^ n;
        int nn = (n * (n * n * 60493 + 19990303) + 1376312589) & 0x7fffffff;

        return 1.0 - ((double) nn / 1073741824.0);
    }

    double smoothNoise2D(double x, double y) {
        double corners = (noise2D(x - 1, y - 1) + noise2D(x + 1, y - 1) + noise2D(x - 1, y + 1) + noise2D(x + 1, y + 1)) / 16;
        double sides = (noise2D(x - 1, y) + noise2D(x + 1, y) + noise2D(x, y - 1) + noise2D(x, y + 1)) / 8;
        double center = noise2D(x, y) / 4;

        return corners + sides + center;
    }

    double cosineInterpolate(double a, double b, double x) {
        double ft = x * Math.PI;
        double f = (1 - Math.cos(ft)) * .5;

        return a * (1 - f) + b * f;
    }
    //TODO dokodit cubicku interpolaciu a linerarnu a umoznit cez InterpolationType.Cubic a pod v constructore vyberat

    double interpolatedNoise2D(double x, double y) {
        //popremenuvavat premenne 0 kommenty ako v http://www.dreamincode.net/forums/showtopic66480.htm

        double integerX = Math.floor(x);
        double fractional_X = x - integerX;

        double integer_Y = Math.floor(y);
        double fractional_Y = y - integer_Y;

        double v1 = smoothNoise2D(integerX, integer_Y);
        double v2 = smoothNoise2D(integerX + 1, integer_Y);
        double v3 = smoothNoise2D(integerX, integer_Y + 1);
        double v4 = smoothNoise2D(integerX + 1, integer_Y + 1);

        double i1 = cosineInterpolate(v1, v2, fractional_X);
        double i2 = cosineInterpolate(v3, v4, fractional_X);

        return cosineInterpolate(i1, i2, fractional_Y);

    }

    double perlinNoise2D(double x, double y) {
        double sum = 0;

        for (int i = 0; i < octaves; i++) {
            double frequency = Math.pow(2, i);
            double amplitude = Math.pow(persistence, i);

            sum += interpolatedNoise2D(x * frequency, y * frequency) * amplitude;
        }

        return sum;
    }

    /**
     * @return the color
     */
    public Color getColor(double u, double v) {
        double x = perlinNoise2D(u, v) + 1;
        double z = Math.cos(u + x)+1;
        return new Color(x, x, x);
    }

    /**
     * @return the shininess
     */
    public double getShininess(Vector3D position) {
        return shininess;
    }

    /**
     * @return the reflectance
     */
    public double getReflectance(Vector3D position) {
        return reflectance;
    }

    /**
     * @return the diffuseReflectionConstant
     */
    public double getDiffuseReflection(Vector3D position) {
        return diffuseReflection;
    }

    /**
     * @return the ambientReflectionConstant
     */
    public double getAmbientReflection(Vector3D position) {
        return ambientReflection;
    }

    /**
     * @return the specularReflectionConstant
     */
    public double getSpecularReflection(Vector3D position) {
        return specularReflection;
    }

    public Vector3D getBumpMapColor(double u, double v) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
