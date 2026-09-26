package com.tecnostore.pos.patron;

import java.math.BigDecimal;

/**
 *
 * @author Jorge Gómez
 */
public class StrategyDescuento {
    private EstrategiaDescuento estrategia;

    public StrategyDescuento(EstrategiaDescuento estrategia) {
        this.estrategia = estrategia;
    }

    public void setEstrategia(EstrategiaDescuento estrategia) {
        this.estrategia = estrategia;
    }

    public BigDecimal aplicarDescuento(BigDecimal precio) {
        return estrategia.aplicar(precio);
    }

    public String getDescripcion() {
        return estrategia.descripcion();
    }
}