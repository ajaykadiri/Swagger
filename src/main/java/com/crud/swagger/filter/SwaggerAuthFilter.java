package com.crud.swagger.filter;


import com.crud.swagger.model.Person;
import com.crud.swagger.repositories.PersonRepo;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;


public class SwaggerAuthFilter implements Filter {

    private static final String USERNAME = "admin";
    private static final String PASSWD = "admin";
    private final PersonRepo prepo;

    public SwaggerAuthFilter(PersonRepo prepo) {
        this.prepo = prepo;
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        System.out.println();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        String authHeader = httpRequest.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Basic ")) {
            String credentials = new String(Base64.getDecoder().decode(authHeader.substring(6)), StandardCharsets.UTF_8);
            String[] usernamePassword = credentials.split(":");
            if (usernamePassword.length >= 2) {
                String username = usernamePassword[0];
                String password = usernamePassword[1];
                Optional<Person> optionalUser = prepo.findByUname(username);

                if(optionalUser.isPresent() && optionalUser.get().getPasswd().equals(password) || username.equals(USERNAME) && password.equals(PASSWD)){
                    System.out.println("["+username+"]"+" Accessing the data");
                    httpRequest.setAttribute("username", username);
                    filterChain.doFilter(httpRequest, httpResponse);
                    return ;
                }

            }

        }

        httpResponse.setHeader("WWW-Authenticate", "Basic realm=\"Restricted Area\"");
        httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }


    @Override
    public void destroy() {
        Filter.super.destroy();
    }

}