package cc.hidev.agendamento.api.domain.validation;

import cc.hidev.agendamento.api.domain.model.consulta.ConsultaCreateDto;
import cc.hidev.agendamento.api.domain.repository.consulta.ConsultaRepository;
import cc.hidev.agendamento.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteComConsultaNoMesmoDiaAgendamentoValidator implements AgendamentoValidator {

    @Autowired
    private ConsultaRepository repository;

    public void validate(ConsultaCreateDto consultaCreateDto) {
        var primeiroHorario = consultaCreateDto.data().withHour(7);
        var ultimoHorario = consultaCreateDto.data().withHour(18);
        var pacienteComConsultaNoMesmoDia = repository.existsByPacienteIdAndDataBetween(consultaCreateDto.idPaciente(), primeiroHorario, ultimoHorario);

        if (!pacienteComConsultaNoMesmoDia) {
            return;
        }

        throw new ValidacaoException("Paciente já possui outra consulta nesse dia");
    }
}
