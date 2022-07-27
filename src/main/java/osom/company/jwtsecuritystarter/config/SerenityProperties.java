package osom.company.jwtsecuritystarter.config;

import java.security.interfaces.RSAPublicKey;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;


import lombok.Data;

@ConfigurationProperties(prefix = "serenity")
@Data
public class SerenityProperties {

    private List<SerenityKeyValue> keys;

    @Data
    public static class SerenityKeyValue {
        private String alias;
        private String publicKey;
    }
    
}
