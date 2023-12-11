package br.com.cespga.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    private String nome;

    @Temporal(TemporalType.DATE)
    private Date dtNascimento;

    @Temporal(TemporalType.DATE)
    private Date dtIniciacao;
    @NotNull
    private String cpf;
    @NotNull
    private String endereco;

    private String genero;
    @NotNull
    private String telefone;

    private Boolean status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cargo")
    private Cargo cargo;

    private String observacao;


}
