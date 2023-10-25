package cc.hidev.agendamento.api.domain.repository.medico;

import cc.hidev.agendamento.api.domain.model.medico.MedicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<MedicoEntity, Long> {

}
