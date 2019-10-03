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

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.akhitev.organizer.logic.business.service.ProjectService;
import ru.akhitev.organizer.logic.business.vo.project.ProjectForShow;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.containsString;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MainControllerSpec {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectService projectService;

    @Value("${name.size}")
    private Integer nameSize;

    @Test
    public void whenNoProjectsFoundThenEmptyNavigation() throws Exception {
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<article class=\"navbar-left-menu\">\n" +
                        "            <h2 class=\"sidebar-title\">P<span class=\"nav-label\">rojects</span></h2>\n" +
                        "            <span class=\"nav-label\">\n" +
                        "                <table>\n" +
                        "                    \n" +
                        "                </table>\n" +
                        "                <button type=\"button\" name=\"addProject\" onclick=\"location.href='/project/new'\"><span class=\"glyphicon glyphicon-plus\"></span></button>\n" +
                        "            </span>\n" +
                        "        </article>")));
    }

    @Test
    public void whenShortNameIsTakenThenShowItFully() throws Exception {
        Set<ProjectForShow> projects = new HashSet<>();
        projects.add(new ProjectForShow(0, "It Organizer", nameSize));
        when(projectService.giveForShow()).thenReturn(projects);
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<article class=\"navbar-left-menu\">\n" +
                        "            <h2 class=\"sidebar-title\">P<span class=\"nav-label\">rojects</span></h2>\n" +
                        "            <span class=\"nav-label\">\n" +
                        "                <table>\n" +
                        "                    <tr>\n" +
                        "                        <td><a href=\"/project/activate/0\"><span class=\"nav_list_element\">It Organizer</span></a></td>\n" +
                        "                        <td><a href=\"/project/edit/0\"><span class=\"glyphicon glyphicon-pencil\" style=\"padding-left: 10px;\"></span></a></td>\n" +
                        "                        <td><a href=\"/project/delete/0\"><span class=\"glyphicon glyphicon-trash\" style=\"padding-left: 10px;\"></span></a></td>\n" +
                        "                    </tr>\n" +
                        "                </table>\n" +
                        "                <button type=\"button\" name=\"addProject\" onclick=\"location.href='/project/new'\"><span class=\"glyphicon glyphicon-plus\"></span></button>\n" +
                        "            </span>\n" +
                        "        </article>")));
    }

    @Test
    public void whenLongNameIsTakenThenAdjustIt() throws Exception {
        Set<ProjectForShow> projects = new HashSet<>();
        projects.add(new ProjectForShow(1, "A project with really long name that should be reduced", nameSize));
        when(projectService.giveForShow()).thenReturn(projects);
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<article class=\"navbar-left-menu\">\n" +
                        "            <h2 class=\"sidebar-title\">P<span class=\"nav-label\">rojects</span></h2>\n" +
                        "            <span class=\"nav-label\">\n" +
                        "                <table>\n" +
                        "                    <tr>\n" +
                        "                        <td><a href=\"/project/activate/1\"><span class=\"nav_list_element\">A project with really long name that...</span></a></td>\n" +
                        "                        <td><a href=\"/project/edit/1\"><span class=\"glyphicon glyphicon-pencil\" style=\"padding-left: 10px;\"></span></a></td>\n" +
                        "                        <td><a href=\"/project/delete/1\"><span class=\"glyphicon glyphicon-trash\" style=\"padding-left: 10px;\"></span></a></td>\n" +
                        "                    </tr>\n" +
                        "                </table>\n" +
                        "                <button type=\"button\" name=\"addProject\" onclick=\"location.href='/project/new'\"><span class=\"glyphicon glyphicon-plus\"></span></button>\n" +
                        "            </span>\n" +
                        "        </article>")));
    }

    // TODO: sorting
}
