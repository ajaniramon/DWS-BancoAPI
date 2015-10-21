package com.fpmislata.banco_api.presentation.controladores;

import com.fpmislata.banco.business.domain.EntidadBancaria;
import com.fpmislata.banco.business.service.EntidadBancariaService;
import com.fpmislata.banco_api.presentation.json.JsonTransformer;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EntidadBancariaController {

    @Autowired
    EntidadBancariaService entidadBancariaService;
    @Autowired
    JsonTransformer jsonTransformer;

    @RequestMapping(value = {"/entidadBancaria"}, method = RequestMethod.GET, produces="application/json")
    public void findAll(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse) {
        List<EntidadBancaria> entidades = entidadBancariaService.findAll();
        String json = jsonTransformer.toJson(entidades);
        try {
            httpServletResponse.getWriter().println(json);
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        } catch (IOException ex) {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }catch(Exception e){
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = {"/entidadBancaria/{id}"}, method = RequestMethod.GET,produces="application/json")
    public void get(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("id") int id) throws IOException{
        try {

            httpServletResponse.getWriter().println(jsonTransformer.toJson(entidadBancariaService.get(id)));
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        } catch (IOException ex) {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }catch(Exception e){
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            httpServletResponse.getWriter().println(e.getMessage());
        }
    }
    @RequestMapping(value = {"/entidadBancaria/{id}"}, method = RequestMethod.DELETE)
    public void delete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("id") int id){
        entidadBancariaService.delete(id);
        httpServletResponse.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
    
    @RequestMapping(value={"/entidadBancaria/"},method = RequestMethod.POST, consumes="application/json",produces="application/json")
    public void insert(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,@org.springframework.web.bind.annotation.RequestBody String JsonEntrada){
        EntidadBancaria entidadBancaria = jsonTransformer.fromJson(JsonEntrada, EntidadBancaria.class);
        entidadBancariaService.insert(entidadBancaria);
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
                try {
            httpServletResponse.getWriter().println(jsonTransformer.toJson(entidadBancaria));
        } catch (IOException ex) {
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
    }
    @RequestMapping(value={"entidadBancaria/"},method=RequestMethod.PUT)
    public void update(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @org.springframework.web.bind.annotation.RequestBody String JsonEntrada){
        EntidadBancaria entidadBancaria = jsonTransformer.fromJson(JsonEntrada, EntidadBancaria.class);
       EntidadBancaria entidadBancariaUpdated= entidadBancariaService.update(entidadBancaria);
        try {
            httpServletResponse.getWriter().println(jsonTransformer.toJson(entidadBancariaUpdated));
        } catch (IOException ex) {
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
       
    }
}