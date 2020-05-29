package com.nrbswift.spring4web.controllers;

import com.itextpdf.html2pdf.HtmlConverter;
import com.nrbswift.spring4web.dao.User;
import com.nrbswift.spring4web.dao.UserDAO;
import com.nrbswift.spring4web.utils.WebContextHolderUtils;
import org.hibernate.dialect.function.TemplateRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Controller
public class HomeController {

    @Autowired
    UserDAO userDao;

    @Autowired
    ViewResolver viewResolver;

    @RequestMapping(value = "/pdf",  produces = "application/pdf")
    public ResponseEntity makePdf(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

        model.addAttribute("name", "Shofiullah babor");

        View view = viewResolver.resolveViewName("home", Locale.getDefault());

        ContentCachingResponseWrapper mockResponse = new ContentCachingResponseWrapper(response);
        view.render(model.asMap(), request, mockResponse);

        byte[] responseArray = mockResponse.getContentAsByteArray();
        String responseStr = new String(responseArray, mockResponse.getCharacterEncoding());

        System.out.println(responseStr);

        final String DEST = "target\\FA-2018-09-04-0001.pdf";

        try {
            HtmlConverter.convertToPdf(responseStr, new FileOutputStream("babor.pdf"));
            return ResponseEntity
                    .ok()
                    .body(new InputStreamResource(new FileInputStream("babor.pdf")));

        } catch (IOException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE);
        }
        //return "home";
    }

    @RequestMapping(value = "/",  produces = "application/pdf")
    public String showHome(Model model) {
        model.addAttribute("name", "Shofiullah babor");
        return "home";
    }
}
