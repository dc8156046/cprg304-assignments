package shapes;

/**
 * Represents a Shape object
 * @author Dan Chen
 * @version Sep. 27, 2024
 */
public abstract class Shape implements Comparable<Shape>
{
	private double height;
	
	/**
	 * Creates a Shape with the specified height
	 * @param height the height
	 */
	public Shape(double height) 
	{
		super();
		this.height = height;
	}
	
	

	/**
	 * Returns the Shape's height
	 * @return the height
	 */
	public double getHeight() 
	{
		return height;
	}



	/**
	 * Sets the Shape's height
	 * @param height the height to set
	 */
	public void setHeight(double height) 
	{
		this.height = height;
	}
	
	@Override
	public int compareTo(Shape other)
	{
		if(this.height > other.height) return -1; // reverse order
		if(this.height < other.height) return 1;
		return 0;
	}
	
	
	/**
	 * Returns the calculated base area of the Shape
	 * @return base area
	 */
	public abstract double calcBaseArea();
	
	/**
	 * Returns the calculated volume of the Shape
	 * @return volume
	 */
	public abstract double calcVolume();



	/**
     * Returns the name of the shape
     * @return the shape's name
     */
    public String getShapeName() 
    {
    	return this.getClass().getSimpleName();
    }

	
    /**
     * Returns a string representation of the shape based on the compare type
     * @param compareType the type to determine the output (h: height, v: volume, a: base area)
     * @return the string representation based on the compare type
     */
    public String toString(String compareType) {
        switch (compareType.toLowerCase()) {
            case "h":
                return "The polygons." + getShapeName() + " has a Height of: " + getHeight();
            case "v":
                return "The polygons." + getShapeName() + " has a Volume of: " + calcVolume();
            case "a":
                return "The polygons." + getShapeName() + " has a Base area of: " + calcBaseArea();
            default:
                return "Unknown compare type";
        }
    }
}
