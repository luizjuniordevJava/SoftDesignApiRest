package br.com.softdesign.service;

import br.com.softdesign.exception.VotoRegistradoException;
import br.com.softdesign.model.entity.Associado;
import br.com.softdesign.model.entity.Pauta;
import br.com.softdesign.model.entity.Voto;
import br.com.softdesign.model.repository.VotoRepository;
import br.com.softdesign.service.dto.VotoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Junior
 * @since 18/02/2021
 * @version 1.0
 *
 * Classe criada para implementar as regras de votação
 *
 */
@Service
@RequiredArgsConstructor
public class VotoService {

    /**
     * Anotação Autowired para permitir a injeção do repository e service
     */
    @Autowired
    private final VotoRepository repository;
    @Autowired
    private final PautaService pautaService;
    @Autowired
    private final AssociadoService associadoService;

    public Voto salvar( VotoDTO votoDTO ){
        Integer idPauta = votoDTO.getIdPauta();
        String usuario = votoDTO.getUsuario();
        Integer idAssociado = associadoService.idAssociadoPorUsername(usuario);

        Associado associado = associadoService.acharPorId(idAssociado);
        Pauta pauta = pautaService.acharPorId(idPauta);

        /**
         * Método que valida se o associado já possuí voto para a pauta
         */
        boolean exists = repository.existsVoteByPautaUser( idPauta.toString(), idAssociado.toString() );

        if (exists){
            throw new VotoRegistradoException();
        }else {

            Voto voto = new Voto();
            voto.setPauta(pauta);
            voto.setAssociado(associado);
            voto.setVoto( votoDTO.getVoto() );

            /**
             * Método que incrementa o total de voto da pauta
             */
            int nVoto = pauta.getTotalVotos()+1;
            int nSim =  pauta.getVotoSim()+1;
            int nN = pauta.getVotoNao()+1;

            if (voto.getVoto().contains("sim")){
                nSim = nSim+1;
            }else{
                nN = nN+1;
            }
            pauta.setTotalVotos( nVoto );
            pauta.setVotoSim(nSim);
            pauta.setVotoNao(nN);

            String resultado = "";

            if (nSim>nN){
                resultado = "Aprovado(a).";
            }else {
                resultado = "Reprovado(a).";
            }
            pauta.setResultado(resultado);
            pautaService.salvar( pauta );

            return repository.save(voto);
        }


    }
}
