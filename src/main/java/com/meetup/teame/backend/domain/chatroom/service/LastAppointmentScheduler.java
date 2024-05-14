package com.meetup.teame.backend.domain.chatroom.service;

import com.meetup.teame.backend.domain.chatroom.entity.DirectChatRoom;
import com.meetup.teame.backend.domain.chatroom.entity.GroupChatRoom;
import com.meetup.teame.backend.domain.chatroom.repository.DirectChatRoomRepository;
import com.meetup.teame.backend.domain.chatroom.repository.GroupChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class LastAppointmentScheduler {
    private final DirectChatRoomRepository directChatRoomRepository;
    private final GroupChatRoomRepository groupChatRoomRepository;

    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    public void updateLastAppointment() {
        log.info("-----updateLastAppointment-----");

        List<DirectChatRoom> directChatRooms = directChatRoomRepository.findUpdatableRooms();
        for (DirectChatRoom directChatRoom : directChatRooms) {
            directChatRoom.setLastAppointment(directChatRoom.getNextAppointment());
            directChatRoom.setNextAppointment(null);
        }

        List<GroupChatRoom> groupChatRooms = groupChatRoomRepository.findUpdatableRooms();
        for (GroupChatRoom groupChatRoom : groupChatRooms) {
            groupChatRoom.setLastAppointment(groupChatRoom.getNextAppointment());
            groupChatRoom.setNextAppointment(null);
        }
    }
}
