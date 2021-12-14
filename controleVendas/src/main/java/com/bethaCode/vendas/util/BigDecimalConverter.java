package com.bethaCode.vendas.util;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BigDecimalConverter {

    public BigDecimal converte(String valorDTO){
        valorDTO = valorDTO
                    .replace(".", "")
                    .replace(",", ".");
        return new BigDecimal(valorDTO);
    }
}
