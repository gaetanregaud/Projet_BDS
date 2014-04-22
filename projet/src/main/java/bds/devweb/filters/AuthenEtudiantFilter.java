package bds.devweb.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenEtudiantFilter implements Filter{
	
	public AuthenEtudiantFilter() {
    }
	
	public void destroy() {}


	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
	    String identifiant = (String) httpRequest.getSession().getAttribute("user_id");
	    String compte = (String) httpRequest.getSession().getAttribute("user_type");
	    
	    if(identifiant == null || !compte.equals("etudiant")) {
	        System.out.println("Il faut ��tre connect�� ETUDIANT pour acc��der �� cette page !");
	        HttpServletResponse httpResponse = (HttpServletResponse) response;
	        httpResponse.sendRedirect("accueil");
	        return;
	    }
	    chain.doFilter(request, response);
		
	}


	public void init(FilterConfig request) throws ServletException {}

}
