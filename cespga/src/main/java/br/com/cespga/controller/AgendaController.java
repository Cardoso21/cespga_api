package br.com.cespga.controller;

import br.com.cespga.date.vo.v1.AgendaVO;
import br.com.cespga.service.AgendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@CrossOrigin(origins = {"http://localhost:8080"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/agenda")
public class AgendaController {

    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private AgendaService service;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<AgendaVO> findAll(){
        return service.buscartodos();
    }

    @GetMapping(value = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public AgendaVO findyById(
            @PathVariable(value = "id")Long id ){
        return service.buscaPorId(id);
    }

    @PostMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public AgendaVO create(
            @RequestBody AgendaVO agendaVO){
        return service.create(agendaVO);
    }

    @PutMapping(value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public AgendaVO update(
            @RequestBody AgendaVO agendaVO) {
        return service.atualizar(agendaVO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id")Long id ){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
