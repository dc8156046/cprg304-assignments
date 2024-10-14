package shapes;

/**
 * Represents a OctagonalPrism
 * @author Dan Chen
 * @version Sep.27, 2024
 */
public class OctagonalPrism extends Prism
{

	/**
	 * Creates a OctagonalPrism with specified height and side
	 * @param height the height
	 * @param side the side
	 */
	public OctagonalPrism(double height, double side) 
	{
		super(height, side);
	}

	@Override
	public double calcBaseArea() 
	{
		return 2 * (1 + Math.sqrt(2)) * getSide() * getSide();
	}

    @Override
    public String toString() {
        return super.toString(); // This will now format as needed
    }
	
	
}
