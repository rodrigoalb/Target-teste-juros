package com.challenge.interest.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class JurosResponse {

    private BigDecimal valorOriginal;
    private LocalDate dataVencimento;
    private LocalDate dataCalculo;
    private long diasAtraso;
    private BigDecimal percentualDiario;
    private BigDecimal juros;
    private BigDecimal valorAtualizado;

    public JurosResponse(BigDecimal valorOriginal, LocalDate dataVencimento, LocalDate dataCalculo,
                         long diasAtraso, BigDecimal percentualDiario, BigDecimal juros, BigDecimal valorAtualizado) {
        this.valorOriginal = valorOriginal;
        this.dataVencimento = dataVencimento;
        this.dataCalculo = dataCalculo;
        this.diasAtraso = diasAtraso;
        this.percentualDiario = percentualDiario;
        this.juros = juros;
        this.valorAtualizado = valorAtualizado;
    }

    public BigDecimal getValorOriginal() {
        return valorOriginal;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public LocalDate getDataCalculo() {
        return dataCalculo;
    }

    public long getDiasAtraso() {
        return diasAtraso;
    }

    public BigDecimal getPercentualDiario() {
        return percentualDiario;
    }

    public BigDecimal getJuros() {
        return juros;
    }

    public BigDecimal getValorAtualizado() {
        return valorAtualizado;
    }
}
