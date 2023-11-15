package cc.hidev.agendamento.api.service.consulta;

import cc.hidev.agendamento.api.domain.model.consulta.ConsultaCancelDto;
import cc.hidev.agendamento.api.domain.model.consulta.ConsultaCreateDto;
import cc.hidev.agendamento.api.domain.model.consulta.ConsultaEntity;
import cc.hidev.agendamento.api.domain.model.consulta.ConsultaListDto;
import cc.hidev.agendamento.api.domain.model.medico.MedicoEntity;
import cc.hidev.agendamento.api.domain.repository.consulta.ConsultaRepository;
import cc.hidev.agendamento.api.domain.repository.medico.MedicoRepository;
import cc.hidev.agendamento.api.domain.repository.paciente.PacienteRepository;
import cc.hidev.agendamento.api.domain.validation.AgendamentoValidator;
import cc.hidev.agendamento.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<AgendamentoValidator> validators;

    public ConsultaListDto agendar(ConsultaCreateDto consultaCreateDto) {
        if (consultaCreateDto.idMedico() != null && !medicoRepository.existsById(consultaCreateDto.idMedico())) {
            throw new ValidacaoException("ID do medico informado não existe");
        }

        if (!pacienteRepository.existsById(consultaCreateDto.idPaciente())) {
            throw new ValidacaoException("ID do paciente informado não existe");
        }

        validators.forEach(v -> v.validate(consultaCreateDto));

        var medico = escolherMedico(consultaCreateDto);
        if (medico == null) {
            throw new ValidacaoException("Não existe médico disponível nesta data");
        }

        var paciente = pacienteRepository.getReferenceById(consultaCreateDto.idPaciente());
        var consulta = new ConsultaEntity(null, medico, paciente, consultaCreateDto.data(), null);
        consultaRepository.save(consulta);

        return new ConsultaListDto(consulta);
    }

    private MedicoEntity escolherMedico(ConsultaCreateDto consultaCreateDto) {
        if (consultaCreateDto.idMedico() != null) {
            return medicoRepository.getReferenceById(consultaCreateDto.idMedico());
        }

        if (consultaCreateDto.especialidade() == null) {
            throw new ValidacaoException("Especialidade é obrigatória quando médico não informado");
        }

        return medicoRepository.medicoAleatorioEspecialidadeNaData(consultaCreateDto.especialidade(), consultaCreateDto.data());
    }

    public void cancelar(ConsultaCancelDto consultaCancelDto) {
        if (!consultaRepository.existsById(consultaCancelDto.idConsulta())) {
            throw new ValidacaoException("Id da consulta informado não existe!");
        }

        var consulta = consultaRepository.getReferenceById(consultaCancelDto.idConsulta());
        consulta.cancelar(consultaCancelDto.motivo());
    }
}
