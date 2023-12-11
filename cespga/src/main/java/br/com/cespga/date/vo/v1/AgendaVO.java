package br.com.cespga.date.vo.v1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AgendaVO extends RepresentationModel<AgendaVO> implements Serializable {
    private static final long serialVersion = 1L;

    private Long id;

    private String nome;

    private Date dtEvento;

    private String descricao;
}
