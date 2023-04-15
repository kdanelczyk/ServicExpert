package com.kamil.servicExpert.pdfGenerator;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

import com.kamil.servicExpert.db.model.Element;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component
public class PdfElementsGeneratorImpl implements PdfElementsGenerator{
	
	@Override
	public byte[] generate(List<Element> elementList, ByteArrayOutputStream byteArrayOutputStream)
			throws DocumentException, IOException {

		Document report = new Document(PageSize.A4);
		PdfWriter.getInstance(report, byteArrayOutputStream);
		report.open();
		Font title = FontFactory.getFont(FontFactory.TIMES_ITALIC);
		title.setSize(14);
		Paragraph paragraph1 = new Paragraph("Serviceman reports elements", title);
		paragraph1.setAlignment(Paragraph.ALIGN_CENTER);
		report.add(paragraph1);
		Font font = FontFactory.getFont(FontFactory.TIMES_ITALIC);
		font.setColor(CMYKColor.WHITE);

		PdfPTable tableForElements = new PdfPTable(6);
		tableForElements.setWidthPercentage(100);
		tableForElements.setWidths(new int[] { 5, 5, 5, 5, 5, 5});
		tableForElements.setSpacingBefore(5);

		PdfPCell cellForElements = new PdfPCell();
		cellForElements.setBackgroundColor(CMYKColor.LIGHT_GRAY);
		cellForElements.setPadding(5);

		cellForElements.setPhrase(new Phrase("element name", font));
		tableForElements.addCell(cellForElements);
		
		cellForElements.setPhrase(new Phrase("type element", font));
		tableForElements.addCell(cellForElements);
		
		cellForElements.setPhrase(new Phrase("element price", font));
		tableForElements.addCell(cellForElements);
		
		cellForElements.setPhrase(new Phrase("elements price", font));
		tableForElements.addCell(cellForElements);
		
		cellForElements.setPhrase(new Phrase("quantity", font));
		tableForElements.addCell(cellForElements);
		
		cellForElements.setPhrase(new Phrase("critical quantity", font));
		tableForElements.addCell(cellForElements);
		
		elementList.stream().forEach(element -> {			
			tableForElements.addCell(String.valueOf(element.getNameOfElement()));
			tableForElements.addCell(String.valueOf(element.getType().getNameOfType()));
			tableForElements.addCell(String.valueOf(element.getPriceOfElement()));
			tableForElements.addCell(String.valueOf(element.getPriceOfElement().multiply(BigDecimal.valueOf(element.getQuantity()))));
			tableForElements.addCell(String.valueOf(element.getQuantity()));
			tableForElements.addCell(String.valueOf(element.getCriticalQuantity()>element.getQuantity()));
			});

		report.add(tableForElements);
		report.close();
		byte[] bytes = byteArrayOutputStream.toByteArray();
		
		return bytes;
	}
	
}
