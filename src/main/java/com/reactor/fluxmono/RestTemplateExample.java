package com.reactor.fluxmono;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reactor.fluxmono.dto.EmployeeDto;
import com.reactor.fluxmono.mapper.EmployeeMapper;
import com.reactor.fluxmono.model.Employee;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.json.Json;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootApplication
public class RestTemplateExample implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(RestTemplateExample.class);

    private EmployeeMapper employeeMapper = Mappers.getMapper(EmployeeMapper.class);

    private String resourceUrl = "http://dummy.restapiexample.com/api/v1/employees";

    public  static void main(String[] args){
        SpringApplication.run(RestTemplateExample.class, args);
    }


    @Bean
    public RestTemplate getRestTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);
        return restTemplate;
    }

    @Bean
    public HttpHeaders getHttpHeaders(){
        return new HttpHeaders();
    }

    @Bean
    public HttpEntity<?> getHttpEntity(HttpHeaders httpHeaders){
        return new HttpEntity<>(httpHeaders);
    }

    @Bean
    public ObjectMapper getObjectMapper(){
        return new ObjectMapper();
    }


    @Override
    public void run(String... args) throws Exception {
        //getHttpHeaders().set("APIKey", "");
        getHttpHeaders().setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        getHttpHeaders().setContentType(MediaType.APPLICATION_JSON);

        /*List<Employee> employeeList = (List<Employee>) getRestTemplate().getForObject(resourceUrl, Employee.class);

        //employeeList.stream().forEach(System.out::println);

        if(employeeList.size() > 0) {
            for (Employee employee:employeeList) {
                System.out.println(employee);
            }
        } else {
            System.out.println("No results");
        }*/





        /*ResponseEntity<Employee[]> responseEntity = getRestTemplate().exchange(resourceUrl, HttpMethod.GET, getHttpEntity(getHttpHeaders()), Employee[].class);

        if(responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            Arrays.stream(responseEntity.getBody()).forEach(employee -> {
                try {
                    EmployeeDto employeeDto = employeeMapper.employeeToEmployeeDto(employee);
                    System.out.println(getObjectMapper().writeValueAsString(employeeDto));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            });
        } else {
            System.out.println("Error");
        }*/





        /*ResponseEntity<PagedResources<Employee[]>> response = getRestTemplate().exchange(resourceUrl,
                HttpMethod.GET, getHttpEntity(getHttpHeaders()), new ParameterizedTypeReference<PagedResources<Employee[]>>() {});
        PagedResources<Employee[]> storiesResources = response.getBody();
        Collection<Employee[]> stories = storiesResources.getContent();*/

        ParameterizedTypeReference<RestResponsePage<Employee>> responseType = new ParameterizedTypeReference<RestResponsePage<Employee>>() { };

        ResponseEntity<RestResponsePage<Employee>> result = getRestTemplate().exchange(resourceUrl, HttpMethod.GET, getHttpEntity(getHttpHeaders()), responseType);

        List<Employee> searchResult = result.getBody().getContent();

        searchResult.forEach(employee -> {
            EmployeeDto employeeDto = employeeMapper.employeeToEmployeeDto(employee);
            try {
                System.out.println(getObjectMapper().writeValueAsString(employeeDto));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
    }

}
