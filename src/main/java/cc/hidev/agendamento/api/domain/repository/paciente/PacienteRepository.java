package cc.hidev.agendamento.api.domain.repository.paciente;

import cc.hidev.agendamento.api.domain.model.paciente.PacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PacienteRepository extends JpaRepository<PacienteEntity, Long> {

    @Query("""
            select p.ativo
            from PacienteEntity p
            where
            p.id = :id
            """)
    boolean findAtivoById(Long id);

}
