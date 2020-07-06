package pl.sborowy.messageApp.domain;

import com.datastax.driver.core.DataType;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.cassandra.core.mapping.*;

import java.util.UUID;

@Table("messages")
public class Message {

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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getMagicNumber() {
        return magicNumber;
    }

    public void setMagicNumber(int magicNumber) {
        this.magicNumber = magicNumber;
    }
}
