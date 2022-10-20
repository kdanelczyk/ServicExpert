package com.kamil.servicExpert.EmailSender;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.kamil.servicExpert.db.model.Element;
import com.kamil.servicExpert.db.model.Repair;
import com.kamil.servicExpert.pdfGenerator.PdfElementsGenerator;
import com.kamil.servicExpert.pdfGenerator.PdfRepairsGenerator;

import jakarta.activation.DataSource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.util.ByteArrayDataSource;

@Service
public class EmailSenderImpl implements EmailSender{
	
    @Autowired
    private JavaMailSender javaMailSender;
    
    @Autowired
    private PdfRepairsGenerator repairsGenerator;
    
    @Autowired
    private PdfElementsGenerator elementsGenerator;
	
    @Override
    public void sendReportsEmail(List<Repair> repairList, List<Element> elementList) throws MessagingException, IOException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        
        helper.setTo("kamil.danelczyk@onet.pl");
        helper.setSubject("ServicExpert reports!");
        helper.setText("<h1>weekly report about repairs and elements..</h1>", true);
        
        ByteArrayOutputStream outputStreamRepair = new ByteArrayOutputStream();
        ByteArrayOutputStream outputStreamElement = new ByteArrayOutputStream();
        DataSource dataSourceRepairs = new ByteArrayDataSource(repairsGenerator.generate(repairList, outputStreamRepair), "application/pdf");
        DataSource dataSourceElements = new ByteArrayDataSource(elementsGenerator.generate(elementList, outputStreamElement), "application/pdf");
        
        helper.addAttachment("Repairs report.pdf", dataSourceRepairs);
        helper.addAttachment("Elements report.pdf", dataSourceElements);
        
        javaMailSender.send(message);
    }
}
