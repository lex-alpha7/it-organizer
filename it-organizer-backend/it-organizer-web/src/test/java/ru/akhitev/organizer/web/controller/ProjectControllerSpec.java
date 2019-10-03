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
package ru.akhitev.organizer.web.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.akhitev.organizer.logic.business.dto.project.ProjectForEdit;
import ru.akhitev.organizer.logic.business.service.ProjectService;

import java.util.Collections;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProjectControllerSpec {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectService projectService;

    @Test
    public void whenNoProjectsFoundThenEmptyNavigation() throws Exception {
        mockMvc.perform(get("/project/new"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<div class=\"main-content\">\n" +
                        "        <form action=\"/project/save\" method=\"post\">\n" +
                        "            <input type=\"hidden\" id=\"id\" name=\"id\" value=\"\"/>\n" +
                        "            Название проекта: <input type=\"text\" id=\"name\" name=\"name\" value=\"\" /><br/>\n" +
                        "            <input type=\"submit\" value=\"Сохранить\" />\n" +
                        "        </form>\n" +
                        "    </div>")));
    }

    @Test
    public void whenEditExistingProjectWithNoTicketsThenFillFields() throws Exception {
        ProjectForEdit projectForEdit = new ProjectForEdit(0, "It Organizer", Collections.emptySet());
        when(projectService.giveForEdit(0)).thenReturn(projectForEdit);
        mockMvc.perform(get("/project/edit/0"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<div class=\"main-content\">\n" +
                        "        <form action=\"/project/save\" method=\"post\">\n" +
                        "            <input type=\"hidden\" id=\"id\" name=\"id\" value=\"0\"/>\n" +
                        "            Название проекта: <input type=\"text\" id=\"name\" name=\"name\" value=\"It Organizer\" /><br/>\n" +
                        "            <input type=\"submit\" value=\"Сохранить\" />\n" +
                        "        </form>\n" +
                        "    </div>")));
    }
}
