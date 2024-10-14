package shapes;

/**
 * Represents a Cylinder
 * @author Dan Chen
 * @version Sep. 27, 2024
 */
public class Cylinder extends Shape
{
	private double radius;

	/**
	 * Creates a Cylinder with the specified height and radius
	 * @param height
	 * @param radius
	 */
	public Cylinder(double height, double radius) 
	{
		super(height);
		this.radius = radius;
	}

	/**
	 * Return the Cylinder's radius
	 * @return the radius
	 */
	public double getRadius() 
	{
		return radius;
	}

	/**
	 * Sets the Cylinder's radius
	 * @param radius the radius to set
	 */
	public void setRadius(double radius) {
		this.radius = radius;
	}

	@Override
	public double calcBaseArea() 
	{
		return Math.PI * getRadius() * getRadius();
	}

	@Override
	public double calcVolume() 
	{
		return calcBaseArea() * getHeight();
	}

	
    @Override
    public String toString() {
        return super.toString(); // This will now format as needed
    }

	
}
