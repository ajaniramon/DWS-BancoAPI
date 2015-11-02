package com.fpmislata.banco_api.presentation.database;

import com.fpmislata.banco.business.domain.EntidadBancaria;
import com.fpmislata.banco_api.presentation.json.JsonTransformer;
import java.util.Date;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class ServletContextListenerImpl implements ServletContextListener {

    @Autowired
    JsonTransformer jsonTransformer;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("INICIADO CONTEXTO.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("PARADO CONTEXTO.");
        
    }

}
