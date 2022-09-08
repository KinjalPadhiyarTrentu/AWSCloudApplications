package SNS;

import java.util.Date;
import java.util.List;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.ConfirmSubscriptionRequest;
import com.amazonaws.services.sns.model.ConfirmSubscriptionResult;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.model.ListTopicsResult;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.services.sns.model.SubscribeResult;
import com.amazonaws.services.sns.model.Topic;

public class FinalTry {

	private static String accessKey = "#";
	private static String secretKey = "#";
	private static Regions region = Regions.US_EAST_2;
	AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
	AmazonSNSClient client = new AmazonSNSClient(awsCredentials);

	public String putdata(String mail) {

		ListTopicsResult lresult = client.listTopics();
		List<Topic> ans = lresult.getTopics();

		String arn = ans.get(1).getTopicArn();

		CreateTopicRequest ctr = new CreateTopicRequest("LoginTopic");
		CreateTopicResult ctresult = client.createTopic(ctr);
		String TopicARN = ctresult.getTopicArn();
		
		SubscribeRequest srequest = new SubscribeRequest(arn,"email",mail);
		SubscribeResult sresult = client.subscribe(srequest);
		
		SubscribeRequest srequest1 = new SubscribeRequest(TopicARN,"email",mail);
		SubscribeResult sresult1 = client.subscribe(srequest1);
		
		System.out.println("Subscription Sent!");
		return TopicARN;
		
	}

	public void createtopic(String u,String m,String a) {
		
		String message = "Welcome "+u;
		String subject = "Subject For"+u+" on "+new Date();
		
		PublishRequest prequest = new PublishRequest(a, message, subject);
		PublishResult presult = client.publish(prequest); 
		System.out.println("Message ID: "+presult.getMessageId());
	
		
	}
}
