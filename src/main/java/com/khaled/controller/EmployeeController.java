package com.khaled.controller;

import org.springframework.web.bind.annotation.*;

import com.khaled.entity.Employee;
import com.khaled.service.EmpService;

@RestController
@RequestMapping("/api/emp")
public class EmployeeController {
 	private EmpService empService;
    public EmployeeController(EmpService empService) {
 		this.empService = empService;
	}
	@PostMapping("/add")
	public String addEmployee(@RequestBody Employee employee) {
		return empService.addEmployee(employee);
	}

	@GetMapping("/{id}")
	public Employee getEmployee(@PathVariable(value = "id") Integer id){
		return empService.getEmployee(id);
	}

	@PutMapping("/update/{id}")
	public String updateEmployee(@PathVariable Integer id,Employee employee){
		return empService.updateEmloyee(id,employee);
	}


	@DeleteMapping("/delete/{id}")
	public String deleteEmployee(@PathVariable Integer id){
		return empService.deleteEmployee(id);
	}
	
	
}
