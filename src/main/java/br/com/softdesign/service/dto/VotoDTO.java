package br.com.softdesign.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VotoDTO {
    @NotEmpty(message = "{campo.voto.obrigatorio}")
    private String voto;
    private String usuario;
    private Integer idPauta;
}
