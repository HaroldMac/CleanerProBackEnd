package com.cleanerPro.CleanerPro.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorHandlerController implements ErrorController {

	@RequestMapping(value="/error")
	public String handleError() {
		return "forward:/index.html";
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}
}
