import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;


public class Search extends HttpServlet {

  //Process the HTTP Get request
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    
    PrintWriter out = response.getWriter();

    	String fname=request.getParameter("uname");
	String pass=request.getParameter("password");
    
    out.println("<html>");
    out.println("<head><title>Response</title></head>");
    out.println("<body bgcolor=\"#ffffff\">");


    try{
    Class.forName("com.mysql.jdbc.Driver");

    String url = "jdbc:mysql://127.0.0.1/bookstore";

    Connection con=DriverManager.getConnection(url,"root","Alina");

    Statement st=con.createStatement();
    
     String query="Select * from info where username='"+fname+"'and password = '"+pass+"' ";
   
     ResultSet rs = st.executeQuery( query );
   
     if(rs.next()){

		String r = rs.getString("role");  
		if(r == null){ 
			response.sendRedirect ("UserPage.html");
		}
		else{
    			response.sendRedirect ("Adminpage.html");
		}
	 }
    	 else{
    		response.sendError(404,"error");
         }


	out.println("</body></html>");
           st.close();
           con.close();

    }catch(Exception e){

      out.println(e);
    }

  }

}