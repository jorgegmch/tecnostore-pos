package com.tecnostore.pos.persistencia;

import com.tecnostore.pos.modelo.Credito;
import java.math.BigDecimal;
import java.sql.SQLException;

public interface ICreditoDAO {
    void registrar(Credito credito) throws SQLException;
    Credito buscarPorVenta(Long idVenta) throws SQLException;
    void actualizarSaldo(Long idCredito, BigDecimal nuevoSaldo) throws SQLException;
}