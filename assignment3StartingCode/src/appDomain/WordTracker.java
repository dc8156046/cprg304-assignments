package appDomain;

import implementations.BSTree;
import implementations.BSTreeNode;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import utilities.Iterator;
import java.util.List;

/**
 * The WordTracker class provides a way to track word occurrences across multiple files.
 * It uses a binary search tree (BST) to store word entries, allowing efficient word lookup
 * and management. Each word entry tracks its occurrences in specific files and lines.
 * The class supports reading files, generating reports, and persisting/reloading the tracker state.
 */
public class WordTracker implements Serializable {
    private static final long serialVersionUID = 3L;
    private BSTree<WordEntry> wordTree;
    
    /**
     * Constructs a new WordTracker with an empty binary search tree.
     */
    public WordTracker() {
        wordTree = new BSTree<>();
    }
    
    /**
     * Processes a file to extract words and record their occurrences.
     *
     * @param fileName the path of the file to process
     * @throws IOException if an error occurs while reading the file
     */
    public void processFile(String fileName) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(fileName));
        
        String shortFileName = new File(fileName).getName(); // Extract just the filename

        
        for (int lineNumber = 0; lineNumber < lines.size(); lineNumber++) {
        	// Split words into array by space
            String[] words = lines.get(lineNumber).replaceAll("[^a-zA-Z0-9\\s]", "").split("\\s+");
            
            for (String word : words) {
                if (!word.isEmpty()) {
                    boolean found = false;
                    Iterator<WordEntry> iterator = wordTree.inorderIterator();
                    while (iterator.hasNext()) {
                        WordEntry existingEntry = iterator.next();
                        if (existingEntry.getWord().equalsIgnoreCase(word)) {
                            existingEntry.addLocation(shortFileName, lineNumber + 1, word);
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        // If the word doesn't exist, create a new entry and add it
                        WordEntry newEntry = new WordEntry(word);
                        newEntry.addLocation(shortFileName, lineNumber + 1, word);
                        wordTree.add(newEntry);
                    }
                }
            }
        }
    }
    
    /**
     * Generates a report of word occurrences in a specified format and outputs it to the given stream.
     *
     * @param format the report format ("-pf", "-pl", "-po" are supported)
     * @param output the stream to write the report to
     */
    public void generateReport(String format, PrintStream output) {
		Iterator<WordEntry> iterator = wordTree.inorderIterator();
        
        while (iterator.hasNext()) {
            WordEntry entry = iterator.next();
            output.println(entry.generateReportForFormat(format));
        }
    }
    
    /**
     * Saves the current state of the WordTracker to a file.
     *
     * @param fileName the name of the file to save the state to
     * @throws IOException if an error occurs while saving the file
     */
    public void saveRepository(String fileName) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(this);
        }
    }
    
    /**
     * Loads a WordTracker state from a file. If the file does not exist or an error occurs,
     * a new WordTracker instance is returned.
     *
     * @param fileName the name of the file to load the state from
     * @return the loaded WordTracker instance, or a new instance if the file is not found or an error occurs
     */
    public static WordTracker loadRepository(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (WordTracker) ois.readObject();
        } catch (FileNotFoundException e) {
            return new WordTracker();
        } catch (Exception e) {
            e.printStackTrace();
            return new WordTracker();
        }
    }
}