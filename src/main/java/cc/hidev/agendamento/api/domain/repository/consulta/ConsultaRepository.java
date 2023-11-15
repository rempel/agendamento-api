package cc.hidev.agendamento.api.domain.repository.consulta;

import cc.hidev.agendamento.api.domain.model.consulta.ConsultaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<ConsultaEntity, Long> {

    boolean existsByPacienteIdAndDataBetween(Long idPaciente, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);

    boolean existsByMedicoIdAndData(Long idMedico, LocalDateTime data);

}
