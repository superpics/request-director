package org.iconsider.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Created by Jesse on 2017-10-24.
 */
@RestController
@RequestMapping("/directTo")
public class RequestController {
    private static final Logger LOG = LoggerFactory.getLogger(RequestController.class);
    @RequestMapping(method = RequestMethod.GET)
    public void direct(HttpServletRequest request, HttpServletResponse response) {
        String serverName = request.getServerName();
        String[] domainInfo = serverName.split("\\.");
        if(null != domainInfo && domainInfo.length > 1) {
            try {
                String subDomain = domainInfo[0];
                //资源无需加.properties后缀名
                ResourceBundle resource = ResourceBundle.getBundle("urls");
                String redirectUrl = resource.getString(subDomain);
                response.sendRedirect(redirectUrl);
                LOG.info("{} => {}", serverName, redirectUrl);
            } catch (Exception e) {
                redirectTo404(response);
                LOG.error("no url match domain {}", serverName);
            }
        } else {
            redirectTo404(response);
            LOG.error("domain {}'s format is wrong", serverName);
        }
    }

    /**
     * 重定向到404页面
     * @param response
     */
    private void redirectTo404(HttpServletResponse response) {
        try {
            response.sendError(404);
        } catch (IOException e1) {
            System.out.println(String.format("sendError exception"));
        }
    }
}
