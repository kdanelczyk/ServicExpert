package com.kamil.servicExpert.scheduler;

import java.io.IOException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.kamil.servicExpert.EmailSender.EmailSender;
import com.kamil.servicExpert.service.ElementService;
import com.kamil.servicExpert.service.NoteService;
import com.kamil.servicExpert.service.RepairService;

import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class Scheduler {

	private RepairService repairService;
	private ElementService elementService;
	private NoteService noteService;
	private EmailSender emailSender;

	@Transactional
    @Scheduled(cron = "0 0 1 * * MON")
	public void emailSender(){
	    try {
			emailSender.sendReportsEmail(repairService.findAll(), elementService.findAll());
		} catch (MessagingException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	@Transactional
	@Scheduled(cron = "0 0 1 * * FRI")
	public void elementChecker() {
		noteService.elementChecker();
	}
}
