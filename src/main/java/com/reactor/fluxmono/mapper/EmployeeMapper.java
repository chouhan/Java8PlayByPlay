package com.reactor.fluxmono.mapper;

import com.reactor.fluxmono.dto.EmployeeDto;
import com.reactor.fluxmono.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface EmployeeMapper {
//    Employee employeeToEmployeeDto(EmployeeDto employeeDto);

    // Here is my source is Employee and would like it to EmployeeDto.
    // It is a a best practise to not expose the Entity Object directly (here it is Employee.java)
    @Mappings({
            @Mapping(target = "employeeName", source = "employee.employee_name"),
            @Mapping(target = "employeeSalary", source = "employee.employee_salary"),
            @Mapping(target = "employeeAge", source = "employee.employee_age"),
            @Mapping(target = "profileImage", source = "employee.profile_image")
    })
    EmployeeDto employeeToEmployeeDto(Employee employee);
}