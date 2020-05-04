package org.pierre.jpgtopdf;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@SpringBootApplication
public class JpgtopdfApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(JpgtopdfApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        File root = new File("D:\\pierre\\pictures\\librogigliodimare\\old");
        String outputFile = "outputgiglioold.pdf";
        List<String> files = new ArrayList<String>();
        String[] alljpgs = root.list((dir, name) -> name.endsWith("png"));
        files.addAll(Arrays.asList(alljpgs));

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(new File(root, outputFile)));
        document.open();
        for (String f : files) {
            document.newPage();
            Image image = Image.getInstance(new File(root, f).getAbsolutePath());
            image.setAbsolutePosition(0, 0);
            image.setBorderWidth(0);
            image.scaleAbsolute(PageSize.A4);
            document.add(image);
        }
        document.close();
    }
}
