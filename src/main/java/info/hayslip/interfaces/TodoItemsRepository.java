package info.hayslip.interfaces;

import info.hayslip.models.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoItemsRepository extends MongoRepository<Todo, String> {
    List<Todo> findByPriority(int priority);
    List<Todo> findByPriorityLessThanEqual(int priority);
    List<Todo> findByPriorityGreaterThanEqual(int priority);

}
