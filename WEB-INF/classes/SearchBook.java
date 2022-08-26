import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.*;


public class SearchBook extends HttpServlet {
  
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

     
     String query = "SELECT * FROM bookinfo WHERE bookname='"+ name +"' and serialno = '"+ serial +"' ";

     ResultSet rs = st.executeQuery( query );

     if(rs.next())
	{
		out.println("<h1>Record Found</h1>");

    	    	String na = rs.getString("bookname");
    	    	String ser = rs.getString("serialno");
		String an = rs.getString("authorname");
		String pr = rs.getString("price");

    	    	out.println("<h1>Book Name: "+ na +" </h1>");
	    	out.println("<h1>Serial No: "+ ser + " </h1>");
		out.println("<h1>Author Name: "+ an + " </h1>");
		out.println("<h1>Book Price: "+ pr + " </h1>");
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
