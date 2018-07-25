package com.erhi.biz;

import java.util.ArrayList;
import java.util.List;

import com.erhi.bean.Car;
import com.erhi.bean.Record;
import com.erhi.bean.User;
import com.erhi.bean.UserCar;

public interface ICarBiz {
	//增加用户
	boolean addUser(String name, String passward );
	//是否有重名
	boolean isSame(String strName);
	//验证用户
	boolean logUser(String strName, String strPwd);
	//验证管理员
	boolean logAdmin(String strName, String strPwd);
	//用户价格排序(上架)
	ArrayList<Car> searchAsce();
	//用户价格降序(上架)
	ArrayList<Car> searchDesc();
	//用户类型搜索(上架)
	void searchType(int typeId);
	//用户品牌搜索(上架)
	void searchBrand(int brandId);
	//管理员查看全部汽车(上架)
	ArrayList<UserCar> searchAll();
	//
	ArrayList<Car> searchAdmin();

	boolean addCar(int bd, int td, String strCarName, String strCarNum, String strCarEm,
			String strCarColor, String strCarValue, String strCarPrice, String outsput1, String outsput2);

	void adminSeID(int carID);

	boolean alterAdmin(int sid, String strPrice, String outsput1);

	boolean isRent(int sc);

//	int getUserId(String strName);

	double getCarPrice(int sc);

	boolean setUserRecord(int sc, int uid, double d, int sr);


	boolean backCar(int cid);

	void userSearchRecord(int id);
	
	void searchAdminRecords();
	void showrentresult(int id);
	boolean isBack(int sc, int uid);
	User getUser(String strName);


	
	
}
