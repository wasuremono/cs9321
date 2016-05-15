/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.servlet;

import controllers.dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.User;

/**
 *
 * @author williamding
 */
@WebServlet(name = "Userview", urlPatterns = {"/Userview"})
public class Userview extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Userview</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Userview at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
          String name = request.getParameter("name");
          String password = request.getParameter("password");
          User user= new User();
          user.setUsername(name);
          user.setPassword(password);
          UserDAO userDAO = new UserDAO();
          User user1= userDAO.selectOneUserInfo(name,password);
          request.setAttribute("User", user1.getUsername());
          request.setAttribute("password", user1.getPassword());
          request.setAttribute("firstName", user1.getFirstName());
          request.setAttribute("lastName", user1.getLastName());
          request.setAttribute("nickName", user1.getNickName());
          request.setAttribute("address", user1.getAddress());
          request.setAttribute("CardNumber", user1.getCardNumber());
          request.setAttribute("CardName", user1.getCardName());
          request.setAttribute("CardExpireDate", user1.getCardExpireDate());
          request.setAttribute("CVC", user1.getCvc());
          request.setAttribute("Email", user1.getEmail());
          request.getRequestDispatcher("/userpreview.jsp").forward(request,response);      
    }

   

}
