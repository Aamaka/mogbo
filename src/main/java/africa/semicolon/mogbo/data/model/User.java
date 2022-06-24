package africa.semicolon.mogbo.data.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document("Users")
public class User {
    @Id
    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;

}
