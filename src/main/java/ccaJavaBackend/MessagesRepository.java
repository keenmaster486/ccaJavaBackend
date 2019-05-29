package ccaJavaBackend;

// This will be AUTO IMPLEMENTED by Spring into a Bean called postRepository
// CRUD refers Create, Read, Update, Delete

import org.springframework.data.repository.CrudRepository;
import java.util.ArrayList;

public interface MessagesRepository extends CrudRepository<Messages, Long> {
    ArrayList<Messages> findByGroupId (Long groupId);
}