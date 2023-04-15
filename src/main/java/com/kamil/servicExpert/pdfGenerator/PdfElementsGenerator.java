package com.kamil.servicExpert.pdfGenerator;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.kamil.servicExpert.db.model.Element;
import com.lowagie.text.DocumentException;

@Component
public interface PdfElementsGenerator {

	public byte[] generate(List<Element> elementList, ByteArrayOutputStream byteArrayOutputStream)
			throws DocumentException, IOException;
	
}
