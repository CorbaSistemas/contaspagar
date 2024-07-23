package com.example.contaspagar.controller;

import com.example.contaspagar.domain.Conta;
import com.example.contaspagar.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @PostMapping
    public ResponseEntity<Conta> createConta(@RequestBody Conta conta) {
        return ResponseEntity.ok(contaService.saveConta(conta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Conta> updateConta(@PathVariable Long id, @RequestBody Conta conta) {
        Conta existingConta = contaService.getContaById(id);
        if (existingConta == null) {
            return ResponseEntity.notFound().build();
        }
        conta.setId(id);
        return ResponseEntity.ok(contaService.saveConta(conta));
    }

    @PatchMapping("/{id}/situacao")
    public ResponseEntity<Conta> updateSituacao(@PathVariable Long id, @RequestBody String situacao) {
        Conta conta = contaService.getContaById(id);
        if (conta == null) {
            return ResponseEntity.notFound().build();
        }
        conta.setSituacao(situacao);
        return ResponseEntity.ok(contaService.saveConta(conta));
    }

    @GetMapping
    public ResponseEntity<Page<Conta>> getContas(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate,
            @RequestParam String descricao,
            @RequestParam int page,
            @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Conta> contas = contaService.getContas(startDate, endDate, descricao, pageable);
        return ResponseEntity.ok(contas.getContent());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Conta> getContaById(@PathVariable Long id) {
        Conta conta = contaService.getContaById(id);
        if (conta == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(conta);
    }

    @GetMapping("/total-pago")
    public ResponseEntity<BigDecimal> getTotalPago(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        return ResponseEntity.ok(contaService.getTotalPago(startDate, endDate));
    }

    @PostMapping("/import")
    public ResponseEntity<Void> importCSV(@RequestParam("file") MultipartFile file) {
        contaService.importCSV(file);
        return ResponseEntity.ok().build();
    }
}
