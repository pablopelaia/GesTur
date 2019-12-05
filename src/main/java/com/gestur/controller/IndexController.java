package com.gestur.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

	@GetMapping({ "/", "", "/index" })
	public String index(Model model) {
		model.addAttribute("titulo", "GesTur");
		model.addAttribute("header", "Gestión de reservas para empresas de turismo");
		return "index";
	}

}
