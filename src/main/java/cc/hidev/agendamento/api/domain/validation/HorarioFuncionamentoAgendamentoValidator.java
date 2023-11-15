package cc.hidev.agendamento.api.domain.validation;

import cc.hidev.agendamento.api.domain.model.consulta.ConsultaCreateDto;
import cc.hidev.agendamento.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class HorarioFuncionamentoAgendamentoValidator implements AgendamentoValidator {

    @Value("${business.rules.horario.atendimento.primeiro}")
    private Integer horarioPrimeiroAtendimento;

    @Value("${business.rules.horario.atendimento.ultimo}")
    private Integer horarioUltimoAtendimento;

    public void validate(ConsultaCreateDto consultaCreateDto) {
        var dataConsulta = consultaCreateDto.data();
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAberturaDaClinica = dataConsulta.getHour() < horarioPrimeiroAtendimento;
        var depoisDoEncerramentoDaClinica = dataConsulta.getHour() > horarioUltimoAtendimento;

        if (!domingo && !antesDaAberturaDaClinica && !depoisDoEncerramentoDaClinica) {
            return;
        }

        throw new ValidacaoException("Consulta fora do horário de funcionamento da clinica");
    }
}
