package pl.sborowy.messageApp.domain;

import com.datastax.driver.core.DataType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.*;

import java.util.UUID;

@Getter
@Setter
@Table("messages")
public class Message {

    // --fields--
    @PrimaryKey
    @CassandraType(type = DataType.Name.UUID)
    private UUID id;

    @Indexed
    private String email;

    private String title;

    private String content;

    @Indexed
    @Column(value = "magic_number")
    @JsonProperty("magic_number")
    private int magicNumber;

    // --constructors--
    public Message() {
        this.id = UUID.randomUUID();
    }

    public Message(String email, String title, String content, int magicNumber) {
        this.id = UUID.randomUUID();
        this.email = email;
        this.title = title;
        this.content = content;
        this.magicNumber = magicNumber;
    }
}
