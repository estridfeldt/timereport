package se.emst.timereport.infrastructure.persistence;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TimeEntryCrudRepository extends ReactiveMongoRepository<TimeDoc, String> {
}
