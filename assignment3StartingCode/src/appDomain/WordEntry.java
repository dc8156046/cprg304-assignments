package appDomain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * The {@code WordEntry} class represents a word entry in the system, tracking its occurrences
 * across multiple files and lines. Each {@code WordEntry} includes the word in lowercase form,
 * along with a list of file locations where the word was found.
 * 
 * <p>This class implements the {@code Comparable} interface to allow sorting by the word value.
 * It also provides methods for adding locations, generating reports, and retrieving metadata
 * about the tracked word.
 */
public class WordEntry implements Comparable<WordEntry>, Serializable {
	
    private static final long serialVersionUID = 3L;
    
    // The word being tracked, stored in lowercase form.
    private String word;
    
    // A list of {@code FileLocation} objects representing occurrences of the word.
    List<FileLocation> locations;
    
    /**
     * The {@code FileLocation} class represents a specific file where the word occurred,
     * along with line numbers and variations of the word's capitalization.
     */
    public static class FileLocation implements Serializable {
        
		private static final long serialVersionUID = 3L;
	
		// The name of the file where the word occurred.
		private String fileName;
		
		// A list of line numbers where the word was found in the file.
        private List<Integer> lineNumbers;
        
        // A list of variations of the word's capitalization found in the file.
        private List<String> variations;
        
        /**
         * Constructs a new {@code FileLocation} for the specified file name.
         *
         * @param fileName the name of the file where the word was found.
         */
        public FileLocation(String fileName) {
            this.fileName = fileName;
            this.lineNumbers = new ArrayList<>();
            this.variations = new ArrayList<>();
        }
        
        /**
         * Adds a line number and variation of the word to the file location.
         *
         * @param lineNumber the line number where the word occurred.
         * @param variation  the variation of the word's capitalization.
         */
        public void addLineNumber(int lineNumber, String variation) {
            if (!lineNumbers.contains(lineNumber)) {
                lineNumbers.add(lineNumber);
                lineNumbers.sort(null);
                variations.add(variation);
            }
        }
        
        /**
         * Returns the name of the file.
         *
         * @return the file name.
         */
        public String getFileName() {
            return fileName;
        }

        /**
         * Returns the list of line numbers where the word occurred.
         *
         * @return a list of line numbers.
         */
        public List<Integer> getLineNumbers() {
            return lineNumbers;
        }
        
        /**
         * Returns the list of variations of the word's capitalization.
         *
         * @return a list of variations.
         */
        public List<String> getVariations() {
            return variations;
        }
    }
    
    /**
     * Constructs a new {@code WordEntry} for the specified word.
     *
     * @param word the word to track, converted to lowercase.
     */
    public WordEntry(String word) {
        this.word = word.toLowerCase();
        this.locations = new ArrayList<>();
    }
    
    /**
     * Adds a new location for the word, including the file name, line number, and original capitalization.
     *
     * @param fileName    the name of the file where the word was found.
     * @param lineNumber  the line number where the word occurred.
     * @param originalWord the original capitalization of the word.
     */
    public void addLocation(String fileName, int lineNumber, String originalWord) {
    	FileLocation existingLocation = null;
        for (FileLocation loc : locations) {
            if (loc.fileName.equals(fileName)) {
                existingLocation = loc;
                break;
            }
        }

        if (existingLocation == null) {
            existingLocation = new FileLocation(fileName);
            locations.add(existingLocation);
        }
        existingLocation.addLineNumber(lineNumber, originalWord);
    }
    
    
    /**
     * Compares this {@code WordEntry} with another based on the word value.
     *
     * @param other the other {@code WordEntry} to compare to.
     * @return a negative integer, zero, or a positive integer as this word is less than,
     *         equal to, or greater than the specified word.
     */
    @Override
    public int compareTo(WordEntry other) {
    	return this.word.compareTo(other.word);
    }
    
    /**
     * Returns the total number of occurrences of the word across all locations.
     *
     * @return the total number of occurrences.
     */
    public int getTotalOccurrences() {
        int total = 0;
        for (FileLocation loc : locations) {
            total += loc.lineNumbers.size();
        }
        return total;
    }
    
    /**
     * Returns the word being tracked.
     *
     * @return the word.
     */
    public String getWord() {
        return word;
    }
    
    /**
     * Returns the list of locations where the word was found.
     *
     * @return a list of {@code FileLocation} objects.
     */
    public List<FileLocation> getLocations() {
        return locations;
    }
    
    /**
     * Generates a report for the word in the specified format.
     *
     * @param format the report format ({@code -pf}, {@code -pl}, or {@code -po}).
     * @return the generated report as a string.
     */
    public String generateReportForFormat(String format) {
    	// Find the most "significant" capitalization (first non-lowercase if exists)
        String displayWord = findMostSignificantCapitalization();
        
        StringBuilder report = new StringBuilder("Key : ===" + displayWord + "=== ");

        for (int i = 0; i < locations.size(); i++) {
            FileLocation loc = locations.get(i);
            
            if (format.equals("-pf")) {
                report.append("found in files: ").append(loc.fileName);
                if (i < locations.size()) {
                    report.append(", ");
                }
            } else if (format.equals("-pl")) {
                report.append("found in file: ").append(loc.fileName)
                    .append(" on lines: ");
                for (int j = 0; j < loc.lineNumbers.size(); j++) {
                    report.append(loc.lineNumbers.get(j));
                    if (j < loc.lineNumbers.size()) {
                        report.append(", ");
                    }
                }
                
            } else if (format.equals("-po")) {
            	if( i == 0 ) {
            		report.append("number of entries: ").append(getTotalOccurrences());
            	}
                report.append(" found in file: ").append(loc.fileName)
                    .append(" on lines: ");
                for (int j = 0; j < loc.lineNumbers.size(); j++) {
                    report.append(loc.lineNumbers.get(j));
                    if (j < loc.lineNumbers.size()) {
                        report.append(", ");
                    }
                }
                
            }
        }

        return report.toString();
    }
    
    /**
     * Finds the most significant capitalization of the word from the stored variations.
     *
     * @return the most significant capitalization or the original word.
     */
    private String findMostSignificantCapitalization() {
        // Defensive programming to handle potential null or empty scenarios
        if (locations == null || locations.isEmpty()) {
            return word; // Fallback to the original word
        }

        // Find the first variation with uppercase
        for (FileLocation loc : locations) {
            if (loc.variations != null && !loc.variations.isEmpty()) {
                for (String variation : loc.variations) {
                    if (!variation.equals(variation.toLowerCase())) {
                        return variation;
                    }
                }
            }
        }
        
        // If no uppercase found, return the first variation or fallback to word
        return locations.get(0).variations != null && !locations.get(0).variations.isEmpty() 
               ? locations.get(0).variations.get(0) 
               : word;
    }
}