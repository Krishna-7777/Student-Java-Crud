package app.college.service;

import app.college.repository.EnrollmentRepository;
import app.college.model.Enrollments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentService {
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public Iterable<Enrollments> findAll() {
        return enrollmentRepository.findAll();
    }

    public Optional<Enrollments> findById(int id) {
        return enrollmentRepository.findById(id);
    }

    @Transactional
    public Enrollments save(Enrollments enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    public Enrollments updateEnrollment(int id, Enrollments updatedEnrollment) {
        Enrollments existing = enrollmentRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setCourses(updatedEnrollment.getCourses());
            existing.setStudent(updatedEnrollment.getStudent());
            return enrollmentRepository.save(existing);
        }
        return null;
    }

    public void deleteById(int id) {
        enrollmentRepository.deleteById(id);
    }

    public List<Enrollments> findByStudentId(int studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }
    @Transactional
    public void deleteByStudentId(int studentId) {
        enrollmentRepository.deleteByStudentId(studentId);
    }
}
