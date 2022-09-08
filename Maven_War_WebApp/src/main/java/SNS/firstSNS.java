package SNS;

import java.util.Date;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.model.PublishRequest;

public class firstSNS {

	private static String accessKey = "#";
	private static String secretKey = "#";
	private static Regions region = Regions.US_EAST_2;

	public static void main(String args[])
	{
		//4 steps: Create Topic, Subscription, Publish Request, Delete Topic
		
		AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
		AmazonSNSClient client = new AmazonSNSClient(awsCredentials);
		
		CreateTopicRequest ctr = new CreateTopicRequest("KinjalProgSNS");
		CreateTopicResult ctresult = client.createTopic(ctr);
		
		System.out.println("ARN: "+ctresult.getTopicArn());
		System.out.println(client.getCachedResponseMetadata(ctr).getRequestId());
		
		PublishRequest publish = new PublishRequest().withTopicArn(ctresult.getTopicArn()).withMessage("New Topic!"+new Date());
		client.publish(publish);
		
		System.out.println("Published!");
	}
}
