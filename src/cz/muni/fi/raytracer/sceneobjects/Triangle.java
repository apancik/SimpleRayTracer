package cz.muni.fi.raytracer.sceneobjects;

import cz.muni.fi.raytracer.Color;
import cz.muni.fi.raytracer.Intersection;
import cz.muni.fi.raytracer.Ray;
import cz.muni.fi.raytracer.Vector3D;
import cz.muni.fi.raytracer.materials.IMaterial;

public class Triangle implements IRenderable {

	private final Vector3D firstPoint;
	private final Vector3D edge1;
	private final Vector3D edge2;
	private final Vector3D normalVector;
	private final double epsilon = 10e-6;
	private final double planeOffset;
	private final IMaterial surface;

	public Triangle(final Vector3D pos1, final Vector3D pos2, final Vector3D pos3, final IMaterial surface) {
		this.firstPoint = pos1;
		this.surface = surface;

		this.edge1 = pos2.minus(pos1);
		this.edge2 = pos3.minus(pos1);

		this.normalVector = this.edge1.cross(this.edge2).normalize();
		this.planeOffset = -pos1.dot(this.normalVector);
	}

	@Override
	public Color getColor(final Vector3D position) {
		return this.surface.getColor(0, 0);
	}

	@Override
	public Intersection getIntersection(final Ray ray) {
		// Fast, Minimum Storage Ray Triangle Intersection, Moller-Trumbore Method

		final Vector3D p = ray.getDirection().cross(this.edge2);

		final double det = this.edge1.dot(p);

		if (Math.abs(det) < this.epsilon) {
			return null;
		}

		final double inv_det = 1.0 / det;

		final Vector3D t = ray.getOrigin().minus(this.firstPoint);

		final double u = t.dot(p) * inv_det;
		if (u < 0.0 || 1.0 < u) {
			return null;
		}

		final Vector3D q = t.cross(this.edge1);

		final double v = ray.getDirection().dot(q) * inv_det;
		if (v < 0.0 || 1.0 < u + v) {
			return null;
		}

		return new Intersection(ray, this, (ray.getOrigin().dot(this.normalVector) + this.planeOffset) / -this.normalVector.dot(ray.getDirection()));
	}

	@Override
	public Vector3D getNormal(final Vector3D position) {
		return this.normalVector;
	}

	@Override
	public IMaterial getSurface() {
		return this.surface;
	}
}
