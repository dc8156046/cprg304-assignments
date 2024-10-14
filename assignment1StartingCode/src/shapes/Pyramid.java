package shapes;

/**
 * Represents a Pyramid
 * @author Dan Chen
 * @version Sep.27, 2024
 */
public class Pyramid extends Shape
{
	private double side;

	/**
	 * Creates a Pyramid with the specified height and side
	 * @param height the height
	 * @param side the side
	 */
	public Pyramid(double height, double side) 
	{
		super(height);
		this.side = side;
	}

	/**
	 * Returns the Pyramid's side
	 * @return the side
	 */
	public double getSide() 
	{
		return side;
	}

	/**
	 * Sets the Pyramid's side
	 * @param side the side to set
	 */
	public void setSide(double side) 
	{
		this.side = side;
	}

	@Override
	public double calcBaseArea() 
	{
		return side * side;
	}

	@Override
	public double calcVolume() 
	{
		return calcBaseArea() * getHeight() / 3;
	}


    @Override
    public String toString() {
        return super.toString(); 
    }
	
	
	
}
