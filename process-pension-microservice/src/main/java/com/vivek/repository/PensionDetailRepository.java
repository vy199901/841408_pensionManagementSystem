package com.vivek.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vivek.model.PensionDetail;

@Repository
public interface PensionDetailRepository extends JpaRepository<PensionDetail, Integer>{

}
