package cc.hidev.agendamento.api.domain.validation;

import cc.hidev.agendamento.api.domain.model.consulta.ConsultaCreateDto;
import cc.hidev.agendamento.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class HorarioAntecedenciaAgendamentoValidator implements AgendamentoValidator {

    @Value("${business.rules.agendamento.antecedencia.minutos}")
    private Integer antecedenciaEmMinutos;

    public void validate(ConsultaCreateDto consultaCreateDto) {
        var dataConsulta = consultaCreateDto.data();
        var agora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(agora, dataConsulta).toMinutes();

        if (diferencaEmMinutos >= antecedenciaEmMinutos) {
            return;
        }

        throw new ValidacaoException("Consulta deve ser agendada com antecedência mínima de " + antecedenciaEmMinutos + " minutos");
    }

}
