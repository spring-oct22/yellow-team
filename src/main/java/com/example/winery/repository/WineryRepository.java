package com.example.winery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.winery.entity.Winery;

@Repository
public interface WineryRepository extends JpaRepository<Winery,Integer>{

}
