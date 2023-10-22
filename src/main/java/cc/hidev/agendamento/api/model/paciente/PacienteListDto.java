package cc.hidev.agendamento.api.model.paciente;

public record PacienteListDto(Long id, String nome, String email, String telefone, String cpf) {

    public PacienteListDto(PacienteEntity paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf());
    }

}
