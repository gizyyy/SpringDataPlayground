package com.example.persistence.entity;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.config.PrivateView;
import com.example.persistence.entity.converter.NameConverter;
import com.example.persistence.entity.converter.ObjectName;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "ADDRESS")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Data
@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(value = PrivateView.class )
	private Integer id;

	@Convert(converter = NameConverter.class)
	private ObjectName cityName;

}
