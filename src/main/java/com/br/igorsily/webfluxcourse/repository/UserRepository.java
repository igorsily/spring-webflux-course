package com.br.igorsily.webfluxcourse.repository;

import com.br.igorsily.webfluxcourse.entity.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {


}
