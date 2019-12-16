/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestur.repository;

import com.gestur.entities.Empleado;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, String> {

	// listar
	@Query("SELECT e FROM Empleado e ORDER BY e.nombre")
	public List<Empleado> empleadosPorNombre();

	// buscar
	@Query("SELECT e FROM Empleado e WHERE e.nombre LIKE %:nombre% ORDER BY e.nombre")
	public List<Empleado> buscarEmpleado(@Param("nombre") String nombre);

	// modificar
	@Modifying
	@Query("UPDATE FROM Empleado e SET e.nombre = :nombre WHERE e.id=:id")
	public void modificarNombreEmpleado(@Param("nombre") String nombre, @Param("id") String id);

	public Empleado findByUsernameAndContraseña(String username, String contraseña);

}
