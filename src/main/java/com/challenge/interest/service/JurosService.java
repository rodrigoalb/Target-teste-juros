package com.challenge.interest.service;

import com.challenge.interest.dto.JurosRequest;
import com.challenge.interest.dto.JurosResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Clock;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class JurosService {

    public static final BigDecimal MULTA_DIARIA = new BigDecimal("0.025");

    private final Clock clock;

    public JurosService(Clock clock) {
        this.clock = clock;
    }

    public JurosResponse calcular(JurosRequest request) {
        LocalDate hoje = LocalDate.now(clock);
        long diasAtraso = Math.max(0, ChronoUnit.DAYS.between(request.getDataVencimento(), hoje));
        BigDecimal valor = request.getValor().setScale(2, RoundingMode.HALF_UP);
        BigDecimal juros = valor
                .multiply(MULTA_DIARIA)
                .multiply(BigDecimal.valueOf(diasAtraso))
                .setScale(2, RoundingMode.HALF_UP);
        BigDecimal valorAtualizado = valor.add(juros);

        return new JurosResponse(
                valor,
                request.getDataVencimento(),
                hoje,
                diasAtraso,
                MULTA_DIARIA,
                juros,
                valorAtualizado
        );
    }
}
