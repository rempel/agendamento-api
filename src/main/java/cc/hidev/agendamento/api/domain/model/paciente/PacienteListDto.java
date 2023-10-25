package cc.hidev.agendamento.api.domain.model.paciente;

import cc.hidev.agendamento.api.domain.model.endereco.EnderecoEntity;

public record PacienteListDto(Long id, String nome, String email, String telefone, String cpf, EnderecoEntity endereco) {

    public PacienteListDto(PacienteEntity paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(), paciente.getEndereco());
    }

}
