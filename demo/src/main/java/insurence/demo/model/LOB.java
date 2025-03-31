package insurence.demo.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "lob")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LOB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    private String description;

    private boolean isActive = true;
}

