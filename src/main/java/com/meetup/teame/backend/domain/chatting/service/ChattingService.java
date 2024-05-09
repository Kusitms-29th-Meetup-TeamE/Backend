package com.meetup.teame.backend.domain.chatting.service;

import com.meetup.teame.backend.domain.chatting.dto.request.AppointmentChatMessageReq;
import com.meetup.teame.backend.domain.chatting.dto.request.EmoticonChatMessageReq;
import com.meetup.teame.backend.domain.chatting.dto.request.TextChatMessageReq;
import com.meetup.teame.backend.domain.chatting.dto.response.ChatMessageRes;
import com.meetup.teame.backend.domain.chatting.entity.document.AppointmentChatMessage;
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

@Service
@RequiredArgsConstructor
public class ChattingService {
    private final UserRepository userRepository;
    private final ChattingRepository chattingRepository;

    public ChatMessageRes sendTextChatting(TextChatMessageReq textChatMessageReq) {
        User sender = userRepository.findById(textChatMessageReq.getSenderId())
                .orElseThrow(() -> new CustomException(ExceptionContent.NOT_FOUND_USER));
        chattingRepository.insert(TextChatMessage.of(
                textChatMessageReq.getSenderId(),
                LocalDateTime.now(),
                textChatMessageReq.getText()
        ));
        return ChatMessageRes.ofText(
                LocalDateTime.now(),
                sender.getName(),
                sender.getImageUrl(),
                textChatMessageReq.getText());
    }

    public ChatMessageRes sendEmoticonChatting(EmoticonChatMessageReq emoticonChatMessageReq) {
        User sender = userRepository.findById(emoticonChatMessageReq.getSenderId())
                .orElseThrow(() -> new CustomException(ExceptionContent.NOT_FOUND_USER));
        chattingRepository.insert(EmoticonChatMessage.of(
                emoticonChatMessageReq.getSenderId(),
                LocalDateTime.now(),
                emoticonChatMessageReq.getEmoticon()
        ));
        return ChatMessageRes.ofEmoticon(
                LocalDateTime.now(),
                sender.getName(),
                sender.getImageUrl(),
                emoticonChatMessageReq.getEmoticon());
    }

    public ChatMessageRes sendAppointmentChatting(AppointmentChatMessageReq appointmentChatMessageReq) {
        User sender = userRepository.findById(appointmentChatMessageReq.getSenderId())
                .orElseThrow(() -> new CustomException(ExceptionContent.NOT_FOUND_USER));
        chattingRepository.insert(AppointmentChatMessage.of(
                appointmentChatMessageReq.getSenderId(),
                LocalDateTime.now(),
                appointmentChatMessageReq.getExperienceType(),
                appointmentChatMessageReq.getAppointmentTime(),
                appointmentChatMessageReq.getLocation()
        ));
        return ChatMessageRes.ofAppointment(
                LocalDateTime.now(),
                sender.getName(),
                sender.getImageUrl(),
                appointmentChatMessageReq.getExperienceType(),
                appointmentChatMessageReq.getAppointmentTime(),
                appointmentChatMessageReq.getLocation());
    }
}
