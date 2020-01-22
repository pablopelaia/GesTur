package com.gestur.controller;

import com.gestur.entities.Actividad;
import com.gestur.exceptions.ErrorServices;
import com.gestur.services.ActividadService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/actividad")
public class ActividadController {

    @Autowired
    private ActividadService actServ;

    @GetMapping({"/", ""})
    public String pagPpal(ModelMap model) throws ErrorServices {
        model.addAttribute("titulo", "Página Principal de Actividad");
        return "actividad.html";
    }

    @GetMapping("/crearActividad")
    public String creAct(ModelMap model) {
        model.addAttribute("titulo", "Crear Actividad");
        return "crearActividad.html";
    }

    //formaction="crear"
    @PostMapping("/crearActividad")
    public String crearActividad(@RequestParam String nombre, @RequestParam Double precio, @RequestParam String lugar) throws ErrorServices {
        actServ.crearActividad(nombre, precio, lugar);
        return "redirect:/listaActividad";
    }

    @PostMapping("/listaActividad")
    public String listaActividad(@RequestParam(required = false) String nombre, @RequestParam(required = false) String lugar, ModelMap model) throws ErrorServices {

        List<Actividad> listaActividad = actServ.listaActividad();
        model.addAttribute("titulo", "Lista de Actividades Disponibles");
        if (nombre != null) {
            listaActividad = actServ.buscarPorNombre(nombre);
        } else if (lugar != null) {
            listaActividad = actServ.buscarPorLugar(lugar);
        }
        model.put("listaActividad", listaActividad);
        return "listaActividad.html";
    }

    //########se me ocurre poner los siguientes 3 en las cabeza de lista para ordenarlas segun indica el metodo, al hacer click#############
    //Añadir modelmap especifico para cada uno de los <h></h> (idea)
    //action="orderByNombre"
    @GetMapping("/listaActividad/orderByNombre")
    public String listaPorNombre(ModelMap model) throws ErrorServices {
        model.addAttribute("titulo", "Lista de Actividades Disponibles");
        List<Actividad> listaActividad = actServ.listaPorNombre();
        model.put("listaActividad", listaActividad);
        return "listaActividad.html";
    }

    //action="orderByPrecio"
    @GetMapping("/listaActividad/orderByPrecio")
    public String listaPorPrecio(ModelMap model) throws ErrorServices {
        model.addAttribute("titulo", "Lista de Actividades Disponibles");
        List<Actividad> listaActividad = actServ.listaPorPrecio();
        model.put("listaActividad", listaActividad);
        return "listaActividad.html";
    }

    //action="orderByLugar"
    @GetMapping("/listaActividad/orderByLugar")
    public String listaPorLugar(ModelMap model) throws ErrorServices {
        model.addAttribute("titulo", "Lista de Actividades Disponibles");
        List<Actividad> listaActividad = actServ.listaPorLugar();
        model.put("listaActividad", listaActividad);
        return "listaActividad.html";
    }
    //########################################################################################################

    //edicion completa de una actividad
    @GetMapping("/editarActividad")
    public String edAct(ModelMap model) {
        model.addAttribute("titulo", "Edite una Actividad completa");
        return "editarActividad.html";

    }

    //formaction="act"
    @PostMapping("/editarActividad")
    public String editarActividad(@RequestParam String id, @RequestParam String nombre, @RequestParam Double precio, @RequestParam String lugar, ModelMap model) throws ErrorServices {
        actServ.modificarActividad(id, nombre, precio, lugar);
        return "redirect:/listaActividad";
    }

    //solo el nombre de actividad
    @GetMapping("/editarNombre")
    public String edNom(ModelMap model) {
        model.addAttribute("titulo", "Edite el Nombre de una Actividad en específico");
        return "editarNombre.html";
    }

    //formaction="nom"
    @PostMapping("/editarNombre")
    public String editarNombre(@RequestParam String id, @RequestParam String nombre, ModelMap model) throws ErrorServices {
        actServ.modificarNombre(id, nombre);
        return "redirect:/listaActividad";
    }

    //solo el lugar de actividad
    @GetMapping("/editarLugar")
    public String edLug(ModelMap model) {
        model.addAttribute("titulo", "Edite el Lugar de una Actividad en específico");
        return "editarLugar.html";
    }

    //formaction=lugar
    @PostMapping("/editarLugar")
    public String editarLugar(@RequestParam String id, @RequestParam String lugar, ModelMap model) throws ErrorServices {
        actServ.modificarLugar(id, lugar);
        return "redirect:/listaActividad";
    }

    //solo el precio de actividad
    @GetMapping("/editarPrecio")
    public String edPre(ModelMap model) {
        model.addAttribute("titulo", "Edite el Precio de una Actividad en específico");
        return "editarPrecio.html";
    }

    //formaction="precio"
    @PostMapping("/editarPrecio")
    public String editarPrecio(@RequestParam String id, @RequestParam Double precio, ModelMap model) throws ErrorServices {
        actServ.modificarPrecio(id, precio);
        return "redirect:/listaActividad";
    }

    @GetMapping("/borrarActividad")
    public String elAct(ModelMap model) {
        model.addAttribute("titulo", "Elimine una Actividad");
        return "eliminarActividad.html";
    }

    //formaction="eliminar"
    @PostMapping("/borrarActividad")
    public String eliminarActividad(@RequestParam String id, ModelMap model) throws ErrorServices {
        actServ.eliminarActividad(id);
        return "redirect:/listaActividad";
    }

}
