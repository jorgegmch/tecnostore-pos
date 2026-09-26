package com.tecnostore.pos.servicio;

import com.tecnostore.pos.modelo.Credito;
import com.tecnostore.pos.modelo.Venta;
import com.tecnostore.pos.persistencia.CreditoDAO;
import com.tecnostore.pos.persistencia.ICreditoDAO;
import java.math.BigDecimal;
import java.sql.SQLException;

public class GestorCreditos {

    private ICreditoDAO creditoDAO;

    public GestorCreditos() {
        this.creditoDAO = new CreditoDAO();
    }

    public void registrarCredito(Venta venta) throws SQLException {
        Credito credito = new Credito(null, venta.getCliente(), venta, venta.getTotal());
        creditoDAO.registrar(credito);
    }

    public Credito buscarPorVenta(Long idVenta) throws SQLException {
        Credito credito = creditoDAO.buscarPorVenta(idVenta);
        if (credito == null) {
            throw new IllegalArgumentException("Esa venta no tiene un credito asociado.");
        }
        return credito;
    }

    public BigDecimal registrarAbono(Long idVenta, BigDecimal monto) throws SQLException {
        Credito credito = buscarPorVenta(idVenta);
        if (monto == null || monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto del abono debe ser mayor a cero.");
        }
        if (monto.compareTo(credito.getSaldoPendiente()) > 0) {
            throw new IllegalArgumentException("El abono no puede ser mayor al saldo pendiente.");
        }
        BigDecimal nuevoSaldo = credito.getSaldoPendiente().subtract(monto);
        creditoDAO.actualizarSaldo(credito.getId(), nuevoSaldo);
        return nuevoSaldo;
    }
}