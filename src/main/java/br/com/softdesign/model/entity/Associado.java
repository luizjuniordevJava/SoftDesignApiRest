package br.com.softdesign.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @author Junior
 * @since 10/02/2021
 * @version 1.0
 *
 * Classe criada para implementar um objeto Associado
 *
 * Anotações Data Lombok para gerar os construtores, getter e setter em tempo de execução
 * Anotações NoArgsConstructor Lombok para gerar um construtor vazio em tempo de execução
 * Anotações AllArgsConstructor Lombok para gerar um construtor completo em tempo de execução
 * Anotação Entity para relacionar a classe Associado a entidade do banco de dados
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Associado {

    /**
     * Anotação Id e GeneratedValue(strategy = GenerationType.IDENTITY)
     * para indicar que o atributo será id único e autoincrmento
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Anotação NotEmpty para validar o campo
     * Anotação Column para configurar coluna
     */
    @Column(nullable = false, length = 150)
    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nome;

    /**
     * Anotação NotNull para validar o campo
     * Anotação Column para configurar coluna
     */
    @Column(nullable = false, length = 11)
    @NotNull(message = "{campo.cpf.obrigatorio}")
    @CPF(message = "{campo.cpf.invalido}")
    private String cpf;

    /**
     * Anotação Column para configurar coluna
     * Anotação JsonFormat para salvar a data com o padrão dia/Mês/ano
     */
    @Column(name = "data_cadastro", updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    /**
     * Anotação PrePersist para executar metodo antes de persistir a informação no BD
     */
    @PrePersist
    public  void prePersist(){
        setDataCadastro(LocalDate.now());
    }


}
