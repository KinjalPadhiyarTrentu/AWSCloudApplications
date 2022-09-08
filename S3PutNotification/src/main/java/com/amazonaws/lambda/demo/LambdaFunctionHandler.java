package com.amazonaws.lambda.demo;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;

public class LambdaFunctionHandler implements RequestHandler<S3Event, String> {

	private static String accessKey = "AKIAJXFVQ3CIKTV3FQWA";
	private static String secretKey = "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";
	private static Regions region = Regions.US_EAST_2;

    private AmazonS3 s3 = AmazonS3ClientBuilder.standard().build();

    public LambdaFunctionHandler() {}

    LambdaFunctionHandler(AmazonS3 s3) {
        this.s3 = s3;
    }

    @Override
    public String handleRequest(S3Event event, Context context) {
        context.getLogger().log("Received event: " + event);

        AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
		AmazonSNSClient client = new AmazonSNSClient(awsCredentials);
		client.setRegion(Region.getRegion(region));
		
        String bucket = event.getRecords().get(0).getS3().getBucket().getName();
        String key = event.getRecords().get(0).getS3().getObject().getKey();
        
        String message = "Image uploaded in "+key;
        String subject = "Object Uploaded in "+bucket;
        String ARN= "arn:aws:sns:us-east-2:836811619026:ImageNotification";
        
		PublishRequest prequest = new PublishRequest(ARN, message, subject);
		client.publish(prequest); 
		
        return "Success";
    }
}