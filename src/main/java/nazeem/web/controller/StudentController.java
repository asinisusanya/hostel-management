package nazeem.web.controller;

import nazeem.web.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private nazeem.web.service.StudentService studentService;

    // Remove @Autowired and make these static final constants
    private static final String ADD_EDIT_TEMPLATE = "/admin/student/add-edit-student";
    private static final String LIST_TEMPLATE = "/admin/student/list-student";
    private static final String LIST_REDIRECT = "redirect:/student/list";



    @GetMapping("/list")
    public String listStudent(Model model) {
        List<Student> listStudents = studentService.listAll();
        model.addAttribute("listStudents", listStudents);
        return LIST_TEMPLATE;
    }

    @GetMapping("/add")
    public String addStudent(Student student, Model model) {
        model.addAttribute("student", student);
        return ADD_EDIT_TEMPLATE;
    }

    @GetMapping("/edit/{id}")
    public String editStudent(@PathVariable("id") long id, Model model) {
        Student student = studentService.get(id);
        model.addAttribute("student", student);
        return ADD_EDIT_TEMPLATE;
    }

    @PostMapping("/save")
    public String saveStudent(@Valid @ModelAttribute("student") Student student,
                              BindingResult result,
                              Model model) {
        model.addAttribute("student", student);

        if(result.hasErrors()) {
            return ADD_EDIT_TEMPLATE;
        }

        studentService.save(student);
        return LIST_REDIRECT;
    }


    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") long id, Model model) {
        studentService.delete(id);
        return LIST_REDIRECT;
    }

    @GetMapping("/profile/{id}")
    public String viewProfile(@PathVariable("id") long id,Model model) {

            Student student = studentService.get(id);
            model.addAttribute("student", student);
            return "/admin/profile/list-profile";  // Correct path to your HTML

    }


}