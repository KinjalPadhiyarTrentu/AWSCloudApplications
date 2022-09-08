package S3Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import org.apache.commons.io.FilenameUtils;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;

public class CreateBucket {

	private static String accessKey = "#";
	private static String secretKey = "#";
	private static Regions region = Regions.US_EAST_2;


	public static void main(String args[])
	{
		AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
		AmazonS3Client c  = new AmazonS3Client(awsCredentials);
		c.setRegion(Region.getRegion(region));
		c.deleteBucket("kinjal-fourth");
		System.out.println("Created");
	}
	/*public String getimage(String imgid) throws IOException
	{
		String imgurl = imgid;
		String dest = "C:/VRAJ/newimg.jpg";
		saveImage(imgurl,dest);
		return dest;
	}
	private void saveImage(String imgurl, String dest) throws IOException {
		URL url = new URL(imgurl);
	    InputStream is = url.openStream();
	    OutputStream os = new FileOutputStream(dest);

	    byte[] b = new byte[2048];
	    int length;

	    while ((length = is.read(b)) != -1) {
	        os.write(b, 0, length);
	    }

	    is.close();
	    os.close();

	}
*/	
	public void imagebykey(String key)
	{
		AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
		AmazonS3Client c  = new AmazonS3Client(awsCredentials);
		c.setRegion(Region.getRegion(region));
		System.out.println("inside method :" + key);
		GetObjectRequest o = new GetObjectRequest("vraj-third",key);
		File f= new File("C:/KINJAL/dn.jpg");  //individual file
		c.getObject(o,f);
	}
}
