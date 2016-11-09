package be.com.exemplo.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sistema.devgo.Model.dao.UsuarioDAO;
import sistema.devgo.java.UsuarioSistema;


/**
 * Servlet implementation class autenticador
 */
@WebServlet("/autenticador")
public class autenticador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public autenticador() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession sessao = request.getSession(false);
			if(sessao!=null){
				sessao.invalidate();
				
			}
			response.sendRedirect("login.html");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String ssenha = request.getParameter("senha");
                
                 int ssenha1 = Integer.parseInt(request.getParameter("senha"));
		
		UsuarioSistema usu = new UsuarioSistema();
		usu.setNome(login);
		usu.setSenha(ssenha1);
		
		UsuarioDAO usuDAO = new UsuarioDAO();
		UsuarioSistema usuAutenticado = usuDAO.autenticacao(usu);
		
		if(usuAutenticado != null){
			HttpSession sessao = request.getSession();
			sessao.setAttribute("usuAutenticado", usuAutenticado);
			//sessao.setMaxInactiveInterval(3000);
			
				request.getRequestDispatcher("Acesso.jsp").forward(request, response);
		}else {
			response.sendRedirect("erroLogin.jsp");
		}
	}

}
