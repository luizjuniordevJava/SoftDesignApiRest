package br.com.softdesign.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author Junior
 * @since 10/02/2021
 * @version 1.0
 *
 * Classe criada para implementar os Votos da Pauta
 *
 * Anotações Data Lombok para gerar os construtores, getter e setter em tempo de execução
 * Anotações NoArgsConstructor Lombok para gerar um construtor vazio em tempo de execução
 * Anotações AllArgsConstructor Lombok para gerar um construtor completo em tempo de execução
 * Anotação Entity para relacionar a classe Associado a entidade do banco de dados
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Voto {

    /**
     * Anotação Id e GeneratedValue(strategy = GenerationType.IDENTITY)
     * para indicar que o atributo será id único e autoincrmento
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Anotação Column para configurar coluna
     */
    @Column(length = 3)
    private String voto;

    /**
     * Anotação OneToOne para relacionar um Voto a um único Associado
     * Anotação JoinColumn para que seja feito a query pelo hinermate
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_associado")
    private Associado associado;

    /**
     * Anotação ManyToOne para relacionar a lista de votos da pauta
     * Anotação JoinColumn para que seja feito a query pelo hinermate
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_pauta")
    private Pauta pauta;

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
    @PrePersist //Anotação para executar metodo antes de persistir a informação no BD
    public  void prePersist(){
        setDataCadastro(LocalDate.now());
    }
}
