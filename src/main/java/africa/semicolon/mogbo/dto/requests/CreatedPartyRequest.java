package africa.semicolon.mogbo.dto.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreatedPartyRequest {
    private String email;
    private String partyName;
    private String partyLocation;

}
