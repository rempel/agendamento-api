package cc.hidev.agendamento.api.domain.validation;

import cc.hidev.agendamento.api.domain.model.consulta.ConsultaCreateDto;

public interface AgendamentoValidator {

    void validate(ConsultaCreateDto consultaCreateDto);

}
