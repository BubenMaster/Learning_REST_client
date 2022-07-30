package com.yojik.spring.rest;

import com.yojik.spring.rest.configuration.MyConfig;
import com.yojik.spring.rest.entity.Employee;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        Communication communication = context.getBean("communication", Communication.class);

        List<Employee> employeeList = communication.getAllEmployees();
        System.out.println(" *** All employees: ");
        employeeList.forEach(System.out::println);

        int id = 20;
        System.out.println(" *** Result of getEmployee method: ");
        Employee employee = communication.getEmployee(id);
        System.out.println(employee);

//        employee = new Employee("Patrick", "Semen", "Sales",870);
        employee.setSalary(1200);
        employee.setDepartment("sales");
        System.out.println(" *** Result of saveEmployee method: ");
        System.out.println(communication.saveEmployee(employee));
//
//        System.out.println(" *** Result of deleteEmployee method: ");
//        System.out.println(communication.deleteEmployee(21));
    }
}
