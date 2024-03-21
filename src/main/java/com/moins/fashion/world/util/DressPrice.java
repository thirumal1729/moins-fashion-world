package com.moins.fashion.world.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.moins.fashion.world.dao.DressDao;
import com.moins.fashion.world.entity.Dress;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Component
@NoArgsConstructor
@Builder
public class DressPrice {
	

	public  List<Dress> getListOfDresses(List<Integer> dressesId, DressDao dressDao) {

		List<Dress> dressesList = new ArrayList<Dress>();
		for (Integer dressId : dressesId) {

			dressesList.add(dressDao.findById(dressId));
		}

		return dressesList;
	}

	public static double getTotalPriceOfDresses(List<Dress> dressesList) {

		double totalPrice = 0;

		for (Dress dresses : dressesList) {
			totalPrice += dresses.getRentPrice();
		}

		return totalPrice;
	}


	
}
