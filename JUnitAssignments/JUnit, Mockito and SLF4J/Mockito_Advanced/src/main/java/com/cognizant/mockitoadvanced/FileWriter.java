package com.cognizant.mockitoadvanced;

/**
 * FileWriter interface for file writing operations.
 * Demonstrates mocking of file write operations.
 */
public interface FileWriter {
    
    /**
     * Writes content to a file.
     * 
     * @param content the content to write
     * @return true if write was successful
     */
    boolean write(String content);
    
    /**
     * Writes content to a specific file.
     * 
     * @param filename the file to write to
     * @param content the content to write
     * @return true if write was successful
     */
    boolean writeFile(String filename, String content);
    
    /**
     * Appends content to a file.
     * 
     * @param filename the file to append to
     * @param content the content to append
     * @return true if append was successful
     */
    boolean appendToFile(String filename, String content);
    
    /**
     * Writes multiple lines to a file.
     * 
     * @param filename the file to write to
     * @param lines the lines to write
     * @return true if write was successful
     */
    boolean writeLines(String filename, String[] lines);
    
    /**
     * Flushes the writer.
     */
    void flush();
}
