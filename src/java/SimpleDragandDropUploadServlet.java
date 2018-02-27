/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Danish
 */
@MultipartConfig
public class SimpleDragandDropUploadServlet extends HttpServlet {

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

            Part part = request.getPart("file");
            String realPath = request.getServletContext().getRealPath("/Uploads");

            String ua = request.getHeader("user-agent");

            System.out.println(ua);

            String file;

            if (ua.contains("Edge")) {
                String name = part.getSubmittedFileName();
                name = name.substring(name.lastIndexOf("\\") + 1);
                file = vmm.FileUploader.savefileonserver(part, realPath, name);
            } else if (ua.contains("Chrome") || ua.contains("Firefox")) {
                file = vmm.FileUploader.savefileonserver(part, realPath);
            } else {
                String name = part.getSubmittedFileName();
                name = name.substring(name.lastIndexOf("\\") + 1);
                file = vmm.FileUploader.savefileonserver(part, realPath, name);
            }
            System.out.println("Uploaded: " + file);
            out.println("success");
        } catch (Exception e) {
            out.println("error " + e.getMessage());
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
