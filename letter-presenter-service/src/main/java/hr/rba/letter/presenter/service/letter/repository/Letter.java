package hr.rba.letter.presenter.service.letter.repository;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.OffsetDateTime;

@Getter
@Entity
@Table(name = "LETTERS")
@ToString
@EntityListeners(AuditingEntityListener.class)
public class Letter {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "letters_seq")
    @SequenceGenerator(name = "letters_seq", sequenceName = "letters_seq", allocationSize = 1)
    @Setter(AccessLevel.PRIVATE)
    private Long id;

    @Column(nullable = false)
    private String letter;

    @Column(nullable = false)
    private String tracingId;

    @Column(nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime createdAt;

    protected Letter() {
    }

    public Letter(String letter, String tracingId) {
        this.letter = letter;
        this.tracingId = tracingId;
    }

    @PrePersist
    public void onPrePersist() {
        createdAt = OffsetDateTime.now();
    }
}
