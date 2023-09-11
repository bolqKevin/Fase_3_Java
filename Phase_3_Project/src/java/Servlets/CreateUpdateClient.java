/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import entityLayer.Client;
import businessLogicLayer.BLClients;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author bolqk
 */
public class CreateUpdateClient extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            BLClients logica = new BLClients();
            Client client = new Client();
            String resultado = "El correo ya está registrado";
            String mensaje = "El correo ya está registrado";
            client.setId_client( new String (request.getParameter("txtIdClient").getBytes("ISO-8859-1"), "UTF-8"));
            client.setFirstName(new String (request.getParameter("txtName").getBytes("ISO-8859-1"), "UTF-8"));
            client.setLastName(new String (request.getParameter("txtLastName").getBytes("ISO-8859-1"), "UTF-8"));
            client.setAddress(new String (request.getParameter("txtAddress").getBytes("ISO-8859-1"), "UTF-8"));
            client.setPhone(new String (request.getParameter("txtPhone").getBytes("ISO-8859-1"), "UTF-8"));
            client.setEmail(new String (request.getParameter("txtEmail").getBytes("ISO-8859-1"), "UTF-8"));
            client.setNotes(new String (request.getParameter("txtNotes").getBytes("ISO-8859-1"), "UTF-8"));
            if(logica.verifyClientIdExists(client.getId_client())){
                logica.UpdateClient(client);
                mensaje=logica.getMessage();
            }else{
                if(!logica.verifyClientEmail(client.getId_client()) || logica.verifyClientPhone(client.getId_client())){
                response.sendRedirect("frmListClients.jsp?mensajeEliminarCliente =" + mensaje + "&resultado" + resultado);
            }
                logica.insertClient(client);
                mensaje=logica.getMessage();
            }
            response.sendRedirect("frmListClients.jsp?createUpdateMessage=" + mensaje + "&resultado=" + resultado);
        } catch (Exception ex) {
            out.print(ex.getMessage());
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
