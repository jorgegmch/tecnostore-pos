package com.tecnostore.pos.patron;

import java.math.BigDecimal;

/**
 *
 * @author camper
 */
public class DescuentoGamaBaja implements EstrategiaDescuento {
    private static final BigDecimal PORCENTAJE = new BigDecimal("0.05");

    @Override
    public BigDecimal aplicar(BigDecimal precioOriginal) {
        return precioOriginal.subtract(precioOriginal.multiply(PORCENTAJE));
    }

    @Override
    public String descripcion() {
        return "Descuento gama baja 5%";
    }
}