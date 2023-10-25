package cc.hidev.agendamento.api.domain.model.medico;

import cc.hidev.agendamento.api.domain.model.endereco.EnderecoEntity;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "medico")
@Entity(name = "MedicoEntity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class MedicoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String telefone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private EspecialidadeDto especialidade;

    @Embedded
    private EnderecoEntity endereco;

    public MedicoEntity(MedicoCreateDto medico) {
        this.nome = medico.nome();
        this.email = medico.email();
        this.telefone = medico.telefone();
        this.crm = medico.crm();
        this.especialidade = medico.especialidade();
        this.endereco = new EnderecoEntity(medico.endereco());
    }

    public void update(MedicoUpdateDto medico) {
        if (medico.nome() != null) {
            this.nome = medico.nome();
        }

        if (medico.email() != null) {
            this.email = medico.email();
        }

        if (medico.telefone() != null) {
            this.telefone = medico.telefone();
        }

        if (medico.endereco() != null) {
            this.endereco.update(medico.endereco());
        }
    }
}
