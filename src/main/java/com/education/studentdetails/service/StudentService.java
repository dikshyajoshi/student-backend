package com.education.studentdetails.service;

import com.education.studentdetails.entity.Student;
import com.education.studentdetails.exception.ResourceNotFound;
import com.education.studentdetails.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public List<Student> selectStudents(){
        return studentRepository.findAll();
    }
    public Student selectStudent(Long id) throws Exception {
       Optional<Student> student =studentRepository.findById(id);
       System.err.println(student);
       if(student.isPresent()){
           return student.get();
       }
//        if(studentRepository.existsById(id)){
//            return studentRepository.findById(id);   //Does not work as it returns Optional
//        }
       throw new Exception("Student Not Found");
    }
    public String saveDetails(Student student){
        System.out.println(student);
        studentRepository.save(student);
        return "Student details saved successfully";
    }

    public String deleteStudent(Long sid) {
//        Optional<Student> student= studentRepository.findById(sid);
//        if(student.isPresent()){
//            studentRepository.deleteById(sid);
//            return "Student Deleted";
//        }

        if(studentRepository.existsById(sid)){
            studentRepository.deleteById(sid);
            return "Student Deleted";
        }
        throw new ResourceNotFound("Student Not Found");

    }
   public String updateStudent(Long sid,Student student){
      Student existingStudent= studentRepository.findById(sid)
                .orElseThrow(()->new ResourceNotFound("Student does not Exist"));
       existingStudent.setFirstName(student.getFirstName());
       existingStudent.setLastName(student.getLastName());
       existingStudent.setAddress(student.getAddress());
       existingStudent.setEmail(student.getEmail());
       existingStudent.setLevel(student.getLevel());
       existingStudent.setGender(student.getGender());
       existingStudent.setContactNumber(student.getContactNumber());
       existingStudent.setDateOfBirth(student.getDateOfBirth());
       existingStudent.setMajor(student.getMajor());
       existingStudent.setCredit(student.getCredit());
       existingStudent.setProgram(student.getProgram());
       existingStudent.setOverallGPA(student.getOverallGPA());
       studentRepository.save(existingStudent);
        return "Updated Successfully";
   }
    public String updatePartialStudent(Long sid,Student student){
        Student existingStudent= studentRepository.findById(sid)
                .orElseThrow(()-> new ResourceNotFound("Student Not Found."));
        if(student.getFirstName() != null){
            existingStudent.setFirstName(student.getFirstName());
        }
        if(student.getLastName() != null){
            existingStudent.setLastName(student.getLastName());
        }
        if(student.getAddress() != null){
            existingStudent.setAddress(student.getAddress());
        }
        if(student.getContactNumber() != null){
            existingStudent.setContactNumber(student.getContactNumber());
        }
        if(student.getEmail() != null){
            existingStudent.setEmail(student.getEmail());
        }
        if(student.getDateOfBirth() != null){
            existingStudent.setDateOfBirth(student.getDateOfBirth());
        }
        if(student.getGender() != null){
            existingStudent.setGender(student.getGender());
        }
        if(student.getLevel() != null){
            existingStudent.setLevel(student.getLevel());
        }
        if(student.getMajor() != null){
            existingStudent.setMajor(student.getMajor());
        }
        if(student.getProgram() != null){
            existingStudent.setProgram(student.getProgram());
        }
        if(student.getOverallGPA() != null){
            existingStudent.setOverallGPA(student.getOverallGPA());
        }
        if(student.getCredit() != null){
            existingStudent.setCredit(student.getCredit());
        }
        studentRepository.save(existingStudent);
        return"Partial Update Successful.";
    }

}
