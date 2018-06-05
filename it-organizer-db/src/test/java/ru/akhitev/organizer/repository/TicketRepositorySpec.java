package ru.akhitev.organizer.repository;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.akhitev.organizer.entity.Project;
import ru.akhitev.organizer.entity.Ticket;

import javax.inject.Inject;
import java.util.Set;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TicketRepositorySpec {
    @Inject
    private TicketRepository ticketRepository;

    @Inject
    private ProjectRepository projectRepository;

//    @Test
    public void whenFindByProjectThenFindTickets() {
        Project project = projectRepository.getOne(0);
        Set<Ticket> tickets = ticketRepository.findByProject(project);
        Assertions.assertThat(tickets)
                .as("Right ticket count was found by project.").hasSize(2);
    }

}
