package com.fpmislata.banco_api.presentation.controladores;

import com.fpmislata.banco.business.domain.Rol;
import com.fpmislata.banco.business.domain.Usuario;
import com.fpmislata.banco_api.presentation.security.WebSession;
import java.io.IOException;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = {"/login"})
public class LoginController {

    @RequestMapping(method = RequestMethod.GET)
    public void newSession(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        Usuario usuario = new Usuario("admin", "admin", Rol.ADMINISTRADOR);
//        try {
            
//            httpServletResponse.getWriter().println(httpServletRequest.getRequestURI());            
//        } catch (IOException ex) {
//            throw new RuntimeException("Error al pintar");
//        }
        
        httpServletRequest.getSession().setAttribute("WEB_SESSION", new WebSession(usuario, new Date()));
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
    }
    
}
