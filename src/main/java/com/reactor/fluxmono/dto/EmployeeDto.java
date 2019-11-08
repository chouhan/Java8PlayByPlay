package com.reactor.fluxmono.dto;


import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDto {

  @JsonProperty("id")
  public String id;

  @JsonProperty("employeeName")
  public String employeeName;

  @JsonProperty("employeeSalary")
  public Long employeeSalary;

  @JsonProperty("employeeAge")
  public Integer employeeAge;

  @JsonProperty("profileImage")
  public String profileImage;

}