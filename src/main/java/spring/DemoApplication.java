package spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoApplication extends ServletInitializer{

	public static void main(String[] args) {
		ApplicationContext ctx =SpringApplication.run(DemoApplication.class, args);
	}
		@Override
		  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		      return builder.sources(DemoApplication.class);
		  }
	}

