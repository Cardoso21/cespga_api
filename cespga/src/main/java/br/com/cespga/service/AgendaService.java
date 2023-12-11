package br.com.cespga.service;

import br.com.cespga.controller.AgendaController;
import br.com.cespga.controller.MediumController;
import br.com.cespga.date.vo.v1.AgendaVO;
import br.com.cespga.date.vo.v1.MediumVO;
import br.com.cespga.exception.ResourceNotFoundException;
import br.com.cespga.mapper.DozerMapper;
import br.com.cespga.model.Agenda;
import br.com.cespga.repositories.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class AgendaService {

    private final Logger logger = Logger.getLogger(MediumService.class.getName());

    @Autowired
    AgendaRepository repository;

    public List<AgendaVO> buscartodos(){
        var persons = DozerMapper.parseListObject(repository.findAll(), AgendaVO.class);
        persons.stream().forEach(p -> p.add(linkTo(methodOn(AgendaController.class).findyById(p.getId())).withSelfRel()));
        return persons;
    }

    public AgendaVO buscaPorId (Long id){
        var entity = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Evento nao encontrado"));
        AgendaVO vo = DozerMapper.parseObject(entity,AgendaVO.class);
        vo.add(linkTo(methodOn(MediumController.class).findyById(id)).withSelfRel());
        return vo;
    }

    public AgendaVO create (AgendaVO agendaVO){
        var entity = DozerMapper.parseObject(agendaVO, Agenda.class);
        AgendaVO agenda = DozerMapper.parseObject(repository.save(entity),AgendaVO.class);
        return agenda.add(linkTo(methodOn(AgendaController.class).findyById(agenda.getId())).withSelfRel());
    }

    public AgendaVO atualizar (AgendaVO agendaVO){
        Agenda agenda = repository.findById(agendaVO.getId()).orElseThrow(()->new ResourceNotFoundException("Evento não encontrado"));
        agenda.setNome(agendaVO.getNome());
        agenda.setDtEvento(agendaVO.getDtEvento());
        agenda.setDescricao(agendaVO.getDescricao());

        AgendaVO agendaV = DozerMapper.parseObject(repository.save(agenda),AgendaVO.class);
        return agendaV.add(linkTo(methodOn(AgendaController.class).findyById(agendaV.getId())).withSelfRel());
    }

    public void delete(Long id){
        Agenda agenda = repository.findById(id).orElseThrow(()->new ResourceNotFoundException("id não encontrado"));
        repository.delete(agenda);
    }
}
