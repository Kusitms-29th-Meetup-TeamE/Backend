package com.meetup.teame.backend.domain.chatroom.entity;

import com.meetup.teame.backend.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Comment("채팅방")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "chat_type")
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "appointmentDate", column = @Column(name = "last_appointment_date")),
            @AttributeOverride(name = "appointmentLocation", column = @Column(name = "last_appointment_location"))
    })
    private Appointment lastAppointment;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "appointmentDate", column = @Column(name = "next_appointment_date")),
            @AttributeOverride(name = "appointmentLocation", column = @Column(name = "next_appointment_location"))
    })
    private Appointment nextAppointment;

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL)
    private List<UserChatRoom> userChatRooms;

    public void setLastAppointment(Appointment lastAppointment) {
        this.lastAppointment = lastAppointment;
    }

    public void setNextAppointment(Appointment nextAppointment) {
        this.nextAppointment = nextAppointment;
    }
}
