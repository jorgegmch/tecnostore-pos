package com.tecnostore.pos.persistencia;

import com.tecnostore.pos.modelo.Celular;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author camper
 */
public interface ICelularDAO {
    void insertar(Celular celular) throws SQLException;
    Celular buscarPorId(Long id) throws SQLException;
    List<Celular> listarTodos() throws SQLException;
    void actualizar(Celular celular) throws SQLException;
    void eliminar(Long id) throws SQLException;
}