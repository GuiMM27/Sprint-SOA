package br.com.fiap.ford_vin_share_api.models;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "leads_servico")
public class Lead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "chassi_vin", nullable = false, length = 17)
    private String chassiVin;

    @Column(name = "concessionaria_id", nullable = false)
    private Long concessionariaId;

    @Column(name = "probabilidade_manutencao", nullable = false)
    private BigDecimal probabilidadeManutencao;

    @Column(name = "tipo_servico_sugerido")
    private String tipoServicoSugerido;

    private String status;

    // Construtor vazio exigido pelo JPA
    public Lead() {}

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getChassiVin() { return chassiVin; }
    public void setChassiVin(String chassiVin) { this.chassiVin = chassiVin; }

    public Long getConcessionariaId() { return concessionariaId; }
    public void setConcessionariaId(Long concessionariaId) { this.concessionariaId = concessionariaId; }

    public BigDecimal getProbabilidadeManutencao() { return probabilidadeManutencao; }
    public void setProbabilidadeManutencao(BigDecimal probabilidadeManutencao) { this.probabilidadeManutencao = probabilidadeManutencao; }

    public String getTipoServicoSugerido() { return tipoServicoSugerido; }
    public void setTipoServicoSugerido(String tipoServicoSugerido) { this.tipoServicoSugerido = tipoServicoSugerido; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}