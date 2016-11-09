/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.devgo.Controller;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author alessandra.vspontes
 */
@WebFilter(filterName = "filtroll", urlPatterns = {"/*"})
public class filtroll implements Filter {
    
    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    
    public filtroll() {
    }    
  
     
public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//HttpSession sessao = request.getSession();
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		
		String url = httpServletRequest .getRequestURI(); // pegar todas urls das paginas, vou filtrar todas as paginas, desde a raiz
		
		HttpSession sessao = httpServletRequest .getSession(); // capturando a sessão
		
		if (sessao.getAttribute("usuAutenticado")!=null || url.lastIndexOf("login.jsp")>-1 ||
		url.lastIndexOf("autenticador") >-1 ){
		chain.doFilter(request, response); // se acessar permite acesso a todas as paginas
		}else{
		((HttpServletResponse) response).sendRedirect("login.jsp"); // senão volta para o login
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

    @Override
    public void destroy() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

}

    /**
     * Return the filter configuration object for this filter.
     */
    