package com.khaled.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khaled.entity.Employee;
import com.khaled.exception.EmpAlreadyExistsException;
import com.khaled.exception.NoSuchEmpExistsException;
import com.khaled.repository.EmployeeRepository;

@Service
public class EmpService {
	
	private EmployeeRepository repository;
	public EmpService(EmployeeRepository repository) {
		this.repository = repository;
	}
	
	
	public String addEmployee(Employee employee){
			Employee exsitingEmp= repository.findByEmpName(employee.getEmpName()).orElse(null);
			if(exsitingEmp!=null) {
				throw new EmpAlreadyExistsException("employee with name: "+employee.getEmpName()+" already exists!");
			}
			repository.save(employee);
	return "Employee saved successfully";
	}
	
	
	
	public Employee getEmployee(Integer id){
		Employee e =repository.findById(id).orElse(null);
		if(e==null) {
			throw new NoSuchEmpExistsException("employee with id:" +id+"doesn't exists!");
		}
		return e;
	}
	
	
	public String updateEmloyee(Integer id,Employee employee) 
	{
		Employee tempEmployee = repository.findById(id).orElse(null);
		if (tempEmployee==null) {
			throw new NoSuchEmpExistsException("Employee with id:"+id+" doesn't exists!");
		}
		String newName =employee.getEmpName();
		if (newName!=null && !newName.equals(tempEmployee.getEmpName()) ) {
			Optional<Employee> optionalEmp =repository.findByEmpName(newName);
			if(optionalEmp.isPresent()) {
				throw new EmpAlreadyExistsException("Employee name "+newName+" already exists");
			}
			tempEmployee.setEmpName(newName);
		}
		if(employee.getSalary()!=0) {
			tempEmployee.setSalary(employee.getSalary());
		}
		repository.save(tempEmployee);
		return "Employee Updated successfully";
	}


	public String deleteEmployee(Integer id) {
		Employee employee =repository.findById(id).orElseThrow(()-> new NoSuchEmpExistsException("Employee id "+id+" not exists"));

		repository.deleteById(id);
		return "Employee id: "+id+" deleted successfully! ";
	}
}
