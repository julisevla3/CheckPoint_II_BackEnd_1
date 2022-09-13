package com.odontologica.clinica.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Consultas")
@AllArgsConstructor
@NoArgsConstructor

public class ConsultasEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private Date dataConsulta;
    private Time horaConsulta;

    private List<ConsultasEntity> consultasEntityList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dentista_id")
    private DentistaEntity dentistaEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private PacienteEntity pacienteEntity;

    public Long getId() {
        return id;
    }
}
