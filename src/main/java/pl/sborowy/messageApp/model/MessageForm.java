package pl.sborowy.messageApp.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class MessageForm {

    // --fields--
    private UUID id;
    private String email;
    private String title;
    private String content;
    private int magicNumber;
}
