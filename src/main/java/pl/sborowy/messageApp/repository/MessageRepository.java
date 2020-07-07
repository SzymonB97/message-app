package pl.sborowy.messageApp.repository;

import org.springframework.data.repository.CrudRepository;
import pl.sborowy.messageApp.domain.Message;

import java.util.List;
import java.util.UUID;

public interface MessageRepository extends CrudRepository<Message, UUID> {

    List<Message> findByMagicNumber(int magicNumber);

    List<Message> findByEmail(String email);
}
