package cc.hidev.agendamento.api.model.medico;

public record MedicoListDto(Long id, String nome, String email, String telefone, String crm, EspecialidadeDto especialidade) {

    public MedicoListDto(MedicoEntity medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getTelefone(), medico.getCrm(), medico.getEspecialidade());
    }

}
