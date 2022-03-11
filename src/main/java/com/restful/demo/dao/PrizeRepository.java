package com.restful.demo.dao;

import com.restful.demo.entity.Prize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrizeRepository extends JpaRepository<Prize, Long> {
}
