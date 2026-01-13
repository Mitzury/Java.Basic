package ru.mitzury.course.app;

import java.io.FileInputStream;
import java.security.*;
import java.security.cert.Certificate;

public final class CertificateLoader {

    private CertificateLoader() {}

    public static Cert load(String path, String password) throws Exception {

        KeyStore ks = KeyStore.getInstance("PKCS12");
        ks.load(
                new FileInputStream(path),
                password != null ? password.toCharArray() : null
        );

        String alias = ks.aliases().nextElement();

        PrivateKey privateKey =
                (PrivateKey) ks.getKey(alias, password.toCharArray());

        Certificate[] chain =
                ks.getCertificateChain(alias);

        return new Cert(privateKey, chain);
    }

    public record Cert(
            PrivateKey privateKey,
            Certificate[] chain
    ) {}
}