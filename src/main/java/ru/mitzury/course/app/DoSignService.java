package ru.mitzury.course.app;

import ru.mitzury.course.core.dto.DoSignCommand;
import java.io.*;
import java.time.ZonedDateTime;

public class DoSignService {

    public void execute(DoSignCommand command) throws Exception {

        ZonedDateTime signDate = command.getDate();
        String inputPdf = command.getFile();
        String outputPdf = "output.pdf";
        String certPath = "cert.pfx";
        String certPassword = "VvmOaS==";

        CertificateLoader.Cert cert =
                CertificateLoader.load(certPath, certPassword);

        try (
                InputStream in = new FileInputStream(inputPdf);
                OutputStream out = new FileOutputStream(outputPdf)
        ) {
            PdfVisualSigner.sign(
                    in,
                    out,
                    cert.privateKey(),
                    cert.chain(),
                    signDate
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}