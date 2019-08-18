package com.capstone.capstoneproject.models.data;

import com.capstone.capstoneproject.models.Document;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface DocumentDao extends CrudRepository<Document, Integer> {}
