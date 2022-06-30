package africa.semicolon.mogbo.dto.requests;

import lombok.Data;

@Data
public class CreatedPartyRequest {
    private String email;
    private String partyName;
    private String partyLocation;

}
