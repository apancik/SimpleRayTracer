package cz.muni.fi.raytracer;

public class Vector2D {
	private static final double EPS = 1.0E-4;

	private final double x;
	private final double y;

	public Vector2D(final double x, final double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @return the X
	 */
	public double getX() {
		return this.x;
	}

	/**
	 * @return the Y
	 */
	public double getY() {
		return this.y;
	}

	public double length() {
		return Math.sqrt(this.times(this));
	}

	public Vector2D minus(final Vector2D subtrahend) {
		return new Vector2D(this.getX() - subtrahend.getX(), this.getY() - subtrahend.getY());
	}

	public Vector2D plus(final Vector2D addend) {
		return new Vector2D(this.getX() + addend.getX(), this.getY() + addend.getY());
	}

	public Vector2D times(final double multiplier) {
		return new Vector2D(this.getX() * multiplier, this.getY() * multiplier);
	}

	public double times(final Vector2D vector) {
		return this.getX() * vector.getX() + this.getY() * vector.getY();
	}

	@Override
	public String toString() {
		return "(x: " + this.x + " y: " + this.y + ")";
	}

}
