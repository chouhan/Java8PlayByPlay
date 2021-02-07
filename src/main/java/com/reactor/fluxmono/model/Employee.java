package com.reactor.fluxmono.model;


import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

//@Entity
//@Table(name = "API_DETAILS", schema = "DEMO")
//@Access(AccessType.FIELD)
@Getter
@Setter
public class Employee {

    //  @Id
//  @Column(name="id")
    public String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public Double getEmployee_salary() {
        return employee_salary;
    }

    public void setEmployee_salary(Double employee_salary) {
        this.employee_salary = employee_salary;
    }

    //  @Column(name="employee_name")
    public String employee_name;

    //  @Column(name="employee_salary")
    public Double employee_salary;

    //  @Column(name="employee_age")
    public Integer employee_age;

    //  @Column(name="profile_image")
    public String profile_image;

    public Employee(String id, String employee_name, Double employee_salary) {
        this.id = id;
        this.employee_name = employee_name;
        this.employee_salary = employee_salary;
    }
}