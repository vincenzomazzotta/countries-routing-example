package cb.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
public class ExampleApplication {

	private static Logger logger = LoggerFactory.getLogger(ExampleApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ExampleApplication.class, args);
	}

}
