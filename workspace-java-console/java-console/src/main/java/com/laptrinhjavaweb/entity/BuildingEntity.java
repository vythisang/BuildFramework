package com.laptrinhjavaweb.entity;

import com.laptrinhjavaweb.annotation.Column;
import com.laptrinhjavaweb.annotation.Entity;
import com.laptrinhjavaweb.annotation.Table;

@Entity
@Table(name = "building")
public class BuildingEntity extends BaseEntity{

	@Column(name = "name")
	private String name;

	@Column(name = "numberofbasement")
	private Integer numberOfBasement;

	@Column(name = "buildingarea")
	private Integer buildingArea;

	@Column(name = "street")
	private String street;

	@Column(name = "ward")
	private String ward;

	@Column(name = "district")
	private String district;

	@Column(name = "structure")
	private String structure;

	@Column(name = "costrent")
	private Integer costRent;

	@Column(name = "costdescription")
	private Integer costDescription;

	@Column(name = "servicecost")
	private String servieCost;

	@Column(name = "carcost")
	private String carCost;

	@Column(name = "motorbikecost")
	private String motorbikeCost;

	@Column(name = "overtimecost")
	private String overtimeCost;

	@Column(name = "type")
	private String type;
	
	@Column(name = "electricitycost")
	private String electricityCost;
	
	@Column(name = "deposit")
	private String deposit;
	
	@Column(name = "payment")
	private String payment;
	
	@Column(name = "timerent")
	private String timeRent;
	
	@Column(name = "timedecorator")
	private String timeDecorator;
	
	@Column(name = "managername")
	private String managerName;
	
	
	
	public String getElectricityCost() {
		return electricityCost;
	}

	public void setElectricityCost(String electricityCost) {
		this.electricityCost = electricityCost;
	}

	public String getDeposit() {
		return deposit;
	}

	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	
	public String getTimeRent() {
		return timeRent;
	}

	public void setTimeRent(String timeRent) {
		this.timeRent = timeRent;
	}

	public String getTimeDecorator() {
		return timeDecorator;
	}

	public void setTimeDecorator(String timeDecorator) {
		this.timeDecorator = timeDecorator;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumberOfBasement() {
		return numberOfBasement;
	}

	public void setNumberOfBasement(Integer numberOfBasement) {
		this.numberOfBasement = numberOfBasement;
	}

	public Integer getBuildingArea() {
		return buildingArea;
	}

	public void setBuildingArea(Integer buildingArea) {
		this.buildingArea = buildingArea;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getStructure() {
		return structure;
	}

	public void setStructure(String structure) {
		this.structure = structure;
	}

	public Integer getCostRent() {
		return costRent;
	}

	public void setCostRent(Integer costRent) {
		this.costRent = costRent;
	}

	public Integer getCostDescription() {
		return costDescription;
	}

	public void setCostDescription(Integer costDescription) {
		this.costDescription = costDescription;
	}

	public String getServieCost() {
		return servieCost;
	}

	public void setServieCost(String servieCost) {
		this.servieCost = servieCost;
	}

	public String getCarCost() {
		return carCost;
	}

	public void setCarCost(String carCost) {
		this.carCost = carCost;
	}

	public String getMotorbikeCost() {
		return motorbikeCost;
	}

	public void setMotorbikeCost(String motorbikeCost) {
		this.motorbikeCost = motorbikeCost;
	}

	public String getOvertimeCost() {
		return overtimeCost;
	}

	public void setOvertimeCost(String overtimeCost) {
		this.overtimeCost = overtimeCost;
	}

}
