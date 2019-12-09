package com.gestur.repository;

import com.gestur.entities.Actividad;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ActividadRepository extends JpaRepository<Actividad, String> {

    //listar
    @Query("SELECT a FROM Actividad a ORDER BY a.lugar")
    public List<Actividad> actsPorLugar();

    @Query("SELECT a FROM Actividad a ORDER BY a.nombre")
    public List<Actividad> actsPorNombre();

    @Query("SELECT a FROM Actividad a ORDER BY DESC a.precio ")
    public List<Actividad> actsPorPrecio();

    //buscar
    @Query("SELECT a FROM Actividad a WHERE a.nombre like %:nombre% ORDER BY a.nombre")
    public List<Actividad> buscarPorNombre(@Param("nombre") String nombre);

    @Query("SELECT a FROM Actividad a WHERE a.lugar like %:lugar% ORDER BY a.lugar")
    public List<Actividad> buscarPorLugar(@Param("lugar") String lugar);

    @Query("SELECT a FROM Actividad a WHERE a.precio like %:precio% ORDER BY DESC a.precio ")
    public List<Actividad> buscarPorPrecio(@Param("precio") Double precio);

    //modificar
    @Modifying
    @Query("UPDATE FROM Actividad a SET a.nombre = :nombre  WHERE a.id=:id")
    public void modificarNombre(@Param("nombre") String nombre, @Param("id") String id);

    @Modifying
    @Query("UPDATE FROM Actividad a SET a.precio = :precio WHERE a.id=:id")
    public void modificarPrecio(@Param("precio") Double precio, @Param("id") String id);

    @Modifying
    @Query("UPDATE FROM Actividad a SET a.lugar = :lugar WHERE a.id=:id")
    public void modificarLugar(@Param("lugar") String lugar, @Param("id") String id);

    @Modifying
    @Query("UPDATE FROM Actividad a SET a.nombre = :nombre, a.lugar = :lugar, a.precio = :precio WHERE a.id=:id")
    public void modificarTodo(@Param("nombre") String nombre, @Param("lugar") String lugar, @Param("precio") Double precio, @Param("id") String id);

}
