package cc.hidev.agendamento.api.controller.paciente;

import cc.hidev.agendamento.api.model.paciente.PacienteCreateDto;
import cc.hidev.agendamento.api.model.paciente.PacienteEntity;
import cc.hidev.agendamento.api.model.paciente.PacienteListDto;
import cc.hidev.agendamento.api.model.paciente.PacienteUpdateDto;
import cc.hidev.agendamento.api.repository.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    public void create(@RequestBody PacienteCreateDto paciente) {
        repository.save(new PacienteEntity(paciente));
    }

    @GetMapping
    public Page<PacienteListDto> list(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginate) {
        return repository.findAll(paginate).map(PacienteListDto::new);
    }

    @GetMapping("/all")
    public List<PacienteListDto> listAll() {
        return repository.findAll().stream().map(PacienteListDto::new).toList();
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody PacienteUpdateDto paciente) {
        PacienteEntity pacienteToUpdate = repository.getReferenceById(paciente.id());
        pacienteToUpdate.update(paciente);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
