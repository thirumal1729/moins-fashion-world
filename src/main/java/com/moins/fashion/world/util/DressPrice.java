package com.moins.fashion.world.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.moins.fashion.world.dao.DressDao;
import com.moins.fashion.world.entity.Dress;
import com.moins.fashion.world.repository.DressRepository;

import lombok.Data;

@Data
@Component
public class DressPrice {
	@Autowired
	private DressDao dressDao;

	public  List<Dress> getListOfDresses(List<Integer> dressesId) {

		List<Dress> dressesList = new ArrayList<Dress>();
		for (Integer dressId : dressesId) {

			dressesList.add(dressDao.findById(dressId));
		}

		return dressesList;
	}

	public static double getTotalPriceOfDresses(List<Dress> dressesList) {

		double totalPrice = 0;

		for (Dress dresses : dressesList) {
			totalPrice += dresses.getPriceMRP();
		}

		return totalPrice;
	}
}
