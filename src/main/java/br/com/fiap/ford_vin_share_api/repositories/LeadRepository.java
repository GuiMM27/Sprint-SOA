package br.com.fiap.ford_vin_share_api.repositories;

import br.com.fiap.ford_vin_share_api.models.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Long> {
    // Buscar todos os leads de uma concessionária específica (Regra de negócio da Ford)
    List<Lead> findByConcessionariaId(Long concessionariaId);
}