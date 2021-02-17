package br.com.softdesign.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 * @author Junior
 * @since 16/02/2021
 * @version 1.0
 *
 * Classe criada para implementar usuário
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
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    @NotEmpty(message = "{campo.login.obrigatorio}")
    private String username;

    @NotEmpty(message = "{campo.senha.obrigatorio}")
    private String password;
}
