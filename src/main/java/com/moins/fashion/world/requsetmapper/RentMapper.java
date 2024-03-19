package com.moins.fashion.world.requsetmapper;

import java.util.List;

import com.moins.fashion.world.entity.Dress;
import com.moins.fashion.world.entity.Rent;
import com.moins.fashion.world.payload.RentDto;
import com.moins.fashion.world.util.DressPrice;
import com.moins.fashion.world.util.Status;

public class RentMapper {

	public static Rent mapToRent(RentDto rentDto) {
		
		DressPrice dp=new DressPrice();
		List<Dress> dresses = dp.getListOfDresses(rentDto.getDressesId());
		double totalPrice = dp.getTotalPriceOfDresses(dresses);

		return Rent.builder().rentDate(rentDto.getRentDate()).totalRentPrice(totalPrice).status(Status.PENDING).dresses(dresses).build();
	}
}
