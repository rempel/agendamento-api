package cc.hidev.agendamento.api.controller.medico;

import cc.hidev.agendamento.api.domain.model.medico.MedicoCreateDto;
import cc.hidev.agendamento.api.domain.model.medico.MedicoEntity;
import cc.hidev.agendamento.api.domain.model.medico.MedicoListDto;
import cc.hidev.agendamento.api.domain.model.medico.MedicoUpdateDto;
import cc.hidev.agendamento.api.domain.repository.medico.MedicoRepository;
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
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid MedicoCreateDto medicoCreateDto, UriComponentsBuilder uriBuilder) {
        MedicoEntity medico = repository.save(new MedicoEntity(medicoCreateDto));

        URI uri = uriBuilder.path("/medico/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new MedicoListDto(medico));
    }

    @GetMapping
    public ResponseEntity<Page<MedicoListDto>> list(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginate) {
        Page<MedicoListDto> medicosCollection = repository.findAll(paginate).map(MedicoListDto::new);

        return ResponseEntity.ok(medicosCollection);
    }

    @GetMapping("/all")
    public ResponseEntity<List<MedicoListDto>> listAll() {
        List<MedicoListDto> medicoCollection =  repository.findAll().stream().map(MedicoListDto::new).toList();

        return ResponseEntity.ok(medicoCollection);
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid MedicoUpdateDto medico) {
        MedicoEntity medicoToUpdate = repository.getReferenceById(medico.id());
        medicoToUpdate.update(medico);

        return ResponseEntity.ok(new MedicoListDto(medicoToUpdate));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity read(@PathVariable Long id) {
        MedicoEntity medico = repository.getReferenceById(id);

        return ResponseEntity.ok(new MedicoListDto(medico));
    }
}
