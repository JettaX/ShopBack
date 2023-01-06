package com.okon.gateway;

import lombok.SneakyThrows;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;

@Component
public class KeyUtil {

    @SneakyThrows
    public PrivateKey getPrivateKey() {
        KeyFactory factory = KeyFactory.getInstance("RSA");
        File file = new File("gateway/src/main/resources/app.key");
        try (FileReader keyReader = new FileReader(file); PemReader pemReader = new PemReader(keyReader)) {
            PemObject pemObject = pemReader.readPemObject();
            byte[] content = pemObject.getContent();
            PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(content);
            return factory.generatePrivate(privateKeySpec);
        }
    }
}
