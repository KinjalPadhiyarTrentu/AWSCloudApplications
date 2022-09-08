package S3Service;

import java.io.File;
import java.util.List;import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.s3.transfer.TransferManager;

public class DownloadFile {

	private static String accessKey = "#";
	private static String secretKey = "#";
	private static Regions region = Regions.US_EAST_2;

	public static void main(String args[])
	{
		AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
		AmazonS3Client c = new AmazonS3Client(awsCredentials);
		c.setRegion(Region.getRegion(region));
		
		GetObjectRequest o = new GetObjectRequest("kinjal-third","58 neeta/58 neeta.txt");
		File f= new File("C:/KINJAL/dn.txt");  //individual file
		c.getObject(o,f);
		TransferManager tm = new TransferManager(c);
		File ff= new File("C:/KINJAL");
		tm.downloadDirectory("kinjal-third","58 neeta",ff); //whole folder
		
		ObjectListing ol =c.listObjects("kinjal-third","Hehe");
		List<S3ObjectSummary> ls = ol.getObjectSummaries();
		
		
		for(int y=1;y<ls.size();y++)
		{
			System.out.println(y);
			System.out.println(ls.get(y).getKey()+ "\n");
			c.deleteObject("kinjal-third",ls.get(y).getKey());
		}   //flush
		//c.deleteObject("vraj-first-bucket","111962-sherlock-sherlock.jpg");
		c.copyObject("kinjal-third","58 neeta/58 neeta.txt","kinjal-third","Hehe/1.txt");
		System.out.println("done");
	}
}
