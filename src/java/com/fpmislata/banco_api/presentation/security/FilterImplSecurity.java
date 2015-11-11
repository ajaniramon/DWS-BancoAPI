package com.fpmislata.banco_api.presentation.security;

import com.fpmislata.banco.business.domain.Usuario;
import com.fpmislata.banco.security.Authorization;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class FilterImplSecurity implements Filter {

    @Autowired
    WebSessionProvider webSessionProvider;
    @Autowired
    Authorization authorization;

    FilterConfig filterConfig = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(httpServletRequest.getServletContext());
        AutowireCapableBeanFactory autowireCapableBeanFactory = webApplicationContext.getAutowireCapableBeanFactory();
        autowireCapableBeanFactory.autowireBean(this);
        WebSession webSession = webSessionProvider.getWebSession(httpServletRequest, httpServletResponse);
        Usuario usuario = null;
        if (webSession != null) {
            usuario = webSession.getUsuario();
        }
        if (authorization.isAuthorizedUrl(usuario, httpServletRequest.getRequestURI(), httpServletRequest.getMethod())) {
            chain.doFilter(servletRequest, servletResponse);
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            httpServletResponse.getWriter().println("<h1> LOGUEATE PRIMERO QUE NO TIENES PERMISO PARA VER ESTO, TRUHÁN. </h1>");
        }

    }

    @Override
    public void destroy() {

    }

}
