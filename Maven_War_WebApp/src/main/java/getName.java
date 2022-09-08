

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;

import S3Service.CreateFolder;

public class getName extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIRECTORY = "Test";

	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB
	private static final int pqr=1;

	private static String image_path = "";
	
	private static String accessKey = "#";
	private static String secretKey = "#";
	

    public getName() {
        super();
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String filePath=null;
		PrintWriter out = response.getWriter();
		String[] details = new String[2];
		
		AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
		AmazonS3Client client  = new AmazonS3Client(awsCredentials);
		
		if (!ServletFileUpload.isMultipartContent(request)) {

			System.out.println("Error: Form must has enctype=multipart/form-data.");
			out.flush();
			return;
		}

		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		ServletFileUpload upload = new ServletFileUpload(factory);

		upload.setFileSizeMax(MAX_FILE_SIZE);
		upload.setSizeMax(MAX_REQUEST_SIZE);

		String uploadPath = getServletContext().getRealPath("") + UPLOAD_DIRECTORY;
		
		
		File uploadDir = new File(uploadPath + "/" + pqr);
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		} 
		try {
			List<FileItem> formItems = upload.parseRequest(request);
			int i = 0;
			if (formItems != null && formItems.size() > 0) {
				for (FileItem item : formItems) {

					if (item.isFormField()) {
						details[i] = item.getString();
                		i++;
					}
					if (!item.isFormField()) {
						String fileName = details[0]+".jpg";
						image_path = pqr + "/" + fileName;
						filePath = uploadPath + "/" + image_path;
						File storeFile = new File(filePath);
						item.write(storeFile);
						client.putObject("kinjal-first-bucket",details[0]+"/"+storeFile.getName(), storeFile);
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection c=DriverManager.getConnection("jdbc:mysql://localhost/db1","root","root"); 
			Statement st=c.createStatement();
			st.executeUpdate("insert into manhar(name,s3link) values('"+details[1]+"','"+details[0]+"')");
			System.out.println("inserted into database");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
