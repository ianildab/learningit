/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author PujA
 */
@WebServlet(name = "emp_update", urlPatterns = {"/emp_update"})
public class emp_update extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
           String fname = request.getParameter("firstName");
            String lname = request.getParameter("lastName");
            String gen = request.getParameter("gender");
            String email = request.getParameter("email");
            String hno = request.getParameter("houseNo");
            String street = request.getParameter("street");
            String contact = request.getParameter("phoneNo");
            String uname = request.getParameter("userName");
            String pwd = request.getParameter("password");
            String cpwd = request.getParameter("conformpassword");
            String id1 = request.getParameter("eid1");
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            //Connection connection = DriverManager.getConnection("jdbc:odbc:abc", "system", "system");
                
           // HttpSession session=req.getSession(true);
	//	 String id1=(String)session.getAttribute("eid1");
		 System.out.println(id1);
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
         Connection connection = DriverManager.getConnection ("jdbc:odbc:abc", "system", "system");
         
         System.out.println("connection established");
         
         
        // String s1="update applicant set fname='"+firstName+"',lname='"+lastName+"',gen='"+gender+"',email='"+email+"',hno='"+houseNo+"',street='"+street+"',contact='"+phoneNo+"',uname='"+userName+"' where id1='"+eid1+"'";
 String s2="update HRMS_EMPLOYEE2 set fname='"+fname+"',lname='"+lname+"',gender='"+gen+"',email='"+email+"',houseNo='"+hno+"',street='"+street+"',phoneNo='"+contact+"',userName='"+uname+"',password='"+pwd+"',conformpassword='"+cpwd+"' where eid='"+id1+"'"; 
 PreparedStatement stmt = connection.prepareStatement(s2);          
 //System.out.println("connection established");
int n=stmt.executeUpdate();
            if(n==1)
         {
         	
             out.println("<html><center><br><br><br><br>");
               out.println("<body background=image298.jpg>");
         	out.println("<font color='green'><h2>Your profile has been updated Successfully</h2></font>");
         	
                out.println("</center></body></html>");
         } 
             
         }
         
     
catch(Exception e)
         {
             out.print(e);
         }
      
         
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(emp_update.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(emp_update.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(emp_update.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(emp_update.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
