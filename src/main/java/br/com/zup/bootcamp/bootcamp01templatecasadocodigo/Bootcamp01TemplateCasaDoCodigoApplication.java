package br.com.zup.bootcamp.bootcamp01templatecasadocodigo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class Bootcamp01TemplateCasaDoCodigoApplication {

	public static void main(String[] args) {
		SpringApplication.run(Bootcamp01TemplateCasaDoCodigoApplication.class, args);
	}

}
