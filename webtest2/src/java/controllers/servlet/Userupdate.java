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
@WebServlet(name = "Userupdate", urlPatterns = {"/Userupdate"})
public class Userupdate extends HttpServlet {

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
            out.println("<title>Servlet Userupdate</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Userupdate at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
        String name = request.getParameter("name");
        String password=request.getParameter("password");
        UserDAO userDao=new UserDAO();
        User user = userDao.selectOneUserInfo(name,password);
          request.setAttribute("User", user.getUsername());
          request.setAttribute("password", user.getPassword());
          request.setAttribute("firstName", user.getFirstName());
          request.setAttribute("lastName", user.getLastName());
          request.setAttribute("nickName", user.getNickName());
          request.setAttribute("address", user.getAddress());
          request.setAttribute("CardNumber", user.getCardNumber());
          request.setAttribute("CardName", user.getCardName());
          request.setAttribute("CardExpireDate", user.getCardExpireDate());
          request.setAttribute("CVC", user.getCvc());
          request.setAttribute("Email", user.getEmail());
          request.getRequestDispatcher("/UserUpdate.jsp").forward(request,response); 
        
    }


}
