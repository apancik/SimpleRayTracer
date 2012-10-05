package cz.muni.fi.raytracer.sceneobjects;

import cz.muni.fi.raytracer.Color;
import cz.muni.fi.raytracer.Intersection;
import cz.muni.fi.raytracer.Ray;
import cz.muni.fi.raytracer.Vector3D;
import cz.muni.fi.raytracer.materials.IMaterial;

public interface IRenderable {

	public Color getColor(Vector3D position);

	public Intersection getIntersection(Ray ray);

	public Vector3D getNormal(Vector3D position); // TODO Add normal map

	public IMaterial getSurface();
	
	/*public double getShininess(Vector3D position);

    public double getReflectance(Vector3D position);

    public double getDiffuseReflection(Vector3D position);

    public double getAmbientReflection(Vector3D position);

    public double getSpecularReflection(Vector3D position);

    public boolean bounding(Ray ray);*/

}
