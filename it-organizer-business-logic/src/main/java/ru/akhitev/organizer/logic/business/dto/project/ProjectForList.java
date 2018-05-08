package ru.akhitev.organizer.logic.business.dto.project;

import com.google.common.base.Joiner;
import org.springframework.util.CollectionUtils;
import ru.akhitev.organizer.logic.business.exception.OrganizerValidationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.stream.Collectors;

public class ProjectForList {
    private static final Integer NAME_SIZE = 20;
    @NotNull
    private final Integer id;

    @NotNull
    private final String name;

/*    private void validate() {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        Set<ConstraintViolation<Object>> constraintViolations = validator
                .validate(this);
        if (!constraintViolations.isEmpty()) {
            Set<String> errorMessages = constraintViolations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toSet());
            String message = Joiner.on("; ").join(errorMessages);
            throw new OrganizerValidationException(ProjectForList.class.getName(), message);
        }
    }*/

    public ProjectForList(Integer id, String name, Integer nameSize) {
        this.id = id;
        this.name = adjustName(name, nameSize);
    }

    private String adjustName(String name, Integer nameSize) {
        return name.substring(0, nameSize);
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
