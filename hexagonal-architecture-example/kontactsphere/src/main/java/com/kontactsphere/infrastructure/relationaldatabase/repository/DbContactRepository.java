package com.kontactsphere.infrastructure.relationaldatabase.repository;

import com.kontactsphere.infrastructure.relationaldatabase.model.DbContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DbContactRepository extends JpaRepository<DbContact, Long> {
    // R.A.S
}
