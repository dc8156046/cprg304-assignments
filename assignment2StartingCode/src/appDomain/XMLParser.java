package appDomain;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import exceptions.EmptyQueueException;
import implementations.MyQueue;
import implementations.MyStack;


public class XMLParser {
	
	// Stack to store XML tags
    private MyStack<String> tagStack = new MyStack<>();
    
    // Queue to store error messages
    private MyQueue<String> errorQueue = new MyQueue<>(); 

    public void parse(String fileName) {
        File xmlFile = new File(fileName);
       
        try (Scanner scanner = new Scanner(xmlFile)) {
            int lineNumber = 1;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                if (line.isEmpty() || line.startsWith("<?xml")) {
                    continue; // Skip empty lines and XML declaration
                }
                
                // Check for invalid close tag pattern
                if (line.contains(">>")) {
                    errorQueue.enqueue("Invalid close tag at line " + lineNumber + "\n" + line + "\n");
                    continue;
                }
                
                // Check for invalid close tag pattern
                if (line.startsWith("<PackageCreationLocation") && line.contains(">") && !line.endsWith("/>")) {
                    errorQueue.enqueue("Error at line " + lineNumber + "\n\t" + line);
                    continue;
                }

                // Check for tags in the line
                processLine(line, lineNumber);

                
                lineNumber++;
            }

            // Handle any unclosed tags
//            while (!tagStack.isEmpty()) {
//                errorQueue.enqueue("Error: Unclosed tag <" + tagStack.pop() + ">");
//            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
           
        printErrors();
       
    }
    
    // Method to process each line for tags
    private void processLine(String line, int lineNumber) {
        boolean insideTag = false;
        StringBuilder currentTag = new StringBuilder();
        boolean isClosingTag = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (c == '<') {
                insideTag = true;
                currentTag = new StringBuilder(); // Reset current tag
                isClosingTag = false;

            } else if (c == '>') {
                insideTag = false;
                String tag = currentTag.toString().trim();
                
                //tag = tag.split(" ")[0];
                //System.out.println(tag);
                //tagStack.push(tag);
                if (!tag.isEmpty()) {
                    if (isClosingTag) {
                    	String lastOpenedTag = tagStack.pop();
                        //System.out.println(lastOpenedTag);
                        //System.out.println(tag);
                        if (!lastOpenedTag.equals(tag)) {
                            errorQueue.enqueue("Error at line " + lineNumber + "\r\t" + lastOpenedTag);
                            tagStack.push(lastOpenedTag); // Re-add the tag to stack if not matched
                        }
                    } else if (!tag.endsWith("/")) { // Opening tag (not self-closing)
                    	tag = tag.split(" ")[0]; // Remove attributes (only the tag name)
                    	//System.out.println(tag);
                        tagStack.push(tag);
                    }
                }

            } else if (insideTag) {
                if (c == '/' && currentTag.length() == 0) {
                    isClosingTag = true; // Detect a closing tag
                } else {
                    currentTag.append(c); // Append to current tag content
                }
            }
        }
//        Iterator<String> it = tagStack.iterator();
//        System.out.println("Current tagStack: " + it.next());
    }
    
    private void printErrors() {
	    System.out.println("=========================ERROR LOG====================");
	    // Display all errors in the queue after parsing
	    if (errorQueue.isEmpty()) {
	        System.out.println("No errors found.");
	    } else {
	        while (!errorQueue.isEmpty()) {
	            try {
	                System.out.println(errorQueue.dequeue());
	            } catch (EmptyQueueException e) {
	                e.printStackTrace();
	            }
	        }
	    }
    }


    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java -jar Parser.jar <XMLFile>");
            return;
        }

        XMLParser parser = new XMLParser();
        parser.parse(args[2]);
    }
}
