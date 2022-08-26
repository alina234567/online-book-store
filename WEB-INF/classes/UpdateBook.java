import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.*;


public class UpdateBook extends HttpServlet {
  
  //Process the HTTP Get request
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  
	response.setContentType("text/html");
    
	// get PrintWriter object
	PrintWriter out = response.getWriter();

    String na = request.getParameter("name");
    String serial = request.getParameter("sno");
    String aut = request.getParameter("aname");
    String pri = request.getParameter("price");

    out.println("<html>");
    out.println("<head><title>Response</title></head>");
    out.println("<body>");
    out.println("</body></html>");


    try{

    Class.forName("com.mysql.jdbc.Driver");

    String url = "jdbc:mysql://127.0.0.1/bookstore";

    Connection con=DriverManager.getConnection(url, "root", "Alina");

    Statement st=con.createStatement();

     
     String query = "UPDATE bookinfo SET authorname='"+ aut +"', price= '"+ pri +"', bookname='"+ na +"' WHERE serialno= '"+ serial +"' ";

      int rs = st.executeUpdate( query );

     if(rs>0)
	{
		response.sendRedirect ("updatebook.html");
	}
	else
	{
		out.println("sorry, not updated");
	}

           st.close();
           con.close();

    }catch(Exception e){

      out.println(e);
    }

  }

}