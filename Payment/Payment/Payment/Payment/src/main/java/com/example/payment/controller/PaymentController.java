package com.example.payment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.payment.model.RecieptClass;
import com.example.payment.repository.RecieptRepository;
import com.example.payment.service.PaymentServiceImpl;

@RestController
@RequestMapping("/payment")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class PaymentController {
	
	@Autowired
	private RecieptRepository recieptRepository;
	
	@Autowired
	private PaymentServiceImpl serviceImpl;
	
	@PostMapping("/create/{orderId}/{username}")
    public ResponseEntity<RecieptClass> createPayment(@PathVariable String orderId,@PathVariable String username,@RequestBody RecieptClass recieptclass) {
		RecieptClass reciept = serviceImpl.createPayment(orderId, username, recieptclass);
        return ResponseEntity.ok(reciept);

	}
	
	@GetMapping("/get/payment/{username}")
	public List<RecieptClass> getPaymentDetails(@PathVariable String username){
		List<RecieptClass> getReceipt= recieptRepository.getByUsername(username);
		return getReceipt;
	}
	
//	@GetMapping("/paymentStatus/{username}/{orderId}")
//	public List<String> getPaymentStatus(@PathVariable String username, @PathVariable String orderId){
//		String status = "";
//		List<String> paymentStatus = new ArrayList<>();
//		List<RecieptClass> getReceipt= recieptRepository.getByUsername(username);
//		for(int i = 0;i<getReceipt.size();i++) {
//			System.out.println("Order Id"+getReceipt.get(i).getOrder().getOrderId());
//			if(getReceipt.get(i).getOrder().getOrderId().equals(orderId)) {
//				status+= getReceipt.get(i).getStatus();
//				paymentStatus.add(status);
//				
//			}else {
//				status+= "Not Found";
//			}
//		}
//		return paymentStatus;
//		
//		
//	}
	
	@GetMapping("/getStatus/{orderId}")
	public List<RecieptClass> getStatusByOrderId(@PathVariable String orderId){
		List<RecieptClass> getAll = serviceImpl.findByOrderId(orderId);
		return getAll;
	}
	
	

}