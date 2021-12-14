package br.com.village.controllers.rest;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import br.com.village.controllers.service.AuthService;
import br.com.village.controllers.service.VillagerService;
import br.com.village.model.dao.UserSpringSecurity;
import br.com.village.util.JWTUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.village.model.transport.JwtDTO;
import br.com.village.model.transport.MailDTO;
import com.google.gson.Gson;

@RestController
@RequestMapping("/auth")
public class AuthRest {
	
	private JWTUtil jwtUtil;
	
	private AuthService authService;
	
	public AuthRest(JWTUtil jwtUtil, AuthService authService) {
		this.jwtUtil = jwtUtil;
		this.authService = authService;
	}

	@PostMapping("/refresh_token")
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) throws IOException{
		UserSpringSecurity userSpringSecurity = VillagerService.authenticated();
		assert userSpringSecurity != null;
		JwtDTO generateToken = jwtUtil.generateToken(userSpringSecurity.getUsername());
		response.addHeader("Authorization", generateToken.getFullToken());
		response.getWriter().append(new Gson().toJson(generateToken));
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/forgot")
	public ResponseEntity<Void> forgot(@RequestBody MailDTO mail){
		authService.sendNewPass(mail.getEmail());
		return ResponseEntity.noContent().build();
	}
	
	

}
