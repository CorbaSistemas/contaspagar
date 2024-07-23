package com.example.contaspagar.infrastructure;

import com.example.contaspagar.domain.Conta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
    List<Conta> findByDataVencimentoBetweenAndDescricaoContaining(LocalDate startDate, LocalDate endDate, String descricao);
    
    Page<Conta> findByDataVencimentoBetweenAndDescricaoContaining(
            LocalDate startDate, LocalDate endDate, String descricao, Pageable pageable);
}
