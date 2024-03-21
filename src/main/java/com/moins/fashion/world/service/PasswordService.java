package com.moins.fashion.world.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.moins.fashion.world.dao.CustomerDao;
import com.moins.fashion.world.dto.ResponseStructure;
import com.moins.fashion.world.entity.Customer;
import com.moins.fashion.world.exception.MailFailedToSendException;


@Service
public class PasswordService {

	@Autowired
	private CustomerDao customerDao;
	
	@Autowired 
	private VerificationToken verificationToken;
	
	@Autowired
	private SendMailService sendMailService;
	
	public ResponseEntity<ResponseStructure<String>> forgotPassword(String email) {
	
		Customer receivedCustomer = customerDao.findbyEmail(email);
		
		if(receivedCustomer!=null) {
			
			String token = verificationToken.generateToken(receivedCustomer);
			String body = "your generated token is : "+token;
			String subject = "Password recovery token for"+receivedCustomer.getEmail();
			
			sendMailService.sendEmail(receivedCustomer.getAlternativeEmail(), subject, body);
			
			ResponseStructure<String> rs = new ResponseStructure<>();

			rs.setData("Recovery Mail Sent Successfully!");
			rs.setMessage("Success");
			rs.setStatusCode(HttpStatus.OK.value());

			return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.OK);

		} else {
			throw new MailFailedToSendException("User not found");
		}
		
	}
	public ResponseEntity<ResponseStructure<String>>verify(String token, String answer){
		
		Customer receivedCustomer = customerDao.findByToken(token);
		if (receivedCustomer != null && receivedCustomer.getAnswer().equalsIgnoreCase(answer)) {
			
			receivedCustomer.set_verified(true);
			customerDao.createCustomer(receivedCustomer);
			
			ResponseStructure<String> rs = new ResponseStructure<>();
			rs.setStatusCode(HttpStatus.OK.value());
			rs.setMessage("Success");
			rs.setData("Verification Done! You can now update password!");

		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.OK);	
		} else {
			throw new MailFailedToSendException("invalid token");
		}
	}
	
	public ResponseEntity<ResponseStructure<String>>setNewPassword(String token, String new_password){
		
		
		Customer receivedCustomer = customerDao.findByToken(token);

		if (receivedCustomer != null) {

			if (receivedCustomer.is_verified()) {

				receivedCustomer.setPassword(new_password);
				receivedCustomer.setMyToken(null);
				receivedCustomer.set_verified(false);

				customerDao.createCustomer(receivedCustomer);

				ResponseStructure<String> rs = new ResponseStructure<>();
				rs.setStatusCode(HttpStatus.OK.value());
				rs.setMessage("Success");
				rs.setData("Password Updated Successfully !!!");

				return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.OK);

			} else {
				throw new MailFailedToSendException("Token is not verified");
			}

		} else {
			throw new MailFailedToSendException("Invalid token");
		}

	}
}
