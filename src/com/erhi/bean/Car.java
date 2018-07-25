package com.erhi.bean;

public class Car {
	private int carId;
	private int typeId;
	private int brandId;
	private int userId;
	private String typeName;
	private String brandName;
	private String carName;
	private String brand;
	private String emission;
	private String status;
	private String useable;
	private Double price;
	private String license;
	private String color;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getCarId() {
		return carId;
	}
//	
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

	public String getUseable() {
		return useable;
	}

	public void setUseable(String useable) {
		this.useable = useable;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
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
	

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Car(int carId, int typeId, int brandId,int userId, String typeName, String brandName, String carName, String brand,
			String emission, String status, String useable, Double price, String license,String color) {
		super();
		this.carId = carId;
		this.typeId = typeId;
		this.brandId = brandId;
		this.userId = userId;
		this.typeName = typeName;
		this.brandName = brandName;
		this.carName = carName;
		this.brand = brand;
		this.emission = emission;
		this.status = status;
		this.useable = useable;
		this.price = price;
		this.license = license;
		this.color = color;
	}

	public Car() {
		super();
	}

	@Override
	public String toString() {
		return "Car [carId=" + carId + ", typeId=" + typeId + ", brandId=" + brandId + ", typeName=" + typeName
				+ ", brandName=" + brandName + ", carName=" + carName + ", brand=" + brand + ", emission=" + emission
				+ ", status=" + status + ", useable=" + useable + ", price=" + price + ", license=" + license
				+ ", color=" + color + "]";
	}

	
	

	

}
