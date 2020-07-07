package pl.sborowy.messageApp.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import pl.sborowy.messageApp.domain.Message;
import pl.sborowy.messageApp.services.MessageService;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class MessageController {

    // --fields--
    private final MessageService messageService;

    // --constructors--
    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    // --request methods--
    @PostMapping(path = "/message", consumes = MediaType.APPLICATION_JSON_VALUE, headers = {"Content-Type=application/json"})
    public ResponseEntity<Message> save(@RequestBody Message message) {
        Message saveMessage = messageService.save(message);

        if (saveMessage != null) {
            return new ResponseEntity<>(saveMessage, HttpStatus.CREATED);
        } else {
            throw new HttpClientErrorException(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(path = "/send", consumes = MediaType.APPLICATION_JSON_VALUE, headers = {"Content-Type=application/json"})
    public ResponseEntity<String> send(@RequestBody Message messageMagicNumber) {
        int magicNumber = messageMagicNumber.getMagicNumber();
        boolean sendResult = messageService.send(magicNumber);

        if (sendResult) {
            return new ResponseEntity<>("Emails with magic number " + magicNumber + " have been sent.", HttpStatus.ACCEPTED);
        } else {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/messages/{emailValue}")
    @ResponseStatus(value = HttpStatus.FOUND)
    public List<Message> getMessages(@PathVariable("emailValue") String email) {
        List<Message> messages = messageService.findByEmail(email);

        if (!messages.isEmpty()) {
            return messages;
        } else {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
    }
}
