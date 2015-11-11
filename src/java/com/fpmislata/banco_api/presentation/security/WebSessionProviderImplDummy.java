package com.fpmislata.banco_api.presentation.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class WebSessionProviderImplDummy implements WebSessionProvider {

    @Override
    public WebSession getWebSession(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        HttpSession httpSession = httpServletRequest.getSession();
        return (WebSession) httpSession.getAttribute("WEB_SESSION");
    }

}
