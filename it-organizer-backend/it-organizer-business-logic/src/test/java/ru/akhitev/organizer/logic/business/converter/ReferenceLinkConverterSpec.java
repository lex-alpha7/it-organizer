package ru.akhitev.organizer.logic.business.converter;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.akhitev.organizer.db.entity.ReferenceLink;
import ru.akhitev.organizer.logic.business.dto.project.link.ReferenceLinkForEdit;
import ru.akhitev.organizer.logic.business.vo.project.link.ReferenceLinkForShow;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReferenceLinkConverterSpec  extends AbstractConverterSpec<ReferenceLink, ReferenceLinkForShow, ReferenceLinkForEdit, ReferenceLinkConverter> {
    @Value("${name.size}")
    protected Integer nameSize;

    private ReferenceLink referenceLink1;

    @Before
    public void setUp() {
        referenceLink1 = new ReferenceLink();
        referenceLink1.setName("Some Name");
    }

    @Autowired
    ReferenceLinkConverter converter;

    @Override
    TestPairOfEntitiesAndValueObjects<ReferenceLink, ReferenceLinkForShow> entitiesExists() {
        return null;
    }

    @Override
    TestPairOfEntitiesAndValueObjects<ReferenceLink, ReferenceLinkForShow> entitiesNotExists() {
        return null;
    }

    @Override
    TestPairOfEntitiesAndValueObjects<ReferenceLink, ReferenceLinkForShow> entitiesIsNull() {
        return null;
    }

    @Override
    TestPairOfEntitiesAndValueObjects<ReferenceLink, ReferenceLinkForShow> entitiesContainsNull() {
        return null;
    }

    @Override
    TestPairOfEntityAndDataTransferObject<ReferenceLink, ReferenceLinkForEdit> entityExist() {
        return null;
    }

    @Override
    TestPairOfEntityAndDataTransferObject<ReferenceLink, ReferenceLinkForEdit> entityNotExist() {
        return null;
    }

    @Override
    TestPairOfDataTransferObjectAndEntitiesBeforeAndAfter<ReferenceLink, ReferenceLinkForEdit> newEntityAndNotNullDTO() {
        return null;
    }

    @Override
    TestPairOfDataTransferObjectAndEntitiesBeforeAndAfter<ReferenceLink, ReferenceLinkForEdit> newEntityAndNullDTO() {
        return null;
    }

    @Override
    TestPairOfDataTransferObjectAndEntitiesBeforeAndAfter<ReferenceLink, ReferenceLinkForEdit> existedEntityAndNotNullDTO() {
        return null;
    }

    @Override
    TestPairOfDataTransferObjectAndEntitiesBeforeAndAfter<ReferenceLink, ReferenceLinkForEdit> existedEntityAndNullDTO() {
        return null;
    }

    @Override
    ReferenceLinkConverter converter() {
        return converter;
    }
}
