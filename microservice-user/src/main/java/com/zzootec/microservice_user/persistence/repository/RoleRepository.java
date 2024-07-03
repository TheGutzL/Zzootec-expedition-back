package com.zzootec.microservice_user.persistence.repository;

import java.util.List;

import com.zzootec.microservice_user.persistence.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    
    List<RoleEntity> findRoleEntitiesByRoleEnumIn(List<String> roleNames);

}
