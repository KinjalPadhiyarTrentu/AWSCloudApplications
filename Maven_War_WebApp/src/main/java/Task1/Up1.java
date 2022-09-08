package Task1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import S3Service.CreateFolder;

public class Up1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIRECTORY = "Compdata";

	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB
	private static final int pqr=1;

	private static String image_path = "";
	
	public Up1() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("in servlet");
		PrintWriter out = response.getWriter();
		String filePath=null;
		String[] details = new String[2];
		
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
			System.out.println("in servlet directory made!");
		} 
		try {
			List<FileItem> formItems = upload.parseRequest(request);
			int i = 0;
			if (formItems != null && formItems.size() > 0) {
				// iterates over form's fields
				for (FileItem item : formItems) {

					if (item.isFormField()) {
						details[i] = item.getString();
                		System.out.println("Details"+(i+1)+":"+details[i]+"<br>");
                		System.out.println("-----------------------<br>");
                		i++;
					}
					if (!item.isFormField()) {
						String fileName = details[0]+".jpg";
						image_path = pqr + "/" + fileName;
						System.out.println("<br>IMAGE PATH:" + image_path + "<br>");
						System.out.println("-----------------------<br>");

						filePath = uploadPath + "/" + image_path;
						System.out.println(filePath);
						File storeFile = new File(filePath);

						item.write(storeFile);

						System.out.println("Image stored in disk<br>");
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		String write = "Your Application Id is: "+details[0]+" Your name is: "+details[1]; //string which is stored in file 
		String fn = uploadPath + "/" +pqr+"/"+details[0]+".txt"; //name and path of file
		File f = new File(fn);
		FileWriter fw = new FileWriter(f);
		fw.write(write);

		if (fw != null) {
			fw.close();
		}

		CreateFolder c = new CreateFolder();
		String x=c.createfoldert(details[0]+" "+details[1],filePath,fn);

		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://root1.cpk7vxbqxrsq.us-west-2.rds.amazonaws.com/Rohan_RDS";
			Connection con= DriverManager.getConnection(url,"root1","rootroot");
			Statement st = con.createStatement();
			st.executeUpdate("insert into Information(ApplicationID,Name,ImageKey) values('"+details[0]+"','"+details[1]+"','"+x+"')");
			System.out.println("inserted into database");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		response.sendRedirect("NewFile.jsp");
	}
}