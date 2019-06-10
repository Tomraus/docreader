package pl.stasko.tomasz.docreader.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.SAXException;

import javax.xml.transform.TransformerException;
import java.io.FileNotFoundException;
import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(value = {MultipartException.class})
    public ModelAndView multipartExceptionHandler(MultipartException e) {
        log.error("File size exceeds limit!", e);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        mav.getModel().put("message", "File size exceeds limit!");
        return mav;
    }

    @ExceptionHandler(value = {FileNotFoundException.class})
    public ModelAndView fileNotFoundExceptionHandler(FileNotFoundException e) {
        log.error("File hasn't been sent ", e);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        mav.getModel().put("message", "File hasn't been sent");
        return mav;
    }

    @ExceptionHandler(value = {IOException.class})
    public ModelAndView ioExceptionHandler(IOException e) {
        log.error("File can not be openned ", e);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        mav.getModel().put("message", "File can not be openned ");
        return mav;
    }

    @ExceptionHandler(value = {SAXException.class})
    public ModelAndView saxExceptionHandler(SAXException e) {
        log.error("Invalid file format ", e);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        mav.getModel().put("message", "Invalid file format");
        return mav;
    }

    @ExceptionHandler(value = {TransformerException.class})
    public ModelAndView transofrmerExceptionHandler(TransformerException e) {
        log.error("Invalid file format ", e);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        mav.getModel().put("message", "Invalid file format");
        return mav;
    }

    @ExceptionHandler(value = {Exception.class})
    public ModelAndView exceptionHandler(Exception e) {
        log.error("Internal server error ", e);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        mav.getModel().put("message", "Internal server error. Please contact administrator");
        return mav;
    }

}