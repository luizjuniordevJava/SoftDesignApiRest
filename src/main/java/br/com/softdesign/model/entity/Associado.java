package br.com.softdesign.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author Junior
 * @since 10/02/2021
 * @version 1.0
 *
 * Classe criada para implementar um objeto Associado
 */

/*Anotações Lombok para gerar os construtores, getter e setter em tempo de execução*/
@Data
@NoArgsConstructor
@AllArgsConstructor
/*Anotação para relacionar a classe Associado a entidade do banco de dados*/
@Entity
public class Associado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false, length = 11)
    private String cpf;

    @Column
    private LocalDate dataCadstro;
}
