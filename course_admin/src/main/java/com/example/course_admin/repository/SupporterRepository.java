package com.example.course_admin.repository;

import com.example.course_admin.database.FakeDB;
import com.example.course_admin.model.Supporter;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SupporterRepository {
    public List<Supporter> findAll(){
        return FakeDB.supporters;
    }

    public Optional<Supporter> findById(int id){
        return FakeDB.supporters.stream()
                .filter(sup -> sup.getId() == id)
                .findFirst();
    }
}
