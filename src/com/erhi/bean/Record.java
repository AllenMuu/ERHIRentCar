package com.erhi.bean;

public class Record {
	private int rentId;
	private int carId;
	private String carName;

	private int userId;
	private String userName;

	private double carPrice;
	private double rentTotalPrice;
	private String carRemark;
	private String brandName;
	private String typeName;

	private String rentTime;
	private String returnTime;
	public int getRentId() {
		return rentId;
	}
	public void setRentId(int rentId) {
		this.rentId = rentId;
	}
	public int getCarId() {
		return carId;
	}
	public void setCarId(int carId) {
		this.carId = carId;
	}
	public String getCarName() {
		return carName;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public double getCarPrice() {
		return carPrice;
	}
	public void setCarPrice(double carPrice) {
		this.carPrice = carPrice;
	}
	public double getRentTotalPrice() {
		return rentTotalPrice;
	}
	public void setRentTotalPrice(double rentTotalPrice) {
		this.rentTotalPrice = rentTotalPrice;
	}
	public String getCarRemark() {
		return carRemark;
	}
	public void setCarRemark(String carRemark) {
		this.carRemark = carRemark;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getRentTime() {
		return rentTime;
	}
	public void setRentTime(String rentTime) {
		this.rentTime = rentTime;
	}
	public String getReturnTime() {
		return returnTime;
	}
	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}
	public Record(int rentId, int carId, String carName, int userId, String userName, double carPrice,
			double rentTotalPrice, String carRemark, String brandName, String typeName, String rentTime,
			String returnTime) {
		super();
		this.rentId = rentId;
		this.carId = carId;
		this.carName = carName;
		this.userId = userId;
		this.userName = userName;
		this.carPrice = carPrice;
		this.rentTotalPrice = rentTotalPrice;
		this.carRemark = carRemark;
		this.brandName = brandName;
		this.typeName = typeName;
		this.rentTime = rentTime;
		this.returnTime = returnTime;
	}
	public Record() {
		super();
	}
	@Override
	public String toString() {
		return "Record [rentId=" + rentId + ", carId=" + carId + ", carName=" + carName + ", userId=" + userId
				+ ", userName=" + userName + ", carPrice=" + carPrice + ", rentTotalPrice=" + rentTotalPrice
				+ ", carRemark=" + carRemark + ", brandName=" + brandName + ", typeName=" + typeName + ", rentTime="
				+ rentTime + ", returnTime=" + returnTime + "]";
	}
	
	
}
