package cc.hidev.agendamento.api.controller.consulta;

import cc.hidev.agendamento.api.domain.model.consulta.ConsultaCancelDto;
import cc.hidev.agendamento.api.domain.model.consulta.ConsultaCreateDto;
import cc.hidev.agendamento.api.service.consulta.ConsultaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consulta")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid ConsultaCreateDto consultaCreateDto) {
        var consultaListDto = consultaService.agendar(consultaCreateDto);
        return ResponseEntity.ok(consultaListDto);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancel(@RequestBody @Valid ConsultaCancelDto consultaCancelDto) {
        consultaService.cancelar(consultaCancelDto);
        return ResponseEntity.noContent().build();
    }
}
