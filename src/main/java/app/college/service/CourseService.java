package app.college.service;

import app.college.repository.CourseRepository;
import app.college.model.Courses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public Iterable<Courses> findAll() {
        return courseRepository.findAll();
    }

    public Optional<Courses> findById(int id) {
        return courseRepository.findById(id);
    }

    public Courses saveCourse(Courses course) {
        return courseRepository.save(course);
    }

    public Courses updateCourse(int id, Courses updatedCourse) {
        Courses existing = courseRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setName(updatedCourse.getName());
            return courseRepository.save(existing);
        }
        return null;
    }

    public void deleteById(int id) {
        courseRepository.deleteById(id);
    }

}
