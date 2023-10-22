package cc.hidev.agendamento.api.repository.medico;

import cc.hidev.agendamento.api.model.medico.MedicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<MedicoEntity, Long> {

}
