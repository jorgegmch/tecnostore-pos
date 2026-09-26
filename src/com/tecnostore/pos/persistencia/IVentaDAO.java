package com.tecnostore.pos.persistencia;

import com.tecnostore.pos.modelo.Venta;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author camper
 */
public interface IVentaDAO {
    void registrarVenta(Venta venta) throws SQLException;
    List<Venta> listarTodas() throws SQLException;
}