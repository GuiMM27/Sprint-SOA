package br.com.fiap.ford_vin_share_api.controllers;

import br.com.fiap.ford_vin_share_api.models.Lead;
import br.com.fiap.ford_vin_share_api.services.LeadService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/leads")
public class LeadController {

    @Autowired
    private LeadService service;

    // 1. GET - Listar todos os leads
    @GetMapping
    public ResponseEntity<List<Lead>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    // 2. GET - Buscar por ID (Demonstra tratamento de erro 404 se não existir)
    @GetMapping("/{id}")
    public ResponseEntity<Lead> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    // 3. GET - Buscar leads de uma concessionária específica (Análise e Visão 360)
    @GetMapping("/concessionaria/{concessionariaId}")
    public ResponseEntity<List<Lead>> listarPorConcessionaria(@PathVariable Long concessionariaId) {
        return ResponseEntity.ok(service.listarPorConcessionaria(concessionariaId));
    }

    // 4. POST - Criar um Lead Preditivo (Uso correto do Status 201 CREATED)
    @PostMapping
    public ResponseEntity<Lead> criar(@Valid @RequestBody Lead lead) {
        Lead novoLead = service.criar(lead);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoLead);
    }

    // 5. PUT - Atualizar Status na Jornada do Cliente (Status 200 OK)
    @PutMapping("/{id}/status")
    public ResponseEntity<Lead> atualizarStatus(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(service.atualizarStatus(id, status));
    }

    // 6. DELETE - Remover Lead (Uso correto do Status 204 NO CONTENT)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}