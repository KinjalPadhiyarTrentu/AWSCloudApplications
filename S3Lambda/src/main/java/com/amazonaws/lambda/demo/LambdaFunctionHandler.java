package com.amazonaws.lambda.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

public class LambdaFunctionHandler implements RequestHandler<S3Event, String> {

    private AmazonS3 s3 = AmazonS3ClientBuilder.standard().build();
	private static String accessKey = "AKIAJXFVQ3CIKTV3FQWA";
	private static String secretKey = "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";
	private static Regions region = Regions.US_WEST_2;

    public LambdaFunctionHandler() {}

    // Test purpose only.
    LambdaFunctionHandler(AmazonS3 s3) {
        this.s3 = s3;
    }

    @Override
    public String handleRequest(S3Event event, Context context) {
        context.getLogger().log("Received event: " + event);
    	AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
		AmazonS3Client c = new AmazonS3Client(awsCredentials);
		c.setRegion(Region.getRegion(region));
        
		String bucket = event.getRecords().get(0).getS3().getBucket().getName();
        String key = event.getRecords().get(0).getS3().getObject().getKey();

    	File fl = new File("/tmp/");
		if (!fl.exists()) 
		{
			fl.mkdirs();
		}
        try {
			
			S3Object response = s3.getObject(new GetObjectRequest(bucket, key));
            String contentType = response.getObjectMetadata().getContentType();
            String keyofobject = response.getKey();
            System.out.println("Provided By S3 "+key);
            System.out.println("Using Method "+keyofobject);
            InputStream is = response.getObjectContent();
            String content;
            BufferedReader bReader = new BufferedReader(new InputStreamReader(is));
            StringBuffer sbfFileContents = new StringBuffer();
            String line = null;
           
            while( (line = bReader.readLine()) != null){
                    sbfFileContents.append(line);
            }
            content = sbfFileContents.toString();
			
            GetObjectRequest g = new GetObjectRequest(bucket, keyofobject);
			
			File destinationFile = new File(fl + keyofobject);
			c.getObject(g,destinationFile);
			
			
    		context.getLogger().log("CONTENT TYPE: " + contentType);
    		context.getLogger().log("CONTENT: " + content);
    		return contentType;
        } catch (Exception e) {
            e.printStackTrace();
            context.getLogger().log(String.format(
                "Error getting object %s from bucket %s. Make sure they exist and"
                + " your bucket is in the same region as this function.", bucket, key));
            return "Hi";
        }
	    
    }
}