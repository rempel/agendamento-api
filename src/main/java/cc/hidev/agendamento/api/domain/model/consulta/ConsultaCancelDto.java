package cc.hidev.agendamento.api.domain.model.consulta;

import jakarta.validation.constraints.NotNull;

public record ConsultaCancelDto (
        @NotNull
        Long idConsulta,

        @NotNull
        MotivoCancelamentoDto motivo
) {
}
