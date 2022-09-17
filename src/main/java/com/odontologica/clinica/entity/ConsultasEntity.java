package com.odontologica.clinica.entity;

import com.odontologica.clinica.controller.dto.ConsultaDTO;
import com.odontologica.clinica.controller.dto.ConsultaRespostaDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "Consultas")
@AllArgsConstructor
@NoArgsConstructor
@Getter

public class ConsultasEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private Date dataConsulta;
    private LocalDateTime horaConsulta;

    @ManyToOne
    private DentistaEntity dentista;

    @ManyToOne
    private PacienteEntity paciente;

    public ConsultasEntity(Date dataConsulta, LocalDateTime horaConsulta, DentistaEntity dentista, PacienteEntity paciente) {
        this.dataConsulta = dataConsulta;
        this.horaConsulta = horaConsulta;
        this.dentista = dentista;
        this.paciente = paciente;
    }

    public ConsultaDTO dtoResposta(){
        ConsultaDTO consulta = new ConsultaDTO(this.dtoResposta().getDataConsulta(), this.dtoResposta().getHoraConsulta(),
                this.dtoResposta().getIdDentista(), this.dtoResposta().getIdPaciente());

        return new ConsultaDTO(
                this.dataConsulta,
                this.horaConsulta,
                consulta.getIdDentista(),
                consulta.getIdPaciente()
        );

    }


}
