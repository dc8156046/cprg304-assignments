Word Tracker Program - ReadMe

Overview
The Word Tracker program analyzes text files to track the occurrences of words, 
their locations (file and line number), and any variations in capitalization. 
It uses a Binary Search Tree (BST) for efficient word storage and supports exporting reports in different formats.


Features
Track word occurrences across multiple files.
Retrieve file names, line numbers, and total occurrences for each word.
Export reports in the following formats:
-pf: List of files where each word is found.
-pl: List of files and line numbers where each word is found.
-po: Total occurrences of each word, along with files and line numbers.


Requirements
Java Development Kit (JDK) version 1.8.
A text editor or eclipse IDE to compile and run the Java program.
Input text files for analysis.

Installation Instructions
Download the project from D2L:
Download and extract the project zip file.

Navigate to the project directory where the source files are located.
The program uses a serialized file (repository.ser) to store word tracking data persistently.


Usage Instructions
Run the program from the command line with the following syntax:

java -jar WordTracker.jar <input.txt> -pf/-pl/-po [-f <output.txt>]

Parameters:
<input.txt>: Path to the text file to be processed.

-pf: Generate a report of files where words are found.
-pl: Generate a report of files and line numbers for each word.
-po: Generate a report showing total occurrences, files, and line numbers.

-f <output.txt> (optional): Specify the output file for the report. If omitted, the report will be printed to the console.

Example Commands:

java -jar WordTracker.jar res\otherTest.txt -pl
java -jar WordTracker.jar res\simpleTest.txt -pf
java -jar WordTracker.jar res\textfile.txt -po
java -jar WordTracker.jar res\simpleTest.txt -po -f output.txt
java -jar WordTracker.jar res\otherTest.txt -pl -f res\output.txt
		
How It Works
The program reads the input file line by line.
Words are extracted and processed to remove non-alphanumeric characters.
A Binary Search Tree (BST) stores each unique word along with its location details.
The user can generate a formatted report of the word occurrences.
Data is persisted in repository.ser for reuse across program executions.


File Structure
appDomain: Contains the main application logic.
implementations: Contains the Binary Search Tree (BST) implementation.
utilities: Provides iterator ADT for traversing the BST.
test: unitTest
res: test txt files

