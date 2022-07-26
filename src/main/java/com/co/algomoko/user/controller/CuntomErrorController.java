package com.co.algomoko.user.controller;

import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller

public class CuntomErrorController implements ErrorController{
	  //protected Logger log = LoggerFactory.getLogger(this.getClass());
	  private final String DEFAULT_ERROR_PATH = "error/error";
	  
	  
	  public String getErrorPath() {
	        return DEFAULT_ERROR_PATH;
	    }
	  
	  @RequestMapping("/error")
	    public String handleError(HttpServletRequest request, Model model) {
	        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
	        HttpStatus httpStatus = HttpStatus.valueOf(Integer.valueOf(status.toString()));
	        //log.info("httpStatus : "+ httpStatus.toString());
	        model.addAttribute("code", status.toString());
	        model.addAttribute("msg", httpStatus.getReasonPhrase());
	        model.addAttribute("timestamp", new Date());
	        return "error/error";
	    }

//	  @RequestMapping(value = "/denied", method= RequestMethod.GET)
//	  public String denied(Model model) {
//		  model.addAttribute("errorCode", "403");
//			model.addAttribute("msg","Forbidden");
//		  
//		  return getErrorPath() + "error/error";
//	  }
	  
	  
//	private String errorHandleImpl(HttpServletRequest request, Model model) {
//		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//		
//		HttpStatus httpstatus = HttpStatus.valueOf(Integer.valueOf(status.toString()));
//		
//		model.addAttribute("errorCode",status.toString());
//		model.addAttribute("msg",httpstatus.getReasonPhrase());
//		return getErrorPath() + "error/error";
//	}
}
