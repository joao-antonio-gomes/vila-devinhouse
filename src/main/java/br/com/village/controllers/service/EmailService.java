package br.com.village.controllers.service;

import br.com.village.model.transport.VillagerDTO;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
	
	void sendEmail(SimpleMailMessage message);
	
	void sendNewPassword(VillagerDTO user, String newPassword);

}
