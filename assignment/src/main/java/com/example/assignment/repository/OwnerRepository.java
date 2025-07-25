package com.example.assignment.repository;

import com.example.assignment.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {


    List<Owner> findByName(String name);
}
