package ru.akhitev.organizer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.akhitev.organizer.logic.business.service.ProjectService;
import ru.akhitev.organizer.logic.business.service.TicketService;

@Controller
public class MainController {

    @RequestMapping("/")
    public String project(@RequestParam(value="name", required=false, defaultValue="AirTransat") String name, Model model) {
        return "main";
    }
}
