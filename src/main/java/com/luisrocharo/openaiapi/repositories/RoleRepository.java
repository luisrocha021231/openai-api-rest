package com.luisrocharo.openaiapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luisrocharo.openaiapi.entities.RoleEntity;
import com.luisrocharo.openaiapi.enums.RoleList;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByName(RoleList name);
}
