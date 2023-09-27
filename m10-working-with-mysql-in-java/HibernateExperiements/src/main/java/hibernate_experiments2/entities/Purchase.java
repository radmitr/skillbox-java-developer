package hibernate_experiments2.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "purchaselist")
public class Purchase implements Serializable {

    @EmbeddedId
    private PurchaseKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_name", referencedColumnName = "name", insertable = false, updatable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_name", referencedColumnName = "name", insertable = false, updatable = false)
    private Course course;

    @Column(name = "price")
    private int price;

    @Column(name = "subscription_date")
    private LocalDateTime subscriptionDate;

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", student=" + student +
                ", course=" + course +
                ", price=" + price +
                ", subscriptionDate=" + subscriptionDate +
                '}';
    }
}
