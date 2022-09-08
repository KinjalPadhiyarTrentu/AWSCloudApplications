package Task1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import S3Service.CreateFolder;
public class UploadDoc extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public UploadDoc() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		String file=request.getParameter("imagefile"); //path
		String id = request.getParameter("appid");	//application id
		String name = request.getParameter("name"); //name 

		String write = "Your Application Id is: "+id+" Your name is: "+name; //string which is stored in file 
		String fn = "C:\\KINJAL\\FIR_TASK\\"+id+" "+name+".txt"; //name and path of file
		File f = new File(fn);
		FileWriter fw = new FileWriter(f);
		fw.write(write);

		if (fw != null) {
			fw.close();
		}

		CreateFolder c = new CreateFolder();
		String x=c.createfoldert(id+" "+name,file,fn);

		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://root1.cpk7vxbqxrsq.us-west-2.rds.amazonaws.com:3306/Kinjal_RDS";
			Connection con= DriverManager.getConnection(url,"root1","rootroot");
			Statement st = con.createStatement();
			st.executeUpdate("insert into Information(ApplicationID,Name,ImageKey) values('"+id+"','"+name+"','"+x+"')");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		response.sendRedirect("NewFile.jsp");
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
