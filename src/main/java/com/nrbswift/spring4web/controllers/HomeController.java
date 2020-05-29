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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    @RequestMapping(value = "/")
    public String showHome(Model model) {
        model.addAttribute("name", "Shofiullah babor");
        return "home";
    }

    @RequestMapping(value = "/file",  method = RequestMethod.GET)
    public String showFileUploadForm(Model model) {

        return "fileForm";
    }

    @RequestMapping(value = "/file",  method = RequestMethod.POST)
    public String postUploadForm(@RequestParam("mypic") MultipartFile file, RedirectAttributes redirectAttributes) {

        String UPLOADED_FOLDER = WebContextHolderUtils.get().getServletContext().getRealPath("/") + "upload\\";

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        try {
            byte[] bytes = file.getBytes();
            if (!(new File(UPLOADED_FOLDER).exists())) {
                new File(UPLOADED_FOLDER).mkdir();
            }
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/file";
    }
}
