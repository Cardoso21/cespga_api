package br.com.cespga.date.vo.v1;

import br.com.cespga.model.Cargo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"id","nome","dtNascimento","dtIniciacao", "cpf","endereco", "genero","telefone","status","cargo","observacao"})
public class MediumVO extends RepresentationModel<MediumVO> implements Serializable {
    private static final long serialVersion = 1L;

    @JsonProperty("id")
    @Mapping("id")
    private Long key;

    private String nome;

    private Date dtNascimento;

    private Date dtIniciacao;

    private String cpf;

    private String endereco;

    private String genero;

    private String telefone;

    private Boolean status;

    private Cargo cargo;

    private String observacao;



}
