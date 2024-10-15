package manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Scanner;

import shapes.Cone;
import shapes.Cylinder;
import shapes.OctagonalPrism;
import shapes.PentagonalPrism;
import shapes.Pyramid;
import shapes.Shape;
import shapes.SquarePrism;
import shapes.TriangularPrism;
import utilities.Sort;
import utilities.VolumeComparator;
import utilities.BaseAreaComparator;

public class SortManager 
{

	public SortManager(String[] args) 
	{
		String fileName = null;
        String compareType = null;
        String sortAlgorithm = null;

        for (String arg : args) 
        {
            if (arg.toLowerCase().startsWith("-f")) 
            {
                fileName = arg.substring(2); // Extract the file name/path after '-f'
            } 
            else if (arg.toLowerCase().startsWith("-t")) 
            {
            	compareType = arg.substring(2); // Extract the attribute (v, h, a)
            } 
            else if (arg.toLowerCase().startsWith("-s")) 
            {
                sortAlgorithm = arg.substring(2); // Extract the sorting algorithm (b, s, i, m, q, z)
            }
        }

        // Validate inputs
        if (fileName == null || compareType == null || sortAlgorithm == null) 
        {
            System.out.println("Error: Missing required arguments.");
            printUsage();
            return;
        }

        // Validate compareType argument
        if (!compareType.equalsIgnoreCase("v") && 
            !compareType.equalsIgnoreCase("h") && 
            !compareType.equalsIgnoreCase("a")) 
        {
            System.out.println("Error: Invalid sort type. Use 'v' for volume, 'h' for height, or 'a' for base area.");
            printUsage();
            return;
        }

        // Validate sortAlgorithm argument
        if (!sortAlgorithm.equalsIgnoreCase("b") && 
            !sortAlgorithm.equalsIgnoreCase("s") && 
            !sortAlgorithm.equalsIgnoreCase("i") && 
            !sortAlgorithm.equalsIgnoreCase("m") && 
            !sortAlgorithm.equalsIgnoreCase("q") && 
            !sortAlgorithm.equalsIgnoreCase("z")) 
        {
            System.out.println("Error: Invalid sort algorithm. Use 'b' for bubble, 's' for selection, 'i' for insertion, 'm' for merge, 'q' for quick, or 'z' for custom.");
            printUsage();
            return;
        }
        
        // Test purpose
        System.out.println("File: " + fileName);
        System.out.println("Sort by: " + compareType);
        System.out.println("Sort algorithm: " + sortAlgorithm);

        
         Shape[] shapes = readShapesFromFile(fileName);
//       for (Shape shape : shapes) 
//       {
//			System.out.println(shape);
//       }
         sortAndBenchmark(shapes, sortAlgorithm, compareType);
         
    }

    // Print usage instructions to the user
    private static void printUsage() 
    {
        System.out.println("Usage:");
        System.out.println("java -jar Sort.jar -f<file_name> -t[v/h/a] -s[b/s/i/m/q/z]");
        System.out.println("Options:");
        System.out.println("-f or -F: specify the file name with path (e.g., -f\"res\\shapes1.txt\")");
        System.out.println("-t or -T: specify 'v' for volume, 'h' for height, or 'a' for base area (e.g., -tH)");
        System.out.println("-s or -S: specify sorting algorithm 'b' (bubble), 's' (selection), 'i' (insertion), 'm' (merge), 'q' (quick), 'z' (custom) (e.g., -sB)");
    }
    
    /**
     * Reads shape data from a specified file and creates an array of Shape objects.
     * Each line in the file is expected to contain information about a shape in the format:
     * "<shapeType> <height> <radiusOrSide>", where:
     * - <shapeType> is the type of the shape (e.g., "Cone", "Cylinder", "Pyramid").
     * - <height> is the height of the shape as a double.
     * - <radiusOrSide> is either the radius (for circular shapes) or side length (for prismatic shapes).
     * 
     * @param fileName  the name of the file containing the shape data.
     *                  It should include the full path if the file is not in the current directory.
     * @return          an array of Shape objects created from the data in the file.
     *                  If the file is empty or contains invalid lines, the returned array may be empty or smaller.
     * @throws FileNotFoundException if the file cannot be found or read. NumberFormatException if invalid number format in file
     */
    private static Shape[] readShapesFromFile(String fileName) 
    {
        File file = new File(fileName);
        Shape[] shapes = null;

        try (Scanner scanner = new Scanner(file)) 
        {
            if (scanner.hasNextInt()) 
            {
                int numShapes = scanner.nextInt(); // Read the number of shapes
                shapes = new Shape[numShapes]; // Create an array with the specified size
                scanner.nextLine(); // Move to the next line after reading the number

                for (int i = 0; i < numShapes; i++) 
                {
                    if (scanner.hasNextLine()) 
                    {
                        String line = scanner.nextLine();
                        shapes[i] = parseShape(line);
                    }
                }
            }
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("Error: File not found: " + fileName);
        } 
        catch (NumberFormatException e) 
        {
            System.out.println("Error: Invalid number format in file.");
        }

        return shapes;
    }

