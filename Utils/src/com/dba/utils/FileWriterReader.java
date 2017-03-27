package com.dba.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Hashtable;

public class FileWriterReader {
	
	private String 				filePath;
	
	public FileWriterReader(){
		
	}

	public void setFilePath (String filePath) {
		this.filePath = filePath;
	}
	
	public String getFilePath() {
		return filePath;
	}
	
	//public void writeToFile(String filePath, String[] valuesToWrite) {
	//public void writeToFile(FileWriter fileWriter) {
	//public void writeHashTableAsObjectToSingleFile (Hashtable<String, String> valuesToWrite) {
	//public void writeHashTableAsObjectToSingleFile (Hashtable<String, Object> valuesToWrite) {

	public void writeObjectToSingleFile (Object valuesToWrite) {
		
		try {
			
			System.out.println ("In writeToFile - writing to - " + filePath + " values - " + valuesToWrite.toString()
					+ " as " + valuesToWrite.getClass());
			
			File file = new File(filePath);
			FileOutputStream fos;

			fos = new FileOutputStream(file);

			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(valuesToWrite);
			oos.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	// TBD - append hash table as object
	// TBD - append 
}
