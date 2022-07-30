package com.yojik.spring.rest;

import com.yojik.spring.rest.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Component
public class Communication {

    @Autowired
    private RestTemplate restTemplate;

    private final String URL = "http://localhost:8080/spring_course_rest/api/employees";

    public List<Employee> getAllEmployees(){
        ResponseEntity<List<Employee>> response =
            restTemplate.exchange(URL, HttpMethod.GET, null
            , new ParameterizedTypeReference<List<Employee>>() {});

        List<Employee> allEmployees = response.getBody();
        return allEmployees;
    }

    public Employee getEmployee(int id){
        ResponseEntity<Employee> response =
//            restTemplate.exchange(URL + "/" + id, HttpMethod.GET, null
//            , new ParameterizedTypeReference<Employee>() {});
                restTemplate.getForEntity(URL + "/" + id, Employee.class);
        Employee employee = response.getBody();
        return employee;
    }

    public String saveEmployee(Employee employee){
        ResponseEntity<String> response;
        int id = employee.getId();
//     if (id == 0) {
//         response = restTemplate.postForEntity(URL, employee, String.class);
//         System.out.println("Added new employee: " + response.getBody());
//     } else {
//         restTemplate.put(URL,employee);
//         System.out.println("Updated employee: " + employee);
//     }

        if (id == 0) {
            RequestEntity<Employee> requestEntityPOST = new RequestEntity<>(employee, HttpMethod.POST, URI.create(URL));
            response = restTemplate.exchange(URL, HttpMethod.POST, requestEntityPOST,new ParameterizedTypeReference<String>() {});
        }
        else {
            RequestEntity<Employee> requestEntityPUT = new RequestEntity<>(employee, HttpMethod.PUT, URI.create(URL));
            response = restTemplate.exchange(URL, HttpMethod.PUT, requestEntityPUT,new ParameterizedTypeReference<String>() {});
        }
        return response.getBody();
    }

    public String deleteEmployee(int id){
        ResponseEntity<String> response = restTemplate.exchange(URL + "/" + id, HttpMethod.DELETE, null
                , new ParameterizedTypeReference<String>() {});
        return response.getBody();
    }

}
