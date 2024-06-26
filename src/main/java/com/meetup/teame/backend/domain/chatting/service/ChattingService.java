package com.meetup.teame.backend.domain.chatting.service;

import com.meetup.teame.backend.domain.chatting.entity.Appointment;
import com.meetup.teame.backend.domain.chatting.entity.DirectChatRoom;
import com.meetup.teame.backend.domain.chatting.repository.ChatRoomRepository;
import com.meetup.teame.backend.domain.chatting.repository.DirectChatRoomRepository;
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
import com.meetup.teame.backend.domain.experience.entity.ExperienceType;
import com.meetup.teame.backend.domain.user.entity.User;
import com.meetup.teame.backend.domain.user.repository.UserRepository;
import com.meetup.teame.backend.global.exception.CustomException;
import com.meetup.teame.backend.global.exception.ExceptionContent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChattingService {
    private final UserRepository userRepository;
    private final ChattingRepository chattingRepository;
    private final DirectChatRoomRepository directChatRoomRepository;
    private final ChatRoomRepository chatRoomRepository;

    @Transactional
    public ChatMessageRes sendTextChatting(TextChatMessageReq textChatMessageReq, String chatRoomId) {
        User sender = userRepository.findById(textChatMessageReq.getSenderId())
                .orElseThrow(() -> new CustomException(ExceptionContent.NOT_FOUND_USER));
        LocalDateTime nowInKorea = ZonedDateTime.now(ZoneId.of("Asia/Seoul")).toLocalDateTime();
        TextChatMessage message = chattingRepository.insert(TextChatMessage.of(
                chatRoomId,
                textChatMessageReq.getSenderId(),
                sender.getName(),
                sender.getImageUrl(),
                nowInKorea,
                textChatMessageReq.getText()
        ));
        setChatRoomUpdateTime(chatRoomId);
        return ChatMessageRes.of(message);
    }

    @Transactional
    public ChatMessageRes sendEmoticonChatting(EmoticonChatMessageReq emoticonChatMessageReq, String chatRoomId) {
        User sender = userRepository.findById(emoticonChatMessageReq.getSenderId())
                .orElseThrow(() -> new CustomException(ExceptionContent.NOT_FOUND_USER));
        LocalDateTime nowInKorea = ZonedDateTime.now(ZoneId.of("Asia/Seoul")).toLocalDateTime();
        EmoticonChatMessage message = chattingRepository.insert(EmoticonChatMessage.of(
                chatRoomId,
                emoticonChatMessageReq.getSenderId(),
                sender.getName(),
                sender.getImageUrl(),
                nowInKorea,
                emoticonChatMessageReq.getEmoticon()
        ));
        setChatRoomUpdateTime(chatRoomId);
        return ChatMessageRes.of(message);
    }

    @Transactional
    public ChatMessageRes sendAppointmentChatting(AppointmentChatMessageReq appointmentChatMessageReq, String chatRoomId) {
        User sender = userRepository.findById(appointmentChatMessageReq.getSenderId())
                .orElseThrow(() -> new CustomException(ExceptionContent.NOT_FOUND_USER));
        LocalDateTime nowInKorea = ZonedDateTime.now(ZoneId.of("Asia/Seoul")).toLocalDateTime();
        Optional<DirectChatRoom> directChatRoom = directChatRoomRepository.findById(Long.parseLong(chatRoomId));
        ExperienceType experienceType = directChatRoom.
                map(chatRoom -> chatRoom.getExperience().getType())
                .orElse(null);

        AppointmentChatMessage message = chattingRepository.insert(AppointmentChatMessage.of(
                chatRoomId,
                appointmentChatMessageReq.getSenderId(),
                sender.getName(),
                sender.getImageUrl(),
                nowInKorea,
                experienceType,
                appointmentChatMessageReq.getAppointmentTime(),
                appointmentChatMessageReq.getLocation()
        ));
        setChatRoomUpdateTime(chatRoomId);
        chatRoomRepository.findById(Long.parseLong(chatRoomId))
                .ifPresent(chatRoom -> {//이미 정해진 약속이 있을 때 예외처리는 프론트 단에서 하는게 편할듯?
                    chatRoom.setNextAppointment(Appointment.of(
                            appointmentChatMessageReq.getAppointmentTime().toLocalDate(),
                            appointmentChatMessageReq.getLocation()
                    ));
                });
        return ChatMessageRes.of(message);
    }

    public ChatMessageLogRes getChattingLog(String chatRoomId) {
        List<ChatMessage> chatMessageLog = chattingRepository.findByChatRoomId(chatRoomId);
        return ChatMessageLogRes.of(chatMessageLog.stream()
                .map(ChatMessageRes::of)
                .toList());
    }

    public void setChatRoomUpdateTime(String chatRoomId) {
        chatRoomRepository.findById(Long.parseLong(chatRoomId))
                .ifPresent(chatRoom -> {
                    chatRoom.setUpdatedAt(LocalDateTime.now());
                });
    }
}
