package pl.sborowy.messageApp.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.sborowy.messageApp.commands.MessageForm;
import pl.sborowy.messageApp.domain.Message;

@Component
public class MessageToMessageForm implements Converter<Message, MessageForm> {

    @Override
    public MessageForm convert(Message message) {
        MessageForm messageForm = new MessageForm();

        messageForm.setId(message.getId());
        messageForm.setEmail(message.getEmail());
        messageForm.setTitle(message.getTitle());
        messageForm.setContent(message.getContent());
        messageForm.setMagicNumber(message.getMagicNumber());

        return messageForm;
    }
}
