package cz.muni.fi.raytracer.materials;

import cz.muni.fi.raytracer.*;

/**
 *
 * @author Andrej Pancik
 */
public interface IMaterial { //TODO IShader

    Color getColor(double u, double v);
    
    Vector3D getBumpMapColor(double u, double v);

    double getShininess(Vector3D position);

    double getReflectance(Vector3D position);

    double getDiffuseReflection(Vector3D position);

    double getAmbientReflection(Vector3D position);

    double getSpecularReflection(Vector3D position);
}
