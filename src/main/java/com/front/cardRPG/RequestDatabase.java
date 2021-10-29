package com.front.cardRPG;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestDatabase implements AutoCloseable {

	private Driver driver;
	private String uri = "bolt://localhost:11005";
	public static final Logger log = LoggerFactory.getLogger(RequestDatabase.class);

	private final String userDataBase = "neo4j";
	private final String passDataBase = "Gsy3o@";

	public String connexionNeo4j() {

		driver = GraphDatabase.driver(uri, AuthTokens.basic(userDataBase, passDataBase));

		try (Session session = driver.session()) {
			Result result = session.run("MATCH (u:User {name: 'Anush'}) RETURN u.name");
			log.info(result.single().get(0).asString());
			return result.toString();
		}

	}

	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub

	}

}
