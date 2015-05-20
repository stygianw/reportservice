package ua.stygianw.reporting.serivces.io;

import java.io.IOException;
import java.nio.file.Path;

import ua.stygianw.reporting.datagen.Report;

/**
 * @author StygianW
 * Abstraction layer for file generation
 */
public abstract class FileGenerator {
	
	protected Report report;
	protected String directory;
	
		
	public FileGenerator(Report report, String directory) {
		this.report = report;
		this.directory = directory;
	}
	
	
	//Constructs the filename, adding timestamp and extension to it
	protected String constructFilename(String body, String extension) {
		
		return String.format("%s_%d.%s", body, System.currentTimeMillis(), extension);
		
	} 
	
	//Returns file mime type
	public abstract String getMimeType();
	
	//Returns file extension
	public abstract String getExtension();
	
	//Returns generated file path
	public abstract Path generateReport() throws IOException;
	
	
	
}
