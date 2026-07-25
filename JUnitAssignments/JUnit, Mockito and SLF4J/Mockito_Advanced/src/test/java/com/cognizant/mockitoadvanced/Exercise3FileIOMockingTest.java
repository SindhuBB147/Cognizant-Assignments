package com.cognizant.mockitoadvanced;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Exercise 3: Mocking File I/O
 * 
 * Tests a service that reads from and writes to files.
 * Demonstrates mocking of file system operations.
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Exercise 3: Mocking File I/O")
public class Exercise3FileIOMockingTest {
    
    private static final Logger logger = LoggerFactory.getLogger(Exercise3FileIOMockingTest.class);
    
    @Mock
    private FileReader mockFileReader;
    
    @Mock
    private FileWriter mockFileWriter;
    
    private FileService fileService;
    
    @BeforeEach
    public void setUp() {
        logger.info("=== SETUP: Initialize FileService with mocked I/O ===");
        fileService = new FileService(mockFileReader, mockFileWriter);
    }
    
    /**
     * Test 1: Basic file read and write
     */
    @Test
    @DisplayName("Test 1: Mock file reader and writer")
    public void testServiceWithMockFileIO() {
        logger.info("--- ARRANGE: Stub file operations ---");
        when(mockFileReader.read()).thenReturn("Mock File Content");
        when(mockFileWriter.write(anyString())).thenReturn(true);
        
        logger.info("--- ACT: Call fileService.processFile() ---");
        String result = fileService.processFile();
        
        logger.info("--- ASSERT: Verify result ---");
        assertEquals("Processed Mock File Content", result);
        verify(mockFileReader).read();
        verify(mockFileWriter).write("Processed Mock File Content");
        logger.info("✓ Test passed");
    }
    
    /**
     * Test 2: Process file by name
     */
    @Test
    @DisplayName("Test 2: Process file by filename")
    public void testProcessFileByName() {
        logger.info("--- ARRANGE: Stub file operations ---");
        when(mockFileReader.fileExists("test.txt")).thenReturn(true);
        when(mockFileReader.readFile("test.txt")).thenReturn("File Content");
        when(mockFileWriter.writeFile("test.txt", "Processed File Content")).thenReturn(true);
        
        logger.info("--- ACT: Call fileService.processFileByName() ---");
        String result = fileService.processFileByName("test.txt");
        
        logger.info("--- ASSERT: Verify result ---");
        assertEquals("Processed File Content", result);
        verify(mockFileReader).fileExists("test.txt");
        verify(mockFileReader).readFile("test.txt");
        logger.info("✓ Test passed");
    }
    
    /**
     * Test 3: File not found
     */
    @Test
    @DisplayName("Test 3: Handle file not found")
    public void testFileNotFound() {
        logger.info("--- ARRANGE: Stub fileExists to return false ---");
        when(mockFileReader.fileExists("missing.txt")).thenReturn(false);
        
        logger.info("--- ACT: Call fileService.processFileByName() ---");
        String result = fileService.processFileByName("missing.txt");
        
        logger.info("--- ASSERT: Verify not found message ---");
        assertEquals("File not found", result);
        logger.info("✓ Test passed");
    }
    
    /**
     * Test 4: Get file size
     */
    @Test
    @DisplayName("Test 4: Get file size information")
    public void testGetFileSize() {
        logger.info("--- ARRANGE: Stub getFileSize ---");
        when(mockFileReader.getFileSize("large.txt")).thenReturn(1024L);
        
        logger.info("--- ACT: Call fileService.getFileSizeInfo() ---");
        long size = fileService.getFileSizeInfo("large.txt");
        
        logger.info("--- ASSERT: Verify file size ---");
        assertEquals(1024L, size);
        verify(mockFileReader).getFileSize("large.txt");
        logger.info("✓ Test passed");
    }
    
    /**
     * Test 5: Process multiple lines
     */
    @Test
    @DisplayName("Test 5: Process file with multiple lines")
    public void testProcessFileLines() {
        logger.info("--- ARRANGE: Stub readLines ---");
        String[] testLines = {"Line 1", "Line 2", "Line 3"};
        when(mockFileReader.readLines()).thenReturn(testLines);
        when(mockFileWriter.writeLines(anyString(), any(String[].class))).thenReturn(true);
        
        logger.info("--- ACT: Call fileService.processFileLines() ---");
        String[] result = fileService.processFileLines();
        
        logger.info("--- ASSERT: Verify processed lines ---");
        assertEquals(3, result.length);
        assertEquals("Line 1: Line 1", result[0]);
        assertEquals("Line 2: Line 2", result[1]);
        assertEquals("Line 3: Line 3", result[2]);
        logger.info("✓ Test passed");
    }
    
    /**
     * Test 6: Empty file
     */
    @Test
    @DisplayName("Test 6: Handle empty file")
    public void testEmptyFile() {
        logger.info("--- ARRANGE: Stub read to return empty ---");
        when(mockFileReader.read()).thenReturn("");
        when(mockFileWriter.write(anyString())).thenReturn(true);
        
        logger.info("--- ACT: Call fileService.processFile() ---");
        String result = fileService.processFile();
        
        logger.info("--- ASSERT: Verify empty handling ---");
        assertEquals("Processed ", result);
        logger.info("✓ Test passed");
    }
    
    /**
     * Test 7: Multiple file operations
     */
    @Test
    @DisplayName("Test 7: Multiple file I/O operations")
    public void testMultipleFileOperations() {
        logger.info("--- ARRANGE: Setup file stubs ---");
        when(mockFileReader.read()).thenReturn("Content");
        when(mockFileReader.fileExists("test.txt")).thenReturn(true);
        when(mockFileReader.readFile("test.txt")).thenReturn("Content");
        when(mockFileReader.getFileSize("test.txt")).thenReturn(100L);
        when(mockFileWriter.write(anyString())).thenReturn(true);
        
        logger.info("--- ACT: Call multiple file methods ---");
        fileService.processFile();
        fileService.processFileByName("test.txt");
        fileService.getFileSizeInfo("test.txt");
        
        logger.info("--- ASSERT: Verify all operations ---");
        verify(mockFileReader).read();
        verify(mockFileReader).fileExists("test.txt");
        verify(mockFileReader).getFileSize("test.txt");
        logger.info("✓ Test passed");
    }
}
