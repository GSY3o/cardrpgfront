package com.front.cardRPG;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dto.CharactDTO;
import dto.LoginDTO;
import dto.SigninDTO;
import dto.UserDTO;
import io.swagger.annotations.ApiParam;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class cardRPGController {

	public static final Logger log = LoggerFactory.getLogger(cardRPGController.class);


	RequestDatabase request = new RequestDatabase();
	
	@PostMapping(value = "/dataUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	public ResponseEntity<UserDTO> postDataLogin(
			@ApiParam(value = "Paramètres de composition", required = true) @RequestBody LoginDTO params) {

		String name = params.getUsername();
		String pass = params.getPassword();

		log.info("Name player : {}", name);
		log.info("Password player : {}", pass);

		UserDTO user = new UserDTO();
		user.setUsername(name);
		user.setPassword(pass);
		
		String result = request.connexionNeo4j();
		
		log.info("Match bdd : {}" , result);
		
		return new ResponseEntity<>(user, HttpStatus.OK);

	}
	
	@PostMapping(value = "/dataSignin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	public ResponseEntity<UserDTO> postDataSign(
			@ApiParam(value = "Paramètres de composition", required = true) @RequestBody SigninDTO params) {

		String name = params.getUsername();
		String pass = params.getPassword();
		String email = params.getEmail();
		
		log.info("Name player : {}", name);
		log.info("Password player : {}", pass);
		log.info("Mail player : {}", email);
		
		UserDTO user = new UserDTO();
		user.setUsername(name);
		user.setPassword(pass);
		
		/*String result = request.connexionNeo4j();
		
		log.info("Match bdd : {}" , result);*/
		
		return new ResponseEntity<>(user, HttpStatus.OK);

	}
	
	@PostMapping(value = "/dataCharacter", consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	public ResponseEntity<UserDTO> postDataCharachter(
			@ApiParam(value = "Paramètres de composition", required = true) @RequestBody CharactDTO params) {

		String name = params.getName();
		String classe = params.getCharClass();
		String force = params.getForce();
		String agi = params.getAgi();
		String intel = params.getIntel();
		String charism = params.getCharisme();
		
		log.info("Name player : {}", name);
		log.info("Classe player : {}", classe);
		log.info("Force player : {}", force);
		log.info("Agi player : {}", agi);
		log.info("Intel player : {}", intel);
		log.info("Charism player : {}", charism);
		
		UserDTO user = new UserDTO();
		user.setUsername(name);
		//user.setPassword(pass);
		
		/*String result = request.connexionNeo4j();
		
		log.info("Match bdd : {}" , result);*/
		
		return new ResponseEntity<>(user, HttpStatus.OK);

	}
	
	
	
}
