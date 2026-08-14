package br.com.datum.score.controller;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ScoreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveRetornarScoreMockParaCpfValido() throws Exception {
        String cpf = "11604567805";

        mockMvc.perform(get("/scores/{cpf}", cpf))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cpf", is(cpf)))
                .andExpect(jsonPath("$.score", is(750)))
                .andExpect(jsonPath("$.classification", is("LOW_RISK")));
    }

    @Test
    void deveEcoarCpfDiferenteInformadoNaRequisicao() throws Exception {
        String cpf = "52998224725";

        mockMvc.perform(get("/scores/{cpf}", cpf))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cpf", is(cpf)));
    }

    @Test
    void deveRetornarBadRequestParaCpfComFormatoInvalido() throws Exception {
        mockMvc.perform(get("/scores/{cpf}", "abc123"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveRetornarBadRequestParaCpfComDigitosVerificadoresIncorretos() throws Exception {
        mockMvc.perform(get("/scores/{cpf}", "11604567800"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveRetornarBadRequestParaCpfComDigitosRepetidos() throws Exception {
        mockMvc.perform(get("/scores/{cpf}", "11111111111"))
                .andExpect(status().isBadRequest());
    }
}
