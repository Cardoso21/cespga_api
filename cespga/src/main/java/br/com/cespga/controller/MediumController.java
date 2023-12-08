package br.com.cespga.controller;

import br.com.cespga.date.vo.v1.MediumVO;
import br.com.cespga.service.MediumService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/medium")
public class MediumController {

    private final AtomicLong counter = new AtomicLong();
    @Autowired
    private MediumService service;

    @GetMapping(value = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public MediumVO findyById(
            @PathVariable(value = "id")Long id ){
        return service.buscaPorId(id);
    }
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<MediumVO> findAll(){
        return service.buscartodos();
    }

    @PostMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public MediumVO create(
            @RequestBody MediumVO mediumVO){
        return service.create(mediumVO);
    }

    @PutMapping(value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public MediumVO update(
            @RequestBody MediumVO mediumVO){
        return service.atualizar(mediumVO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity <?> delete(@PathVariable(value = "id")Long id ){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
