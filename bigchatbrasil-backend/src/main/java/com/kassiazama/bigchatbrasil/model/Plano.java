package com.kassiazama.bigchatbrasil.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Plano implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @NotNull
    @Column(name = "tipoplano")
    @Enumerated(EnumType.STRING)
    private TipoPlano tipoPlano;

    @Column(name = "saldototal")
    private BigDecimal saldoPrePagoTotal = BigDecimal.ZERO;

    @Column(name = "limitetotal")
    private BigDecimal limiteTotal = BigDecimal.ZERO;

    @Column(name = "limiteconsumido")
    private BigDecimal limiteConsumido = BigDecimal.ZERO;
}
