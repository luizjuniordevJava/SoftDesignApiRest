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
 */
/*Anotações Lombok para gerar os construtores, getter e setter em tempo de execução*/
@Data
@NoArgsConstructor
@AllArgsConstructor
/*Anotação para relacionar a classe Voto a entidade do banco de dados*/
@Builder
@Entity
public class Voto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Boolean voto;

    /*Anotação para relacionar o Voto a um único Associado */
    @OneToOne
    @JoinColumn(name="id_associado")
    private Associado associado;

    /*Anotação para relacionar o voto a Pauta*/
    @ManyToOne
    @JoinColumn(name = "id_pauta")
    private Pauta pauta;

    @Column(name = "data_cadastro", updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;
}
