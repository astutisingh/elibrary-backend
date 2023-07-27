package com.example.payment.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.example.payment.model.RecieptClass;

@Component
public interface RecieptRepository extends MongoRepository<RecieptClass, String>{
	
	List<RecieptClass> getByUsername(String username);

}
