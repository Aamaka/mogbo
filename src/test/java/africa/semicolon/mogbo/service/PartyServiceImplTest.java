package africa.semicolon.mogbo.service;

import africa.semicolon.mogbo.data.model.Party;
import africa.semicolon.mogbo.data.repository.PartyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PartyServiceImplTest {

    @Autowired
    private PartyService partyService;

    @Autowired
    private PartyRepository partyRepository;


    @Test
    public void addNewPartyTest(){
        Party party = new Party();
        Party savedParty = partyService.saveParty(party);
        assertEquals(1, partyRepository.count());
    }
}