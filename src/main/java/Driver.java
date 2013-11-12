import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.util.Progressable;

import com.river.twitter.TwitterDam;
import com.river.twitter.TwitterRiver;

public class Driver {
	
	
	public static void main(String[] args) throws IOException, URISyntaxException {
		
		TwitterDam dam = new TwitterDam();
		TwitterRiver river = new TwitterRiver();
		river.setDam(dam);
		river.start();
		
//		Configuration configuration = new Configuration();
//	    FileSystem hdfs = FileSystem.get( new URI( "hdfs://127.0.0.1:9000" ), configuration );
//	    Path file = new Path("hdfs://127.0.0.1:9000/river.txt");
//	    if ( hdfs.exists( file )) { hdfs.delete( file, true ); } 
//	    OutputStream os = hdfs.create( file,
//	        new Progressable() {
//	            public void progress() {
//	                System.out.print(".");
//	            } });
//	    final BufferedWriter br = new BufferedWriter( new OutputStreamWriter( os, "UTF-8" ) );
//	    br.write("Hello World");
//	    br.close();
//	    hdfs.close();
		
//		
	    
	    
	    
	}

}
