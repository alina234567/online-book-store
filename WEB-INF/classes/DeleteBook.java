import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.*;


public class DeleteBook extends HttpServlet {
  
  //Process the HTTP Get request
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  
	response.setContentType("text/html");
    
	// get PrintWriter object
	PrintWriter out = response.getWriter();

    String name = request.getParameter("dname");
    String serial = request.getParameter("sno");

    out.println("<html>");
    out.println("<head><title>Response</title></head>");
    out.println("<body>");
    out.println("</body></html>");


    try{

    Class.forName("com.mysql.jdbc.Driver");

    String url = "jdbc:mysql://127.0.0.1/bookstore";

    Connection con=DriverManager.getConnection(url, "root", "Alina");

    Statement st=con.createStatement();

     
     String query = "DELETE FROM bookinfo WHERE bookname='"+ name +"' and serialno = '"+ serial +"' ";

     System.out.println(query);

      int rs = st.executeUpdate( query );

     if(rs>0)
	{
		response.sendRedirect ("deletebook.html");
	}
	else
	{
		out.println(" Sorry, record not found");
	}

           st.close();
           con.close();

    }catch(Exception e){

      out.println(e);
    }

  }

}