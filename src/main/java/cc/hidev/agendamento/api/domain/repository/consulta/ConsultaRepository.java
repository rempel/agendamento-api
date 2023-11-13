package cc.hidev.agendamento.api.domain.repository.consulta;

import cc.hidev.agendamento.api.domain.model.consulta.ConsultaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository<ConsultaEntity, Long> {

}
