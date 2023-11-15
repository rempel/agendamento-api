package cc.hidev.agendamento.api.domain.validation;

import cc.hidev.agendamento.api.domain.model.consulta.ConsultaCreateDto;
import cc.hidev.agendamento.api.domain.repository.consulta.ConsultaRepository;
import cc.hidev.agendamento.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicoComConsultaNoMesmoHorarioAgendamentoValidator implements AgendamentoValidator {

    @Autowired
    private ConsultaRepository repository;

    public void validate(ConsultaCreateDto consultaCreateDto) {
        var medicoPossuiConsultaNoMesmoHorario = repository.existsByMedicoIdAndData(consultaCreateDto.idMedico(), consultaCreateDto.data());

        if (!medicoPossuiConsultaNoMesmoHorario) {
            return;
        }

        throw new ValidacaoException("Médico já possui outra consulta nesse mesmo horário");
    }
}
