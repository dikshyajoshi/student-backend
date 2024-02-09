package com.education.studentdetails.controller;

import com.education.studentdetails.entity.Student;
import com.education.studentdetails.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/v1/students")
public class StudentController {


    //@Autowired
    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService=studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> selectStudents(){
        return ResponseEntity.ok(studentService.selectStudents());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Student> selectStudent(@PathVariable ("id") Long sid) throws Exception {
        return ResponseEntity.ok(studentService.selectStudent(sid));
    }

    @PostMapping
    public ResponseEntity<String> saveStudentDetails(@Valid @RequestBody Student student){
                return ResponseEntity.ok(studentService.saveDetails(student));
        //return student.toString();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String >deleteStudent(@PathVariable("id") Long sid){
        System.out.println("id from front end: "+ sid);
        return ResponseEntity.ok(studentService.deleteStudent(sid));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable("id") Long sid,@RequestBody Student student){
        return ResponseEntity.ok(studentService.updateStudent(sid,student));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<String> updatePartialStudent(@PathVariable("id") Long sid, @RequestBody Student student){
    return ResponseEntity.ok(studentService.updatePartialStudent(sid,student));
    }
}