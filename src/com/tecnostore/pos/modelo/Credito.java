package com.tecnostore.pos.modelo;

import java.math.BigDecimal;

/**
 *
 * @author camper
 */
public class Credito {

    private Long id;
    private Cliente cliente;
    private Venta venta;
    private BigDecimal saldoPendiente;

    public Credito(Long id, Cliente cliente, Venta venta, BigDecimal saldoPendiente) {
        if (cliente == null) throw new IllegalArgumentException("El cliente no puede ser nulo");
        if (venta == null) throw new IllegalArgumentException("La venta no puede ser nula");
        if (saldoPendiente == null || saldoPendiente.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("El saldo pendiente no puede ser nulo ni negativo");
        this.id = id;
        this.cliente = cliente;
        this.venta = venta;
        this.saldoPendiente = saldoPendiente;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Cliente getCliente() { return cliente; }
    public Venta getVenta() { return venta; }
    public BigDecimal getSaldoPendiente() { return saldoPendiente; }
}