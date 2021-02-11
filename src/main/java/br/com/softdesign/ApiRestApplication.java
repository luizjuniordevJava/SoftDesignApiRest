package br.com.softdesign;

import br.com.softdesign.model.entity.Associado;
import br.com.softdesign.model.entity.Pauta;
import br.com.softdesign.model.entity.Voto;
import br.com.softdesign.model.repository.AssociadoRepository;
import br.com.softdesign.model.repository.PautaRepository;
import br.com.softdesign.model.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiRestApplication {

	@Autowired
	AssociadoRepository repositoryA;

	@Autowired
	PautaRepository repositoryP;

	@Autowired
	VotoRepository repositoryV;

	@Bean
	public CommandLineRunner run(){
		return args -> {
			Associado associado = Associado.builder().nome("Fulano").cpf("00000000000").build();
			repositoryA.save(associado);
			Pauta pauta = Pauta.builder().descricao("Aprovar fundo de investimento para saúde").build();
			repositoryP.save(pauta);
			Voto voto = Voto.builder().voto(true).associado(associado).pauta(pauta).build();
			repositoryV.save(voto);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ApiRestApplication.class, args);
	}

}
