package com.moins.fashion.world.requsetmapper;

import com.moins.fashion.world.entity.Dress;
import com.moins.fashion.world.payload.DressDto;

public class DressMapper {

	public static Dress mapToDress(DressDto dressDto, String filePath) {

		return 	Dress.builder().type(dressDto.getType()).priceMRP(dressDto.getPriceMRP()).rentPrice(dressDto.getRentPrice())
				.depositPrice(dressDto.getDepositPrice()).brandName(dressDto.getBrandName()).dressImage(filePath)
				.dressSize(dressDto.getDressSize()).build();
	}
}
