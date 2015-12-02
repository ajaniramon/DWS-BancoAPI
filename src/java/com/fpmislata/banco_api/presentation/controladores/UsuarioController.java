package com.fpmislata.banco_api.presentation.controladores;

import com.fpmislata.banco.business.domain.Usuario;
import com.fpmislata.banco.business.service.UsuarioService;
import com.fpmislata.banco.persistence.core.BusinessException;
import com.fpmislata.banco_api.presentation.json.JsonTransformer;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UsuarioController {
    
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    JsonTransformer jsonTransformer;
    
    @RequestMapping(value = {"/usuario"}, method = RequestMethod.GET, produces = "application/json")
    public void findAll(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        List<Usuario> usuarios = usuarioService.findAll();
        try {
            httpServletResponse.getWriter().println(jsonTransformer.toJson(usuarios));
            httpServletResponse.setStatus(200);
        } catch (IOException ex) {
            httpServletResponse.setStatus(500);
        }
        
    }
    
    @RequestMapping(value = {"/usuario/{id}"}, method = RequestMethod.GET, produces = "application/json")
    public void get(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable int id) {
        try {
            httpServletResponse.getWriter().println(jsonTransformer.toJson(usuarioService.get(id)));
            httpServletResponse.setStatus(200);
        } catch (IOException ex) {
            httpServletResponse.setStatus(500);
        }        
    }

    @RequestMapping(value = {"/usuario"}, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public void insert(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada) throws BusinessException {
        try {
            httpServletResponse.getWriter().println(jsonTransformer.toJson(usuarioService.insert(jsonTransformer.fromJson(jsonEntrada, Usuario.class))));
            httpServletResponse.setStatus(200);
        } catch (IOException ex) {
            httpServletResponse.setStatus(500);
        }
    }
    @RequestMapping(value = {"/usuario"}, method= RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public void update(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada){
        try {
            httpServletResponse.getWriter().println(jsonTransformer.toJson(usuarioService.update(jsonTransformer.fromJson(jsonEntrada, Usuario.class))));
            httpServletResponse.setStatus(200);
        } catch (IOException e) {
            httpServletResponse.setStatus(500);
        }
    }
    @RequestMapping( value = {"/usuario/{id}"}, method = RequestMethod.DELETE)
    public void delete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable int id){
        usuarioService.delete(id);
        httpServletResponse.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
}
