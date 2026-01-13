package ru.mitzury.course.app;

import ru.mitzury.course.core.dto.DoSignCommand;
import java.io.*;

public class DoSignService {

    public void execute(DoSignCommand command) throws Exception {

        String data = command.getData();


        String inputPdf = command.getFile();
        String outputPdf = "output.pdf";
        String certPath = "cert.pfx";
        String certPassword = "";

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
                    cert.chain()
            );
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("PDF signed successfully");

    }
}
