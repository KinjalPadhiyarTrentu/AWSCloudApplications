package S3Service;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class CreateFolder {

	private static String accessKey = "#";
	private static String secretKey = "#";

	public static void main(String args[]) {
		AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
		AmazonS3Client c = new AmazonS3Client(awsCredentials);

		File f = new File("C:/KINJAL/Thol Photos/100_1899.JPG");
		c.putObject("kinjal-first-bucket", "DemoFolder/" + f.getName(), f);
		System.out.println("object submitted");
	}

	//filename is the path of the image
	//and details-file is the path of the text file
	public String createfoldert(String nameoffolder, String filename, String detailsfile) {

		AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
		AmazonS3Client c = new AmazonS3Client(awsCredentials);

		File f = new File(filename);
		File f1 = new File(detailsfile);
		//c.putObject("vraj-third", nameoffolder + "/" + f.getName(), f); //without-public-permission
		c.putObject(
				   new PutObjectRequest("kinjal-third",nameoffolder + "/" + f.getName(),f)
				      .withCannedAcl(CannedAccessControlList.PublicRead)); //to make public
		c.putObject("kinjal-third", nameoffolder + "/" + f1.getName(), f1);
		//String x=c.getResourceUrl("vraj-third",nameoffolder + "/" + f.getName());
		System.out.println("object submitted");
		String x=nameoffolder + "/" + f.getName();
		System.out.println("object submission"+x);
		return x;
	}
}
