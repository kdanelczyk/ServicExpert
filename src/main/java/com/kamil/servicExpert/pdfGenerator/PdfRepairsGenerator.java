package com.kamil.servicExpert.pdfGenerator;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.kamil.servicExpert.db.model.Repair;
import com.lowagie.text.DocumentException;

@Component
public interface PdfRepairsGenerator {

	public byte[] generate(List<Repair> repairList, ByteArrayOutputStream byteArrayOutputStream) 
			throws DocumentException, IOException;
	
}
