package com.moins.fashion.world.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.moins.fashion.world.entity.Dress;
import com.moins.fashion.world.repository.DressRepository;

public class DressPrice {

	@Autowired
	private static DressRepository dressRepository;

	public static List<Dress> getListOfDresses(List<Integer> dressesId) {
		List<Dress> dressesList = new ArrayList<Dress>();
		for (Integer dressId : dressesId) {

			dressesList.add(dressRepository.findById(dressId).get());
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
