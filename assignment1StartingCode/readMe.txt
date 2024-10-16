# Sorting Program ReadMe

## Overview
This program provides various sorting algorithms for sorting arrays, including:
- Bubble Sort
- Insertion Sort
- Selection Sort
- Merge Sort
- Quick Sort
- Heap Sort

It supports sorting with both `Comparable` objects and custom `Comparator`s, allowing flexibility in sorting different data types.

## Prerequisites
- Java JDK
- An Eclipse IDE 

## Installation

### Step 1: Download assignment1StartingCode code from D2L

### Step 2: Import the Project
    - Open the Eclipse IDE.
    - Import the project as a Java project by selecting the root folder where the downloaded codes resides.
    - Ensure that the Java build path is set correctly.

### Running the Program
To run the sorting program, execute the following command from Command line in current project folder:
java -jar Sort.jar -f<file_name> -t[v/h/a] -s[b/s/i/m/q/z]

or in Eclipse IDE find assignment1StartingCode\src\appDomain\AppDriver.java, right click the file, click Run as, Run configurations, in Arguements:
-f<file_name> -t[v/h/a] -s[b/s/i/m/q/z]


### Sorting Algorithms
The program supports different sorting algorithms that can be applied based on user choice. When prompted, specify which sorting algorithm you would like to use by providing the corresponding option.

Example:
- b 	Bubble Sort
- i 	Insertion Sort
- s 	Selection Sort
- m 	Merge Sort
- q 	Quick Sort
- z 	Heap Sort

Sort with comparable:
- "h" for sorting by height.

If you want to sort with a custom comparator (e.g., sorting shapes by volume or base area):
- The program provides options to input custom comparator types, such as:
    - "v" for sorting by volume.
    - "a" for sorting by base area.
    
### Sorting a Large Number of Items
The program can handle large arrays for sorting, but certain algorithms like Bubble Sort may be inefficient for very large datasets (e.g., 1,000,000 elements). It is recommended to use `Merge Sort` or `Quick Sort` for large data sets.

## Example Input
To test the sorting program with shapes, ensure that a data file (e.g., `shapes1.txt`, `shapes2.txt`) are available with shape descriptions.



