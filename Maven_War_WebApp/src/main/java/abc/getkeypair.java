package abc;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.DescribeKeyPairsRequest;
import com.amazonaws.services.ec2.model.DescribeKeyPairsResult;
import com.amazonaws.services.ec2.model.KeyPairInfo;

public class getkeypair {

	private static String accessKey = "#";
	private static String secretKey = "#";
	private static Regions region = Regions.US_EAST_2;

	static{
		int a=0;
		System.out.println(a);
	}
	public static List getkeylist() throws Exception {

		AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
		AmazonEC2Client amazonEC2Client = new AmazonEC2Client(awsCredentials);
		amazonEC2Client.setRegion(Region.getRegion(region));
		
		
		DescribeKeyPairsRequest dkpr = new DescribeKeyPairsRequest();
	    DescribeKeyPairsResult dkpresult =  amazonEC2Client.describeKeyPairs(dkpr);

	    List<KeyPairInfo> keyPairs = dkpresult.getKeyPairs();
	    List<String> keyPairNameList = new ArrayList();

	    for (KeyPairInfo keyPairInfo : keyPairs) {
	        keyPairNameList.add(keyPairInfo.getKeyName());
	    }
	    
	    for (KeyPairInfo keyPairInfo : keyPairs) {
	        System.out.println(keyPairInfo.getKeyName());
	    }
	    return keyPairNameList;

	}
}
