/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pirasalbe
 */
public class destination extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String destination = request.getParameter("denom");
        
        String query = "select * "
                + "from nazioni n "
                + "where denom = '" + destination + "' ";
        
        Connection c = null;
        Statement s = null;
        ResultSet r = null;
        String result = "", header ="";
        int totalPrice = 0;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/viaggi", "root","");
            s = c.createStatement();
            
            //query
            r = s.executeQuery(query);
            
            if(r.next()) {
                header = "<div class=\"row\">\n" +
                        "<div class=\"jumbotron text-center\"><h2>" + destination + "</h2></div>" +
                        "</div>" +
                                "<div class='row'>"
                                    + "<div class='alert alert-success'>"
                                        + "<div class='col-sm-3'>Moneta</div>"
                                        + "<div class='col-sm-3'>Clima</div>"
                                        + "<div class='col-sm-3'>Costo Visto</div>"
                                        + "<div class='col-sm-3'>Rilascio visto</div>"
                                    + "</div>"
                            + "</div>";
                result += "<div class='row'>"
                                    + "<div class='alert alert-info'>"
                                        + "<div class='col-sm-3'>" + r.getString("moneta") + "</div>"
                                        + "<div class='col-sm-3'>" +  r.getString("clima") + "</div>"
                                        + "<div class='col-sm-3'>" +  r.getFloat("costovisto") + "</div>"
                                        + "<div class='col-sm-3'>" +  r.getString("rilasciovisto") + "</div>"
                                    + "</div>"
                            + "</div>";
            }

            result = header + result;
        } catch(ClassNotFoundException | SQLException e){
            System.out.println(e); //for debug purpose
            result += e;
            result += "<br>" + query;
        }
        
        
        try (PrintWriter out = response.getWriter()) {
            out.println("<head>\n" +
                        "<title>" + destination +"</title>" +
                        "<meta charset=\"UTF-8\">" +
                        "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                        "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">" +
                        "</head>");
            out.println("<body>");
            
            out.println("<div class='container'>");
            
            //show result query
            out.println(result);
            
            //come back home
            out.println("<div class='row'>"
                    + "<input type='button' class='btn btn-default' value='Home' onClick=\"javascript:location.href='index.html'\" />"
                    + "</div>");
            
            out.println("</div>");
            
            out.println("</body>");
            out.println("</html>");
        } finally {
            try {
            //free memory
                if (r!=null) r.close();
                if(s!=null) s.close();
                if(c !=null) c.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
