package com.meetup.teame.backend.domain.chatting.service;

import com.meetup.teame.backend.domain.chatting.dto.request.AppointmentChatMessageReq;
import com.meetup.teame.backend.domain.chatting.dto.request.EmoticonChatMessageReq;
import com.meetup.teame.backend.domain.chatting.dto.request.TextChatMessageReq;
import com.meetup.teame.backend.domain.chatting.dto.response.ChatMessageLogRes;
import com.meetup.teame.backend.domain.chatting.dto.response.ChatMessageRes;
import com.meetup.teame.backend.domain.chatting.entity.document.AppointmentChatMessage;
import com.meetup.teame.backend.domain.chatting.entity.document.ChatMessage;
import com.meetup.teame.backend.domain.chatting.entity.document.EmoticonChatMessage;
import com.meetup.teame.backend.domain.chatting.entity.document.TextChatMessage;
import com.meetup.teame.backend.domain.chatting.repository.ChattingRepository;
import com.meetup.teame.backend.domain.user.entity.User;
import com.meetup.teame.backend.domain.user.repository.UserRepository;
import com.meetup.teame.backend.global.exception.CustomException;
import com.meetup.teame.backend.global.exception.ExceptionContent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChattingService {
    private final UserRepository userRepository;
    private final ChattingRepository chattingRepository;

    public ChatMessageRes sendTextChatting(TextChatMessageReq textChatMessageReq, String chatRoomId) {
        User sender = userRepository.findById(textChatMessageReq.getSenderId())
                .orElseThrow(() -> new CustomException(ExceptionContent.NOT_FOUND_USER));
        TextChatMessage message = chattingRepository.insert(TextChatMessage.of(
                chatRoomId,
                textChatMessageReq.getSenderId(),
                sender.getName(),
                sender.getImageUrl(),
                LocalDateTime.now(),
                textChatMessageReq.getText()
        ));
        return ChatMessageRes.of(message);
    }

    public ChatMessageRes sendEmoticonChatting(EmoticonChatMessageReq emoticonChatMessageReq, String chatRoomId) {
        User sender = userRepository.findById(emoticonChatMessageReq.getSenderId())
                .orElseThrow(() -> new CustomException(ExceptionContent.NOT_FOUND_USER));
        EmoticonChatMessage message = chattingRepository.insert(EmoticonChatMessage.of(
                chatRoomId,
                emoticonChatMessageReq.getSenderId(),
                sender.getName(),
                sender.getImageUrl(),
                LocalDateTime.now(),
                emoticonChatMessageReq.getEmoticon()
        ));
        return ChatMessageRes.of(message);
    }

    public ChatMessageRes sendAppointmentChatting(AppointmentChatMessageReq appointmentChatMessageReq, String chatRoomId) {
        User sender = userRepository.findById(appointmentChatMessageReq.getSenderId())
                .orElseThrow(() -> new CustomException(ExceptionContent.NOT_FOUND_USER));
        AppointmentChatMessage message = chattingRepository.insert(AppointmentChatMessage.of(
                chatRoomId,
                appointmentChatMessageReq.getSenderId(),
                sender.getName(),
                sender.getImageUrl(),
                LocalDateTime.now(),
                appointmentChatMessageReq.getExperienceType(),
                appointmentChatMessageReq.getAppointmentTime(),
                appointmentChatMessageReq.getLocation()
        ));
        return ChatMessageRes.of(message);
    }

    public ChatMessageLogRes getChattingLog(String chatRoomId) {
        List<ChatMessage> chatMessageLog = chattingRepository.findByChatRoomId(chatRoomId);
        return ChatMessageLogRes.of(chatMessageLog.stream()
                .map(ChatMessageRes::of)
                .toList());
    }
}
