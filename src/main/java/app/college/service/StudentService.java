package app.college.service;

import app.college.repository.StudentRepository;
import app.college.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Iterable<Student> findAll() {
        return studentRepository.findAll();
    }

    public Optional<Student> findById(int id) {
        return studentRepository.findById(id);
    }

    public Student saveStudent(Student Student) {
        return studentRepository.save(Student);
    }

    public Student updateStudent(int id, Student Student) {
        Student updatedStudent = studentRepository.findById(id).orElse(null);
        updatedStudent.setName(Student.getName());
        updatedStudent.setEmail(Student.getEmail());
        return studentRepository.save(updatedStudent);
    }

    public void deleteById(int id) {
        studentRepository.deleteById(id);
    }

    public List<Object[]> findAllStudentWithCourses() {
        return studentRepository.findAllStudentWithCourses();
    }

}
