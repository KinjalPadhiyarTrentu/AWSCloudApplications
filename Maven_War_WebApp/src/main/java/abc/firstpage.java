package abc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class firstpage extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public firstpage() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String x = request.getParameter("iid");
		String x1 = request.getParameter("r1");
		String x3 = "new";
		String x6 = request.getParameter("selectedkey");
		String x4 = request.getParameter("nameofi");
		createnew c = new createnew();
		if (x1.equals(x3)) {
			try {
				c.createinstance(x4,x);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else
		{	
			c.createinstanceexist(x6,x);
		}
		
		
		out.println("Instance Has been created!");
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
