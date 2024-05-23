package com.meetup.teame.backend.domain.chatting.service;

import com.meetup.teame.backend.domain.chatting.entity.DirectChatRoom;
import com.meetup.teame.backend.domain.chatting.entity.GroupChatRoom;
import com.meetup.teame.backend.domain.chatting.repository.DirectChatRoomRepository;
import com.meetup.teame.backend.domain.chatting.repository.GroupChatRoomRepository;
import com.meetup.teame.backend.domain.review.entity.Review;
import com.meetup.teame.backend.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class LastAppointmentScheduler {
    private final DirectChatRoomRepository directChatRoomRepository;
    private final GroupChatRoomRepository groupChatRoomRepository;
    private final ReviewRepository reviewRepository;

    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    public void updateLastAppointment() {
        log.info("-----updateLastAppointment-----");

        List<Review> reviews = new ArrayList<>();
        List<DirectChatRoom> directChatRooms = directChatRoomRepository.findUpdatableRooms();
        for (DirectChatRoom directChatRoom : directChatRooms) {
            reviews.add(Review.of(
                    directChatRoom.getExperience(),
                    directChatRoom.getExperience().getUser(),
                    directChatRoom.getMentee(),
                    directChatRoom.getNextAppointment()
            ));
            directChatRoom.setLastAppointment(directChatRoom.getNextAppointment());
            directChatRoom.setNextAppointment(null);
            directChatRoom.getExperience().incrementReviewCount();
        }
        reviewRepository.saveAll(reviews);

        List<GroupChatRoom> groupChatRooms = groupChatRoomRepository.findUpdatableRooms();
        for (GroupChatRoom groupChatRoom : groupChatRooms) {
            groupChatRoom.setLastAppointment(groupChatRoom.getNextAppointment());
            groupChatRoom.setNextAppointment(null);
        }
    }
}
