package pivotal.io.boot.httpie.demo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping ("/api/employee")
public class EmployeeRest
{
    private static Log logger = LogFactory.getLog(EmployeeRest.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/emps")
    public List<Employee> allEmployees()
    {
        return employeeRepository.findAll();
    }

    @GetMapping("/emps/{employeeId}")
    public Employee findEmployee (@PathVariable Long employeeId)
    {
        Employee emp = employeeRepository.findOne(employeeId);

        return emp;
    }

    @PostMapping("/emps")
    public ResponseEntity<Void> createEmployee(@RequestBody Employee employee)
    {
        Employee emp = employeeRepository.save(employee);

        // Build the location URI of the new item
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{employeeId}")
                .buildAndExpand(emp.getId())
                .toUri();

        // Explicitly create a 201 Created response
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/emps")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateEmployee(@RequestBody Employee employee)
    {
        employeeRepository.save(employee);
    }

    @DeleteMapping("/emps/{employeeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable Long employeeId)
    {
        Employee emp = employeeRepository.findOne(employeeId);
        employeeRepository.delete(emp);
        logger.info("Employee with id " + employeeId + " deleted...");
    }

}
