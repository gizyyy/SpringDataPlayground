package com.example.persistence.entity;

import java.util.List;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.config.PrivateView;
import com.example.persistence.entity.converter.NameConverter;
import com.example.persistence.entity.converter.ObjectName;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "SCHOOL")
@Entity
public class School {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(value = PrivateView.class )
	private Integer id;
	
	@Convert(converter = NameConverter.class)
	private ObjectName schoolName;

	@OneToMany(mappedBy = "school", fetch = FetchType.LAZY)
	private List<Course> courses;
}
