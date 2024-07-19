package com.app.persistence.repository;

import com.app.persistence.entity.AddressEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

    List<AddressEntity> findAllByUserId(Long userId);
}
