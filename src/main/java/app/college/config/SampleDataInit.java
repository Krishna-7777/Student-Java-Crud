package app.college.config;

import app.college.model.Courses;
import app.college.model.Enrollments;
import app.college.model.Student;
import app.college.repository.CourseRepository;
import app.college.repository.EnrollmentRepository;
import app.college.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SampleDataInit {
    @Bean
    CommandLineRunner SampleDataForDB(StudentRepository studentRepo,
                                      CourseRepository courseRepo,
                                      EnrollmentRepository enrollRepo) {
        return args -> {
            // Adding Courses
            if (courseRepo.count() == 0) {
                courseRepo.save(new Courses(1, "Mathematics"));
                courseRepo.save(new Courses(2, "Physics"));
                courseRepo.save(new Courses(3, "Chemistry"));
                courseRepo.save(new Courses(4, "Computer Science"));
                courseRepo.save(new Courses(5, "English"));
            }

            // Adding Sample Students
            long studentCount = studentRepo.count();
            if (studentCount == 0) {
                studentRepo.saveAll(Arrays.asList(
                        new Student(1, "Krishna", "krishna@mail.com"),
                        new Student(2, "Monkey D. Luffy", "luffy@mail.com"),
                        new Student(3, "Roronoa Zoro", "zoro@mail.com"),
                        new Student(4, "Nami", "nami@mail.com"),
                        new Student(5, "Usopp", "usopp@mail.com"),
                        new Student(6, "Sanji", "sanji@mail.com"),
                        new Student(7, "Tony Tony Chopper", "chopper@mail.com"),
                        new Student(8, "Vivi", "vivi@mail.com"),
                        new Student(9, "Shanks", "shanks@mail.com"),
                        new Student(10, "Mi Hawk", "hawk@mail.com")
                ));
            }

            // Adding Sample Enrollments
            if (studentCount == 0) {
                Student s1 = studentRepo.findById(1).orElse(null);
                Student s2 = studentRepo.findById(2).orElse(null);
                Student s3 = studentRepo.findById(3).orElse(null);
                Student s4 = studentRepo.findById(4).orElse(null);
                Student s5 = studentRepo.findById(5).orElse(null);
                Student s6 = studentRepo.findById(6).orElse(null);
                Student s7 = studentRepo.findById(7).orElse(null);
                Student s8 = studentRepo.findById(8).orElse(null);
                Student s9 = studentRepo.findById(9).orElse(null);
                Student s10 = studentRepo.findById(10).orElse(null);
                Courses c1 = courseRepo.findById(1).orElse(null);
                Courses c2 = courseRepo.findById(2).orElse(null);
                Courses c3 = courseRepo.findById(3).orElse(null);
                Courses c4 = courseRepo.findById(4).orElse(null);
                Courses c5 = courseRepo.findById(5).orElse(null);


                enrollRepo.saveAll(Arrays.asList(
                        new Enrollments(1, s1, c1),
                        new Enrollments(2, s1, c3),
                        new Enrollments(3, s1, c5),

                        new Enrollments(4, s2, c2),
                        new Enrollments(5, s2, c3),

                        new Enrollments(6, s3, c1),
                        new Enrollments(7, s3, c4),
                        new Enrollments(8, s3, c5),

                        new Enrollments(9, s4, c2),
                        new Enrollments(10, s4, c5),

                        new Enrollments(11, s5, c1),
                        new Enrollments(12, s5, c2),
                        new Enrollments(13, s5, c4),

                        new Enrollments(14, s6, c3),
                        new Enrollments(15, s6, c4),

                        new Enrollments(16, s7, c2),
                        new Enrollments(17, s7, c5),
                        new Enrollments(18, s7, c1),

                        new Enrollments(19, s8, c1),
                        new Enrollments(20, s8, c3),

                        new Enrollments(21, s9, c2),
                        new Enrollments(22, s9, c4),
                        new Enrollments(23, s9, c5),

                        new Enrollments(24, s10, c3),
                        new Enrollments(25, s10, c4)

                ));
            }
        };
    }
}
