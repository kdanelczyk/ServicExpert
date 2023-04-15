package com.kamil.servicExpert.EmailSender;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kamil.servicExpert.db.model.Element;
import com.kamil.servicExpert.db.model.Repair;

import jakarta.mail.MessagingException;

@Service
public interface EmailSender {

	public void sendReportsEmail(List<Repair> repairList, List<Element> elementList) throws MessagingException, IOException;
	
}
