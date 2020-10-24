package cz.upce.vetalmael.controller;

import cz.upce.vetalmael.model.Message;
import cz.upce.vetalmael.model.dto.MessageDto;
import cz.upce.vetalmael.service.MessageService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

import static cz.upce.vetalmael.config.SwaggerConfig.SWAGGER_AUTH_KEY;

@RestController
@RequestMapping("/message")
@SecurityRequirement(name = SWAGGER_AUTH_KEY)
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Transactional(rollbackOn = Exception.class)
    @PostMapping
    public ResponseEntity<Message> sendMessage(@RequestBody MessageDto messageDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getPrincipal().toString();
        return ResponseEntity.ok(messageService.sendMessage(messageDto,username));
    }
}
