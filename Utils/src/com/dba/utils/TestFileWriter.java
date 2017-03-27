package com.dba.utils;

import java.util.ArrayList;
import java.util.Hashtable;

public class TestFileWriter {

	public static void main(String[] args) {

		FileWriterReader fileWriter = new FileWriterReader();
		
		String filePath = "test-file-writer.dat";
		Hashtable<String, String> valuesToWrite = new Hashtable<String, String>();
		
		valuesToWrite.put("name", "myname");
		valuesToWrite.put("city", "mycity");
		
		fileWriter.setFilePath (filePath);
		fileWriter.writeObjectToSingleFile(valuesToWrite);
		
	}

}
