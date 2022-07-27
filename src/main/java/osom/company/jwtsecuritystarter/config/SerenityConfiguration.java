package osom.company.jwtsecuritystarter.config;

import java.io.ByteArrayInputStream;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.converter.RsaKeyConverters;

import com.nimbusds.jose.proc.JWSKeySelector;
import com.nimbusds.jose.proc.SecurityContext;

import lombok.extern.slf4j.Slf4j;
import osom.company.jwtsecuritystarter.jwt.OldSerenityKeySelector;



@EnableConfigurationProperties(SerenityProperties.class)
@Slf4j
public class SerenityConfiguration {

    

    @Bean
    JWSKeySelector<SecurityContext> jwsKeySelector(SerenityProperties serenityProperties) {
        final Map<String,RSAPublicKey> mapping = serenityProperties
            .getKeys()
            .stream()
            .collect(Collectors.toMap(
                s -> s.getAlias(),
                s -> RsaKeyConverters.x509().convert(new ByteArrayInputStream(s.getPublicKey().getBytes()))
                )
            );
        mapping.entrySet().forEach(entry -> log.info("Adding kid {} with Pk {} ", entry.getKey(), entry.getValue().getAlgorithm()));
        return new OldSerenityKeySelector(mapping);
    }
    
}
