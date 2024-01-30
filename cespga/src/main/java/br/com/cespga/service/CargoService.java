package br.com.cespga.service;

import br.com.cespga.controller.CargoController;
import br.com.cespga.date.vo.v1.CargoVO;
import br.com.cespga.exception.ResourceNotFoundException;
import br.com.cespga.mapper.DozerMapper;
import br.com.cespga.repositories.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class CargoService {

    @Autowired
    CargoRepository cargoRepository;

    public List<CargoVO> mostrarCargos(){
        var cargo = DozerMapper.parseListObject(cargoRepository.findAll(),CargoVO.class);
        return cargo;
    }

    public CargoVO buscarPorId(Long id){
        var entity = cargoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("id não encontrado"));
        CargoVO cargoVO = DozerMapper.parseObject(entity,CargoVO.class);
        return cargoVO.add(linkTo(methodOn(CargoController.class).findyById(id)).withSelfRel());
    }



}
