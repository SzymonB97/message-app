package pl.sborowy.messageApp.controllers.rest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class ShutdownController implements ApplicationContextAware {

    // --fields--
    private ApplicationContext context;

    // --public methods--
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.context = applicationContext;
    }

    // --request methods--
    @PostMapping(path = "/shutdown")
    public void shutdownContext() {
        ((ConfigurableApplicationContext) context).close();
    }
}
