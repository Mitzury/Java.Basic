package ru.mitzury.course.app.DoSign;

import com.itextpdf.forms.form.element.SignatureFieldAppearance;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.signatures.*;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.IOException;
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
            Certificate[] chain,
            ZonedDateTime signDate) throws Exception {

        PdfSigner signer = new PdfSigner(
                new PdfReader(pdfIn),
                pdfOut,
                new StampingProperties().useAppendMode()
        );

        SignatureFieldAppearance appearance =
                new SignatureFieldAppearance("Signature1");

        appearance
                .setFontSize(7)
                .setContent(createStampContent(signDate));

        int pageNumber = 1;
        PdfDocument pdfDoc = signer.getDocument();
        PdfPage page = pdfDoc.getPage(pageNumber);
        Rectangle pageSize = page.getPageSize();


        float margin = 25f;
        float width = 220f;
        float height = 70f;

        float x = pageSize.getLeft() + margin;
        float y = pageSize.getTop() - height - margin;
        Rectangle signatureRect = new Rectangle(x, y, width, height);

        signer.getSignerProperties()
                .setSignatureAppearance(appearance)
                .setPageNumber(1)
                .setPageRect(signatureRect)
                .setReason("Документ подписан цифровой подписью")
                .setLocation("Санкт Петербург")
                .setCertificationLevel(
                        AccessPermissions.NO_CHANGES_PERMITTED
                );

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

    private static Div createStampContent(ZonedDateTime signDate) throws IOException {
        InputStream is = PdfVisualSigner.class
                .getClassLoader()
                .getResourceAsStream("fonts/arial.ttf");

        if (is == null) {
            throw new IllegalStateException("Шрифт fonts/arial.ttf не найден");
        }

        PdfFont font = PdfFontFactory.createFont(
                is.readAllBytes(),
                PdfEncodings.IDENTITY_H,
                PdfFontFactory.EmbeddingStrategy.PREFER_EMBEDDED
        );

        DateTimeFormatter fmt =
                DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

        String date = fmt.format(signDate);

        Paragraph p = new Paragraph()
                .setFont(font)
                .add("Подписано цифровой подписью\n")
                .add("Дата и время подписи: " + date + "\n")
                .add("Место подписания: Санкт Петербург");

        return new Div().add(p);
    }
}
