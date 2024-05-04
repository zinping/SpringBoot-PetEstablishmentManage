package es.upm.dit.isst.mascotmercio21front.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configuracion {

    @Value("${proprietario.manager.url}")
    private String propietarioManagerUrl;

    @Value("${cliente.manager.url}")
    private String clienteManagerUrl;

    @Value("${establecimiento.manager.url}")
    private String establecimientoManagerUrl;

    @Bean
    public String propietarioManagerString() {
        return propietarioManagerUrl;
    }

    @Bean
    public String clienteManagerString() {
        return clienteManagerUrl;
    }

    @Bean
    public String establecimientoManagerString() {
        return establecimientoManagerUrl;
    }
}
