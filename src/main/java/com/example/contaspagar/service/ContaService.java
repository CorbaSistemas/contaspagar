package com.example.contaspagar.service;

import com.example.contaspagar.domain.Conta;
import com.example.contaspagar.infrastructure.ContaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    public Conta saveConta(Conta conta) {
        return contaRepository.save(conta);
    }

    public List<Conta> getContas(LocalDate startDate, LocalDate endDate, String descricao) {
        return contaRepository.findByDataVencimentoBetweenAndDescricaoContaining(startDate, endDate, descricao);
    }
    
    public Page<Conta> getContas(LocalDate startDate, LocalDate endDate, String descricao, Pageable pageable) {
        return contaRepository.findByDataVencimentoBetweenAndDescricaoContaining(startDate, endDate, descricao, pageable);
    }

    public Conta getContaById(Long id) {
        return contaRepository.findById(id).orElse(null);
    }

    public void deleteConta(Long id) {
        contaRepository.deleteById(id);
    }

    public BigDecimal getTotalPago(LocalDate startDate, LocalDate endDate) {
        List<Conta> contas = contaRepository.findByDataVencimentoBetweenAndDescricaoContaining(startDate, endDate, "");
        return contas.stream()
                .map(Conta::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void importCSV(MultipartFile file) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Conta conta = new Conta();
                conta.setDataVencimento(LocalDate.parse(data[0]));
                conta.setDataPagamento(LocalDate.parse(data[1]));
                conta.setValor(new BigDecimal(data[2]));
                conta.setDescricao(data[3]);
                conta.setSituacao(data[4]);
                contaRepository.save(conta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
