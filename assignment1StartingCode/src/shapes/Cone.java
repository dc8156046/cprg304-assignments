package shapes;

/**
 *  Represents a Cone
 *  @author Dan Chen
 *  @version Sep. 27, 2024
 */
public class Cone extends Shape
{
	private double radius;

	/**
	 * Creates a Cone with the specified height and radius
	 * @param height the height
 	 * @param radius the radius
	 */
	public Cone(double height, double radius) 
	{
		super(height);
		this.radius = radius;
	}

	/**
	 * Return the Cone's radius
	 * @return the radius
	 */
	public double getRadius() 
	{
		return radius;
	}

	/**
	 * Sets the Cone's radius
	 * @param radius the radius to set
	 */
	public void setRadius(double radius) 
	{
		this.radius = radius;
	}

	@Override
	public double calcBaseArea() 
	{
		return Math.PI * radius * radius;
	}

	@Override
	public double calcVolume() 
	{
		return calcBaseArea() * getHeight() / 3;
	}

	

    @Override
    public String toString() {
        return super.toString(); // This will now format as needed
    }
	
	
}
