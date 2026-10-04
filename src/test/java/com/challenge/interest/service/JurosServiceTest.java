package com.challenge.interest.service;

import com.challenge.interest.dto.JurosRequest;
import com.challenge.interest.dto.JurosResponse;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JurosServiceTest {

    private static final ZoneId ZONE = ZoneId.of("America/Sao_Paulo");

    @Test
    void semAtrasoNaoGeraJuros() {
        JurosService service = service("2026-10-03");
        JurosResponse response = service.calcular(request("1000.00", "2026-10-03"));

        assertEquals(0, response.getDiasAtraso());
        assertEquals(new BigDecimal("0.00"), response.getJuros());
        assertEquals(new BigDecimal("1000.00"), response.getValorAtualizado());
    }

    @Test
    void doisDiasDeAtrasoAplicamDoisEMeioPorcentoAoDia() {
        JurosService service = service("2026-10-05");
        JurosResponse response = service.calcular(request("1000.00", "2026-10-03"));

        assertEquals(2, response.getDiasAtraso());
        assertEquals(new BigDecimal("50.00"), response.getJuros());
        assertEquals(new BigDecimal("1050.00"), response.getValorAtualizado());
    }

    private JurosService service(String hoje) {
        Instant instant = LocalDate.parse(hoje).atStartOfDay(ZONE).toInstant();
        return new JurosService(Clock.fixed(instant, ZONE));
    }

    private JurosRequest request(String valor, String vencimento) {
        JurosRequest request = new JurosRequest();
        request.setValor(new BigDecimal(valor));
        request.setDataVencimento(LocalDate.parse(vencimento));
        return request;
    }
}
