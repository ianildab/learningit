/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author PujA
 */
@WebServlet(name = "aplicant_registeration", urlPatterns = {"/aplicant_registeration"})
public class aplicant_registeration extends HttpServlet {

    
    @Override
    public void service(HttpServletRequest req,HttpServletResponse res)
            throws ServletException,IOException
    {
 		PrintWriter out=res.getWriter();
 		res.setContentType("text/html");
 		
 		 
 		try
        {
         String EmailAddress=req.getParameter("Emailadd");
         
         String Firstname=req.getParameter("fn");
         
         String Middlename=req.getParameter("Mn");
         String Lastname=req.getParameter("ln");
         String s11=req.getParameter("age");
         int age=Integer.parseInt(s11);
         String Add1=req.getParameter("add1");
         String Add2=req.getParameter("add2");
         String City =req.getParameter("city");
         String Zipcode=req.getParameter("Zip");
         String State=req.getParameter("ste");
         String s9=req.getParameter("phoneno");
         int Phoneno=Integer.parseInt(s9);
         String Mobile=req.getParameter("mobile");
         
         String db=req.getParameter("db");
         String mon=req.getParameter("mon");
         String yr=req.getParameter("year");
         String t1=db.concat("-");
         String t2=t1.concat(mon);
         String t3=t2.concat("-");
         String t4=t3.concat(yr);
         
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
         Connection connection = DriverManager.getConnection ("jdbc:odbc:HrDsn", "Hrweb", "hr");
         System.out.println("connection established");
         Statement statement = connection.createStatement();
         
         
         String s1="insert into applicant values(s.NEXTVAL,'"+EmailAddress+"','"+Firstname+"','"+Middlename+"','"+Lastname+"',"+age+",'"+t4+"','"+Add1+"','"+Add2+"','"+City+"','"+Zipcode+"','"+State+"','"+Phoneno+"',"+Mobile+",'"+null+"')";
         if((statement.executeUpdate(s1))==1)
         {
         	out.println("<font color='green'><h2>Registered Successfully</h2></font>");
         }
         
         String s5="select Regid from applicant where firstname='"+Firstname+"' and lastname='"+Lastname+"'";
         ResultSet rs3=statement.executeQuery(s5);
         String uid="";
         while(rs3.next())
         {
         	uid=rs3.getString(1);
         	String s2="insert into login1 values('"+uid+"',p.NEXTVAL)";
         	ResultSet rs=statement.executeQuery(s2);
         }
         
         Cookie c=new Cookie("regid",uid);
         res.addCookie(c);
         
         out.println("<font color='green'><h2>REGID</h2></font>");
         
         String s6="select Regid from applicant where firstname='"+Firstname+"' and lastname='"+Lastname+"'";
         ResultSet rs4=statement.executeQuery(s6);
         while(rs4.next())
         {
         	out.println(rs4.getString(1));
         }
         
         out.println("<font color='green'><h2>Password</h2></font>");
         String s7="select password from login1 where userid='"+uid+"'";
         ResultSet rs5=statement.executeQuery(s7);
         while(rs5.next())
         {
        	 out.println(rs5.getString(1));
         }
         out.println("<a href='./pg_files/resume.html'>Upload Resume</a>");
        }
        catch(Exception e)
	    {
           System.out.println(e); 
        }
   }
}
