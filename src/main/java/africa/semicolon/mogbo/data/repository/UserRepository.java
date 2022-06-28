package africa.semicolon.mogbo.data.repository;

import africa.semicolon.mogbo.data.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends MongoRepository<User, String> {

   User findByEmail(String email);
}
