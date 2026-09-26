package com.tecnostore.pos.patron;

import java.math.BigDecimal;

/**
 *
 * @author camper
 */
public interface EstrategiaDescuento {
    BigDecimal aplicar(BigDecimal precioOriginal);
    String descripcion();
}