    /**
     * Parses a line of text to create a Shape object.
     * The input line is expected to contain the shape type, height, and dimension (radius or side)
     * separated by spaces. This method extracts these values and creates the corresponding Shape.
     *
     * @param line  a string containing the shape information, formatted as:
     *              "<shapeType> <height> <radiusOrSide>", where:
     *              - <shapeType> is the type of the shape (e.g., "Cone", "Cylinder", "Pyramid").
     *              - <height> is the height of the shape as a double.
     *              - <radiusOrSide> is either the radius (for circular shapes) or side length (for prismatic shapes).
     * @return      a new Shape object created from the parsed information.
     *              Returns null if the input line is invalid or if the shape type is unrecognized.
     * 
     */
    private static Shape parseShape(String line) 
    {
        String[] parts = line.split(" ");
        if (parts.length < 3) 
        {
            System.out.println("Error: Invalid shape data: " + line);
            return null;
        }

        String shapeType = parts[0];
        double height = Double.parseDouble(parts[1]);
        double radiusOrSide = Double.parseDouble(parts[2]);

        return createShape(shapeType, height, radiusOrSide);
    }

    /**
     * Creates a Shape object based on the specified shape type, height, and dimension.
     * The dimension can represent either the radius (for circular shapes like Cone and Cylinder)
     * or the side length (for shapes like Pyramid etc).
     *
     * @param shapeType     the type of shape to create, such as "Cone", "Cylinder", "Pyramid", 
     *                      "OctagonalPrism", "PentagonalPrism", or "TriangularPrism".
     *                      The string is case-insensitive.
     * @param height        the height of the shape.
     * @param radiusOrSide  the radius or side length of the shape, depending on the type:
     *                      - For "Cone" or "Cylinder", this value represents the radius.
     *                      - For "Pyramid", "OctagonalPrism", "PentagonalPrism", or "TriangularPrism", 
     *                        this value represents the side length.
     * @return              a new Shape object of the specified type with the given dimensions.
     *                      Returns null if the shape type is not recognized.
     */
    private static Shape createShape(String shapeType, double height, double radiusOrSide) 
    {
        switch (shapeType.toLowerCase()) 
        {
	        case "cylinder":
	            return new Cylinder(height, radiusOrSide);
	        case "cone":
	            return new Cone(height, radiusOrSide);
            case "pyramid":
                return new Pyramid(height, radiusOrSide);
            case "triangularprism":
                return new TriangularPrism(height, radiusOrSide);
            case "squareprism":
            	return new SquarePrism(height, radiusOrSide);
            case "pentagonalprism":
                return new PentagonalPrism(height, radiusOrSide);
            case "octagonalprism":
                return new OctagonalPrism(height, radiusOrSide);
            default:
                System.out.println("Error: Unknown shape type: " + shapeType);
                return null;
        }
    }
    
