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
public class buyTravel extends HttpServlet {

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
        
        String dateda = request.getParameter("dateda"), datea = request.getParameter("datea") ;
        if(dateda.equals("") || dateda == null) dateda = "2010-01-01";
        if(datea.equals("") || datea == null) datea = "2017-01-01";
        
        String query = "select * "
                + "from clienti c "
                + "where c.nome like '" + request.getParameter("name") + "%' "
                + "and c.cognome like '" + request.getParameter("surname") + "%' ";
        
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
                //real query with the first client
                query = "select * "
                    + "from (((clienti c inner join acquisti a on a.codcliente=c.codcliente) "
                    + "inner join pacchetti p on a.codpacchetto=p.codpacchetto) "
                    + "inner join nazioni n on n.codNazioni=p.codNazioni) "
                    + "inner join touroperator t on t.codorg=p.codorg "
                    + "where c.nome like '" + r.getString("nome") + "%' "
                    + "and c.cognome like '" + r.getString("cognome") + "%' "
                    + "and a.dataacquisti between '" + dateda + "' and '" + datea + "'";

                //query
                r = s.executeQuery(query);

                while(r.next()){
                    header = "<div class=\"row\">\n" +
                            "<div class=\"jumbotron text-center\"><h2>" + r.getString("c.nome") + " " + r.getString("c.cognome") + "</h2></div>" +
                            "</div>" +
                                    "<div class='row'>"
                                        + "<div class='alert alert-warning'>"
                                            + "<div class='col-sm-2'>Data Acquisto</div>"
                                            + "<div class='col-sm-2'>Data Partenza</div>"
                                            + "<div class='col-sm-2'>Destinazione</div>"
                                            + "<div class='col-sm-2'>Tour operator</div>"
                                            + "<div class='col-sm-2'>Prezzo</div>"
                                        + "</div>"
                                + "</div>";
                    result += "<div class='row'>"
                                        + "<div class='alert alert-info'>"
                                            + "<div class='col-sm-2'>" + r.getDate("dataacquisti") + "</div>"
                                            + "<div class='col-sm-2'>" +  r.getDate("partenza") + "</div>"
                                            + "<div class='col-sm-2'>" +  r.getString("denom") + "</div>"
                                            + "<div class='col-sm-2'>" +  r.getString("t.nome") + "</div>"
                                            + "<div class='col-sm-2'>" +  r.getInt("prezzo") + "</div>"
                                        + "</div>"
                                + "</div>";
                    
                    totalPrice += r.getInt("prezzo");
                }

                result= header + result;
            }
        } catch(ClassNotFoundException | SQLException e){
            System.out.println(e); //for debug purpose
            result += e;
            result += "<br>" + query;
        }
        
        
        try (PrintWriter out = response.getWriter()) {
            out.println("<head>\n" +
                        "<title>Clienti</title>" +
                        "<meta charset=\"UTF-8\">" +
                        "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                        "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">" +
                        "</head>");
            out.println("<body>");
            
            out.println("<div class='container'>");
            
            //show result query
            out.println(result);
            
            //cshow total price
            if(!result.equals(""))
                out.println("<div class='row'>"
                    + "<div class='alert alert-warning'>"
                    + "Spese totali: " + totalPrice
                    + "</div></div>");
            
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
