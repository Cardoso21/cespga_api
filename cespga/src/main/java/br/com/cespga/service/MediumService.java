package br.com.cespga.service;

import br.com.cespga.controller.MediumController;
import br.com.cespga.date.vo.v1.MediumVO;
import br.com.cespga.exception.ResourceNotFoundException;
import br.com.cespga.mapper.DozerMapper;
import br.com.cespga.model.Medium;
import br.com.cespga.repositories.MediumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class MediumService {
    private final Logger logger = Logger.getLogger(MediumService.class.getName());

    private final MediumRepository repository;
    @Autowired
    public MediumService(MediumRepository repository) {
        this.repository = repository;
    }
    public List<MediumVO> buscartodos() {

        logger.info("buscando todos os persons! ");

        var persons = DozerMapper.parseListObject(repository.findAll(), MediumVO.class);
        persons.stream().forEach(p -> p.add(linkTo(methodOn(MediumController.class).findyById(p.getKey())).withSelfRel()));
        return persons;
    }

    public MediumVO buscaPorId (Long id){
        var entity = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Medium nao encontrado"));
        MediumVO vo = DozerMapper.parseObject(entity,MediumVO.class);
        vo.add(linkTo(methodOn(MediumController.class).findyById(id)).withSelfRel());
        return vo;
    }

    public MediumVO create(MediumVO mediumVO) {
        var entity = DozerMapper.parseObject(mediumVO, Medium.class);
        // Define o Medium como ativo ao criar
        entity.setStatus(true);
        MediumVO vo = DozerMapper.parseObject(repository.save(entity), MediumVO.class);
        return vo.add(linkTo(methodOn(MediumController.class).findyById(vo.getKey())).withSelfRel());
    }

    public MediumVO atualizar(  MediumVO mediumVO) {

        Medium medium = repository.findById(mediumVO.getKey()).orElseThrow(()->new ResourceNotFoundException("id não encontrado"));
        medium.setNome(mediumVO.getNome());
        medium.setEndereco(mediumVO.getEndereco());
        medium.setGenero(mediumVO.getGenero());
        medium.setTelefone(mediumVO.getTelefone());
        medium.setObservacao(mediumVO.getObservacao());
        medium.setDtIniciacao(mediumVO.getDtIniciacao());


        MediumVO mediumVO1 = DozerMapper.parseObject(repository.save(medium),MediumVO.class);
        return mediumVO1.add(linkTo(methodOn(MediumController.class).findyById(mediumVO1.getKey())).withSelfRel());
    }

    public void delete(Long id) {
        Medium medium = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id não encontrado"));
        // Define o Medium como inativo ao deletar
        medium.setStatus(false);
        repository.save(medium);
    }



}
