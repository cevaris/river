package twitter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import util.Utils;

import com.river.Dam;

public class TwitterDam extends Dam {
	
	
	public TwitterDam() {
		try{
			this.log = File.createTempFile(Utils.getJulianDate(), ".log");
			this.logWriter = new FileWriter(this.log);
		} catch(IOException e){
			e.printStackTrace();
		} 
	}

}
