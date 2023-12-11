package br.com.cespga.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "medium")
public class Medium implements Serializable {
    private static final long serialVersion = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;
    @NotBlank
    @Temporal(TemporalType.DATE)
    private Date dtNascimento;
    @NotBlank
    @Temporal(TemporalType.DATE)
    private Date dtIniciacao;
    @NotBlank
    private String cpf;
    @NotBlank
    private String endereco;

    private String genero;
    @NotBlank
    private String telefone;

    private Boolean status;
    @NotBlank
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cargo")
    private Cargo cargo;

    private String observacoes;


}
