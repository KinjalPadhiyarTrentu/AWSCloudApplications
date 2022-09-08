package SNS;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.ConfirmSubscriptionRequest;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.services.sns.model.SubscribeResult;

public class ConfirmSubscription {

	private static String accessKey = "#";
	private static String secretKey = "#";
	private static Regions region = Regions.US_EAST_2;

	static BlockingQueue<Map<String, String>> messageQueue = new LinkedBlockingQueue<Map<String, String>>();
	public static void main(String args[]) throws Exception
	{
		AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
		AmazonSNSClient client = new AmazonSNSClient(awsCredentials);
		
		CreateTopicRequest req =  new CreateTopicRequest("KinjalProg3SNS");
		CreateTopicResult createRes = client.createTopic(req);
		System.out.println("Topic Created");
		
		SubscribeRequest subscribeReq = new SubscribeRequest()
		            .withTopicArn(createRes.getTopicArn())
		            .withProtocol("email")
		            .withEndpoint("kinjalpadhiyar@gmail.com");
		
		client.subscribe(subscribeReq);
		try{
		Map<String, String> messageMap = messageQueue.take();
		String token = messageMap.get("Token");
        System.out.println("Token is : "+token);
        if (token != null) 
        {    
        	ConfirmSubscriptionRequest confirmReq = new ConfirmSubscriptionRequest()
                .withTopicArn(createRes.getTopicArn())
                .withToken(token);
            client.confirmSubscription(confirmReq);
            System.out.println("Subscription Confirmed!");
            
            SubscribeResult sresult = client.subscribe(subscribeReq);
    		
    		String message = "Have You got mail with this ARN?: "+createRes.getTopicArn();
    		String subject = "Latest Subject"+new Date();
    		
    		PublishRequest prequest = new PublishRequest(createRes.getTopicArn(), message, subject);
    		PublishResult presult = client.publish(prequest); 
    		System.out.println("Message ID: "+presult.getMessageId());
    		System.out.println("Message Sent!");
        }
		}
		catch(Exception e)
		{
			System.out.println("Error: "+e.getMessage());
		}
	}
}