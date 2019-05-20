package com.example.security.controller;

import com.auth0.AuthenticationController;
import com.auth0.IdentityVerificationException;
import com.auth0.Tokens;
import com.auth0.jwt.JWT;
import com.example.security.AppConfig;
import com.example.security.TokenAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Controller
public class AuthController {

    @Autowired
    private AuthenticationController controller;
    @Autowired
    private AppConfig appConfig;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String redirectOnFail;
    private final String redirectOnSuccess;

    public AuthController() {
        this.redirectOnFail = "/login";
        this.redirectOnSuccess = "/orders";
    }

    @GetMapping({"/login"})
    public String login(final HttpServletRequest req) {
        logger.info("Performing login");
        String redirectUri = req.getScheme() + "://" + req.getServerName();
        if ((req.getScheme().equals("http") && req.getServerPort() != 80)
                || (req.getScheme().equals("https") && req.getServerPort() != 443)) {
            redirectUri += ":" + req.getServerPort();
        }
        redirectUri += "/callback";
        String authorizeUrl = controller.buildAuthorizeUrl(req, redirectUri)
                .withAudience(String.format("https://%s/userinfo", appConfig.getDomain()))
                .withScope("openid email profile")
                .build();
        return "redirect:" + authorizeUrl;
    }


    @GetMapping(value = "/callback")
    public void getCallback(final HttpServletRequest req, final HttpServletResponse res) throws IOException {
        logger.info("Performing GET callback");
        try {
            Tokens tokens = controller.handle(req);
            TokenAuthentication tokenAuth = new TokenAuthentication(JWT.decode(tokens.getIdToken()));
            SecurityContextHolder.getContext().setAuthentication(tokenAuth);
            res.sendRedirect(redirectOnSuccess);
        } catch (AuthenticationException | IdentityVerificationException e) {
            e.printStackTrace();
            SecurityContextHolder.clearContext();
            res.sendRedirect(redirectOnFail);
        }
    }

}

