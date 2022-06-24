package com.highgroup.highgroup.config.webSocket.controller;

import java.util.List;
import java.util.Map;

import com.highgroup.highgroup.common.controller.DefaultController;
import com.highgroup.highgroup.config.webSocket.model.ChatMessage;
import com.highgroup.highgroup.config.webSocket.repo.ChatRoomRepository;
import com.highgroup.highgroup.config.webSocket.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.WebSocketHandler;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ChatController{

    private final ChatRoomRepository chatRoomRepository;
    private final ChatService chatService;

    /**
     * websocket "/pub/chat/message"로 들어오는 메시징을 처리한다.
     */
    @MessageMapping("/chat/message")
    //public void message(ChatMessage message, @Header("token") String token) 
    public void message( ChatMessage message ) 
    {       
        // 채팅방 인원수 세팅
        message.setUserCount(chatRoomRepository.getUserCount(message.getRoomId()));
        // Websocket에 발행된 메시지를 redis로 발행(publish)
        chatService.sendChatMessage(message);
    }
}
