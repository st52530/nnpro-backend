package cz.upce.vetalmael.controller;

import cz.upce.vetalmael.model.Message;
import cz.upce.vetalmael.model.dto.MessageDto;
import cz.upce.vetalmael.service.MessageService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

import static cz.upce.vetalmael.config.SwaggerConfig.SWAGGER_AUTH_KEY;

@RestController
@SecurityRequirement(name = SWAGGER_AUTH_KEY)
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Transactional(rollbackOn = Exception.class)
    @PostMapping("/animal/{idAnimal}/message")
    public ResponseEntity<Message> sendMessage(@RequestBody MessageDto messageDto, @PathVariable int idAnimal) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getPrincipal().toString();
        return ResponseEntity.ok(messageService.sendMessage(messageDto,idAnimal,username));
    }
}
