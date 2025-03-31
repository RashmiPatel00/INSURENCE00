package insurence.demo.model;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ratings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long quoteId;
    private Double premium;
    private LocalDateTime calculatedAt;
    private String ratingParameters;

    @ManyToOne
    @JoinColumn(name = "broker_id")
    private User broker;
}

