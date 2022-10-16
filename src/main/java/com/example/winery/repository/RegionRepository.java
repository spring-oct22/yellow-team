package com.example.winery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.winery.entity.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region,Integer> {

}
