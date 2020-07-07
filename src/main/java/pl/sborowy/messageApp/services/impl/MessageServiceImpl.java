package pl.sborowy.messageApp.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.InsertOptions;
import org.springframework.stereotype.Service;
import pl.sborowy.messageApp.domain.Message;
import pl.sborowy.messageApp.repository.MessageRepository;
import pl.sborowy.messageApp.services.MessageService;

import java.util.Collections;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    // --fields--
    private final MessageRepository messageRepository;
    private final CassandraOperations operations;

    // --constructors--
    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository, CassandraOperations operations) {
        this.messageRepository = messageRepository;
        this.operations = operations;
    }

    // --public methods--
    @Override
    public Message save(Message message) {
        if (!message.getEmail().equals("")
                && !message.getTitle().equals("")
                && !message.getContent().equals("")
                && !String.valueOf(message.getMagicNumber()).equals("")) {
            InsertOptions insertOptions = InsertOptions
                    .builder()
                    .ttl(120)
                    .build();
            operations.insert(message, insertOptions);

            return message;
        }

        return null;
    }

    @Override
    public boolean send(int magicNumber) {
        List<Message> messages = messageRepository.findByMagicNumber(magicNumber);

        if (!messages.isEmpty()) {
            messages.forEach(message -> messageRepository.deleteById(message.getId()));

            return true;
        }

        return false;
    }

    @Override
    public List<Message> findByEmail(String email) {
        List<Message> messages = messageRepository.findByEmail(email);

        if (!messages.isEmpty()) {
            return messages;
        }

        return Collections.emptyList();
    }
}
