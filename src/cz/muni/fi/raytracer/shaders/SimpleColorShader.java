package cz.muni.fi.raytracer.shaders;

import cz.muni.fi.raytracer.Color;
import cz.muni.fi.raytracer.Vector2D;
import cz.muni.fi.raytracer.Vector3D;

public class SimpleColorShader implements IShader {

    private Color color;
    private double shininess;
    private double reflectance;
    private double diffuseReflection;
    private double ambientReflection;
    private double specularReflection;

    public SimpleColorShader(Color color, double shininess, double reflectance) {
        this.color = color;
        this.reflectance = reflectance;
        this.shininess = shininess;
        this.diffuseReflection = 1;
        this.specularReflection = 1;
        this.ambientReflection = 1;
    }

    public SimpleColorShader(Color color, double shininess, double reflectance, double diffuseReflectionConstant, double specularReflectionConstant, double ambientReflectionConstant) {
        this.color = color;
        this.shininess = shininess;
        this.reflectance = reflectance;
        this.diffuseReflection = diffuseReflectionConstant;
        this.specularReflection = specularReflectionConstant;
        this.ambientReflection = ambientReflectionConstant;
    }

    public Color getColor(Vector3D position, Vector2D uv) {
        return color;
    }

    public Vector3D getNormal(Vector3D originalNormal, Vector3D position, Vector2D uv) {
        return originalNormal;
    }

    public double getShininess(Vector3D position, Vector2D uv) {
        return shininess;
    }

    public double getReflectance(Vector3D position, Vector2D uv) {
        return reflectance;
    }

    public double getDiffuseReflection(Vector3D position, Vector2D uv) {
        return diffuseReflection;
    }

    public double getAmbientReflection(Vector3D position, Vector2D uv) {
        return ambientReflection;
    }

    public double getSpecularReflection(Vector3D position, Vector2D uv) {
        return specularReflection;
    }
}
