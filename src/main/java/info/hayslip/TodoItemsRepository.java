package info.hayslip;

import info.hayslip.models.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoItemsRepository extends MongoRepository<Todo, String> {

}
