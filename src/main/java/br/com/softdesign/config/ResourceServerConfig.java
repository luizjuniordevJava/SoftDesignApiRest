package br.com.softdesign.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @author Junior
 * @since 16/02/2021
 * @version 1.0
 *
 * Classe criada para configurar o acesso de api externas
 *
 * Anotação Configuration para informar que a classe faz parte da configuração do spring
 *
 */

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/api/usuarios/**").permitAll()
                .antMatchers(
                        "/api/associados/**",
                        "/api/pautas/**").authenticated()
                .anyRequest().denyAll();
    }
}
