package br.com.village.controllers.service;

import br.com.village.model.transport.ResidentsDTO;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
	
	void sendEmail(SimpleMailMessage message);
	
	void sendNewPassword(ResidentsDTO user, String newPassword);

}
