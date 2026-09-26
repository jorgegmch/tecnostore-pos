package com.tecnostore.pos.persistencia;

import com.tecnostore.pos.modelo.Cliente;
import com.tecnostore.pos.modelo.Credito;
import com.tecnostore.pos.modelo.Venta;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreditoDAO implements ICreditoDAO {

    @Override
    public void registrar(Credito credito) throws SQLException {
        String sql = "INSERT INTO creditos (id_cliente, id_venta, saldo_pendiente) VALUES (?, ?, ?)";
        try (Connection con = ConexionDB.getInstancia().obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, credito.getCliente().getId());
            ps.setLong(2, credito.getVenta().getId());
            ps.setBigDecimal(3, credito.getSaldoPendiente());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    credito.setId(keys.getLong(1));
                }
            }
        }
    }

    @Override
    public Credito buscarPorVenta(Long idVenta) throws SQLException {
        String sql = "SELECT cr.id, cr.saldo_pendiente, " +
                     "c.id AS cliente_id, c.nombre, c.identificacion, c.correo, c.telefono, " +
                     "v.id AS venta_id, v.total, v.fecha " +
                     "FROM creditos cr " +
                     "JOIN clientes c ON cr.id_cliente = c.id " +
                     "JOIN ventas v ON cr.id_venta = v.id " +
                     "WHERE cr.id_venta = ?";
        try (Connection con = ConexionDB.getInstancia().obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, idVenta);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setId(rs.getLong("cliente_id"));
                    cliente.setNombre(rs.getString("nombre"));
                    cliente.setIdentificacion(rs.getString("identificacion"));
                    cliente.setCorreo(rs.getString("correo"));
                    cliente.setTelefono(rs.getString("telefono"));

                    Venta venta = new Venta();
                    venta.setId(rs.getLong("venta_id"));
                    venta.setCliente(cliente);
                    venta.setTotal(rs.getBigDecimal("total"));
                    venta.setFecha(rs.getDate("fecha").toLocalDate().atStartOfDay());

                    return new Credito(
                        rs.getLong("id"),
                        cliente,
                        venta,
                        rs.getBigDecimal("saldo_pendiente")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public void actualizarSaldo(Long idCredito, BigDecimal nuevoSaldo) throws SQLException {
        String sql = "UPDATE creditos SET saldo_pendiente = ? WHERE id = ?";
        try (Connection con = ConexionDB.getInstancia().obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setBigDecimal(1, nuevoSaldo);
            ps.setLong(2, idCredito);
            ps.executeUpdate();
        }
    }
}