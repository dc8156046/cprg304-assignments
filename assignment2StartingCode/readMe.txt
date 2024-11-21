# Data Structures and XML Parser Program ReadMe

## Overview

This project involves creating custom data structures, implementing utility classes, and building an XML parser using only the specified ADTs,
including:
- MyArrayList
- MyDLL
- MyDLLNode
- MyQueue
- MyStack
- XMLParser

It supports parser xml files that print all lines that are not properly constructed in the order in which the errors occur.

## Prerequisites

- Java JDK
- An Eclipse IDE 

## Installation

### Step 1: Download assignment2StartingCode code from D2L

### Step 2: Import the Project
    - Open the Eclipse IDE.
    - Import the project as a Java project by selecting the root folder where the downloaded codes resides.
    - Ensure that the Java build path is set correctly.

### Running the Program
To run the sorting program, execute the following command from Command line in current project folder:
java -jar Parser.jar <xml_file_name> 
For example: java -jar Parser.jar sample1.xml 

or in Eclipse IDE find assignment2StartingCode\src\appDomain\XMLParser.java, right click the file, click Run as, Run configurations, in Arguements:
java XMLParser res/sample2.xml


## XML Parser

Functionality
Parse supplied XML documents and validate their syntax.
Output all errors found in the XML document in the order they occur.



