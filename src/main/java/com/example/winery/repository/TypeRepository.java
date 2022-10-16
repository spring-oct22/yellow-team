package com.example.winery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.winery.entity.Type;



@Repository
public interface TypeRepository  extends JpaRepository<Type,Integer>{

}
