package br.com.softdesign.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Locale;

/**
 * @author Junior
 * @since 11/02/2021
 * @version 1.0
 *
 * Classe criada para configurar a internacionalização de mensagens
 */
/*Anotação para indicar que a classe é do tipo configuração*/
@Configuration
public class InternacionalizacaoConfig {

    /*Metodo para configurar as mensagens*/
    @Bean
    public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("messages");/*arquivo messages.properties*/
        messageSource.setDefaultEncoding("ISO-8859-1");/*para detectar caracteres especiais*/
        messageSource.setDefaultLocale( Locale.getDefault());/* pegar localização automaticamente*/
        return messageSource;
    }

    /*Integração de mensagens através de interpolação */
    @Bean
    public LocalValidatorFactoryBean validatorFactoryBean(){
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }

}
