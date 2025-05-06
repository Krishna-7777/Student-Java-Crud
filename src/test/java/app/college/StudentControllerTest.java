package app.college;

import app.college.controller.StudentController;
import app.college.model.Courses;
import app.college.model.Enrollments;
import app.college.model.Student;
import app.college.service.CourseService;
import app.college.service.EnrollmentService;
import app.college.service.StudentService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.ui.Model;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class StudentControllerTest {

    @InjectMocks
    private StudentController studentController;

    @Mock
    private StudentService studentService;

    @Mock
    private CourseService courseService;

    @Mock
    private EnrollmentService enrollmentService;

    @Mock
    private Model model;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testShowReadStudentPage() {
        when(studentService.findAllStudentWithCourses()).thenReturn(new ArrayList<>());

        String viewName = studentController.showReadStudentPage(model);
        verify(studentService).findAllStudentWithCourses();
        verify(model).addAttribute(eq("students"), any());
        assertEquals("readstudent", viewName);
    }

    @Test
    void testShowCreateStudentPage() {
        String viewName = studentController.showCreateStudentPage(model);
        verify(model).addAttribute(eq("command"), any(Student.class));
        assertEquals("createstudent", viewName);
    }

    @Test
    void testCreateStudent() {
        Student s = new Student();
        s.setId(1);
        when(studentService.saveStudent(any(Student.class))).thenReturn(s);

        String viewName = studentController.createStudent(s);
        assertEquals("redirect:/update-student-courses/1", viewName);
    }

    @Test
    void testShowUpdateStudentPage() {
        Student s = new Student();
        when(studentService.findById(1)).thenReturn(Optional.of(s));

        String viewName = studentController.showUpdateStudentPage(1, model);
        verify(model).addAttribute("id", 1);
        verify(model).addAttribute("command", s);
        assertEquals("updatestudent", viewName);
    }

    @Test
    void testUpdateStudent() {
        Student s = new Student();
        String viewName = studentController.updateStudent(1, s);
        verify(studentService).updateStudent(1, s);
        assertEquals("redirect:/read-students", viewName);
    }

    @Test
    void testDeleteStudent() {
        String viewName = studentController.deleteStudent(1);
        verify(enrollmentService).deleteByStudentId(1);
        verify(studentService).deleteById(1);
        assertEquals("redirect:/read-students", viewName);
    }

    @Test
    void testShowUpdateCoursesForm_ValidStudent() {
        Student s = new Student();
        s.setId(1);
        List<Courses> allCourses = Arrays.asList(new Courses(1, "Math"), new Courses(2, "Physics"));
        List<Enrollments> enrollments = new ArrayList<>();

        Enrollments e = new Enrollments();
        e.setStudent(s);
        e.setCourses(new Courses(1, "Math"));
        enrollments.add(e);

        when(studentService.findById(1)).thenReturn(Optional.of(s));
        when(courseService.findAll()).thenReturn(allCourses);
        when(enrollmentService.findByStudentId(1)).thenReturn(enrollments);

        String viewName = studentController.showUpdateCoursesForm(1, model);
        verify(model).addAttribute(eq("allCourses"), any());
        verify(model).addAttribute(eq("enrolledCourseIds"), any());
        verify(model).addAttribute(eq("student"), eq(s));
        assertEquals("updatestudentcourses", viewName);
    }

    @Test
    void testUpdateStudentCourses() {
        Student s = new Student();
        s.setId(1);

        Courses c1 = new Courses(1, "Math");
        Courses c2 = new Courses(2, "Science");

        when(studentService.findById(1)).thenReturn(Optional.of(s));
        when(courseService.findById(1)).thenReturn(Optional.of(c1));
        when(courseService.findById(2)).thenReturn(Optional.of(c2));

        List<Integer> courseIds = Arrays.asList(1, 2);
        String viewName = studentController.updateStudentCourses(1, courseIds);

        verify(enrollmentService).deleteByStudentId(1);
        verify(enrollmentService, times(2)).save(any(Enrollments.class));
        assertEquals("redirect:/read-students", viewName);
    }

    @Test
    void testUpdateStudentCourses_NoStudent() {
        when(studentService.findById(100)).thenReturn(Optional.empty());
        String view = studentController.updateStudentCourses(100, Arrays.asList(1, 2));
        assertEquals("redirect:/read-students", view);
    }
}
