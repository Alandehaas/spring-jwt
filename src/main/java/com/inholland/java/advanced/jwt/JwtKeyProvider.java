package com.inholland.java.advanced.jwt;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.security.*;

@Getter
@Component
public class JwtKeyProvider {
    private final Key privateKey;
    private final PublicKey publicKey;

    public JwtKeyProvider() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);

        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        this.privateKey = keyPair.getPrivate();
        this.publicKey = keyPair.getPublic();
    }
}