package africa.semicolon.mogbo.data.repository;

import africa.semicolon.mogbo.data.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

}
