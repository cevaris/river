package com.river;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import util.Utils;


public class Dam {
	
	protected File log;
	protected File zippedLog;
	protected FileWriter logWriter;

	
	public void log(String message) {
		try {
			logWriter.append(String.format("%s %s", Utils.getDate(), message ));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String compress(){
		byte[] buffer = new byte[1024];
		
		String compressLocation = this.log.getAbsolutePath().replace(".log", ".zip");
		 
    	try{
    		FileOutputStream fos = new FileOutputStream(this.log);
    		ZipOutputStream zos = new ZipOutputStream(fos);
    		ZipEntry ze= new ZipEntry(this.log.getName());
    		zos.putNextEntry(ze);
    		FileInputStream in = new FileInputStream(compressLocation);
 
    		int len;
    		while ((len = in.read(buffer)) > 0) {
    			zos.write(buffer, 0, len);
    		}
 
    		in.close();
    		zos.closeEntry();
 
    		//remember close it
    		zos.close();
 
    		System.out.println("Done");
 
    	}catch(IOException ex){
    	   ex.printStackTrace();
    	}
    	
    	return compressLocation;
	}

	

}
