package com.river.twitter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import util.Utils;

import com.river.Dam;

public class TwitterDam extends Dam {
	
	
	public TwitterDam() {
		try{
			this.log = new File(TEMP_DIR + Utils.getJulianDate() + ".log");
			this.logWriter = new FileWriter(this.log,true);
		} catch(IOException e){
			e.printStackTrace();
		} 
	}

}
