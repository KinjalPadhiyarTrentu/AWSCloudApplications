package S3Service;

import java.util.List;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class BucketListing {

	private static String accessKey = "#";
	private static String secretKey = "#";
	//Do not mention Region, as S3 is worldwide.
	
	public static void main(String args[]) {
		AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
		AmazonS3Client c = new AmazonS3Client(awsCredentials);

		List<Bucket> ls = c.listBuckets();
		
		for (int b = 0; b < ls.size(); b++) 
		{
			System.out.println(ls.get(b).getName());

			ObjectListing ls1 = c.listObjects(ls.get(b).getName());
			List<S3ObjectSummary> ls2 = ls1.getObjectSummaries();

			for (int t = 0; t < ls2.size(); t++) 
			{
				System.out.println(ls2.get(t).getKey());
			}
		}
	}

}
