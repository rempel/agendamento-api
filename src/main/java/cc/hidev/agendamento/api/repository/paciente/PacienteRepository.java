package cc.hidev.agendamento.api.repository.paciente;

import cc.hidev.agendamento.api.model.paciente.PacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<PacienteEntity, Long> {

}
