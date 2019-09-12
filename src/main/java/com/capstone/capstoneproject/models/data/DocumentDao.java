package com.capstone.capstoneproject.models.data;

import com.capstone.capstoneproject.models.Document;
import org.hibernate.cfg.JPAIndexHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface DocumentDao extends JpaRepository<Document, Integer> {
    @Query(value = "SELECT * FROM document WHERE CONTAINS (title, 'searchTerm')", nativeQuery = true)
    Document findBySearchTerm(@Param("searchTerm") String searchTerm);
}
