package br.com.softdesign.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

/**
 * @author Junior
 * @since 10/02/2021
 * @version 1.0
 *
 * Classe criada para implementar um objeto Pauta
 */

/*Anotações Lombok para gerar os construtores, getter e setter em tempo de execução*/
@Data
@NoArgsConstructor
@AllArgsConstructor
/*Anotação para relacionar a classe Pauta a entidade do banco de dados*/
@Entity
@Builder
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 150)
    /*Anotação para validar o campo nome*/
    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nome;

    @Column(nullable = false, length = 250)
    /*Anotação para validar o campo descricao*/
    @NotEmpty(message = "{campo.descricao.obrigatorio}")
    private String descricao;

    @Column
    private int totalVotos;

    @Column
    private Boolean resultado;

    @Column(name = "data_cadastro", updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    @PrePersist //Anotação para executar metodo antes de persistir a informação no BD
    public  void prePersist(){
        setDataCadastro(LocalDate.now());
    }
}
