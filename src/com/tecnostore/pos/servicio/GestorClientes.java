package com.tecnostore.pos.servicio;

import com.tecnostore.pos.modelo.Cliente;
import com.tecnostore.pos.persistencia.ClienteDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Servicio de gestión de clientes. Implementa patrón Singleton.
 */
public class GestorClientes {
    private static GestorClientes instance;
    private final ClienteDAO clienteDAO;
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    private GestorClientes() {
        this.clienteDAO = new ClienteDAO();
    }

    public static GestorClientes getInstance() {
        if (instance == null) {
            instance = new GestorClientes();
        }
        return instance;
    }

    /** Registra un nuevo cliente después de validar sus datos. */
    public void registrar(Cliente cliente) throws SQLException {
        validarCliente(cliente, false);
        clienteDAO.insertar(cliente);
    }

    /** Actualiza un cliente existente. */
    public void actualizar(Cliente cliente) throws SQLException {
        validarCliente(cliente, true);
        clienteDAO.actualizar(cliente);
    }

    /** Elimina un cliente por su ID. */
    public void eliminar(Long id) throws SQLException {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo.");
        }
        Cliente existente = buscarPorId(id);
        if (existente == null) {
            throw new IllegalArgumentException("Cliente no encontrado.");
        }
        clienteDAO.eliminar(id);
    }

    /** Devuelve la lista completa de clientes. */
    public List<Cliente> listarTodos() throws SQLException {
        return clienteDAO.listarTodos();
    }

    /** Busca un cliente por su ID. */
    public Cliente buscarPorId(Long id) throws SQLException {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo.");
        }
        return clienteDAO.buscarPorId(id);
    }

    /** Validaciones compartidas para registro y actualización. */
    private void validarCliente(Cliente cliente, boolean esActualizacion) throws SQLException {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo.");
        }
        if (esActualizacion && cliente.getId() == null) {
            throw new IllegalArgumentException("Se requiere ID para actualizar.");
        }
        if (cliente.getNombre() == null || cliente.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del cliente no puede estar vacío.");
        }
        if (cliente.getIdentificacion() == null || cliente.getIdentificacion().trim().isEmpty()) {
            throw new IllegalArgumentException("La identificación no puede estar vacía.");
        }
        // Verificar unicidad de identificación al crear
        if (!esActualizacion) {
            Cliente existente = clienteDAO.buscarPorIdentificacion(cliente.getIdentificacion());
            if (existente != null) {
                throw new IllegalArgumentException("Identificación ya registrada.");
            }
        }
        if (cliente.getCorreo() != null && !cliente.getCorreo().isEmpty() && !EMAIL_PATTERN.matcher(cliente.getCorreo()).matches()) {
            throw new IllegalArgumentException("Formato de correo inválido.");
        }
        // teléfono es opcional, no se valida aquí
    }
}
