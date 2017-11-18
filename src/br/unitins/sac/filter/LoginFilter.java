package br.unitins.sac.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.unitins.sac.model.Usuario;

public class LoginFilter implements Filter {

	public void init(FilterConfig config) throws ServletException {
		System.out.println("\n\nFiltro iniciado\n\n");
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpSession session = ((HttpServletRequest) req).getSession();

		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

		if (usuario == null) {
			// redireciona para a pagina de login
			((HttpServletResponse) res).sendRedirect("../login.xhtml");
		} else {
			// continua o fluxo
			chain.doFilter(req, res);
		}
		
//		String nomeProjeto = ((HttpServletRequest) req).getContextPath();
//		String host = ((HttpServletRequest) req).getLocalName();
//		String url = ((HttpServletRequest) req).getRequestURL().toString();
		

	}

	public void destroy() {

	}

}
