package ru.mitzury.course.app;

import com.itextpdf.forms.form.element.SignatureFieldAppearance;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.signatures.*;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.InputStream;
import java.io.OutputStream;
import java.security.*;
import java.security.cert.Certificate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public final class PdfVisualSigner {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    private PdfVisualSigner() {}

    public static void sign(
            InputStream pdfIn,
            OutputStream pdfOut,
            PrivateKey privateKey,
            Certificate[] chain
    ) throws Exception {

        PdfReader reader = new PdfReader(pdfIn);

        PdfSigner signer = new PdfSigner(
                reader,
                pdfOut,
                new StampingProperties().useAppendMode()
        );

        // ===== ВИЗУАЛЬНОЕ ОТОБРАЖЕНИЕ (Adobe-style) =====
        SignatureFieldAppearance appearance =
                new SignatureFieldAppearance("Signature1");

        appearance
                .setFontSize(7)
                .setContent(createAdobeLikeContent());

        signer.getSignerProperties()
                .setSignatureAppearance(appearance)
                .setPageNumber(1)
                .setPageRect(new Rectangle(50, 50, 220, 65))
                .setReason("Approved")
                .setLocation("Moscow")
                .setCertificationLevel(
                        AccessPermissions.NO_CHANGES_PERMITTED
                );

        // ===== КРИПТО =====
        IExternalSignature signature =
                new PrivateKeySignature(
                        privateKey,
                        "SHA-256",
                        "BC"
                );

        IExternalDigest digest =
                new BouncyCastleDigest();

        signer.signDetached(
                digest,
                signature,
                chain,
                null,
                null,
                null,
                0,
                PdfSigner.CryptoStandard.CADES
        );
    }

    private static Div createAdobeLikeContent() {

        DateTimeFormatter fmt =
                DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

        String date =
                fmt.format(ZonedDateTime.now());

        Paragraph p = new Paragraph()
                .add("Digitally signed by John Doe\n")
                .add("Date: " + date + "\n")
                .add("Reason: Approved\n")
                .add("Location: Moscow");

        return new Div().add(p);
    }
}