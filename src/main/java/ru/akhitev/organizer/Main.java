package ru.akhitev.organizer;

import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.akhitev.organizer.view.MainView;

@SpringBootApplication
@EnableJpaRepositories
public class Main extends AbstractJavaFxApplicationSupport {

    public static void main(String[] args) {
        launchApp(Main.class, MainView.class, args);
    }

}
