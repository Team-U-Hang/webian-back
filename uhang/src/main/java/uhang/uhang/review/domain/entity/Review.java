package uhang.uhang.review.domain.entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity(name = "comments")
@Getter
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @Column(name = "event_id", nullable = false)
    private Long eventId;

    @Column(name = "review_rate", nullable = false)
    private Integer reviewRate;


    @Builder
    public Review(Long reviewId, Long eventId, Integer reviewRate) {
        this.reviewId = reviewId;
        this.eventId = eventId;
        this.reviewRate = reviewRate;
    }
}
