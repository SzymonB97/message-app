package pl.sborowy.messageApp.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.InsertOptions;
import org.springframework.stereotype.Service;
import pl.sborowy.messageApp.domain.Message;
import pl.sborowy.messageApp.repository.MessageRepository;
import pl.sborowy.messageApp.services.MessageService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final CassandraOperations operations;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository, CassandraOperations operations) {
        this.messageRepository = messageRepository;
        this.operations = operations;
    }

    @Override
    public Message save(Message message) {
        InsertOptions insertOptions = InsertOptions.builder().ttl(300).build();
        operations.insert(message, insertOptions);

        return message;
    }

    @Override
    public void delete(UUID uuid) {
        messageRepository.deleteById(uuid);
    }

    @Override
    public Optional<List<Message>> findByMagicNumber(int magicNumber) {
        return messageRepository.findByMagicNumber(magicNumber);
    }

    @Override
    public Optional<List<Message>> findByEmail(String email) {
        return messageRepository.findByEmail(email);
    }
}
