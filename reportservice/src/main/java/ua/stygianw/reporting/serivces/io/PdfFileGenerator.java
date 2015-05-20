package ua.stygianw.reporting.serivces.io;

import java.io.IOException;
import java.nio.file.Path;

import ua.stygianw.reporting.datagen.Report;

public class PdfFileGenerator extends FileGenerator {

	public PdfFileGenerator(Report report, String directory) {
		super(report, directory);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getMimeType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getExtension() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Path generateReport() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
