package ru.akhitev.organizer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class ProjectController {

    @RequestMapping("/project")
    public String welcome(@RequestParam(value="name", required=false, defaultValue="AirTransat") String name, Model model) {
        model.addAttribute("name", name);
        return "project";
    }
}
