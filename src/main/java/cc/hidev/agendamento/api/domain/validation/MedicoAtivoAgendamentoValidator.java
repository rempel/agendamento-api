package cc.hidev.agendamento.api.domain.validation;

import cc.hidev.agendamento.api.domain.model.consulta.ConsultaCreateDto;
import cc.hidev.agendamento.api.domain.repository.medico.MedicoRepository;
import cc.hidev.agendamento.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicoAtivoAgendamentoValidator implements AgendamentoValidator {

    @Autowired
    private MedicoRepository repository;

    public void validate(ConsultaCreateDto consultaCreateDto) {
        if (consultaCreateDto.idMedico() == null) {
            return;
        }

        var medicoEstaAtivo = repository.findAtivoById(consultaCreateDto.idMedico());
        if (medicoEstaAtivo) {
            return;
        }

        throw new ValidacaoException("Consulta não pode ser agendada com médico excluído");
    }

}
