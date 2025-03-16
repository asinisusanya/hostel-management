package nazeem.web.controller;

import javax.validation.Valid;
import nazeem.web.model.Hostel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/hostel")
public class HostelController {

    @Autowired
    private nazeem.web.service.HostelService hostelService;

    // Remove @Autowired and make these static final constants
    private static final String ADD_EDIT_TEMPLATE = "/admin/hostel/add-edit-hostel";
    private static final String LIST_TEMPLATE = "/admin/hostel/list-hostel";
    private static final String LIST_REDIRECT = "redirect:/student/list";



    @GetMapping("/list")
    public String listHostel(Model model) {

        List<Hostel> listHostels = hostelService.listAll();
        model.addAttribute("listHostels", listHostels);

        return LIST_TEMPLATE;
    }

    @GetMapping("/add")
    public String addHostel(Hostel hostel, Model model) {
        model.addAttribute("hostel", hostel);
        return ADD_EDIT_TEMPLATE;
    }

    @GetMapping("/edit/{id}")
    public String editHostel(@PathVariable("id") long id, Model model) {
        Hostel hostel = hostelService.get(id);
        model.addAttribute("hostel", hostel);
        return ADD_EDIT_TEMPLATE;
    }

    @PostMapping("/save")
    public String saveHostel(@Valid @ModelAttribute("hostel") Hostel hostel,
                             BindingResult result,
                             Model model) {
        model.addAttribute("hostel", hostel);

        if(result.hasErrors()) {
            return ADD_EDIT_TEMPLATE;
        }

        hostelService.save(hostel);
        return LIST_REDIRECT;
    }





    @GetMapping("/delete/{id}")
    public String deleteHostel(@PathVariable("id") long id, Model model) {
        hostelService.delete(id);
        return LIST_REDIRECT;
    }
}