package com.project.java.java_project.repository;

import com.project.java.java_project.model.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <UsersEntity, Integer>{

    Optional<UsersEntity> findUsersEntitiesByNameAndPassword(String name, String password);

}

