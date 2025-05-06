package app.college.repository;

import app.college.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {

    @Query(value = "SELECT \n" +
            "s.id AS student_id,\n" +
            "s.name AS student_name,\n" +
            "s.email,\n" +
            "GROUP_CONCAT(c.name SEPARATOR ', ') AS courses\n" +
            "FROM \n" +
            "Student s\n" +
            "LEFT JOIN \n" +
            "Enrollments e ON s.id = e.student_id\n" +
            "LEFT JOIN \n" +
            "Courses c ON e.course_id = c.id\n" +
            "GROUP BY \n" +
            "s.id, s.name, s.email;", nativeQuery = true)
    List<Object[]> findAllStudentWithCourses();
}
