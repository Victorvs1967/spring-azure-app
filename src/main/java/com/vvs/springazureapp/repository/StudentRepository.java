package com.vvs.springazureapp.repository;

import com.vvs.springazureapp.model.Student;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Mono;

@Repository
public interface StudentRepository extends ReactiveMongoRepository<Student, ObjectId> {
  Mono<Student> findById(String id);
}
