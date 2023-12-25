package com.learn.springrest.demo.rest;

import com.learn.springrest.demo.entity.Student;
import com.learn.springrest.demo.exceptions.data.StudentErrorResponse;
import com.learn.springrest.demo.exceptions.StudentNotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> students;
    //Add code for /hello endpoint

    @PostConstruct
    public void loadData()
    {
        students = new ArrayList<>();

        Student student1 = new Student("Uday","Kiran","udaykiran@tenl.com");
        Student student2 = new Student("Roger","Federer","rogerf@tenl.com");
        Student student3 = new Student("Novak","Djokovic","novakd@tenl.com");

        students.add(student1);
        students.add(student2);
        students.add(student3);
    }

    @GetMapping("/students")
    public List<Student> getStudents()
    {

        return students;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId)
    {

        if(studentId<0 || studentId >= students.size())
        {
            throw new StudentNotFoundException("Student not found for studentId :"+studentId);
        }

        return students.get(studentId);
        //return null;
    }

    //Add an exception handler using @ExceptionHandler


}
