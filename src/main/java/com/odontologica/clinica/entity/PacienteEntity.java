package com.odontologica.clinica.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter

@Entity
@Table(name = "Paciente")
public class PacienteEntity {
    @Id
    @SequenceGenerator(name = "paciente_sequence",sequenceName = "paciente_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sequence_generator")
    private Long id;
    private String nome;
    private String sobrenome;
    private String endereco;
    private String rg;
    private Date dataAlta;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private DentistaEntity dentistaEntity;

    public PacienteEntity(Long id, String nome, String sobrenome, String endereco, String rg, Date dataAlta) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.endereco = endereco;
        this.rg = rg;
        this.dataAlta = dataAlta;
    }

    public PacienteEntity(String nome, String sobrenome, String endereco, String rg, Date dataAlta) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.endereco = endereco;
        this.rg = rg;
        this.dataAlta = dataAlta;
    }

    public PacienteEntity() {
    }
}
