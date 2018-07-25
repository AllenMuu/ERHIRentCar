package com.erhi.bean;

public class UserCar {
	private int carId;
	private int typeId;
	private int brandId;
	private String typeName;
	private String brandName;
	private String carName;
	private String brand;
	private String emission;
	private String status;
	private Double price;
	public int getCarId() {
		return carId;
	}
	public void setCarId(int carId) {
		this.carId = carId;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public int getBrandId() {
		return brandId;
	}
	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getCarName() {
		return carName;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getEmission() {
		return emission;
	}
	public void setEmission(String emission) {
		this.emission = emission;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public UserCar(int carId, int typeId, int brandId, String typeName, String brandName, String carName, String brand,
			String emission, String status, Double price) {
		super();
		this.carId = carId;
		this.typeId = typeId;
		this.brandId = brandId;
		this.typeName = typeName;
		this.brandName = brandName;
		this.carName = carName;
		this.brand = brand;
		this.emission = emission;
		this.status = status;
		this.price = price;
	}
	public UserCar() {
		super();
	}
	
	
	

}
