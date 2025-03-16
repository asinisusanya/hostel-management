package nazeem.web.controller;
import nazeem.web.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private nazeem.web.service.RoomService roomService;

    // Remove @Autowired and make these static final constants
    private static final String ADD_EDIT_TEMPLATE = "/admin/room/add-edit-room";
    private static final String LIST_TEMPLATE = "/admin/room/list-room";
    private static final String LIST_REDIRECT = "redirect:/room/list";



    @GetMapping("/list")
    public String listRoom(Model model) {
        List<Room> listRooms = roomService.listAll();
        model.addAttribute("listRooms", listRooms);  // Change listRoom to listRooms to match template
        return LIST_TEMPLATE;
    }

    @GetMapping("/add")
    public String addRoom(Room room, Model model) {
        model.addAttribute("room", room);
        return ADD_EDIT_TEMPLATE;
    }

    @GetMapping("/edit/{id}")
    public String editRoom(@PathVariable("id") long id, Model model) {
        Room room = roomService.get(id);
        model.addAttribute("room", room);
        return ADD_EDIT_TEMPLATE;
    }

    @PostMapping("/save")
    public String saveRoom(@Valid @ModelAttribute("room") Room room,
                             BindingResult result,
                             Model model) {
        model.addAttribute("room", room);

        if(result.hasErrors()) {
            return ADD_EDIT_TEMPLATE;
        }

        roomService.save(room);
        return LIST_REDIRECT;
    }

    @GetMapping("/delete/{id}")
    public String deleteRoom(@PathVariable("id") long id, Model model) {
        roomService.delete(id);
        return LIST_REDIRECT;
    }
}