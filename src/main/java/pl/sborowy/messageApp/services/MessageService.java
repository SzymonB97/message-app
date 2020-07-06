package pl.sborowy.messageApp.services;

import pl.sborowy.messageApp.domain.Message;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MessageService {

    Message save(Message message);

    void delete(UUID uuid);

    Optional<List<Message>> findByMagicNumber(int magicNumber);

    Optional<List<Message>> findByEmail(String email);
}
