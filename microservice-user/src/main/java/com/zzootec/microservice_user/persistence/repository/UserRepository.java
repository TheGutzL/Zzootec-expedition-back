package com.zzootec.microservice_user.persistence.repository;

import java.util.Optional;

import com.zzootec.microservice_user.persistence.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long>{

    Optional<UserEntity> findUserEntityByUsername(String username);

}
