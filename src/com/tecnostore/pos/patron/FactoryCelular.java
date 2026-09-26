package com.tecnostore.pos.patron;

import com.tecnostore.pos.modelo.CategoriaGama;
import com.tecnostore.pos.modelo.Celular;
import com.tecnostore.pos.modelo.SistemaOperativo;
import java.math.BigDecimal;

public class FactoryCelular {

    private static final BigDecimal PRECIO_GAMA_ALTA = new BigDecimal("3000001");
    private static final BigDecimal PRECIO_GAMA_MEDIA = new BigDecimal("1000001");

    public static Celular crear(String marca, String modelo, BigDecimal precio,
                                int stock, SistemaOperativo so) {
        CategoriaGama gama = determinarGama(precio);
        return new Celular(null, marca, modelo, precio, stock, so, gama);
    }

    /**
     * Determina la gama según el precio. Se usa al crear un celular y al
     * actualizar su precio, para que gama y precio nunca queden desincronizados.
     */
    public static CategoriaGama determinarGama(BigDecimal precio) {
        if (precio.compareTo(PRECIO_GAMA_ALTA) >= 0) {
            return CategoriaGama.ALTA;
        } else if (precio.compareTo(PRECIO_GAMA_MEDIA) >= 0) {
            return CategoriaGama.MEDIA;
        } else {
            return CategoriaGama.BAJA;
        }
    }
}