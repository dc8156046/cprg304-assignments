package shapes;

/**
 * Represents a SquarePrism
 */
public class SquarePrism extends Prism
{

	/**
	 * Creates a SquarePrism with specified height and side
	 * @param height the height
	 * @param side the side
	 */
	public SquarePrism(double height, double side) 
	{
		super(height, side);
	}

	@Override
	public double calcBaseArea() 
	{
		return getSide() * getSide();
	}


    @Override
    public String toString() {
        return super.toString(); // This will now format as needed
    }
	
	
}
