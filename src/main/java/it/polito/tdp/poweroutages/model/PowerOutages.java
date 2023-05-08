package it.polito.tdp.poweroutages.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class PowerOutages implements Comparable<PowerOutages> {
	
	private int id;
//	private int eventTypeId;
//	private int tagId;
//	private int areaId;
//	private int responsibleId;
	private int customersAffected;
	private LocalDateTime dateEventBegan;
	private LocalDateTime dateEventFinished;
//	private int demandLoss;
	private long outageDuration;
	private int year;
	private Nerc nerc;
	public PowerOutages(int id, int customersAffected, LocalDateTime dateEventBegan, LocalDateTime dateEventFinished,
			Nerc nerc) {
		
		this.id = id;
		this.customersAffected = customersAffected;
		this.dateEventBegan = dateEventBegan;
		this.dateEventFinished = dateEventFinished;
		this.nerc = nerc;
		
		LocalDateTime tempDateTime = LocalDateTime.from(dateEventBegan);
		this.outageDuration = tempDateTime.until(dateEventFinished , ChronoUnit.HOURS);
		this.year = dateEventBegan.getYear();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCustomersAffected() {
		return customersAffected;
	}
	public void setCustomersAffected(int customersAffected) {
		this.customersAffected = customersAffected;
	}
	public LocalDateTime getDateEventBegan() {
		return dateEventBegan;
	}
	public void setDateEventBegan(LocalDateTime dateEventBegan) {
		this.dateEventBegan = dateEventBegan;
	}
	public LocalDateTime getDateEventFinished() {
		return dateEventFinished;
	}
	public void setDateEventFinished(LocalDateTime dateEventFinished) {
		this.dateEventFinished = dateEventFinished;
	}
	public long getOutageDuration() {
		return outageDuration;
	}
	public void setOutageDuration(long outageDuration) {
		this.outageDuration = outageDuration;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public Nerc getNerc() {
		return nerc;
	}
	public void setNerc(Nerc nerc) {
		this.nerc = nerc;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PowerOutages other = (PowerOutages) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "PowerOutages [id=" + id + ", customersAffected=" + customersAffected + ", dateEventBegan="
				+ dateEventBegan + ", dateEventFinished=" + dateEventFinished + ", outageDuration=" + outageDuration
				+ ", year=" + year + ", nerc=" + nerc + "]\n";
	}
	
	
	@Override
	public int compareTo(PowerOutages o) {
		return this.getDateEventBegan().compareTo(o.getDateEventBegan());
	}
	
	


	
	

}
