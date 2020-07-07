package pl.sborowy.messageApp.services;

import pl.sborowy.messageApp.domain.Message;

import java.util.List;

public interface MessageService {

    Message save(Message message);

    boolean send(int magicNumber);

    List<Message> findByEmail(String email);
}
