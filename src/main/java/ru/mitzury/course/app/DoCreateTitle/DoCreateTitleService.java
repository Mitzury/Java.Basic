package ru.mitzury.course.app.DoCreateTitle;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.borders.Border;
import ru.mitzury.course.core.dto.DoCreateTitleCommand;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

public class DoCreateTitleService {

    public void execute(DoCreateTitleCommand cmd) throws Exception {

        // ===== путь рядом с JAR =====
        Path output = Path.of(System.getProperty("user.dir"), cmd.getFile());

        PdfWriter writer = new PdfWriter(output.toString());
        PdfDocument pdf = new PdfDocument(writer);
        Document doc = new Document(pdf, PageSize.A4);
        doc.setMargins(40, 40, 40, 40);

        // ===== шрифты =====
        PdfFont regularFont = loadFont("fonts/arial.ttf");
        PdfFont boldFont = loadFont("fonts/arialbd.ttf");

        // ===== ШАПКА (ЖИРНЫЙ) =====
        doc.add(new Paragraph(cmd.getSchoolName())
                .setFont(boldFont)
                .setTextAlignment(TextAlignment.CENTER)
        );

        // ===== ПОДШАПКА =====
        doc.add(new Paragraph(
                        cmd.getAddress() + ", тел. " + cmd.getPhone() + "\n" +
                                cmd.getSite() + ", e-mail: " + cmd.getEmail()
                )
                        .setFont(regularFont)
                        .setFontSize(10)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setMarginBottom(20)
        );

        // ===== ТАБЛИЦА =====
        Table table = new Table(UnitValue.createPercentArray(new float[]{1, 1}))
                .useAllAvailableWidth();

        table.addCell(cell(
                "ПРИНЯТО\n" +
                        "протокол от " + cmd.getApprovedDate(),
                regularFont
        ));

        table.addCell(cell(
                "УТВЕРЖДЕНО\n" +
                        "приказом от " + cmd.getApprovedDate() + " " + cmd.getApprovedOrder(),
                regularFont
        ));

        doc.add(table.setMarginBottom(40));

        // ===== ЗАГОЛОВОК =====
        doc.add(new Paragraph(
                        "ПОЛОЖЕНИЕ\n" +
                                "О ПОРЯДКЕ ВЕДЕНИЯ ОФИЦИАЛЬНОЙ СТРАНИЦЫ\n" +
                                cmd.getSchoolName().toUpperCase()
                )
                        .setFont(boldFont)
                        .setFontSize(14)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setMarginTop(40)
        );

        // ===== НИЗ СТРАНИЦЫ =====
        doc.add(new Paragraph("\n\n\n\n" + cmd.getCity() + "\n" + cmd.getYear())
                .setFont(regularFont)
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(11)
        );

        doc.close();
    }

    // ===== ВСПОМОГАТЕЛЬНЫЕ МЕТОДЫ =====

    private PdfFont loadFont(String path) throws IOException {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(path)) {
            if (is == null) {
                throw new IllegalStateException("Font not found: " + path);
            }
            return PdfFontFactory.createFont(
                    is.readAllBytes(),
                    PdfEncodings.IDENTITY_H,
                    PdfFontFactory.EmbeddingStrategy.PREFER_EMBEDDED
            );
        }
    }

    private Cell cell(String text, PdfFont font) {
        return new Cell()
                .add(new Paragraph(text).setFont(font))
                .setBorder(Border.NO_BORDER)
                .setFontSize(11);
    }
}
