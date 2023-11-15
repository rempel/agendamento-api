package cc.hidev.agendamento.api.domain.validation;

import cc.hidev.agendamento.api.domain.model.consulta.ConsultaCreateDto;
import cc.hidev.agendamento.api.domain.repository.paciente.PacienteRepository;
import cc.hidev.agendamento.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteAtivoAgendamentoValidator implements AgendamentoValidator {

    @Autowired
    private PacienteRepository repository;

    public void validate(ConsultaCreateDto consultaCreateDto) {
        var pacienteEstaAtivo = repository.findAtivoById(consultaCreateDto.idPaciente());

        if (pacienteEstaAtivo) {
            return;
        }

        throw new ValidacaoException("Consulta não pode ser agendada com paciente excluído");
    }

}
