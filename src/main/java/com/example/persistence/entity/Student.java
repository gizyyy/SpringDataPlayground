package com.example.persistence.entity;

import java.util.List;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.config.PrivateView;
import com.example.persistence.entity.converter.NameConverter;
import com.example.persistence.entity.converter.ObjectName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "STUDENT")
@Entity
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(value = PrivateView.class )
	private Integer id;
	
	@Convert(converter = NameConverter.class)
	private ObjectName studentName;

	@OneToOne(targetEntity = Address.class)
	@JoinColumn(name = "address_id")
	private Address address;

	@ManyToMany(mappedBy = "students", fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = { "students" })
	@JsonUnwrapped
	private List<Course> courses;
}
