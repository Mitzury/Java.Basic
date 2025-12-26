package ru.mitzury.course;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.IOException;

public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("text/plain; charset=UTF-8");
        resp.getWriter().println("Hello from Embedded Tomcat 11!");


            try (Document document = new Document(new PdfDocument(new PdfWriter("./hello-pdf.pdf")))) {
                document.add(new Paragraph("Hello PDF!"));
            }

    }
}