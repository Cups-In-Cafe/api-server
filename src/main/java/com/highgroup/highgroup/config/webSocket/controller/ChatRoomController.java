package com.highgroup.highgroup.config.webSocket.controller;

import com.highgroup.highgroup.common.controller.DefaultController;
import com.highgroup.highgroup.common.model.BaseModel;
import com.highgroup.highgroup.common.utils.StringUtil;
import com.highgroup.highgroup.config.webSocket.model.ChatRoom;
import com.highgroup.highgroup.config.webSocket.repo.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/chat")
public class ChatRoomController   extends DefaultController {

    private final ChatRoomRepository chatRoomRepository;

    @RequestMapping(value = "/rooms")
    public BaseModel room() throws Exception{
        List<ChatRoom> chatRooms = chatRoomRepository.findAllRoom();
        chatRooms.stream().forEach(room -> room.setUserCount(chatRoomRepository.getUserCount(room.getRoomId())));
        Map<String,Object> obj = new HashMap<String,Object>();
        obj.put("resultList", chatRooms);
        return setStatus(obj);
        
    }

    @RequestMapping(value = "/create")
    public BaseModel createRoom() throws Exception{
        String roomNm = StringUtil.noNull( commandMap.get("roomNm") , "new Room");
        ChatRoom ChatRoom = chatRoomRepository.createChatRoom(roomNm);
        Map<String,Object> obj = new HashMap<String,Object>();
        obj.put("result",ChatRoom);
        return setStatus(obj);
    }

    
    @RequestMapping(value = "/room/{roomId}")
    public BaseModel roomInfo(@PathVariable String roomId) throws Exception{
        ChatRoom chatRoom= chatRoomRepository.findRoomById(roomId);
        Map<String,Object> obj = new HashMap<String,Object>();
        obj.put("result", chatRoom );
        return setStatus(obj);
    }


}
