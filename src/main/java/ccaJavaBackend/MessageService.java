package ccaJavaBackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("MessageService")
public class MessageService {

    private MessagesRepository messagesRepository;

    @Autowired
    public MessageService(MessagesRepository messagesRepository)
    {
        this.messagesRepository = messagesRepository;
    }

    public Messages saveMessage(Messages message)
    {
        messagesRepository.save(message);
        return message;
    }
}