package storage;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.jets3t.service.S3Service;
import org.jets3t.service.S3ServiceException;
import org.jets3t.service.impl.rest.httpclient.RestS3Service;
import org.jets3t.service.model.S3Object;
import org.jets3t.service.security.AWSCredentials;


public class AmazonS3 {
	
	
	public AmazonS3() {
		connect();
	}

	private void connect() {
		String awsAccessKey = "YOUR_AWS_ACCESS_KEY";
		String awsSecretKey = "YOUR_AWS_SECRET_KEY";
		AWSCredentials awsCredentials = 
		    new AWSCredentials(awsAccessKey, awsSecretKey);
		
		try {
			S3Service s3Service = new RestS3Service(awsCredentials);
			
			File fileData = new File("src/org/jets3t/samples/CodeSamples.java");
			S3Object fileObject = new S3Object(fileData);
			
			
		} catch (S3ServiceException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
	}

}
