package pivotal.io.boot.httpie.demo;

import com.google.common.base.Predicates;
import io.swagger.annotations.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;

import static springfox.documentation.builders.PathSelectors.regex;

@SpringBootApplication
public class HttpieSpringbootApplication {

	@Autowired
	private EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(HttpieSpringbootApplication.class, args);
	}

	@PostConstruct
	public void init()
	{
		employeeRepository.save(new Employee("pas", "Apicella", "CEO"));
		employeeRepository.save(new Employee("lucia", "Apicella", "CIO"));
		employeeRepository.save(new Employee("lucas", "Apicella", "MANAGER"));
		employeeRepository.save(new Employee("siena", "Apicella", "CLERK"));
	}
}
