package com.gestur.repository;

import com.gestur.entities.Reserva;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {

	@Query("SELECT c FROM Reserva c")
	public List<Reserva> listaReserva();

	@Query("SELECT c FROM Reserva c,Actividad d WHERE c.actividad.id=d.id AND d.nombre LIKE %:nombre% ORDER BY d.nombre")
	public List<Reserva> reservaPorActividad(@Param("nombre") String nombre);

	@Query("SELECT c FROM Reserva c,Actividad d WHERE c.actividad.id=d.id AND d.lugar LIKE %:nombre% ORDER BY d.lugar")
	public List<Reserva> reservaPorLugar(@Param("nombre") String nombre);

	// Modificar todo al mismo tiempo
	@Modifying
	@Query("UPDATE FROM Reserva c SET fechaActividad=:fechaActividad,cantPasajeros=:cantPas WHERE c.id=:id")
	public void modificarReserva(@Param("fechaActividad") Date fechaActividad, @Param("cantPas") Integer cantPasajeros,
			@Param("id") Integer id);

	@Modifying
	@Query("UPDATE FROM Reserva c SET fechaActividad=:fechaActividad WHERE c.id=:id")
	public void modificarFecha(@Param("fechaActividad") Date fechaActividad, @Param("id") Integer id);

	@Modifying
	@Query("UPDATE FROM Reserva c SET cantPasajeros=:cantPas WHERE c.id=:id")
	public void modificarPasajeros(@Param("cantPas") Integer cantPasajeros, @Param("id") Integer id);

}