    /**
     * Sorts an array of Shape objects using the specified sorting algorithm and comparison type,
     * then measures and displays the time taken for the sorting process.
     * The sorted shapes are printed according to their values based on the comparison type.
     *
     * @param shapes        an array of Shape objects to be sorted.
     * @param sortAlgorithm the sorting algorithm to use, represented by:
     *                      "b" for bubble sort, "s" for selection sort, "i" for insertion sort,
     *                      "m" for merge sort, "q" for quick sort, and "z" for a custom sorting algorithm.
     * @param compareType   the type of comparison to sort by:
     *                      "h" for height, "v" for volume, and "a" for base area.
     *                      Determines how the shapes are compared and the output format after sorting.
     */
    private static void sortAndBenchmark(Shape[] shapes, String sortAlgorithm, String compareType) 
    {
        Shape[] shapesCopy = shapes.clone();
    	
        long startTime = System.nanoTime();

        // Determine the comparator based on the compare type
        Comparator<Shape> comparator;
        
        if ("h".equals(compareType.toLowerCase())) 
        {
            // Use the compareTo method for height sorting
            // Sort based on compareTo method (not directly using a comparator)
            switch (sortAlgorithm.toLowerCase()) 
            {
                case "b":
                    Sort.bubbleSort(shapesCopy);
                    break;
                case "s":
                    Sort.selectionSort(shapesCopy); 
                    break;
                case "i":
                    Sort.insertionSort(shapesCopy); 
                    break;
                case "m":
                    Sort.mergeSort(shapesCopy); 
                    break;
                case "q":
                    Sort.quickSort(shapesCopy); 
                    break;
                case "z":
                    Sort.heapSort(shapesCopy); 
                    break;
                default:
                    System.out.println("Invalid sort method. Use 'b', 's', 'i', 'm', 'q', or 'z'.");
                    return;
            }
        } 
        else 
        {
            // For base area or volume sorting, determine which comparator to use
            switch (compareType.toLowerCase()) 
            {
                case "a":
                    comparator = new BaseAreaComparator(); 
                    break;
                case "v":
                    comparator = new VolumeComparator();
                    break;
                default:
                    System.out.println("Invalid compare type. Use 'v' for volume, 'h' for height, or 'a' for base area.");
                    return;
            }
           
            // Perform the selected sort using the comparator
            switch (sortAlgorithm.toLowerCase()) 
            {
                case "b":
                    Sort.bubbleSort(shapesCopy, comparator);
                    break;
                case "s":
                    Sort.selectionSort(shapesCopy, comparator);
                    break;
                case "i":
                    Sort.insertionSort(shapesCopy, comparator);
                    break;
                case "m":
                    Sort.mergeSort(shapesCopy, comparator);
                    break;
                case "q":
                    Sort.quickSort(shapesCopy, comparator);
                    break;
                case "z":
                    Sort.heapSort(shapesCopy, comparator); 
                    break;
                default:
                    System.out.println("Invalid sort method. Use 'b', 's', 'i', 'm', 'q', or 'z'.");
                    return;
            }
            
        }

        long endTime = System.nanoTime();
        long durationMillis = (endTime - startTime) / 1_000_000; // Convert to milliseconds
        
        // Output the sorted data and benchmark results
        printSortedResults(shapesCopy, compareType);
        System.out.println(sortAlgorithm + " run time was: " + durationMillis + " milliseconds.");
    }
    
    /**
     * Prints the sorted results of an array of Shape objects based on the specified comparison type.
     * The method prints all elements if the array length is less than 1000; otherwise, it prints 
     * the first element, every thousandth element, the second last element (if applicable), and the last element.
     *
     * @param shapes       an array of Shape objects that have been sorted.
     * @param compareType  the type of comparison used to sort the shapes:
     *                     "h" for height, "v" for volume, and "a" for base area.
     *                     The output format of each shape's details will change based on this type.
     */
    private static void printSortedResults(Shape[] shapes, String compareType) 
    {
    	int length = shapes.length;
        StringBuilder output = new StringBuilder();

        if (length <= 1000) 
        {
            // Print all elements when the array has less than 1000 shapes
            for (int i = 0; i < length; i++) 
            {
                output.append(shapes[i].toString(compareType)).append("\n");
            }
        } 
        else 
        {
            output.append("First element is: ").append(shapes[0].toString(compareType)).append("\n");
            for (int i = 1000; i < length; i += 1000) 
            {
                if (i < length) 
                {
                    output.append(i).append("-th element is: ").append(shapes[i].toString(compareType)).append("\n");
                }
            }
            // Print the second last element if there are at least 2 elements
            if (length > 1) 
            {
                output.append("Second last element is: ").append(shapes[length - 2].toString(compareType)).append("\n");
            }
            output.append("Last element is: ").append(shapes[length - 1].toString(compareType)).append("\n");
        }

        // Print all accumulated output at once
        System.out.print(output.toString());
    }

}
