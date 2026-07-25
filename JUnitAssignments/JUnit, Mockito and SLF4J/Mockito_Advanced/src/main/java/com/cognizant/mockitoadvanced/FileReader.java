package com.cognizant.mockitoadvanced;

/**
 * FileReader interface for file reading operations.
 * Demonstrates mocking of I/O operations.
 */
public interface FileReader {
    
    /**
     * Reads content from a file.
     * 
     * @return file content
     */
    String read();
    
    /**
     * Reads content from a file with specific filename.
     * 
     * @param filename the file to read
     * @return file content
     */
    String readFile(String filename);
    
    /**
     * Reads multiple lines from a file.
     * 
     * @return array of lines
     */
    String[] readLines();
    
    /**
     * Checks if file exists.
     * 
     * @param filename the file to check
     * @return true if file exists
     */
    boolean fileExists(String filename);
    
    /**
     * Gets the size of a file.
     * 
     * @param filename the file
     * @return file size in bytes
     */
    long getFileSize(String filename);
}
