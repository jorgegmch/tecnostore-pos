package com.tecnostore.pos.servicio;

import com.tecnostore.pos.modelo.Celular;
import com.tecnostore.pos.persistencia.CelularDAO;
import com.tecnostore.pos.persistencia.ICelularDAO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Jorge Gómez
 */
public class GestorCelulares {

    private ICelularDAO celularDAO;

    public GestorCelulares() {
        this.celularDAO = new CelularDAO();
    }

    public void registrar(Celular celular) throws SQLException {
        celularDAO.insertar(celular);
    }

    public void actualizar(Celular celular) throws SQLException {
        Celular existente = celularDAO.buscarPorId(celular.getId());
        if (existente == null) {
            throw new IllegalArgumentException("Celular no encontrado.");
        }
        celularDAO.actualizar(celular);
    }

    public void eliminar(Long id) throws SQLException {
        Celular existente = celularDAO.buscarPorId(id);
        if (existente == null) {
            throw new IllegalArgumentException("Celular no encontrado.");
        }
        celularDAO.eliminar(id);
    }

    public Celular buscarPorId(Long id) throws SQLException {
        return celularDAO.buscarPorId(id);
    }

    public List<Celular> listarTodos() throws SQLException {
        return celularDAO.listarTodos();
    }
}