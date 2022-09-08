package Task1;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;

import S3Service.CreateBucket;

public class DisplayData extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIRECTORY = "Compdata";
	private static String accessKey = "#";
	private static String secretKey = "#";
	private static Regions region = Regions.US_EAST_2;
	private static final int pqr=2;
	private static String image_path = "";

	public DisplayData() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
		AmazonS3Client c  = new AmazonS3Client(awsCredentials);
		c.setRegion(Region.getRegion(region));

		String uploadPath = getServletContext().getRealPath("") + UPLOAD_DIRECTORY;
		File uploadDir = new File(uploadPath + "/" + pqr);
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
			System.out.println("in servlet directory made!");
		}
		String name = null;
		String imageurl = null; // which is actually key of the object
		String x = request.getParameter("search");
		HttpSession session = request.getSession();
		session.setAttribute("id",x);
		int p = Integer.parseInt(x);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://root1.cpk7vxbqxrsq.us-west-2.rds.amazonaws.com/Kinjal_RDS";
			Connection con= DriverManager.getConnection(url,"root1","rootroot");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from Information where ApplicationID='" + p + "'");

			while (rs.next()) {
				
				name = rs.getString("Name");
				session.setAttribute("nameofperson",name);
				imageurl = rs.getString("ImageKey");
				
				String fileName = "Downloaded"+x+".jpg";
				
				System.out.println("in jsp" + imageurl);
				GetObjectRequest o = new GetObjectRequest("vraj-third",imageurl);
				
				File f= new File(uploadPath + "/" + pqr +"/" +fileName);
				c.getObject(o,f);
				System.out.println("Downloaded!");
				response.sendRedirect("ShowDetails.jsp?imgname="+UPLOAD_DIRECTORY +"/"+pqr +"/" +fileName);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
