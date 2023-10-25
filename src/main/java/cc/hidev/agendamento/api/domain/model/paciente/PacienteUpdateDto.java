package cc.hidev.agendamento.api.domain.model.paciente;

import cc.hidev.agendamento.api.domain.model.endereco.EnderecoDto;
import jakarta.validation.constraints.NotNull;

public record PacienteUpdateDto(
        @NotNull
        Long id,

        String nome,
        String email,
        String telefone,
        EnderecoDto endereco
) {

}
