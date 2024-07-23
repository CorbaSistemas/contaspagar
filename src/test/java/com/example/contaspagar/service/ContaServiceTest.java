package com.example.contaspagar.service;

import com.example.contaspagar.domain.Conta;
import com.example.contaspagar.infrastructure.ContaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ContaServiceTest {

    @Mock
    private ContaRepository contaRepository;

    @InjectMocks
    private ContaService contaService;

    public ContaServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveConta() {
        Conta conta = new Conta();
        when(contaRepository.save(conta)).thenReturn(conta);
        Conta savedConta = contaService.saveConta(conta);
        assertEquals(conta, savedConta);
    }

    @Test
    public void testGetContas() {
        List<Conta> contas = Arrays.asList(new Conta(), new Conta());
        when(contaRepository.findByDataVencimentoBetweenAndDescricaoContaining(any(), any(), anyString())).thenReturn(contas);
        List<Conta> result = contaService.getContas(LocalDate.now(), LocalDate.now(), "descricao");
        assertEquals(contas, result);
    }

    @Test
    public void testGetTotalPago() {
        Conta conta1 = new Conta();
        conta1.setValor(new BigDecimal("100.00"));
        Conta conta2 = new Conta();
        conta2.setValor(new BigDecimal("200.00"));
        List<Conta> contas = Arrays.asList(conta1, conta2);
        when(contaRepository.findByDataVencimentoBetweenAndDescricaoContaining(any(), any(), anyString())).thenReturn(contas);
        BigDecimal totalPago = contaService.getTotalPago(LocalDate.now(), LocalDate.now());
        assertEquals(new BigDecimal("300.00"), totalPago);
    }
}
