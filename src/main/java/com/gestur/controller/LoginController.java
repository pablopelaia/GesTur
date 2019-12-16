package com.gestur.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gestur.entities.Empleado;
import com.gestur.services.EmpleadoService;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private EmpleadoService es;

	@GetMapping({ "", "/" })
	public String login() {
		return "login";
	}

	@PostMapping("/")
	public String userLogin(@RequestParam String username, @RequestParam String contraseña, RedirectAttributes flash) {

		Empleado empleado = es.buscarPorUsername(username, contraseña);

		System.out.println(empleado);

		if (empleado == null) {
			flash.addFlashAttribute("error", "Nombre de usuario o contraseña incorrectos");
			return "redirect:/login";
		}

		flash.addFlashAttribute("empleado", empleado);
		return "redirect:/index";
	}

}
