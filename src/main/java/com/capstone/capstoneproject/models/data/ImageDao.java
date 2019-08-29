package com.capstone.capstoneproject.models.data;

import com.capstone.capstoneproject.models.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageDao extends CrudRepository<Image, Integer> {
}
