package io.devsmile.khnu.repository;

import io.devsmile.khnu.model.Person;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonRepository extends MongoRepository<Person,ObjectId> {

}
