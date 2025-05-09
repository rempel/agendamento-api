package cc.hidev.agendamento.api.controller.medico;

import cc.hidev.agendamento.api.domain.model.medico.MedicoCreateDto;
import cc.hidev.agendamento.api.domain.model.medico.MedicoEntity;
import cc.hidev.agendamento.api.domain.model.medico.MedicoListDto;
import cc.hidev.agendamento.api.domain.model.medico.MedicoUpdateDto;
import cc.hidev.agendamento.api.domain.repository.medico.MedicoRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/medico")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid MedicoCreateDto medicoCreateDto, UriComponentsBuilder uriBuilder) {
        var medico = repository.save(new MedicoEntity(medicoCreateDto));
        var uri = uriBuilder.path("/medico/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new MedicoListDto(medico));
    }

    @GetMapping
    public ResponseEntity<Page<MedicoListDto>> list(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginate) {
        var medicosCollection = repository.findAll(paginate).map(MedicoListDto::new);
        return ResponseEntity.ok(medicosCollection);
    }

    @GetMapping("/all")
    public ResponseEntity<List<MedicoListDto>> listAll() {
        var medicoCollection =  repository.findAll().stream().map(MedicoListDto::new).toList();
        return ResponseEntity.ok(medicoCollection);
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid MedicoUpdateDto medico) {
        var medicoToUpdate = repository.getReferenceById(medico.id());
        medicoToUpdate.update(medico);
        return ResponseEntity.ok(new MedicoListDto(medicoToUpdate));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        medico.delete();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity read(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new MedicoListDto(medico));
    }
}
