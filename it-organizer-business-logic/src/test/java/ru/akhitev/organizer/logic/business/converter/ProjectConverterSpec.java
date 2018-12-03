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
package ru.akhitev.organizer.logic.business.converter;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.akhitev.organizer.db.entity.Project;
import ru.akhitev.organizer.logic.business.dto.project.ProjectForEdit;
import ru.akhitev.organizer.logic.business.vo.project.ProjectForShow;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectConverterSpec extends AbstractConverterSpec<Project, ProjectForShow, ProjectForEdit, ProjectConverter> {
    /** size for adjustment too long names */
    @Value("${name.size}")
    protected Integer nameSize;

    private Project project1;

    @Autowired
    ProjectConverter converter;

    @Before
    public void setUp() {
        project1 = new Project();
        final Integer id1 = 0;
        project1.setId(id1);
        final String name1 = "Name 1";
        project1.setName(name1);
    }

    @Override
    TestPairOfEntitiesAndValueObjects<Project, ProjectForShow> entitiesExists() {
        List<Project> projects = new ArrayList<>();
        Set<ProjectForShow> expectedValueObjects = new HashSet<>();
        projects.add(project1);
        expectedValueObjects.add(new ProjectForShow(project1.getId(), project1.getName(), nameSize));
        Project project2 = new Project();
        final Integer id2 = 1;
        project2.setId(id2);
        final String name2 = "Name 2";
        project2.setName(name2);
        projects.add(project2);
        expectedValueObjects.add(new ProjectForShow(id2, name2, nameSize));
        return new TestPairOfEntitiesAndValueObjects<>(projects, expectedValueObjects);
    }

    @Override
    TestPairOfEntitiesAndValueObjects<Project, ProjectForShow> entitiesNotExists() {
        return new TestPairOfEntitiesAndValueObjects<>(Collections.emptyList(), Collections.emptySet());
    }

    @Override
    TestPairOfEntitiesAndValueObjects<Project, ProjectForShow> entitiesIsNull() {
        return new TestPairOfEntitiesAndValueObjects<>(null, Collections.emptySet());
    }

    @Override
    TestPairOfEntitiesAndValueObjects<Project, ProjectForShow> entitiesContainsNull() {
        List<Project> projects = new ArrayList<>();
        Set<ProjectForShow> expectedValueObjects = new HashSet<>();
        projects.add(project1);
        expectedValueObjects.add(new ProjectForShow(project1.getId(), project1.getName(), nameSize));
        projects.add(null);
        return new TestPairOfEntitiesAndValueObjects<>(projects, expectedValueObjects);
    }

    @Override
    TestPairOfEntityAndDataTransferObject<Project, ProjectForEdit> entityExist() {
        ProjectForEdit projectForEdit = new ProjectForEdit(project1.getId(), project1.getName(), Collections.emptySet());
        return new TestPairOfEntityAndDataTransferObject<>(project1, projectForEdit);
    }

    @Override
    TestPairOfEntityAndDataTransferObject<Project, ProjectForEdit> entityNotExist() {
        return new TestPairOfEntityAndDataTransferObject<>(null, null);
    }

    @Override
    TestPairOfDataTransferObjectAndEntitiesBeforeAndAfter<Project, ProjectForEdit> newEntityAndNotNullDTO() {
        ProjectForEdit projectForEdit = new ProjectForEdit(project1.getId(), project1.getName(), Collections.emptySet());
        return new TestPairOfDataTransferObjectAndEntitiesBeforeAndAfter<>(null, projectForEdit, project1);
    }

    @Override
    TestPairOfDataTransferObjectAndEntitiesBeforeAndAfter<Project, ProjectForEdit> newEntityAndNullDTO() {
        return new TestPairOfDataTransferObjectAndEntitiesBeforeAndAfter<>(null, null, null);
    }

    @Override
    TestPairOfDataTransferObjectAndEntitiesBeforeAndAfter<Project, ProjectForEdit> existedEntityAndNotNullDTO() {
        ProjectForEdit projectForEdit = new ProjectForEdit(project1.getId(), "project 2", Collections.emptySet());
        Project project2 = project1;
        project2.setName("project 2");
        return new TestPairOfDataTransferObjectAndEntitiesBeforeAndAfter<>(project1, projectForEdit, project2);
    }

    @Override
    TestPairOfDataTransferObjectAndEntitiesBeforeAndAfter<Project, ProjectForEdit> existedEntityAndNullDTO() {
        return new TestPairOfDataTransferObjectAndEntitiesBeforeAndAfter<>(project1, null, null);
    }

    @Override
    ProjectConverter converter() {
        return converter;
    }
}
