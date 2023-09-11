/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import businessLogicLayer.BLPets;
import entityLayer.Pet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author bolqk
 */
public class DeletePet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            BLPets logic = new BLPets();
            int petId = Integer.parseInt(request.getParameter("petId"));
            Pet pet = new Pet();
            pet.setPetId(petId);
            
            logic.deletePet(pet);
            String mensaje = "Mascota eliminada de manera satisfactoria";
            response.sendRedirect("frmListPets.jsp?mensajeEliminarMascota=" + mensaje + "&resultado=" + mensaje);
        } catch (NumberFormatException ex) {
            // Manejo de errores si el valor de petId no es un número válido
            out.print("Error: El ID de mascota debe ser un número entero válido.");
        } catch (Exception ex) {
            String mensaje = "La mascota tiene Registro medico o citas pendientes no puede ser eliminada";
            response.sendRedirect("frmListPets.jsp?mensajeEliminarMascota=" + mensaje + "&resultado=" + mensaje);
        }
    }

}
