package com.front.cardRPG;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ctc.wstx.shaded.msv_core.reader.State;

import dto.CharactDTO;
import dto.LoginDTO;
import dto.SigninDTO;
import dto.UserDTO;
import io.swagger.annotations.ApiParam;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class cardRPGController {

	public static final Logger log = LoggerFactory.getLogger(cardRPGController.class);
	public final String userDb = "phpmyadmin";
	public final String password = "Canelle3o@";
	public final String db = "RPGDB";

	RequestDatabase request = new RequestDatabase();

	@PostMapping(value = "/dataUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	public ResponseEntity<UserDTO> postDataLogin(
			@ApiParam(value = "Paramètres de composition", required = true) @RequestBody LoginDTO params)
			throws ClassNotFoundException {

		String name = params.getUsername();
		String pass = params.getPassword();

		log.info("Name player : {}", name);
		log.info("Password player : {}", pass);

		UserDTO user = new UserDTO();
		user.setUsername(name);
		user.setPassword(pass);

		Connection connection = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			connection = DriverManager
					.getConnection("jdbc:mariadb://localhost:3306/" + db + "?user=" + userDb + "&password=" + password);
			log.info("Connexion returns : {}", connection.isValid(0));
		} catch (SQLException e) {
			log.info("Erreur SQl : {}", e);
			e.printStackTrace();
		}

		return new ResponseEntity<>(user, HttpStatus.OK);

	}

	@PostMapping(value = "/dataSignin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	public ResponseEntity<UserDTO> postDataSign(
			@ApiParam(value = "Paramètres de composition", required = true) @RequestBody SigninDTO params)
			throws ClassNotFoundException {

		String name = params.getUsername();
		String pass = params.getPassword();
		String email = params.getEmail();

		log.info("Name player : {}", name);
		log.info("Password player : {}", pass);
		log.info("Mail player : {}", email);

		Connection connection = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			connection = DriverManager
					.getConnection("jdbc:mariadb://localhost:3306/" + db + "?user=" + userDb + "&password=" + password);

			String query = "INSERT INTO User (username, password, email) VALUES (?, ?, ?)";

			PreparedStatement statement = connection.prepareStatement(query);

			statement.setString(1, name);
			statement.setString(2, pass);
			statement.setString(3, email);

			statement.executeUpdate();

			connection.close();

		} catch (SQLException e) {
			log.info("Erreur SQl : {}", e);
			e.printStackTrace();
		}

		UserDTO user = new UserDTO();
		user.setUsername(name);
		user.setPassword(pass);

		/*
		 * String result = request.connexionNeo4j();
		 * 
		 * log.info("Match bdd : {}" , result);
		 */

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
		// user.setPassword(pass);

		/*
		 * String result = request.connexionNeo4j();
		 * 
		 * log.info("Match bdd : {}" , result);
		 */

		return new ResponseEntity<>(user, HttpStatus.OK);

	}

}
