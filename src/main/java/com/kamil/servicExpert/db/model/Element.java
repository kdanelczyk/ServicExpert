package com.kamil.servicExpert.db.model;

import java.math.BigDecimal;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "elements")
public class Element {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "critical_quantity")
	private int criticalQuantity;
	
	@Column(name = "name_of_element")
	private String nameOfElement;
	
	@Column(name = "price_of_element")
	private BigDecimal priceOfElement;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "type_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Type type;

}
