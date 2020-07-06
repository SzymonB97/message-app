package pl.sborowy.messageApp.repository;

import org.springframework.data.repository.CrudRepository;
import pl.sborowy.messageApp.domain.Message;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MessageRepository extends CrudRepository<Message, UUID> {

    Optional<List<Message>> findByMagicNumber(int magicNumber);

    Optional<List<Message>> findByEmail(String email);
}
