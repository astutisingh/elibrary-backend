package com.example.payment.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.payment.model.Order;
import com.example.payment.model.RecieptClass;
import com.example.payment.repository.RecieptRepository;

@Service
public class PaymentServiceImpl{
	
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private RecieptRepository recieptRepository;
	
	
	public RecieptClass createPayment(String orderId, String username,RecieptClass recieptclass) {
		Order orders = restTemplate.getForObject("http://localhost:8083/order/order/"+orderId,Order.class);
		RecieptClass reciept = new RecieptClass();
		reciept.setUsername(username);
		reciept.setOrder(orders);
		reciept.setAmount(orders.getTotal());
		reciept.setStatus(recieptclass.getStatus());
		return recieptRepository.save(reciept);
		
	}
	
	public List<RecieptClass> findByOrderId(String orderId) {
		// TODO Auto-generated method stub
		List<RecieptClass> newCreatedList = new ArrayList<>();
		List<RecieptClass> getall=recieptRepository.findAll();
		for(RecieptClass b:getall) {
			if(b.getOrder().getOrderId().equals(orderId)) {
				newCreatedList.add(b);
				
			}
		}
		return newCreatedList;
		
		
	}
}

