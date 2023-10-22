package cc.hidev.agendamento.api.controller.medico;

import cc.hidev.agendamento.api.model.medico.MedicoCreateDto;
import cc.hidev.agendamento.api.model.medico.MedicoEntity;
import cc.hidev.agendamento.api.model.medico.MedicoListDto;
import cc.hidev.agendamento.api.model.medico.MedicoUpdateDto;
import cc.hidev.agendamento.api.repository.medico.MedicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void create(@RequestBody @Valid MedicoCreateDto medico) {
        repository.save(new MedicoEntity(medico));
    }

    @GetMapping
    public Page<MedicoListDto> list(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginate) {
        return repository.findAll(paginate).map(MedicoListDto::new);
    }

    @GetMapping("/all")
    public List<MedicoListDto> listAll(Pageable paginate) {
        return repository.findAll(paginate).stream().map(MedicoListDto::new).toList();
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid MedicoUpdateDto medico) {
        MedicoEntity medicoToUpdate = repository.getReferenceById(medico.id());
        medicoToUpdate.update(medico);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
