package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
//to enable we security
@EnableWebFluxSecurity
public class SecurityConfig {
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity serverHttpSecurity) {
//        we are going to disable csrf - [Cross-site Request Forgery (CSRF, sometimes also called XSRF) is an attack that
//        can trick an end-user using a web application to unknowingly execute actions that can compromise security]
//        cause we are only communicating through REST API through postman Client.
        serverHttpSecurity.csrf().disable().authorizeExchange(authorizeExchangeSpec ->
                        authorizeExchangeSpec.pathMatchers()
                        .permitAll()
                        .anyExchange()
                        .authenticated())
//now we r going to add resource sever capablities in our API gateway
                .oauth2ResourceServer
//                        now enable JSON web token capabilities
        (ServerHttpSecurity.OAuth2ResourceServerSpec::jwt);
//        this will create obj of type security web filter chain.
        return serverHttpSecurity.build();
    }
}
