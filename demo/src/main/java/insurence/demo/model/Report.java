package insurence.demo.model;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    private String reportType;
    private Object reportData;  // Depending on the report type, this could be a list of policies, quotes, etc.
}

