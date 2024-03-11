package com.kassiazama.bigchatbrasil.model.record;

import com.kassiazama.bigchatbrasil.model.TipoPlano;

import java.math.BigDecimal;

public record Saldos(BigDecimal saldoConsumido, BigDecimal saldoTotal, BigDecimal limiteTotal, TipoPlano tipoPlano) {
}
