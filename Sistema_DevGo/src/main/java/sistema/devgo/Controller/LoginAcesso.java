/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.devgo.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sistema.devgo.Model.dao.UsuarioDAO;
import sistema.devgo.java.UsuarioSistema;

/**
 *
 * @author natan
 */
@WebServlet(name = "LoginAcesso", urlPatterns = {"/Login"})
public class LoginAcesso extends HttpServlet {

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
            out.println("<title>Servlet LoginAcesso</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginAcesso at " + request.getContextPath() + "</h1>");
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

            RequestDispatcher dispatcher
                    = request.getRequestDispatcher("/Login.jsp");
            dispatcher.forward(request, response);
        
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

        String login = request.getParameter("Login");
        String senha = request.getParameter("Senha");
        
       int senha1 = Integer.parseInt(request.getParameter("Senha"));

        // Validar nome de usuario e senha
        UsuarioSistema usuario = new UsuarioSistema();
        usuario.setNome(login);
        usuario.setSenha(senha1);
        
        UsuarioDAO usuDAO = new UsuarioDAO();
        
            UsuarioSistema usuAutenticado;
            usuAutenticado = usuDAO.autenticacao(usuario);
            System.out.println("Aceito");

                   if (usuAutenticado!=null){
            request.getRequestDispatcher("Acesso.jsp").forward(request, response);
        }
        else{
            response.sendRedirect("/WEB-INF/erroLogin.jsp");
        }
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
