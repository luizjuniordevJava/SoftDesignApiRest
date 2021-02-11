package br.com.softdesign.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

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

    @Column(nullable = false, length = 250)
    private String descricao;
}
