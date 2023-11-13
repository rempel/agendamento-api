package cc.hidev.agendamento.api.domain.model.paciente;

import cc.hidev.agendamento.api.domain.model.endereco.EnderecoEntity;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Table(name = "paciente")
@Entity(name = "PacienteEntity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class PacienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    private Boolean ativo;

    @NotNull
    @Valid EnderecoEntity endereco;

    public PacienteEntity(PacienteCreateDto paciente) {
        this.nome = paciente.nome();
        this.email = paciente.email();
        this.telefone = paciente.telefone();
        this.cpf = paciente.cpf();
        this.ativo = true;
        this.endereco = new EnderecoEntity(paciente.endereco());
    }

    public void update(PacienteUpdateDto paciente) {
        if (paciente.nome() != null) {
            this.nome = paciente.nome();
        }

        if (paciente.email() != null) {
            this.email = paciente.email();
        }

        if (paciente.telefone() != null) {
            this.telefone = paciente.telefone();
        }

        if (paciente.endereco() != null) {
            this.endereco.update(paciente.endereco());
        }
    }

    public void delete() {
        this.ativo = false;
    }
}
