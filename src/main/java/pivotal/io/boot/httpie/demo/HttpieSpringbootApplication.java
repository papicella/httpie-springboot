package pivotal.io.boot.httpie.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

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
