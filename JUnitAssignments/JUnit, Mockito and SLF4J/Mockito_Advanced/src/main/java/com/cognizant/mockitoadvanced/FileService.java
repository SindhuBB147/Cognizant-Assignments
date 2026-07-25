package com.cognizant.mockitoadvanced;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service for Exercise 3: Mocking File I/O.
 * Demonstrates testing file operations with mocked readers and writers.
 */
public class FileService {
    
    private static final Logger logger = LoggerFactory.getLogger(FileService.class);
    private final FileReader fileReader;
    private final FileWriter fileWriter;
    
    /**
     * Constructor with file dependencies injection.
     */
    public FileService(FileReader fileReader, FileWriter fileWriter) {
        this.fileReader = fileReader;
        this.fileWriter = fileWriter;
    }
    
    /**
     * Processes file by reading and returning processed content.
     */
    public String processFile() {
        logger.info("Processing file");
        String content = fileReader.read();
        if (content != null) {
            String processed = "Processed " + content;
            fileWriter.write(processed);
            logger.info("File processed: {}", processed);
            return processed;
        }
        return "No content";
    }
    
    /**
     * Processes file with specific filename.
     */
    public String processFileByName(String filename) {
        logger.info("Processing file: {}", filename);
        if (fileReader.fileExists(filename)) {
            String content = fileReader.readFile(filename);
            String processed = "Processed " + content;
            fileWriter.writeFile(filename, processed);
            return processed;
        }
        return "File not found";
    }
    
    /**
     * Reads and returns file size.
     */
    public long getFileSizeInfo(String filename) {
        logger.info("Getting file size: {}", filename);
        return fileReader.getFileSize(filename);
    }
    
    /**
     * Processes multiple lines from file.
     */
    public String[] processFileLines() {
        logger.info("Processing file lines");
        String[] lines = fileReader.readLines();
        if (lines != null && lines.length > 0) {
            String[] processed = new String[lines.length];
            for (int i = 0; i < lines.length; i++) {
                processed[i] = "Line " + (i + 1) + ": " + lines[i];
            }
            fileWriter.writeLines("output.txt", processed);
            return processed;
        }
        return new String[0];
    }
}
