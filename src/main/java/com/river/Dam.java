package com.river;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

import util.Utils;


public class Dam {
	
	protected String TEMP_DIR = System.getProperty("java.io.tmpdir");
	protected File log;
	protected File zippedLog;
	protected FileWriter logWriter;
	
	public void log(String message) {
		try {
			String logMessage = String.format("%s\t%s\n", Utils.getDate(), message );
			logWriter.append(logMessage);
//			System.out.print(logMessage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public String zip(String filepath)  {
		File zip = new File(this.log.getAbsolutePath().replace(".log", ".zip"));
		File source = new File(filepath);
		
	    ZipOutputStream zos = null;
	    try {
	      String name = source.getName();
	      zos = new ZipOutputStream(new FileOutputStream(zip));
	      zos.putNextEntry(new ZipEntry(name));

	      FileInputStream fis = null;
	      try {
	        fis = new FileInputStream(source);
	        byte[] byteBuffer = new byte[1024];
	        int bytesRead = -1;
	        while ((bytesRead = fis.read(byteBuffer)) != -1) {
	          zos.write(byteBuffer, 0, bytesRead);
	        }
	        zos.flush();
	      } finally {
	        try {
	          fis.close();
	        } catch (Exception e) {
	        }
	      }
	      zos.closeEntry();

	      zos.flush();
	    } catch (IOException e) {
	    	e.printStackTrace();
	    } finally {
	      try {
	        zos.close();
	      } catch (Exception e) {
	    	  e.printStackTrace();
	      }
	    }
	    
	    return zip.getAbsolutePath();
	  }

//	public String compress(){
////		byte[] buffer = new byte[1024];
//		
//		String compressLocation = this.log.getAbsolutePath().replace(".log", ".zip");
//		
//		
//		
//		try{
//			
//			ZipFile zipFile = new ZipFile(compressLocation);
//			
////			ZipParameters parameters = new ZipParameters();
////			System.out.println("Done");
//
////			parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
////			parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
//
//			zipFile.addFile(this.log);
////			zipFile.createZipFile(this.log, parameters);
//			
//
//		 } catch (ZipException e) {
//		 	e.printStackTrace();
//		 } 
//		
//		
////    	try{
////    		FileOutputStream fos = new FileOutputStream(compressLocation);
////    		ZipOutputStream zos = new ZipOutputStream(fos);
////    		ZipEntry ze= new ZipEntry(this.log.getAbsolutePath());
////    		zos.putNextEntry(ze);
////    		FileInputStream in = new FileInputStream(this.log);
//// 
////    		int len;
////    		while ((len = in.read(buffer)) > 0) {
////    			zos.write(buffer, 0, len);
////    		}
//// 
////    		in.close();
////    		zos.closeEntry();
//// 
////    		//remember close it
////    		zos.close();
//// 
////    		System.out.println("Done");
//// 
////    	}catch(IOException ex){
////    	   ex.printStackTrace();
////    	}
//    	
//    	return compressLocation;
//	}

	public synchronized void cleanup() {
		String filePath = this.log.getAbsolutePath();
		
		try {
			logWriter.flush();
			logWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(zip(filePath));
		
		
	}

	

}
