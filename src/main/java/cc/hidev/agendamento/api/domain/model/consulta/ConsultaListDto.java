package cc.hidev.agendamento.api.domain.model.consulta;

import java.time.LocalDateTime;

public record ConsultaListDto(Long id, Long idMedico, Long idPaciente, LocalDateTime data) {

    public ConsultaListDto(ConsultaEntity consulta) {
        this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData());
    }
}
