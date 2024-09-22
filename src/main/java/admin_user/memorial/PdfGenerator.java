package admin_user.memorial;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class PdfGenerator {

    public static ByteArrayInputStream generatePdf(Memorial memorial, InputStream photoStream) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();
            document.add(new Paragraph("Hero's Name: " + memorial.getHeroName()));
            document.add(new Paragraph("Rank: " + memorial.getHeroRank()));
            document.add(new Paragraph("Branch of Service: " + memorial.getHeroBranch()));
            document.add(new Paragraph("Date of Sacrifice: " + memorial.getDateOfSacrifice().toString()));
            document.add(new Paragraph("Tribute: " + memorial.getTribute()));

            // Add photo if available
            if (photoStream != null) {
                try {
                    Image image = Image.getInstance(photoStream.readAllBytes());
                    image.scaleToFit(150, 150); // Adjust the size as needed
                    document.add(image);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
