package insurence.demo.model;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "policies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Policy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String policyNumber;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String status;  // e.g., Active, Expired, Pending
    private String description;

    @ManyToOne
    @JoinColumn(name = "broker_id")
    private User broker;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
}

