package com.gestur.controller;

import com.gestur.entities.Empleado;
import com.gestur.exceptions.ErrorServices;
import com.gestur.services.EmpleadoService;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping({ "/empleado" })
@SessionAttributes("empleado")
public class EmpleadoController {

	@Autowired
	private EmpleadoService empServ;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@GetMapping({ "/", "" })
	public String empleado(ModelMap model) {
		model.addAttribute("titulo", "Página Principal de Empleados");
		Empleado empleado = new Empleado();
		model.addAttribute("empleado", empleado);
		return "emp";
	}

	@PostMapping("/crearEmpleado")
	public String crEmp(@Valid Empleado empleado, BindingResult result, Model model, SessionStatus status)
			throws ErrorServices {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Página Principal de Empleados");
			return "emp";
		}

		empServ.crearEmpleado(empleado.getNombre(), empleado.getUsername(), encoder.encode(empleado.getContraseña()));

		status.setComplete();

		return "redirect:/";
	}

//    @PostMapping("/listaEmpleado")
//    public String listaEmpleado(@RequestParam(required = false) String nombre, ModelMap model) throws ErrorServices {
//        List<Empleado> listaEmpleado = empServ.listaEmpleado();
//        if (nombre != null) {
//            listaEmpleado = empServ.buscarEmpleado(nombre);
//        }
//        model.addAttribute("listaEmpleado", listaEmpleado);
//        return "listaEmpleado.html";
//    }

	@GetMapping("/editarEmpleado")
	public String edEmp(ModelMap model) {
		model.addAttribute("titulo", "Editar Empleado");
		return "editarEmpleado.html";
	}

//    //formaction="editar"
//    @PostMapping("/editarEmpleado")
//    public String editarEmpleado(@RequestParam String id, @RequestParam String nombre, ModelMap model) throws ErrorServices {
//        empServ.modificarEmpleado(id, nombre);
//        return "redirect:/listaEmpleado";
//    }
//
//    @GetMapping("/eliminarEmpleado")
//    public String elEmp(ModelMap model) {
//        model.addAttribute("titulo", "Eliminar Empleado");
//        return "eliminarEmpleado.html";
//    }
//
//    //formaction="eliminar"
//    @PostMapping("/eliminarEmpleado")
//    public String eliminarEmpleado(@RequestParam String id, ModelMap model) throws ErrorServices {
//        empServ.borrarEmpleado(id);
//        return "redirect:/listaEmpleado";
//    }

}
