package com.erhi.dao;

import java.util.ArrayList;
import java.util.List;

import com.erhi.bean.Car;
import com.erhi.bean.Record;
import com.erhi.bean.User;
import com.erhi.bean.UserCar;

public interface ICarDao {
	/*
	 * 用户注册(添加普通用户)
	 */
	boolean addUser(String name ,String passward );
	/*
	 * 普通用户登录
	 */
	boolean  logUser(String strName, String strPwd);
	/*
	 * 管理员登录
	 */
	boolean logAdmin(String strName, String strPwd);
	/*
	 * 注册时查看是否有相同的用户名 
	 */
	boolean isSame(String strName);
	//价格排序(上架)
	ArrayList<Car> searchAsce();
	//价格降序(上架)
	ArrayList<Car> searchDesc();
	//类型搜索(上架)
	void searchType(int typeId);
	//品牌搜索(上架)
	void searchBrand(int brandId);
	//用户查看全部汽车(上架)
	ArrayList<UserCar> searchAll();
	//管理员查询全部
	ArrayList<Car> searchAdmin();
	//增加车辆
	boolean addCar(int bd, int td, String strCarName, String strCarNum, String strCarEm,
			String strCarColor, String strCarValue, String strCarPrice, String outsput1, String outsput2);
	//管理员根据ID查询车辆
	void adminSeID(int carID);
	//管理员修改车辆信息
	boolean alterAdmin(int sid, String strPrice, String outsput1);
	//是否可租
	boolean isRent(int sc);
//	//由用户name得到用户ID
//	int getUserId(String strName);
	//根据汽车ID 得到 汽车
	double getCarPrice(int sc);
	boolean setUserRecord(int sc, int uid, double d, int sr);
	boolean backCar(int cid);
	void userSearchRecord(int id);
	void searchAdminRecords();
	void showrentresult(int id);
	boolean isBack(int sc, int uid);
	User getUser(String strName);
	



}
