package br.com.cespga.controller;

import br.com.cespga.date.vo.v1.CargoVO;
import br.com.cespga.service.CargoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@CrossOrigin(origins = {"http://localhost:8080"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cargo")
public class CargoController {

    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private CargoService cargoService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<CargoVO> findAll(){
        return cargoService.mostrarCargos();
    }

    @GetMapping(value = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public CargoVO findyById(
            @PathVariable(value = "id")Long id ){
        return cargoService.buscarPorId(id);
    }
}
