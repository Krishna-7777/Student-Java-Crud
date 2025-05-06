package app.college.controller;

import app.college.model.Courses;
import app.college.model.Enrollments;
import app.college.model.Student;
import app.college.service.CourseService;
import app.college.service.EnrollmentService;
import app.college.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private EnrollmentService enrollmentService;

    @RequestMapping("/read-students")
    public String showReadStudentPage(Model model) {
        model.addAttribute("students", studentService.findAllStudentWithCourses());
        return "readstudent";
    }

    @RequestMapping("/create-student")
    public String showCreateStudentPage(Model model) {
        model.addAttribute("command", new Student());
        return "createstudent";
    }

    @RequestMapping(value = "/create-student", method = RequestMethod.POST)
    public String createStudent(@ModelAttribute("student") Student student) {
        Student saved = studentService.saveStudent(student);
        return "redirect:/update-student-courses/" + saved.getId();
    }

    @RequestMapping(value = "/update-student/{id}")
    public String showUpdateStudentPage(@PathVariable int id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("command", studentService.findById(id).orElse(null));
        return "updatestudent";
    }

    @RequestMapping(value = "/update-student/{id}", method = RequestMethod.POST)
    public String updateStudent(@PathVariable int id, @ModelAttribute("student") Student student) {
        studentService.updateStudent(id, student);
        return "redirect:/read-students";
    }

    @RequestMapping(value = "/delete-student/{id}")
    public String deleteStudent(@PathVariable int id) {
        enrollmentService.deleteByStudentId(id);
        studentService.deleteById(id);
        return "redirect:/read-students";
    }

    @RequestMapping(value = "/update-student-courses/{id}")
    public String showUpdateCoursesForm(@PathVariable("id") int studentId, Model model) {
        Optional<Student> student = studentService.findById(studentId);
        if (!student.isPresent()) {
            return "redirect:/read-students";
        }
        Iterable<Courses> allCourses = courseService.findAll();
        List<Enrollments> enrollments = enrollmentService.findByStudentId(studentId);
        String enrolledIdsCsv = enrollments.stream().map(e -> String.valueOf(e.getCourses().getId())).collect(Collectors.joining(","));
        model.addAttribute("allCourses", allCourses);
        model.addAttribute("enrolledCourseIds", enrolledIdsCsv);
        model.addAttribute("student", student.get());
        return "updatestudentcourses";
    }

    @RequestMapping(value = "/update-student-courses/{id}", method = RequestMethod.POST)
    public String updateStudentCourses(@PathVariable("id") int studentId, @RequestParam(value = "courseIds", required = false) List<Integer> courseIds) {
        Optional<Student> studentOpt = studentService.findById(studentId);
        if (!studentOpt.isPresent()) {
            return "redirect:/read-students";
        }
        Student student = studentOpt.get();
        enrollmentService.deleteByStudentId(studentId);
        if (courseIds != null) {
            for (Integer courseId : courseIds) {
                Optional<Courses> courseOpt = courseService.findById(courseId);
                if (courseOpt.isPresent()) {
                    Enrollments enrollment = new Enrollments();
                    enrollment.setStudent(student);
                    enrollment.setCourses(courseOpt.get());
                    enrollmentService.save(enrollment);
                }
            }
        }
        return "redirect:/read-students";
    }
}
