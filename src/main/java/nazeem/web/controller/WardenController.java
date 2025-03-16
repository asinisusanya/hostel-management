package nazeem.web.controller;
import nazeem.web.model.Warden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/warden")
public class WardenController {

    @Autowired
    private nazeem.web.service.WardenService wardenService;

    // Remove @Autowired and make these static final constants
    private static final String ADD_EDIT_TEMPLATE = "/admin/warden/add-edit-warden";
    private static final String LIST_TEMPLATE = "/admin/warden/list-warden";
    private static final String LIST_REDIRECT = "redirect:/warden/list";



    @GetMapping("/list")
    public String listWarden(Model model) {
        List<Warden> listWardens = wardenService.listAll();
        model.addAttribute("listWardens", listWardens);
        return LIST_TEMPLATE;
    }

    @GetMapping("/add")
    public String addWarden(Warden warden, Model model) {
        model.addAttribute("warden", warden);
        return ADD_EDIT_TEMPLATE;
    }
//@GetMapping("/add")
//public String addWarden(Model model) {
//    Warden warden = new Warden();
//    warden.setHostelId(0L);  // Or whatever default value makes sense
//    model.addAttribute("warden", warden);
//    return ADD_EDIT_TEMPLATE;
//}

    @GetMapping("/edit/{id}")
    public String editWarden(@PathVariable("id") long id, Model model) {
        Warden warden = wardenService.get(id);
        model.addAttribute("warden", warden);
        return ADD_EDIT_TEMPLATE;
    }

    @PostMapping("/save")
    public String saveWarden(@Valid @ModelAttribute("warden") Warden warden,
                             BindingResult result,
                             Model model) {
        model.addAttribute("warden", warden);

        if(result.hasErrors()) {
            return ADD_EDIT_TEMPLATE;
        }

        wardenService.save(warden);
        return LIST_REDIRECT;
    }

    @GetMapping("/delete/{id}")
    public String deleteWarden(@PathVariable("id") long id, Model model) {
        wardenService.delete(id);
        return LIST_REDIRECT;
    }
}