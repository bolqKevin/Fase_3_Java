/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entityLayer.Client;
import businessLogicLayer.BLClients;
/**
 *
 * @author Kevin Bolaños.
 */
@WebServlet(name = "DeleteClient", urlPatterns = {"/DeleteClient"})
public class DeleteClient extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            try {
                BLClients logic = new BLClients();
                String id = request.getParameter("idClient").replace("\"", "");
                String idClient = id;
                Client client = new Client();
                client.setId_client(idClient);
                logic.MarkClientForDeletion(client.getId_client());
                String mensaje = "Cliente Eliminado de manera satisfactoria";
                response.sendRedirect("frmListClients.jsp?mensajeEliminarCliente=" + mensaje + "&resultado=" + mensaje);
            }catch(Exception ex){
                String mensaje = "El cliente tiene facturas o citas y no puede ser eliminado";
                response.sendRedirect("frmListClients.jsp?mensajeEliminarCliente=" + mensaje + "&resultado=" + mensaje);
            }
        }
}
