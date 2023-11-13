package cc.hidev.agendamento.api.controller.paciente;

import cc.hidev.agendamento.api.domain.model.paciente.PacienteCreateDto;
import cc.hidev.agendamento.api.domain.model.paciente.PacienteEntity;
import cc.hidev.agendamento.api.domain.model.paciente.PacienteListDto;
import cc.hidev.agendamento.api.domain.model.paciente.PacienteUpdateDto;
import cc.hidev.agendamento.api.domain.repository.paciente.PacienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid PacienteCreateDto pacienteCreateDto, UriComponentsBuilder uriBuilder) {
        PacienteEntity paciente = repository.save(new PacienteEntity(pacienteCreateDto));

        URI uri = uriBuilder.path("/paciente/{id}").buildAndExpand(paciente.getId()).toUri();

        return ResponseEntity.created(uri).body(new PacienteListDto(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<PacienteListDto>> list(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginate) {
        Page<PacienteListDto> pacienteColletion = repository.findAll(paginate).map(PacienteListDto::new);

        return ResponseEntity.ok(pacienteColletion);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PacienteListDto>> listAll() {
        List<PacienteListDto> pacienteCollection = repository.findAll().stream().map(PacienteListDto::new).toList();

        return ResponseEntity.ok(pacienteCollection);
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid PacienteUpdateDto paciente) {
        PacienteEntity pacienteToUpdate = repository.getReferenceById(paciente.id());
        pacienteToUpdate.update(paciente);

        return ResponseEntity.ok(new PacienteListDto(pacienteToUpdate));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        PacienteEntity paciente = repository.getReferenceById(id);
        paciente.delete();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity read(@PathVariable Long id) {
        PacienteEntity paciente = repository.getReferenceById(id);
        return ResponseEntity.ok(new PacienteListDto(paciente));
    }


}
