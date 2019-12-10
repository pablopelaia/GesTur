package com.gestur.controller;

import com.gestur.entities.Actividad;
import com.gestur.exceptions.ErrorServices;
import com.gestur.services.ActividadService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
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

    //formaction="crear" u otro que quiera Cocco
    @GetMapping("/crearActividad/crear")
    public String crearActividad(@RequestParam String nombre, @RequestParam Double precio, @RequestParam String lugar) throws ErrorServices {
        actServ.crearActividad(nombre, precio, lugar);
        return "redirect:/listaActividad";
    }

    @GetMapping("/listaActividad")
    public String listaActividad(@RequestParam(required = false) String nombre, @RequestParam(required = false) String lugar, ModelMap model) throws ErrorServices {

        List<Actividad> listActividad = actServ.listaActividad();
        model.addAttribute("titulo", "Lista de Actividades Disponibles");
        if (nombre != null || !(nombre.isEmpty())) {
            listActividad = actServ.buscarPorNombre(nombre);
        } else if (lugar != null || !(lugar.isEmpty())) {
            listActividad = actServ.buscarPorLugar(lugar);
        }
        model.put("listaActividad", listActividad);
        return "listaActividad.html";
    }

    //########se me ocurre poner los siguientes 3 en las cabeza de lista para ordenarlas segun indica el metodo, al hacer click#########
    //Añadir modelmap especifico para cada uno de los <h></h> (idea)
    @GetMapping("/listaActividad/orderByNombre")
    public String listaPorNombre(ModelMap model) throws ErrorServices {
        model.addAttribute("titulo", "Lista de Actividades Disponibles");
        List<Actividad> listaActividad = actServ.listaPorNombre();
        model.put("listaActividad", listaActividad);
        return "listaActividad.html";
    }

    @GetMapping("/listaActividad/orderByPrecio")
    public String listaPorPrecio(ModelMap model) throws ErrorServices {
        model.addAttribute("titulo", "Lista de Actividades Disponibles");
        List<Actividad> listaActividad = actServ.listaPorPrecio();
        model.put("listaActividad", listaActividad);
        return "listaActividad.html";
    }

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
    @GetMapping("/editarActividad/act")
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
    @GetMapping("/editarNombre/nom")
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

    //formaction=lug
    @GetMapping("/editarLugar/lug")
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

    //formaction="pre"
    @GetMapping("/editarPrecio/pre")
    public String editarPrecio(@RequestParam String id, @RequestParam Double precio, ModelMap model) throws ErrorServices {
        actServ.modificarPrecio(id, precio);
        return "redirect:/listaActividad";
    }

    @GetMapping("/borrarActividad")
    public String elAct(ModelMap model) {
        model.addAttribute("titulo", "Elimine una Actividad");
        return "eliminarActividad.html";
    }

    //formaction="borrar"
    @GetMapping("/borrarActividad/borrar")
    public String eliminarActividad(@RequestParam String id, ModelMap model) throws ErrorServices {
        actServ.borrarActividad(id);
        return "redirect:/listaActividad";
    }

}
