package com.moins.fashion.world.entity;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.moins.fashion.world.util.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder // if I use @Builder then by difault all constructord are private , so we have to write both annotations to activate it
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	@Email
	@Column(unique = true)
	private String email;

	
	private long phone;

	private String password;

	private String address;
	private String securityQuestion;
	private String answer;
	private boolean is_verified = false;
	@JsonIgnore
	private String myToken;
	private String alternativeEmail;

	@OneToMany(mappedBy = "customer")
	@JsonIgnore
	List<Rent> rents;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(() -> Role.CUSTOMER.toString());
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
