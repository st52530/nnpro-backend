package cz.upce.vetalmael.service.implementation;

import cz.upce.vetalmael.model.Animal;
import cz.upce.vetalmael.model.Message;
import cz.upce.vetalmael.model.User;
import cz.upce.vetalmael.model.dto.MessageDto;
import cz.upce.vetalmael.repository.MessageRepository;
import cz.upce.vetalmael.service.LoginService;
import cz.upce.vetalmael.service.MessageService;
import cz.upce.vetalmael.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service(value = "messageService")
public class MessageServiceImpl implements MessageService {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserService userService;

    @Override
    public Message sendMessage(MessageDto messageDto, int idAnimal, String senderUsername) {
        Message message = new Message();
        message.setText(messageDto.getText());
        Animal animal = entityManager.getReference(Animal.class, idAnimal);
        message.setAnimal(animal);
        User sender = userService.findByUsername(senderUsername);
        message.setSender(sender);
        return messageRepository.save(message);
    }
}
