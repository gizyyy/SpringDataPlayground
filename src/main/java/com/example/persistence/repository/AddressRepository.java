package com.example.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.persistence.entity.Address;
import com.example.persistence.entity.converter.ObjectName;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
	
	public Optional<Address> findByCityName(ObjectName cityName);

}
