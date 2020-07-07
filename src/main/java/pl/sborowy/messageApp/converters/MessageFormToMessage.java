package pl.sborowy.messageApp.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import pl.sborowy.messageApp.model.MessageForm;
import pl.sborowy.messageApp.domain.Message;

@Component
public class MessageFormToMessage implements Converter<MessageForm, Message> {

    // --public methods--
    @Override
    public Message convert(MessageForm messageForm) {
        Message message = new Message();

        if (messageForm.getId() != null && !StringUtils.isEmpty(messageForm.getId())) {
            message.setId(messageForm.getId());
        }

        message.setEmail(messageForm.getEmail());
        message.setTitle(messageForm.getTitle());
        message.setContent(messageForm.getContent());
        message.setMagicNumber(messageForm.getMagicNumber());

        return message;
    }
}
