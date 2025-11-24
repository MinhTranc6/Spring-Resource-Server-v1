package minhtranc6.Spring_Resource_Server_v1;

import com.nimbusds.jose.jwk.RSAKey;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

/**
 * Utility class for generating and managing JSON Web Keys (JWKs).
 * This class is required by AuthorizationServerConfig to create the JWKSource bean.
 */
public final class Jwks {

    private Jwks() {}

    /**
     * Generates a new RSA Key Pair and wraps it in an RSAKey object for JWKSet.
     */
    public static RSAKey generateRsa() {
        try {
            KeyPair keyPair = generateRsaKey();
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

            return new RSAKey.Builder(publicKey)
                    .privateKey(privateKey)
                    .keyID(UUID.randomUUID().toString())
                    .build();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    /**
     * Helper method to generate an RSA KeyPair.
     */
    private static KeyPair generateRsaKey() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048); // 2048-bit key size is standard
            return keyPairGenerator.generateKeyPair();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }
}