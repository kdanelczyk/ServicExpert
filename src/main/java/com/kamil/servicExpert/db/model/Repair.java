package com.kamil.servicExpert.db.model;

import java.util.Date;
import java.util.List;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import jakarta.persistence.*;
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
@Table(name = "repairs")
public class Repair {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "cost")
	private float cost;

	@Column(name = "note")
	private String note;

	@Column(name = "dateCreated")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "device_id", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private Device device;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private User user;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "repair_elements", joinColumns = { 
			@JoinColumn(name = "repair_id") }, inverseJoinColumns = {
			@JoinColumn(name = "element_id") })
	private List<Element> elements;
	
	public void addElement(Element element) {
		this.elements.add(element);
		element.getRepairs().add(this);
	}

	public void removeElement(long elementId) {
		Element element = this.elements.stream().filter(t -> t.getId() == elementId).findFirst().orElse(null);
		if (element != null) {
			this.elements.remove(element);
			element.getRepairs().remove(this);
		}
	}
}
