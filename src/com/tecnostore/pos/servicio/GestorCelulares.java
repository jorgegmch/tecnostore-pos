package com.tecnostore.pos.servicio;

import com.tecnostore.pos.modelo.Celular;
import com.tecnostore.pos.persistencia.CelularDAO;
import java.sql.SQLException;
import java.util.List;

/**
 * Servicio de gestión de celulares. Implementa patrón Singleton.
 */
public class GestorCelulares {
    private static GestorCelulares instance;
    private final CelularDAO celularDAO;

    private GestorCelulares() {
        this.celularDAO = new CelularDAO();
    }

    public static GestorCelulares getInstance() {
        if (instance == null) {
            instance = new GestorCelulares();
        }
        return instance;
    }

    /** Registra un nuevo celular después de validar sus atributos. */
    public void registrar(Celular celular) throws SQLException {
        if (celular.getPrecio() == null || celular.getPrecio().compareTo(java.math.BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El precio debe ser positivo.");
        }
        if (celular.getStock() < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo.");
        }
        celularDAO.insertar(celular);
    }

    /** Actualiza un celular existente. */
    public void actualizar(Celular celular) throws SQLException {
        if (celular.getId() == null) {
            throw new IllegalArgumentException("Se requiere ID para actualizar.");
        }
        if (celular.getPrecio() == null || celular.getPrecio().compareTo(java.math.BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El precio debe ser positivo.");
        }
        if (celular.getStock() < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo.");
        }
        celularDAO.actualizar(celular);
    }

    /** Elimina un celular por su ID. */
    public void eliminar(Long id) throws SQLException {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo.");
        }
        Celular existente = buscarPorId(id);
        if (existente == null) {
            throw new IllegalArgumentException("Celular no encontrado.");
        }
        celularDAO.eliminar(id);
    }

    /** Devuelve la lista completa de celulares. */
    public List<Celular> listarTodos() throws SQLException {
        return celularDAO.listarTodos();
    }

    /** Busca un celular por su ID. */
    public Celular buscarPorId(Long id) throws SQLException {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo.");
        }
        return celularDAO.buscarPorId(id);
    }
}
