package cc.hidev.agendamento.api.domain.model.medico;

import cc.hidev.agendamento.api.domain.model.endereco.EnderecoEntity;

public record MedicoListDto(Long id, String nome, String email, String telefone, String crm, EspecialidadeDto especialidade, EnderecoEntity endereco) {

    public MedicoListDto(MedicoEntity medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getTelefone(), medico.getCrm(), medico.getEspecialidade(), medico.getEndereco());
    }
}
