package cc.hidev.agendamento.api.domain.repository.medico;

import cc.hidev.agendamento.api.domain.model.medico.EspecialidadeDto;
import cc.hidev.agendamento.api.domain.model.medico.MedicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<MedicoEntity, Long> {

    @Query("""
            select m from MedicoEntity m
            where 
            m.ativo = true
            and
            m.especialidade = :especialidade
            and
            m.id not in(
                select c.medico.id from ConsultaEntity c
                where
                c.data = :data
            )
            order by rand()
            limit 1
            """)
    MedicoEntity medicoAleatorioEspecialidadeNaData(EspecialidadeDto especialidade, LocalDateTime data);
}
