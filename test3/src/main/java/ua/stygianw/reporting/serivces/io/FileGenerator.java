package ua.stygianw.reporting.serivces.io;

import java.io.IOException;
import java.nio.file.Path;

import ua.stygianw.reporting.datagen.Report;

public abstract class FileGenerator {
	
	protected Report report;
	protected String directory;
	
		
	public FileGenerator(Report report, String directory) {
		this.report = report;
		this.directory = directory;
	}
	
	protected String constructFilename(String body, String extension) {
		
		return String.format("%s_%d.%s", body, System.currentTimeMillis(), extension);
		
	} 
	
	public abstract String getMimeType();
	
	public abstract String getExtension();
	
	public abstract Path generateReport() throws IOException;
	
	
	
}
