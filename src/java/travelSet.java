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
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author pirasalbe
 */
public class travelSet extends HttpServlet {

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
        
        //session
        HttpSession session = request.getSession();
        int value = 1;
        
        String min = request.getParameter("min"), max = request.getParameter("max") ;
        if(min.equals("") || min == null) min = "0";
        if(max.equals("") || max == null) max = "30";
        
        String query = "select * "
                + "from nazioni "
                + "where denom like '" + request.getParameter("destination") + "%' ";
        
        
        
        Connection c = null;
        Statement s = null;
        ResultSet r = null;
        String result = "";
        String destination = "";
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/viaggi", "root","");
            s = c.createStatement();
            
            //query
            r = s.executeQuery(query);
            
            if(r.next()) {
                destination = r.getString("denom");
                
                //query for the destination
                query = "select * "
                    + "from (pacchetti p inner join nazioni n on n.codNazioni=p.codNazioni)"
                    + "inner join touroperator t on t.codorg=p.codorg "
                    + "where n.denom = '" + destination + "' "
                    + "and p.giorni between " + min + " and " + max
                    + " order by p.giorni, p.prezzo";

                //query
                r = s.executeQuery(query);

                result += "<div class='row'>"
                                        + "<div class='alert alert-warning'>"
                                            + "<div class='col-sm-2'>Descrizione</div>"
                                            + "<div class='col-sm-2'>Giorni</div>"
                                            + "<div class='col-sm-2'>Modalità</div>"
                                            + "<div class='col-sm-2'>Prezzo</div>"
                                            + "<div class='col-sm-2'>Modalità</div>"
                                            + "<div class='col-sm-2'>Tour operator</div>"
                                        + "</div>"
                                + "</div>";

                while(r.next()){
                    result += "<div class='row'>"
                                        + "<div class='alert alert-info'>"
                                            + "<div class='col-sm-2'>" + r.getString("descrizione") + "</div>"
                                            + "<div class='col-sm-2'>" +  r.getInt("giorni") + "</div>"
                                            + "<div class='col-sm-2'>" +  r.getString("modalita") + "</div>"
                                            + "<div class='col-sm-2'>" +  r.getInt("prezzo") + "</div>"
                                            + "<div class='col-sm-2'>" +  r.getString("modalita") + "</div>"
                                            + "<div class='col-sm-2'>" +  r.getString("nome") + "</div>"
                                        + "</div>"
                                + "</div>";
                }

                //views
                if(session.getAttribute(destination)==null){
                    session.setAttribute(destination, value);
                } else{
                    value = (int)session.getAttribute(destination) + 1;
                    session.setAttribute(destination, value);
                }
            } else value = 0;
        } catch(ClassNotFoundException | SQLException e){
            System.out.println(e); //for debug purpose
            result += e;
            result += "<br>" + query;
        }
        
        
        try (PrintWriter out = response.getWriter()) {
            out.println("<head>\n" +
                        "<title>" + destination + "</title>" +
                        "<meta charset=\"UTF-8\">" +
                        "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                        "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">" +
                        "</head>");
            out.println("<body>");
            
            out.println("<div class='container'>");
            
            out.println("<div class=\"row\">\n" +
                        "<div class=\"jumbotron text-center\"><h2>" + destination + "</h2></div>\n" +
                        "</div>");
            
            //show result query
            out.println(result);
            
            //come back home
            out.println("<div class='row'>"
                    + "<input type='button' class='btn btn-default' value='Home' onClick=\"javascript:location.href='index.html'\" />"
                    + "</div>");
            
            //views
            if(value > 0)
            out.println("<br><div class='row'>"
                    + "<div class='alert alert-success'>"
                    + "Views: " + value
                    + "</div></div>");
            
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
