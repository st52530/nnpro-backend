package cz.upce.vetalmael.service;

import cz.upce.vetalmael.model.Message;
import cz.upce.vetalmael.model.dto.MessageDto;

public interface MessageService {

    Message sendMessage(MessageDto messageDto, String senderUsername);
}
