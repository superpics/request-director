package org.iconsider.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Jesse on 2017-10-24.
 */
@RestController
@RequestMapping("/directTo")
public class RequestController {
    @RequestMapping(method = RequestMethod.GET)
    public String direct(HttpServletRequest request, HttpServletResponse response) {
        String str = request.getServerName();
        return String.format("host is: %s", str);
    }
}
