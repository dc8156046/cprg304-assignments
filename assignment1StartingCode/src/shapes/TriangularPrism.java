package shapes;

/**
 * Represents a OctagonalPrism
 * @author Dan Chen
 * @version Sep.27, 2024
 */
public class TriangularPrism extends Prism
{

	/**
	 * Creates a TriangularPrism with specified height and side
	 * @param height
	 * @param side
	 */
	public TriangularPrism(double height, double side) {
		super(height, side);
	}

	@Override
	public double calcBaseArea() {
		return getSide() * getSide() * Math.sqrt(3) / 4;
	}

	
    @Override
    public String toString() {
        return super.toString(); // This will now format as needed
    }
	
	
}
