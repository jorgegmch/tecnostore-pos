package com.tecnostore.pos.patron;

import java.math.BigDecimal;

/**
 *
 * @author camper
 */
public class SinDescuento implements EstrategiaDescuento {
    @Override
    public BigDecimal aplicar(BigDecimal precioOriginal) {
        return precioOriginal;
    }

    @Override
    public String descripcion() {
        return "Sin descuento";
    }
}