//package com.ricbap.salvavidas.domain.service;
//
//import java.util.Random;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.ricbap.salvavidas.domain.model.Pessoa;
//import com.ricbap.salvavidas.domain.repository.PessoaRepository;
//
//@Service
//public class AuthService {
//	
//	@Autowired
//	private PessoaRepository pessoaRepository;
//	
//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;
//	
//	@Autowired
//	private EmailService emailService;
//	
//	private Random rand = new Random();
//	
//	public void sendNewPassword(String email) {
//		Pessoa pessoa = pessoaRepository.findByEmail(email);
//		if(pessoa == null) {
//			throw new EmptyResultDataAccessException(1);
//		}
//		
//		String newPass = newPassword();
//		pessoa.setSenha(bCryptPasswordEncoder.encode(newPass));
//		
//		pessoaRepository.save(pessoa);
//		emailService.sendNewPasswordEmail(pessoa, newPass);
//	}
//
//	private String newPassword() {
//		char[] vet = new char[10];
//		for(int i = 0; i < 10; i++) {
//			vet[i] = randomChar();
//		}
//		return new String(vet);
//	}
//
//	private char randomChar() {
//		int opt = rand.nextInt(3);
//		if(opt == 0) {        
//			return (char) (rand.nextInt(10) + 48); // <-- Gera um dígito
//		} else if(opt == 1) { 
//			return (char) (rand.nextInt(26) + 65); // <-- Gera letra maiúscula
//		} else {             
//			return (char) (rand.nextInt(26) + 97); // <-- Gera letra minúscula
//		}
//	}
//
//}
