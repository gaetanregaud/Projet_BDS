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


public class AuthenVpFilter implements Filter {


    public AuthenVpFilter() {
        // TODO Auto-generated constructor stub
    }


	public void destroy() {
		// TODO Auto-generated method stub
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
	    String identifiant = (String) httpRequest.getSession().getAttribute("user_id");
	    String compte = (String) httpRequest.getSession().getAttribute("user_type");
	    
	    if(identifiant == null || !compte.equals("vp")) {
	        System.out.println("Il faut ��tre connect�� ETUDIANT pour acc��der �� cette page !");
	        HttpServletResponse httpResponse = (HttpServletResponse) response;
	        httpResponse.sendRedirect("accueil");
	        return;
	    }
		chain.doFilter(request, response);
	}


	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
