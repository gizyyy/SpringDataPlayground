package com.example.persistence.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.domain.AbstractAggregateRoot;

import com.example.config.PrivateView;
import com.example.persistence.entity.converter.NameConverter;
import com.example.persistence.entity.converter.ObjectName;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "STUDENT")
@Entity
@DynamicUpdate
public class Student extends AbstractAggregateRoot<Student> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(value = PrivateView.class)
	private Integer id;

	@Convert(converter = NameConverter.class)
	private ObjectName studentName;

	@OneToOne(targetEntity = Address.class, cascade = { CascadeType.ALL })
	@JoinColumn(name = "address_id")
	private Address address;

	@JoinTable(name = "course_students", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JsonUnwrapped
	private List<Course> courses;

	public Student announceCreation() {
		registerEvent(new StudentCreatedEvent(this));
		return this;

	}

}
