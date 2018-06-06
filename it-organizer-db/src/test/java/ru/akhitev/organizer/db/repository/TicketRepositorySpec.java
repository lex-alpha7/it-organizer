/*
 * IT-Organizer is an organizer for a developer and other IT-specialists.
 * Copyright (c) 2017 Aleksei Khitev (Хитёв Алексей Юрьевич).
 *
 * This file is part of IT-Organizer
 *
 * IT-Organizer is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * IT-Organizer is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */
package ru.akhitev.organizer.db.repository;

import org.assertj.core.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.akhitev.organizer.db.entity.Project;
import ru.akhitev.organizer.db.entity.Ticket;

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
