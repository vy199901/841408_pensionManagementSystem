package com.vivek.pms.pensionerdetail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vivek.pms.pensionerdetail.model.PensionTransactionDetail;

@Repository
public interface PensionTransactionDetailRepository extends JpaRepository<PensionTransactionDetail, String> {

}
