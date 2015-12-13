/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//package src;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javazoom.upload.MultipartFormDataRequest;
import java.util.Hashtable;
import java.util.Enumeration;
import javazoom.upload.UploadFile;
import javazoom.upload.UploadBean;

/**
 *
 * @author Fayaz
 */
public class Applicant_Temp extends HttpServlet {

    @Override
 public void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
 	{
 	 
 	 
 		PrintWriter out=res.getWriter();
 		res.setContentType("text/html");
 		
 		 
 	try
        {
         String EmailAddress=req.getParameter("Emailadd");
         //System.out.println(EmailAddress);
         
        // System.out.println(req.getParameter("Emailadd"));
         //System.out.println(req.getParameter("fn"));
         //String 
         String Firstname=req.getParameter("fn");
         //System.out.println("2");
         String Middlename=req.getParameter("Mn");
         System.out.println(Middlename);
         String Lastname=req.getParameter("ln");
         System.out.println(Lastname);
        
         //String sa = req.getParameter("age");
        // int age=Integer.parseInt(sa);
         //System.out.println(age);
         int age = 22;
         String Add1=req.getParameter("add1");
         
         String Add2=req.getParameter("add2");
         
         String City =req.getParameter("city");
         
         String Zipcode=req.getParameter("Zip");
         System.out.println("7");
         String State=req.getParameter("ste");
         
         String s9=req.getParameter("phoneno");
         int Phoneno=Integer.parseInt(s9);
         System.out.println("8");
         String Mob=req.getParameter("mobile");
         int  Mobile = Integer.parseInt(Mob);
         System.out.println("9");
         String Resume=req.getParameter("resume");
         
         String db=req.getParameter("db");
         String mon=req.getParameter("mon");
         String yr=req.getParameter("year");
         String t1=db.concat("-");
         String t2=t1.concat(mon);
         String t3=t2.concat("-");
         String t4=t3.concat(yr);
         System.out.println("10");
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
         Connection connection = DriverManager.getConnection ("jdbc:odbc:HrDsn", "Hrweb", "hr");
         System.out.println("connection established");
         Statement statement = connection.createStatement();
         
         UploadBean upb = new UploadBean();
   	 out.println("<br>Testing Line 1");
   	 String str=""+getServletContext().getRealPath("/")+"contents";
   	 out.println("<br>"+str);
   	 upb.setFolderstore(str);
   	 out.println("<br>T line 2");
	 upb.setOverwrite(false);
   	 out.println("<HEAD><TITLE> " + "File Upload  " +
                "</TITLE></HEAD><BODY>");
    	Hashtable ht;
    	UploadFile curfile;

/* create a parser for parsing form data */
	    MultipartFormDataRequest nreq = new MultipartFormDataRequest(req);
            //out.println("hhh");
        ht = nreq.getFiles();
        //out.println(ht.size());
        Enumeration files = ht.elements();
    	String filnam=null;
    	while(files.hasMoreElements())
    	{
			curfile = (UploadFile) files.nextElement();
           	filnam=curfile.getFileName() ;
		   	out.println(filnam +"<BR>");
		   	out.println(curfile.getFileName() +"<BR>");
			upb.store(nreq);		
         }
         String s1="insert into applicant values(s.NEXTVAL,'"+EmailAddress+"','"+Firstname+"','"+Middlename+"','"+Lastname+"',"+age+",'"+t4+"','"+Add1+"','"+Add2+"','"+City+"','"+Zipcode+"','"+State+"',"+Phoneno+","+Mobile+",'"+filnam+"')";
         
         out.println(s1);
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
        }
        catch(Exception e)
	    {
           e.printStackTrace(); 
        }
      }

  
}
