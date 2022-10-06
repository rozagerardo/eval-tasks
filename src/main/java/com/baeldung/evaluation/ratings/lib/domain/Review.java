package com.baeldung.evaluation.ratings.lib.domain;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false, updatable = false)
	private String uuid = UUID.randomUUID().toString();

	private String description;

	@NotNull
	@Min(0)
	@Max(100)
	private Integer rating;

	private LocalDateTime date = LocalDateTime.now();

	public Review() {
	}

	public Review(String description, Integer rating) {
		super();
		this.description = description;
		this.rating = rating;
	}

	public String getDescription() {
		return description;
	}

	public Integer getRating() {
		return rating;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public String getUuid() {
		return uuid;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		return Objects.hash(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Review other = (Review) obj;
		return Objects.equals(uuid, other.uuid);
	}
}
