package com.vivek.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vivek.model.PensionerDetail;

@Repository
public interface PensionerDetailRepository extends JpaRepository<PensionerDetail, String> {

}
