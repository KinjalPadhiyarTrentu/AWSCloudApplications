package SNS;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class getdatafornot extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public getdatafornot() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("un");
		String password = request.getParameter("pw");
		String mail = request.getParameter("email");
		
		HttpSession session = request.getSession();
		
		session.setAttribute("uname",uname);
		session.setAttribute("pword",password);
		session.setAttribute("mailid",mail);
		
		FinalTry ft = new FinalTry();
		String arnofnewtopic = ft.putdata(mail);
		
		session.setAttribute("kinjalpadhiyar",arnofnewtopic);
		
		response.sendRedirect("login.jsp");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
