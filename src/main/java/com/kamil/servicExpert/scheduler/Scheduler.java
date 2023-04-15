package com.kamil.servicExpert.scheduler;

import java.io.IOException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.kamil.servicExpert.EmailSender.EmailSender;
import com.kamil.servicExpert.repository.ElementRepository;
import com.kamil.servicExpert.repository.RepairRepository;
import com.kamil.servicExpert.service.NoteService;

import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class Scheduler {

	private RepairRepository repairRepository;
	private ElementRepository elementRepository;
	private NoteService noteService;
	private EmailSender emailSender;

	@Transactional
    @Scheduled(cron = "0 0 1 * * MON")
	public void emailSender(){
	    try {
			emailSender.sendReportsEmail(repairRepository.findAll(), elementRepository.findAll());
		} catch (MessagingException error) {
			
			error.printStackTrace();
		} catch (IOException error) {
			
			error.printStackTrace();
		}
	}
	
	@Transactional
	@Scheduled(cron = "0 0 1 * * MON")
	public void elementChecker() {
		noteService.elementChecker();
	}
	
}
