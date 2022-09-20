package com.odontologica.clinica.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter

@Entity
@Table(name = "Dentista")
public class DentistaEntity {

    @Id
    @SequenceGenerator(name = "dentista_sequence",sequenceName = "dentista_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sequence_generator")
    @Column(name = "dentista_id")
    private Long id;
    private String nome;
    private String sobrenome;
    private String matricula;


    public DentistaEntity(Long id, String nome, String sobrenome, String matricula) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.matricula = matricula;
    }

    public DentistaEntity(String nome, String sobrenome, String matricula) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.matricula = matricula;
    }

    public DentistaEntity() {
    }
}
