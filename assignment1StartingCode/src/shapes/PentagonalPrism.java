package shapes;

/**
 * Represents a OctagonalPrism
 * @author Dan Chen
 * @version Sep.27, 2024
 */
public class PentagonalPrism extends Prism
{

	/**
	 * Creates a PentagonalPrism with specified height and side
	 * @param height the height
	 * @param side the side
	 */
	public PentagonalPrism(double height, double side) 
	{
		super(height, side);
	}

	@Override
	public double calcBaseArea() 
	{
		return 5 * getSide() * getSide() * Math.tan(Math.toRadians(54)) / 4;
	}


    @Override
    public String toString() {
        return super.toString(); // This will now format as needed
    }
	
	
	
}
