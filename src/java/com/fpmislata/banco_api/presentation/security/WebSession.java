package com.fpmislata.banco_api.presentation.security;

import com.fpmislata.banco.business.domain.Usuario;
import java.util.Date;


public class WebSession {
    Usuario usuario;
    Date fecha;

    public WebSession() {
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
}
