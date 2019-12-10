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
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Transactional
    public void crearUsuario(int legajo, String nombre, String apellido, String mail, Date fechaingreso, Permisos permiso, String contraseña)throws ErrorServices{
        
        validarDatos(legajo, nombre, apellido, mail, fechaingreso, permiso, contraseña);
        
        Usuarios usuarios = new Usuarios();
        
        usuarios.setLegajo(legajo);
        usuarios.setNombre(nombre);
        usuarios.setApellido(apellido);
        usuarios.setMail(mail);
        usuarios.setFechaingreso(fechaingreso);
        usuarios.setPermiso(permiso);
        usuarios.setContraseña(contraseña);
        
        usuarioRepository.save(usuarios);
        
    }
    
    @Transactional
    public void modificarLegajo (int legajo) throws ErrorServices{
         
        Optional<Usuarios> respuesta=usuarioRepository.findById(legajo);
        if (respuesta.isPresent()){
            Usuarios usuario = respuesta.get();
            
            validarDatos(legajo, usuario.getNombre(), usuario.getApellido(), usuario.getMail(), usuario.getFechaingreso(), usuario.getPermiso(), usuario.getContraseña());
            
            usuario.setLegajo(legajo);
            usuarioRepository.save(usuario);
        }else{
            throw new ErrorServices("no existe usuario con  ese legajo");
        }
    }
    
    @Transactional
    public void modificarNombre (int legajo, String nombre) throws ErrorServices{
         
        Optional<Usuarios> respuesta=usuarioRepository.findById(legajo);
        if (respuesta.isPresent()){
            Usuarios usuario = respuesta.get();
            
            validarDatos(legajo, nombre, usuario.getApellido(), usuario.getMail(), usuario.getFechaingreso(), usuario.getPermiso(), usuario.getContraseña());
            
            usuario.setNombre(nombre);
            usuarioRepository.save(usuario);
        }else{
            throw new ErrorServices("no existe usuario con  ese legajo");
        }
    }
    
    @Transactional
    public void modificarApellido (int legajo, String apellido) throws ErrorServices{
         
        Optional<Usuarios> respuesta=usuarioRepository.findById(legajo);
        if (respuesta.isPresent()){
            Usuarios usuario = respuesta.get();
            
            validarDatos(legajo, usuario.getNombre(), apellido, usuario.getMail(), usuario.getFechaingreso(), usuario.getPermiso(), usuario.getContraseña());
            
            usuario.setApellido(apellido);
            usuarioRepository.save(usuario);
        }else{
            throw new ErrorServices("no existe usuario con  ese legajo");
        }
    }
     
    @Transactional
    public void modificarMail (int legajo, String mail) throws ErrorServices{
         
        Optional<Usuarios> respuesta=usuarioRepository.findById(legajo);
        if (respuesta.isPresent()){
            Usuarios usuario = respuesta.get();
            
            validarDatos(legajo, usuario.getNombre(), usuario.getApellido(), mail, usuario.getFechaingreso(), usuario.getPermiso(), usuario.getContraseña());
            
            usuario.setMail(mail);
            usuarioRepository.save(usuario);
        }else{
            throw new ErrorServices("no existe usuario con  ese legajo");
        }
    }
    
    @Transactional
    public void modificarFechaDeIngreso (int legajo, Date fecha) throws ErrorServices{
         
        Optional<Usuarios> respuesta=usuarioRepository.findById(legajo);
        if (respuesta.isPresent()){
            Usuarios usuario = respuesta.get();
            
            validarDatos(legajo, usuario.getNombre(), usuario.getApellido(), usuario.getMail(), fecha, usuario.getPermiso(), usuario.getContraseña());
            
            usuario.setFechaingreso(fecha);
            usuarioRepository.save(usuario);
        }else{
            throw new ErrorServices("no existe usuario con  ese legajo");
        }
    }
    
    @Transactional
    public void modificarPermisos (int legajo, Permisos permiso) throws ErrorServices{
         
        Optional<Usuarios> respuesta=usuarioRepository.findById(legajo);
        if (respuesta.isPresent()){
            Usuarios usuario = respuesta.get();
            
            validarDatos(legajo, usuario.getNombre(), usuario.getApellido(), usuario.getMail(), usuario.getFechaingreso(), permiso, usuario.getContraseña());
            
            usuario.setPermiso(permiso);
            usuarioRepository.save(usuario);
        }else{
            throw new ErrorServices("no existe usuario con  ese legajo");
        }
    }
    
    @Transactional
    public void modificarContraseña (int legajo, String contraseña) throws ErrorServices{
         
        Optional<Usuarios> respuesta=usuarioRepository.findById(legajo);
        if (respuesta.isPresent()){
            Usuarios usuario = respuesta.get();
            
            validarDatos(legajo, usuario.getNombre(), usuario.getApellido(), usuario.getMail(), usuario.getFechaingreso(), usuario.getPermiso(), contraseña);
            
            usuario.setContraseña(contraseña);
            usuarioRepository.save(usuario);
        }else{
            throw new ErrorServices("no existe usuario con  ese legajo");
        }
    }
    
    @Transactional
    public void elliminarUsuario (int legajo) throws ErrorServices{
         
        Optional<Usuarios> respuesta=usuarioRepository.findById(legajo);
        if (respuesta.isPresent()){
            Usuarios usuario = respuesta.get();
            
            validarDatos(legajo, usuario.getNombre(), usuario.getApellido(), usuario.getMail(), usuario.getFechaingreso(), usuario.getPermiso(), usuario.getContraseña());
            
            usuarioRepository.delete(usuario);
        }else{
            throw new ErrorServices("no existe usuario con  ese legajo");
        }
    }

    @Transactional
    private void validarDatos(Integer legajo, String nombre, String apellido, String mail, Date fechaingreso, Permisos permiso, String contraseña) throws ErrorServices {
        
        if (legajo.equals(null)){
            throw new ErrorServices("El legajo no puede ser nulo");
        }
        
        if (nombre.isEmpty()||nombre.equals(null)){
            throw new ErrorServices("El nombre no puede estar vacío");
        }
        
        if (apellido.isEmpty()||apellido.equals(null)){
            throw new ErrorServices("El apellido no puede estar vacío");
        }
        
        if (mail.isEmpty()||mail.equals(null)){
            throw new ErrorServices("Mail incorrecto");
        }
        
        if (fechaingreso.equals(null)){
            throw new ErrorServices("La fecha de ingreso no puede estar vacía");
        }
        
        if (permiso.equals(null)){
            throw new ErrorServices("El campo permisos no puede estar vacío");
        }
        
        if (contraseña.isEmpty()||contraseña.equals(null)){
            throw new ErrorServices("contraseña inválida");
        }
    }    
        
}
