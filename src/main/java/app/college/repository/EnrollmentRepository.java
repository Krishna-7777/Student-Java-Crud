package app.college.repository;

import app.college.model.Enrollments;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends CrudRepository<Enrollments, Integer> {
    List<Enrollments> findByStudentId(int studentId);
    void deleteByStudentId(int studentId);
}
