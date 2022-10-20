package com.kamil.servicExpert.db.model;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
@Table(name = "devices")
public class Device {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "customerPhoneNumber")
	@JsonProperty("customer_phone_number")
	@NotNull(message = "customerPhoneNumber is required.")
	private long customerPhoneNumber;

	@Column(name = "nameOfCustomer")
	@JsonProperty("name_of_customer")
	@Size(min = 3, max = 20, message = "Name should have min 3 and max 20 characters.")
	private String nameOfCustomer;

	@Column(name = "dateOfReceipt")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonProperty("date_of_receipt")
	private Date dateOfReceipt;
	
	@Column(name = "repaired")
	private boolean repaired;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "type_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Type type;

	@OneToMany(mappedBy = "device")
	@JsonIgnore
	private List<Repair> repairs;
}
