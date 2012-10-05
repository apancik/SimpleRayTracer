package cz.muni.fi.raytracer.sceneobjects;

import cz.muni.fi.raytracer.Color;
import cz.muni.fi.raytracer.Intersection;
import cz.muni.fi.raytracer.Ray;
import cz.muni.fi.raytracer.Vector3D;
import cz.muni.fi.raytracer.materials.IMaterial;

/**
 * 
 * @author Andrej Pancik
 */
public class Sphere implements IRenderable {

	private final Vector3D center;
	private final double radius;
	private final IMaterial surface;

	public Sphere(final Vector3D center, final double radius, final IMaterial surface) {
		this.center = center;
		this.radius = radius;
		this.surface = surface;
	}

	@Override
	public Color getColor(final Vector3D position) {
		// Glassner, A. (ed) Graphics Gems. Academic Press New York, N.Y. 1990.
		final Vector3D Vp = this.getNormal(position);
		final Vector3D Vn = new Vector3D(0, 1, 0);
		final Vector3D Ve = new Vector3D(1, 0, 0);

		final double phi = Math.acos(-Vn.dot(Vp));
		final double v = phi / Math.PI;

		final double theta = Math.acos(Vp.dot(Ve) / Math.sin(phi)) / (2 * Math.PI);

		double u;
		if (Vn.cross(Ve).dot(Vp) > 0) {
			u = theta;
		} else {
			u = 1 - theta;
		}

		return this.surface.getColor(u, v);
	}

	@Override
	public Intersection getIntersection(final Ray ray) {
		final Vector3D vector = this.center.minus(ray.getOrigin());

		if (vector.length() < this.radius) {
			return null; // ray origin is inside of the sphere
		}

		final double t0 = vector.dot(ray.getDirection());

		final double d2 = vector.dot(vector) - Math.pow(t0, 2);

		final double td2 = Math.pow(this.radius, 2) - d2;

		if (td2 >= 0) {
			final double td = Math.sqrt(td2);

			return new Intersection(ray, this, Math.min(t0 + td, t0 - td));
		} else {
			return null;
		}
	}

	@Override
	public Vector3D getNormal(final Vector3D pos) {
		// HACK this should not be here
		// Glassner, A. (ed) Graphics Gems. Academic Press New York, N.Y. 1990.
		final Vector3D Vp = pos.minus(this.center).normalize();
		final Vector3D Vn = new Vector3D(0, 1, 0);
		final Vector3D Ve = new Vector3D(1, 0, 0);

		final double phi = Math.acos(-Vn.dot(Vp));
		final double v = phi / Math.PI;

		final double theta = Math.acos(Vp.dot(Ve) / Math.sin(phi)) / (2 * Math.PI);

		double u;
		if (Vn.cross(Ve).dot(Vp) > 0) {
			u = theta;
		} else {
			u = 1 - theta;
		}

		return pos.minus(this.center).plus(this.getSurface().getBumpMapColor(u, v)).normalize();
	}

	@Override
	public IMaterial getSurface() {
		return this.surface;
	}
}
