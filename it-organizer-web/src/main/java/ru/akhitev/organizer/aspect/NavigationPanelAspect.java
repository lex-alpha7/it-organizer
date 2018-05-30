package ru.akhitev.organizer.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;
import ru.akhitev.organizer.logic.business.service.ProjectService;
import ru.akhitev.organizer.logic.business.service.TicketService;

import java.util.Arrays;

@Aspect
@Configuration
public class NavigationPanelAspect {
    public static final int NAME_SIZE = 35;

    @Autowired
    ProjectService projectService;

    @Autowired
    TicketService ticketService;

    @Before("execution(* ru.akhitev.organizer.controller.*.*(..))  && args(..,model)")
    public void beforeImpl(Model model) {
        if (model == null) {
            return;
        }
        model.addAttribute("projects", projectService.giveProjectsForList(NAME_SIZE));
        model.addAttribute("tickets", ticketService.giveTicketsForListByProject(NAME_SIZE));
        model.addAttribute("ifActiveProject", projectService.ifActiveProject());
        model.addAttribute("ifActiveTicket", ticketService.ifActiveTicket());
    }
}
