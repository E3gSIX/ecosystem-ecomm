package br.com.fiap.postech.adjt.cart.model.dto.response;

import java.math.BigDecimal;

public record ItemResponse(
        String name,
        BigDecimal price,
        Integer quantity
) {
}
