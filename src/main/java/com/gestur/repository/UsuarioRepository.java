package com.gestur.repository;

import com.gestur.entities.Usuarios;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuarios, Integer> {
    
    @Query("SELECT u FROM Usuarios u ORDER BY u.apellido")
    public List<Usuarios> usuariosPorApellido();

    @Query("SELECT u FROM Usuarios u WHERE u.apellido LIKE %:apellido% ORDER BY e.apellido")
    public List<Usuarios> buscarUsuario(@Param("apellido") String apellido);

    @Modifying
    @Query("UPDATE FROM Empleado e SET e.apellido = :apellido WHERE e.id=:id")
    public void modificarNombreEmpleado(@Param("nombre") String apellido, @Param("id") int id);
    
}
