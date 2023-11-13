package cc.hidev.agendamento.api.domain.model.consulta;

import cc.hidev.agendamento.api.domain.model.medico.EspecialidadeDto;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ConsultaCreateDto(
        Long idMedico,

        @NotNull
        Long idPaciente,

        @NotNull
        @Future
        LocalDateTime data,

        EspecialidadeDto especialidade
) {
}
