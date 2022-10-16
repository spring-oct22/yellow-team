package com.example.winery.repository;



import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.winery.entity.Wine;

import java.util.List;

@Repository
public interface WineRepository extends JpaRepository<Wine,Integer>{


    public List<Wine> findAllByOrderByRatingDesc(PageRequest pageRequest);

}
