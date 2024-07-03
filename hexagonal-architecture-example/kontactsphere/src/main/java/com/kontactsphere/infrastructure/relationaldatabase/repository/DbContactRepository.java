package com.kontactsphere.infrastructure.relationaldatabase.repository;

import com.kontactsphere.infrastructure.relationaldatabase.model.DbContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DbContactRepository extends JpaRepository<DbContact, Long> {
    Optional<DbContact> findById(Long contactId);
}
