package com.tecnostore.pos.persistencia;

import com.tecnostore.pos.modelo.Cliente;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author camper
 */
public interface IClienteDAO {
    void insertar(Cliente cliente) throws SQLException;
    void actualizar(Cliente cliente) throws SQLException;
    void eliminar(Long id) throws SQLException;
    Cliente buscarPorId(Long id) throws SQLException;
    Cliente buscarPorIdentificacion(String identificacion) throws SQLException;
    List<Cliente> listarTodos() throws SQLException;
}