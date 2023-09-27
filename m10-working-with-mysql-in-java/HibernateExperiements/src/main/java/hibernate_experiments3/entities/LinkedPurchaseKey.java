package hibernate_experiments3.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class LinkedPurchaseKey implements Serializable {

    @Column(name = "student_id")
    private int studentId;

    @Column(name = "course_id")
    private int courseId;

    @SuppressWarnings("unused")
    public LinkedPurchaseKey() {
    }

    @Override
    public String toString() {
        return "LinkedPurchaseKey{" +
                "studentId=" + studentId +
                ", courseId=" + courseId +
                '}';
    }
}
