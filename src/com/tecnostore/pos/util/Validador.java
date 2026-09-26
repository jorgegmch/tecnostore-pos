package com.tecnostore.pos.util;

import java.math.BigDecimal;
import java.util.regex.Pattern;

/**
 *
 * @author Jorge Gómez
 */
public class Validador {
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
    );

    /**
     * Valida que un texto no sea nulo ni vacío
     */
    public static boolean esTextoValido(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }

    /**
     * Valida el formato de un correo electrónico
     */
    public static boolean esCorreoValido(String correo) {
        if (correo == null || correo.isEmpty()) return false;
        return EMAIL_PATTERN.matcher(correo).matches();
    }

    /**
     * Valida que un precio sea positivo
     */
    public static boolean esPrecioValido(BigDecimal precio) {
        return precio != null && precio.compareTo(BigDecimal.ZERO) > 0;
    }

    /**
     * Valida que un stock sea un entero no negativo
     */
    public static boolean esStockValido(int stock) {
        return stock >= 0;
    }
}