package osom.company.jwtsecuritystarter.jwt;

import java.io.ByteArrayInputStream;
import java.io.StringBufferInputStream;
import java.io.StringReader;
import java.security.Key;
import java.security.interfaces.RSAPublicKey;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.security.converter.RsaKeyConverters;

import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.KeySourceException;
import com.nimbusds.jose.proc.JWSKeySelector;
import com.nimbusds.jose.proc.SecurityContext;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class OldSerenityKeySelector implements JWSKeySelector<SecurityContext> {


    private final Map<String,RSAPublicKey> kidMappings;
    

    @Override
    public List<? extends Key> selectJWSKeys(JWSHeader header, SecurityContext context) throws KeySourceException {
        final var kid = header.getKeyID();
        final var key = kidMappings.get(kid);
        if(key == null) {
            throw new KeySourceException("No Public Key for kid '" + kid + "'");
        }
        log.info("Found public key for {}", kid);
        return Collections.singletonList(key);
    }


    
    
}
