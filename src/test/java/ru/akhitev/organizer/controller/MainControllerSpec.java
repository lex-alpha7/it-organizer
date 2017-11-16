package ru.akhitev.organizer.controller;

import de.roskenet.jfxsupport.test.GuiTest;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.akhitev.organizer.view.MainView;

import javax.annotation.PostConstruct;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testfx.api.FxAssert.verifyThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MainControllerSpec extends GuiTest {

    @PostConstruct
    public void constructView() throws Exception {
        init(MainView.class);
    }

    @Test
    public void testClickButton_Berta() {
        Label label = (Label) find("#helloLabel");
        assertThat(label.getText())
                .as("When started, the combo box has promt text equal to expected text.")
                .isEqualTo("Hello World!");
    }
}
