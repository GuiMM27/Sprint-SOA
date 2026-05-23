CREATE TABLE leads_servico (
    id BIGSERIAL PRIMARY KEY,
    chassi_vin VARCHAR(17) NOT NULL,
    concessionaria_id BIGINT NOT NULL,
    probabilidade_manutencao DECIMAL(5,2) NOT NULL,
    tipo_servico_sugerido VARCHAR(100),
    status VARCHAR(20) DEFAULT 'NOVO'
);