package osom.company.jwtsecuritystarter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

import com.nimbusds.jose.proc.JWSKeySelector;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;
import com.nimbusds.jwt.proc.JWTProcessor;

@Configuration
public class SecurityConfiguration {


    @Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests((authorize) -> authorize
						.anyRequest().authenticated()
				)
				.oauth2ResourceServer(configurer -> 
                    configurer
                        .jwt()
                        //.jwtAuthenticationConverter(jwtAuthenticationConverter)
                        
                        )
				 .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				 .exceptionHandling((exceptions) -> exceptions
				 		.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
				 		.accessDeniedHandler(new BearerTokenAccessDeniedHandler())
				 )
                ;
		return http.build();
	}


	@Bean
	JwtDecoder jwtDecoder(JWSKeySelector<SecurityContext> jwtKeySelector) {
		return new NimbusJwtDecoder(processor(jwtKeySelector));
	}

    private JWTProcessor<SecurityContext> processor(JWSKeySelector<SecurityContext> jwtKeySelector) {
        DefaultJWTProcessor<SecurityContext> jwtProcessor = new DefaultJWTProcessor<>();
        jwtProcessor.setJWSKeySelector(jwtKeySelector);
        jwtProcessor.setJWTClaimsSetVerifier((claims, context) -> {
            //TODO: something...
        });
        return jwtProcessor;
    }


    
}
