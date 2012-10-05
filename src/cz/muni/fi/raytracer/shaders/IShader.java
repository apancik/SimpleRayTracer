package cz.muni.fi.raytracer.shaders;

import cz.muni.fi.raytracer.*;

public interface IShader {

    public Color getColor(Vector3D position, Vector2D uv);

    public Vector3D getNormal(Vector3D originalNormal, Vector3D position, Vector2D uv);

    public double getShininess(Vector3D position, Vector2D uv);

    public double getReflectance(Vector3D position, Vector2D uv);

    public double getDiffuseReflection(Vector3D position, Vector2D uv);

    public double getAmbientReflection(Vector3D position, Vector2D uv);

    public double getSpecularReflection(Vector3D position, Vector2D uv);
}
