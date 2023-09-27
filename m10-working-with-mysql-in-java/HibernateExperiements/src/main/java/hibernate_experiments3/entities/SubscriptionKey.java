package hibernate_experiments3.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.io.Serializable;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class SubscriptionKey implements Serializable {

    @NonNull
    @Column(name = "student_id", nullable = false)
    private int studentId;

    @NonNull
    @Column(name = "course_id", nullable = false)
    private int courseId;

    @SuppressWarnings("unused")
    public SubscriptionKey() {
    }

    @Override
    public String toString() {
        return "SubscriptionKey{" +
                "studentId=" + studentId +
                ", courseId=" + courseId +
                '}';
    }
}
