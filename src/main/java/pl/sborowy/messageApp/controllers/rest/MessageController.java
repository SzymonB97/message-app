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
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping(path = "/message", consumes = MediaType.APPLICATION_JSON_VALUE, headers = {"Content-Type=application/json"})
    public ResponseEntity<Message> save(@RequestBody Message message) {
        if (!message.getEmail().equals("")
                && !message.getTitle().equals("")
                && !message.getContent().equals("")
                && !String.valueOf(message.getMagicNumber()).equals("")) {
            Message savedMessage = this.messageService.save(message);
            return new ResponseEntity<>(savedMessage, HttpStatus.CREATED);
        } else {
            throw new HttpClientErrorException(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(path = "/send", consumes = MediaType.APPLICATION_JSON_VALUE, headers = {"Content-Type=application/json"})
    public ResponseEntity<String> send(@RequestBody Message messageMagicNumber) {
        int magicNumber = messageMagicNumber.getMagicNumber();
        Optional<List<Message>> messages = this.messageService.findByMagicNumber(magicNumber);

        if (messages.isPresent() && !messages.get().isEmpty()) {
            messages.get().forEach(message -> this.messageService.delete(message.getId()));
            return new ResponseEntity<>("Emails with magic number " + magicNumber + " have been sent.", HttpStatus.ACCEPTED);
        } else {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/messages/{emailValue}")
    @ResponseStatus(value = HttpStatus.FOUND)
    public List<Message> getByEmail(@PathVariable("emailValue") String email) {
        Optional<List<Message>> messages = messageService.findByEmail(email);

        if (messages.isPresent() && !messages.get().isEmpty()) {
            return messages.get();
        } else {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
    }
}
