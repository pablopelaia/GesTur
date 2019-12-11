package com.gestur.services;

import com.gestur.entities.Usuarios;
import com.gestur.exceptions.ErrorServices;
import com.gestur.repository.UsuarioRepository;
import enumeraciones.Permisos;
import java.util.Date;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Transactional
    private void validarDatos(Integer legajo, String nombre, String apellido, String mail, Date fechaingreso, Permisos permiso, String contraseña) throws ErrorServices {
        
        if (legajo.equals(null)){
            throw new ErrorServices("El legajo no puede ser nulo");
        }else{
            if (!(legajo instanceof Integer)){
                throw new ErrorServices("El legajo debe ser un entero");
            }
        }
        
        if (nombre.isEmpty()||nombre.equals(null)){
            throw new ErrorServices("El nombre no puede estar vacío");
        }else{
            if (!(nombre instanceof String)){
                throw new ErrorServices("El nombre debe ser un String");
            }
        }
        
        if (apellido.isEmpty()||apellido.equals(null)){
            throw new ErrorServices("El apellido no puede estar vacío");
        }else{
            if (!(apellido instanceof String)){
                throw new ErrorServices("El apellino debe ser un String");
            }
        }
        
        if (mail.isEmpty()||mail.equals(null)){
            throw new ErrorServices("Mail incorrecto");
        }else{
            if (!(mail instanceof String)){
                throw new ErrorServices("El mail debe ser un string");
            }
        }
        
        if (fechaingreso.equals(null)){
            throw new ErrorServices("La fecha de ingreso no puede estar vacía");
        }
        
        if (permiso.equals(null)){
            throw new ErrorServices("El campo permisos no puede estar vacío");
        }
        
        if (contraseña.isEmpty()||contraseña.equals(null)){
            throw new ErrorServices("contraseña inválida");
        }else{
            if (!(contraseña instanceof String)){
                throw new ErrorServices("La contraseña debe ser un String");
            }
        }
    }    
        
}
