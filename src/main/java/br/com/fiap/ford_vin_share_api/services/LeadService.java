package br.com.fiap.ford_vin_share_api.services;

import br.com.fiap.ford_vin_share_api.exceptions.ResourceNotFoundException;
import br.com.fiap.ford_vin_share_api.models.Lead;
import br.com.fiap.ford_vin_share_api.repositories.LeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeadService {

    @Autowired
    private LeadRepository repository;

    public List<Lead> listarTodos() {
        return repository.findAll();
    }

    public List<Lead> listarPorConcessionaria(Long concessionariaId) {
        return repository.findByConcessionariaId(concessionariaId);
    }

    public Lead buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lead com ID " + id + " não encontrado."));
    }

    public Lead criar(Lead lead) {
        // Regra de Negócio: Todo lead preditivo começa com o status NOVO
        lead.setStatus("NOVO");
        return repository.save(lead);
    }

    public Lead atualizarStatus(Long id, String novoStatus) {
        Lead leadExistente = buscarPorId(id);

        // Validação simples de status para a jornada do cliente
        leadExistente.setStatus(novoStatus.toUpperCase());
        return repository.save(leadExistente);
    }

    public void deletar(Long id) {
        Lead leadExistente = buscarPorId(id);
        repository.delete(leadExistente);
    }
}