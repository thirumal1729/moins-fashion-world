package com.moins.fashion.world.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "admin")
	@SequenceGenerator(name = "admin", initialValue = 101, allocationSize = 1, sequenceName = "admin_sequence")
	private int adminId;
	private String adminName;
	private String email;
	private long phone;
	private String password;
}
