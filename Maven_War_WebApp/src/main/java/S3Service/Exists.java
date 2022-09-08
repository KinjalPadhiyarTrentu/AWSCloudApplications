package S3Service;

import java.util.List;

import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.BucketPolicy;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class Exists {

	private static String accessKey = "#";
	private static String secretKey = "#";
	private static Regions region = Regions.US_EAST_2;
	static AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
	static AmazonS3Client c = new AmazonS3Client(awsCredentials);

	public boolean exist(String x, String x1) {
		c.setRegion(Region.getRegion(region));
		return c.doesObjectExist(x, x1);
	}

	public static void main(String args[])
	{
		System.out.println(c.doesObjectExist("kinjaltry/Output Files Trans/Transcoded","Kinjal Padhiyar360p(4:3).mp4"));
	}
}
