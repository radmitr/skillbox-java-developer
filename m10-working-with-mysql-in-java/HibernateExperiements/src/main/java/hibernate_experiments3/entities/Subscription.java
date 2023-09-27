package hibernate_experiments3.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "subscriptions")
public class Subscription implements Serializable {

    @EmbeddedId
    private SubscriptionKey id;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", referencedColumnName = "id", insertable = false, updatable = false)
    protected Student student;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", referencedColumnName = "id", insertable = false, updatable = false)
    protected Course course;

    @NonNull
    @Column(name = "subscription_date", nullable = false)
    private LocalDateTime subscriptionDate;

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", student=" + student +
                ", course=" + course +
                ", subscriptionDate=" + subscriptionDate +
                '}';
    }
}
