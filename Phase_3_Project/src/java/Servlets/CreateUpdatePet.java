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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author bolqk
 */
public class CreateUpdatePet extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        try {
            BLPets logica = new BLPets();
            Pet pet = new Pet();
            String resultado = "El ID de mascota ya está registrado";
            String mensaje = "El ID de mascota ya está registrado";
            
            // Convierte el valor de txtPetId a un entero
            int petId = Integer.parseInt(request.getParameter("txtPetId"));
            
            pet.setPetId(petId);
            pet.setPetName(new String(request.getParameter("txtPetName").getBytes("ISO-8859-1"), "UTF-8"));
            pet.setSpecies(new String(request.getParameter("txtSpecies").getBytes("ISO-8859-1"), "UTF-8"));
            pet.setBreed(new String(request.getParameter("txtBreed").getBytes("ISO-8859-1"), "UTF-8"));
            pet.setBirthDate(new String(request.getParameter("txtBirthDate").getBytes("ISO-8859-1"), "UTF-8"));
            pet.setGender(new String(request.getParameter("txtGender").getBytes("ISO-8859-1"), "UTF-8"));
            pet.setClientId(new String(request.getParameter("txtClientId").getBytes("ISO-8859-1"), "UTF-8"));
            
            if (logica.verifyPetIdExists(pet)) {
                logica.updatePet(pet);
                mensaje = logica.getMensaje();
            } else {
                logica.insertPet(pet);
                mensaje = logica.getMensaje();
            }
            response.sendRedirect("frmListPets.jsp?createUpdateMessage=" + mensaje + "&resultado=" + resultado);
        } catch (NumberFormatException ex) {
            out.print("Error: El ID de mascota debe ser un número entero válido.");
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
