/**
 * 
 */
package shapes;

/**
 * Represents a Prism
 * @author Dan Chen
 * @version Sep. 27, 2024
 */
public abstract class Prism extends Shape
{
	private double side;

	/**
	 * Creates a Prism with specified height and side
	 * @param height the height
	 * @param side the side
	 */
	public Prism(double height, double side) 
	{
		super(height);
		this.side = side;
	}
	
	

	/**
	 * Returns the Prism's side
	 * @return the side
	 */
	public double getSide() 
	{
		return side;
	}



	/**
	 * Sets the Prism's side
	 * @param side the side to set
	 */
	public void setSide(double side) 
	{
		this.side = side;
	}



	@Override
	public double calcVolume() 
	{
		return calcBaseArea() * getHeight();
	}
	
	

}
