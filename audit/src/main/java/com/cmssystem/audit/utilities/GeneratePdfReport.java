package com.cmssystem.audit.utilities;

import com.cmssystem.audit.dto.AuditResponseDto;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class GeneratePdfReport {

    private static final Logger logger = LoggerFactory.getLogger(GeneratePdfReport.class);

    public static ByteArrayInputStream pdfReport(List<AuditResponseDto> responseDtos) {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            PdfPTable table = new PdfPTable(7);
            table.setWidthPercentage(100);

            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

            PdfPCell hcell;
            hcell = new PdfPCell(new Phrase("Count", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Asset", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Action", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Action Time", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Modifier", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Old Value", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("New Value", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            long count = 1;
            for (AuditResponseDto auditResponseDto : responseDtos) {

                PdfPCell cell;

                cell = new PdfPCell(new Phrase(String.valueOf(count++)));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(auditResponseDto.getAsset()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(auditResponseDto.getAction()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                String date = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date(auditResponseDto.getActionTime()));
                cell = new PdfPCell(new Phrase(date));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(auditResponseDto.getModifier())));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setPaddingRight(5);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(auditResponseDto.getOldValue())));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setPaddingRight(5);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(auditResponseDto.getNewValue())));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setPaddingRight(5);
                table.addCell(cell);
            }

            PdfWriter.getInstance(document, out);
            document.open();
            document.add(table);

            document.close();

        } catch (DocumentException ex) {

            logger.error("Error occurred: {0}", ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}