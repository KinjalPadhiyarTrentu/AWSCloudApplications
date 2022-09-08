import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class getFiles extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String accessKey = "#";
	private static String secretKey = "#";

	public getFiles() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name= request.getParameter("fn");
		
		AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
		AmazonS3Client c = new AmazonS3Client(awsCredentials);
		
		ObjectListing ol = c.listObjects("kinjal-first-bucket",name);
		
		List<S3ObjectSummary> ls2 = ol.getObjectSummaries();
		
		System.out.println("Objects in folder: "+name);
		
		for (int t = 0; t < ls2.size(); t++) 
		{
			System.out.println(ls2.get(t).getKey());
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
