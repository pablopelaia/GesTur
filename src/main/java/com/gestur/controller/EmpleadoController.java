package com.gestur.controller;

import com.gestur.entities.Empleado;
import com.gestur.exceptions.ErrorServices;
import com.gestur.services.EmpleadoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping({"/empleado"})
public class EmpleadoController {

    @Autowired
    private EmpleadoService empServ;

    @GetMapping({"/", ""})
    public String empleado(ModelMap model) {
        model.addAttribute("titulo", "Página Principal de Empleados");
        return "empleado.html";
    }

    @PostMapping("/crearEmpleado")
    public String crEmp(String nombre) throws ErrorServices {
        empServ.crearEmpleado(nombre);
        return "redirect:/listaEmpleado";
    }

    @PostMapping("/listaEmpleado")
    public String listaEmpleado(@RequestParam(required = false) String nombre, ModelMap model) throws ErrorServices {
        List<Empleado> listaEmpleado = empServ.listaEmpleado();
        if (nombre != null) {
            listaEmpleado = empServ.buscarEmpleado(nombre);
        }
        model.addAttribute("listaEmpleado", listaEmpleado);
        return "listaEmpleado.html";
    }

    @GetMapping("/editarEmpleado")
    public String edEmp(ModelMap model) {
        model.addAttribute("titulo", "Editar Empleado");
        return "editarEmpleado.html";
    }

    //formaction="editar"
    @PostMapping("/editarEmpleado")
    public String editarEmpleado(@RequestParam String id, @RequestParam String nombre, ModelMap model) throws ErrorServices {
        empServ.modificarEmpleado(id, nombre);
        return "redirect:/listaEmpleado";
    }

    @GetMapping("/eliminarEmpleado")
    public String elEmp(ModelMap model) {
        model.addAttribute("titulo", "Eliminar Empleado");
        return "eliminarEmpleado.html";
    }

    //formaction="eliminar"
    @PostMapping("/eliminarEmpleado")
    public String eliminarEmpleado(@RequestParam String id, ModelMap model) throws ErrorServices {
        empServ.borrarEmpleado(id);
        return "redirect:/listaEmpleado";
    }

}
