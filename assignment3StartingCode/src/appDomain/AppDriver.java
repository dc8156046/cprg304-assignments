package appDomain;

import java.io.File;
import java.io.PrintStream;
/**
 * The {@code AppDriver} class serves as the entry point for the WordTracker application.
 * It processes command-line arguments to determine the input file, output format, and optional output file,
 * while interacting with a {@code WordTracker} instance to process text and generate reports.
 * 
 * <p>Usage:
 * <pre>
 * java AppDriver -pf/-pl/-po <filename> [-f <output>]
 * </pre>
 * where:
 * <ul>
 *   <li>{@code -pf}: Output report in alphabetic order all words, along with the corresponding list of files 
in which the words occur.</li>
 *   <li>{@code -pl}: Output report in alphabetic order all words, along with the corresponding list of files 
and line numbers in which the word occur.</li>
 *   <li>{@code -po}: Output report  in alphabetic order all words, along with the corresponding list of files, 
line numbers in which the word occur and the frequency of occurrence of the 
words.</li>
 *   <li>{@code <filename>}: The file to be processed.</li>
 *   <li>{@code -f <output>}: Optional flag to specify an output file for the report.</li>
 * </ul>
 * 
 * <p>The application supports loading and saving repository data to persist tracked words across runs.
 */
public class AppDriver {
	
	// The name of the repository file used for saving and loading {@code WordTracker} data.
	private static final String REPOSITORY_FILE = "repository.ser";
	
	/**
     * The main method that processes command-line arguments, initializes the {@code WordTracker},
     * and generates reports based on the specified options.
     *
     * @param args the command-line arguments specifying input, output format, and optional output file.
     */
	public static void main(String[] args) {
		
		// Ensure the correct number of arguments is provided
		if (args.length < 2) {
            System.out.println("Usage: java AppDriver -pf/-pl/-po <filename> [-f <output>]");
            return;
        }
		
		WordTracker wordTracker;

        // Check if the repository file exists, and load it if available
        File repositoryFile = new File(REPOSITORY_FILE);
        if (repositoryFile.exists()) {
            wordTracker = WordTracker.loadRepository(REPOSITORY_FILE);
        } else {
            wordTracker = new WordTracker();
        }
		
		try {
			// Process the specified file
			wordTracker.processFile(args[0]);
			
			// Save the repository to ensure persistence
			wordTracker.saveRepository(REPOSITORY_FILE);
			
			// Determine the output format (e.g., -pf, -pl, -po)
			String outputFormat = args[1];
			PrintStream output = System.out;
			
			// Check if an output file is specified
			if (args.length > 3 && args[2].equals("-f")) {
			    output = new PrintStream(args[3]);
			    System.out.println("Exporting report to: " + args[3]);
			}
			
			System.out.println("Writing " + outputFormat +" format\n");
			
			// Generate and write the report in the specified format
			wordTracker.generateReport(outputFormat, output);
			
			// Close the output stream if it is not the standard output
			if (output != System.out) {
			    output.close();
			} else {
				System.out.println("\nNot exporting file");
			}

        } catch (Exception e) {
            e.printStackTrace();
        }

	}

}
