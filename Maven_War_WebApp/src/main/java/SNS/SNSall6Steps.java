package SNS;

import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import com.amazonaws.AmazonWebServiceResponse;
import com.amazonaws.ResponseMetadata;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.http.SdkHttpMetadata;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.ConfirmSubscriptionRequest;
import com.amazonaws.services.sns.model.ConfirmSubscriptionResult;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.model.GetTopicAttributesRequest;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.services.sns.model.SubscribeResult;

public class SNSall6Steps {

	private static String accessKey = "#";
	private static String secretKey = "#";
	private static Regions region = Regions.US_EAST_2;

	public static void main(String args[])
	{
		AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
		AmazonSNSClient client = new AmazonSNSClient(awsCredentials);
		
		CreateTopicRequest ctr = new CreateTopicRequest("KinjalPadhiyarSNS");
		CreateTopicResult ctresult = client.createTopic(ctr);
		System.out.println("Topic Created");
		
		SubscribeRequest srequest = new SubscribeRequest(ctresult.getTopicArn(),"email","kinjalpadhiyar@gmail.com");
		SubscribeResult sresult = client.subscribe(srequest);
		
		System.out.println("Subscription Sent!");
		
		String message = "Have You got mail with this ARN?: "+ctresult.getTopicArn();
		String subject = "Latest Subject"+new Date();
		
		PublishRequest prequest = new PublishRequest(ctresult.getTopicArn(), message, subject);
		PublishResult presult = client.publish(prequest); 
		System.out.println("Message ID: "+presult.getMessageId());
		
		System.out.println("Published!");
	}
}
