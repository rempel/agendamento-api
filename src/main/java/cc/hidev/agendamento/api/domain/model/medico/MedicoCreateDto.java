package cc.hidev.agendamento.api.domain.model.medico;

import cc.hidev.agendamento.api.domain.model.endereco.EnderecoDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record MedicoCreateDto(
        @NotBlank
        String nome,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @Pattern(regexp = "\\d{10,11}")
        String telefone,

        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,

        @NotNull
        EspecialidadeDto especialidade,

        @NotNull
        @Valid
        EnderecoDto endereco
) {
}