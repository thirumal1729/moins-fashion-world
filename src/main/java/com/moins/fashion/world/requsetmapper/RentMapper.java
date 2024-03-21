package com.moins.fashion.world.requsetmapper;

import java.util.List;

import com.moins.fashion.world.dao.DressDao;
import com.moins.fashion.world.entity.Dress;
import com.moins.fashion.world.entity.Rent;
import com.moins.fashion.world.payload.RentDto;
import com.moins.fashion.world.util.DressPrice;
import com.moins.fashion.world.util.Status;

public class RentMapper {

	public static Rent mapToRent(RentDto rentDto, DressDao dressDao) {

		DressPrice dressPrice = new DressPrice();

		List<Dress> dresses = dressPrice.getListOfDresses(rentDto.getDressesId(), dressDao);
		
		double totalPrice = dressPrice.getTotalPriceOfDresses(dresses);

		Rent rent = Rent.builder().rentDate(rentDto.getRentDate()).totalRentPrice(totalPrice).status(Status.PENDING)
				.dresses(dresses).build();

//		for (Dress dressList : dresses) {
//			dressList.setRent(rent);
//			dressDao.saveDress(dressList);
//		}

		return rent;
	}
}
