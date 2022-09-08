

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import S3Service.Exists;
public class bservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public bservlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String s1= request.getParameter("bname");
		String s2= request.getParameter("oname");
		
		Exists e =new Exists();
		Boolean f =e.exist(s1,s2);
		if(f)
		{
			out.println("Exists");
		}
		else
		{
			out.println(" Doesnt Exist");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
