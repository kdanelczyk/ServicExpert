package com.kamil.servicExpert.pdfGenerator;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.kamil.servicExpert.db.model.Repair;
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
public class PdfRepairsGeneratorImpl implements PdfRepairsGenerator{

	@Override
	public byte[] generate(List<Repair> repairList, ByteArrayOutputStream byteArrayOutputStream) 
			throws DocumentException, IOException {

		Document report = new Document(PageSize.A4);
		PdfWriter.getInstance(report, byteArrayOutputStream);
		report.open();
		Font title = FontFactory.getFont(FontFactory.TIMES_ITALIC);
		title.setSize(14);
		Paragraph paragraph1 = new Paragraph("Serviceman reports repairs", title);
		paragraph1.setAlignment(Paragraph.ALIGN_CENTER);
		report.add(paragraph1);
		Font font = FontFactory.getFont(FontFactory.TIMES_ITALIC);
		font.setColor(CMYKColor.WHITE);
		
		PdfPTable tableForTotals = new PdfPTable(3);
		tableForTotals.setWidthPercentage(100);
		tableForTotals.setWidths(new int[] {3, 3, 3});
		tableForTotals.setSpacingBefore(10);

		PdfPCell cellForTotals = new PdfPCell();
		cellForTotals.setBackgroundColor(CMYKColor.DARK_GRAY);
		cellForTotals.setPadding(10);
		
		cellForTotals.setPhrase(new Phrase("price_of_repairs", font));
		tableForTotals.addCell(cellForTotals);
		
		cellForTotals.setPhrase(new Phrase("cost_of_elements", font));
		tableForTotals.addCell(cellForTotals);
		
		cellForTotals.setPhrase(new Phrase("profit", font));
		tableForTotals.addCell(cellForTotals);
		
		double priceOfRepairs = repairList.stream()
				.map(repair -> repair.getCost())
				.mapToDouble(Float::doubleValue)
				.sum();
		
		double costOfElements = repairList.stream()
				.map(repair -> repair.getElements().stream()
						.map(element -> element.getPriceOfElement())
						.mapToDouble(Float::doubleValue)
						.sum())
				.collect(Collectors.summingDouble(Double::doubleValue));
		
		repairList.stream().forEach(repair -> {
			PdfPTable tableForRepairs = new PdfPTable(5);
			tableForRepairs.setWidthPercentage(100);
			tableForRepairs.setWidths(new int[] { 2, 2, 2, 2, 2 });
			tableForRepairs.setSpacingBefore(5);

			PdfPCell cellForRepairs = new PdfPCell();
			cellForRepairs.setBackgroundColor(CMYKColor.GRAY);
			cellForRepairs.setPadding(5);

			PdfPTable tableForElements = new PdfPTable(2);
			tableForElements.setWidthPercentage(100);
			tableForElements.setWidths(new int[] { 5, 5 });
			tableForElements.setSpacingBefore(5);

			PdfPCell cellForElements = new PdfPCell();
			cellForElements.setBackgroundColor(CMYKColor.LIGHT_GRAY);
			cellForElements.setPadding(5);

			cellForRepairs.setPhrase(new Phrase("Id", font));
			tableForRepairs.addCell(cellForRepairs);

			cellForRepairs.setPhrase(new Phrase("date_created", font));
			tableForRepairs.addCell(cellForRepairs);

			cellForRepairs.setPhrase(new Phrase("type_of_device", font));
			tableForRepairs.addCell(cellForRepairs);

			cellForRepairs.setPhrase(new Phrase("total_cost", font));
			tableForRepairs.addCell(cellForRepairs);

			cellForRepairs.setPhrase(new Phrase("user", font));
			tableForRepairs.addCell(cellForRepairs);

			cellForElements.setPhrase(new Phrase("name_of_element", font));
			tableForElements.addCell(cellForElements);

			cellForElements.setPhrase(new Phrase("price_of_element", font));
			tableForElements.addCell(cellForElements);

			tableForRepairs.addCell(String.valueOf(repair.getId()));
			tableForRepairs.addCell(String.valueOf(repair.getDateCreated()));
			tableForRepairs.addCell(String.valueOf(repair.getDevice().getType().getNameOfType()));
			tableForRepairs.addCell(String.valueOf(repair.getCost()));
			tableForRepairs.addCell(String.valueOf(repair.getUser().getName()));
			
			repair.getElements().stream().forEach(element -> {
				tableForElements.addCell(String.valueOf(element.getNameOfElement()));
				tableForElements.addCell(String.valueOf(element.getPriceOfElement()));
			});
			report.add(tableForRepairs);
			report.add(tableForElements);
		});

		tableForTotals.addCell(String.valueOf(priceOfRepairs));
		tableForTotals.addCell(String.valueOf(costOfElements));
		tableForTotals.addCell(String.valueOf(priceOfRepairs - costOfElements));
		report.add(tableForTotals);
		report.close();
		byte[] bytes = byteArrayOutputStream.toByteArray();
		return bytes;
	}

}
