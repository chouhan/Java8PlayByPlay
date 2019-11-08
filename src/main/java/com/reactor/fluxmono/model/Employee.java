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

//  @Column(name="employee_name")
  public String employee_name;

//  @Column(name="employee_salary")
  public Long employee_salary;

//  @Column(name="employee_age")
  public Integer employee_age;

//  @Column(name="profile_image")
  public String profile_image;

}