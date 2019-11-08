package com.reactor.fluxmono.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person {

  private final String id;
  private final String firstName;
  private final String familyName;
  private final int age;

  public Person(String id, String firstName, String familyName, int age) {
    this.id = id;
    this.firstName = firstName;
    this.familyName = familyName;
    this.age = age;
  }
}