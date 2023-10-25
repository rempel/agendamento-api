package cc.hidev.agendamento.api.domain.model.medico;

import cc.hidev.agendamento.api.domain.model.endereco.EnderecoDto;
import jakarta.validation.constraints.NotNull;

public record MedicoUpdateDto(
        @NotNull
        Long id,

        String nome,
        String email,
        String telefone,
        EnderecoDto endereco
) {

}
