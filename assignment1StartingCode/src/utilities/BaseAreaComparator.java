package utilities;

import java.util.Comparator;

import shapes.Shape;

/**
 * A comparator for comparing the base areas of two Shape objects.
 *
 * <p>This comparator orders shapes in descending order based on their calculated base area.
 * If the base area of the first shape is greater than the second, it returns a negative integer,
 * indicating that the first shape should come before the second in the sorted order.
 *
 * @see Shape
 * @author Dan Chen
 * @version Sep. 27, 2024
 */
public class BaseAreaComparator implements Comparator<Shape> 
{

	@Override
	public int compare(Shape shape1, Shape shape2) 
	{
		if (shape1.calcBaseArea() > shape2.calcBaseArea()) return -1; // descending order
		if (shape1.calcBaseArea() < shape2.calcBaseArea()) return 1;
		return 0;
	}

}
