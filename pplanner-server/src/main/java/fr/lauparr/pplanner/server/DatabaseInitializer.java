package fr.lauparr.pplanner.server;

import fr.lauparr.pplanner.server.services.utils.SrvDatabaseInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer implements CommandLineRunner {

	@Autowired
	private SrvDatabaseInitializer srvDatabaseInitializer;

	@Override
	public void run(final String... args) throws Exception {
		this.srvDatabaseInitializer.initialize();
	}

}
