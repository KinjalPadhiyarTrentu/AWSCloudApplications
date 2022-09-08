package SNS;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class verifydata extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public verifydata() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String s1 = request.getParameter("fromun");
		String s2 = request.getParameter("frompw");
		
		HttpSession session = request.getSession();

		String u = (String) session.getAttribute("uname");
		String p = (String) session.getAttribute("pword");
		String m = (String) session.getAttribute("mailid");
		String a = (String) session.getAttribute("kinjalpadhiyar");
		
		if(s1.equals(u) && s2.equals(p))
		{
			FinalTry ft = new FinalTry();
			ft.createtopic(u,m,a);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
