package com.meetup.teame.backend.domain.chatroom.service;

import com.meetup.teame.backend.domain.activity.entity.Activity;
import com.meetup.teame.backend.domain.activity.repository.ActivityRepository;
import com.meetup.teame.backend.domain.chatroom.dto.response.DirectChatRoomRes;
import com.meetup.teame.backend.domain.chatroom.dto.response.GroupChatRoomRes;
import com.meetup.teame.backend.domain.chatroom.dto.response.ReadDirectChatRoomsRes;
import com.meetup.teame.backend.domain.chatroom.dto.response.ReadGroupChatRoomsRes;
import com.meetup.teame.backend.domain.chatroom.entity.DirectChatRoom;
import com.meetup.teame.backend.domain.chatroom.entity.GroupChatRoom;
import com.meetup.teame.backend.domain.chatroom.entity.UserChatRoom;
import com.meetup.teame.backend.domain.chatroom.repository.DirectChatRoomRepository;
import com.meetup.teame.backend.domain.chatroom.repository.GroupChatRoomRepository;
import com.meetup.teame.backend.domain.chatroom.repository.UserChatRoomRepository;
import com.meetup.teame.backend.domain.chatting.entity.document.ChatMessage;
import com.meetup.teame.backend.domain.chatting.repository.ChattingRepository;
import com.meetup.teame.backend.domain.experience.entity.Experience;
import com.meetup.teame.backend.domain.experience.repository.ExperienceRepository;
import com.meetup.teame.backend.domain.user.entity.User;
import com.meetup.teame.backend.domain.user.repository.UserRepository;
import com.meetup.teame.backend.global.exception.CustomException;
import com.meetup.teame.backend.global.exception.ExceptionContent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatRoomService {
    private final UserRepository userRepository;
    private final DirectChatRoomRepository directChatRoomRepository;
    private final GroupChatRoomRepository groupChatRoomRepository;
    private final ActivityRepository activityRepository;
    private final UserChatRoomRepository userChatRoomRepository;
    private final ExperienceRepository experienceRepository;
    private final ChattingRepository chattingRepository;

    public ReadGroupChatRoomsRes readGroupChatRooms() {
        //todo : 현재는 더미 유저지만 추후에는 SecurityContextHolder 정보를 조회해서 유저 정보를 가져와야 함
        User user = userRepository.findById(5L)
                .orElseThrow(() -> new CustomException(ExceptionContent.NOT_FOUND_USER));
        return ReadGroupChatRoomsRes.of(
                user,
                groupChatRoomRepository.findForUser(user).stream()
                        .map(chatRoom -> {
                            String chatRoomId = String.valueOf(chatRoom.getId());
                            List<ChatMessage> chatMessages = chattingRepository.findByChatRoomIdOrderByCreatedAtDesc(chatRoomId);
                            ChatMessage chatMessage = chatMessages.isEmpty() ? null : chatMessages.get(0);
                            return GroupChatRoomRes.of(chatRoom, chatMessage);
                        })
                        .toList()
        );
    }

    public ReadDirectChatRoomsRes readDirectChatRooms() {
        //todo : 현재는 더미 유저지만 추후에는 SecurityContextHolder 정보를 조회해서 유저 정보를 가져와야 함
        User user = userRepository.findById(5L)
                .orElseThrow(() -> new CustomException(ExceptionContent.NOT_FOUND_USER));
        return ReadDirectChatRoomsRes.of(
                user,
                directChatRoomRepository.findForUser(user).stream()
                        .map(chatRoom -> {
                            String chatRoomId = String.valueOf(chatRoom.getId());
                            List<ChatMessage> chatMessages = chattingRepository.findByChatRoomIdOrderByCreatedAtDesc(chatRoomId);
                            ChatMessage chatMessage = chatMessages.isEmpty() ? null : chatMessages.get(0);
                            return DirectChatRoomRes.of(chatRoom, user, chatMessage);
                        })
                        .toList()
        );
    }

    @Transactional
    public Long joinGroupChatRoom(Long activityId) {
        //todo SecurityContextHolder 적용
        User user = userRepository.findById(5L)
                .orElseThrow(() -> new CustomException(ExceptionContent.NOT_FOUND_USER));
        GroupChatRoom groupChatRoom = groupChatRoomRepository.findByActivityId(activityId)
                .orElseGet(() -> {
                    Activity activity = activityRepository.findById(activityId)
                            .orElseThrow(() -> new CustomException(ExceptionContent.NOT_FOUND_ACTIVITY));
                    return groupChatRoomRepository.save(GroupChatRoom.of(activity));
                });
        if (userChatRoomRepository.existsByChatRoomIdAndUserId(groupChatRoom.getId(), user.getId()))
            throw new CustomException(ExceptionContent.BAD_REQUEST_ALREADY_JOIN_CHATROOM);
        userChatRoomRepository.save(UserChatRoom.of(groupChatRoom, user));
        return groupChatRoom.getId();
    }

    @Transactional
    public Long joinDirectChatRoom(Long experienceId) {
        //todo SecurityContextHolder 적용
        User user = userRepository.findById(5L)
                .orElseThrow(() -> new CustomException(ExceptionContent.NOT_FOUND_USER));
        Experience experience = experienceRepository.findById(experienceId)
                .orElseThrow(() -> new CustomException(ExceptionContent.NOT_FOUND_EXPERIENCE));
        DirectChatRoom directChatRoom = directChatRoomRepository.findByMentorAndMentee(experience.getUser(), user)
                .orElseGet(() -> {
                    return directChatRoomRepository.save(DirectChatRoom.of(experience, user));
                });
        if (!directChatRoom.getExperience().equals(experience))
            directChatRoom.setExperience(experience);
        if (userChatRoomRepository.existsByChatRoomIdAndUserId(directChatRoom.getId(), user.getId()))
            throw new CustomException(ExceptionContent.BAD_REQUEST_ALREADY_JOIN_CHATROOM);
        userChatRoomRepository.save(UserChatRoom.of(directChatRoom, user));
        return directChatRoom.getId();
    }
}
