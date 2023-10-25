package cc.hidev.agendamento.api.domain.repository.paciente;

import cc.hidev.agendamento.api.domain.model.paciente.PacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<PacienteEntity, Long> {

}
