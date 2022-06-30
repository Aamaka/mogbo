package africa.semicolon.mogbo.data.repository;

import africa.semicolon.mogbo.data.model.Party;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PartyRepository extends MongoRepository<Party, String> {

}
