package com.kamil.servicExpert.db.model;

import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
	@NotNull(message = "quantity is required.")
	private int quantity;
	
	@Column(name = "criticalQuantity")
	@JsonProperty("critical_quantity")
	@NotNull(message = "criticalQuantity is required.")
	private int criticalQuantity;
	
	@Column(name = "nameOfElement")
	@JsonProperty("name_of_element")
	@Size(min = 3, max = 20, message = "Name should have min 3 and max 20 characters.")
	private String nameOfElement;
	
	@Column(name = "priceOfElement")
	@JsonProperty("price_of_element")
	@NotNull(message = "quantity is required.")
	private float priceOfElement;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "type_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Type type;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "elements")
	@JsonIgnore
	private List<Repair> repairs;
}
