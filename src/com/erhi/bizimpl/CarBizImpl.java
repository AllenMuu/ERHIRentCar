package com.erhi.bizimpl;

import java.util.ArrayList;
import java.util.List;

import com.erhi.bean.Car;
import com.erhi.bean.Record;
import com.erhi.bean.User;
import com.erhi.bean.UserCar;
import com.erhi.biz.ICarBiz;
import com.erhi.dao.ICarDao;
import com.erhi.daoimpl.CarDaoImpl;

public class CarBizImpl implements ICarBiz {
	private ICarDao mCarDaoImpl;

	public CarBizImpl() {
		mCarDaoImpl = new CarDaoImpl();
	}

	/*
	 * 用户注册增加用户 (non-Javadoc)
	 * 
	 * @see com.erhi.biz.ICarBiz#addUser(com.erhi.bean.User)
	 */
	@Override
	public boolean addUser(String name, String passward) {
		// TODO Auto-generated method stub
		return mCarDaoImpl.addUser(name, passward);
	}

	/*
	 * 用户登录
	 * 
	 * @see com.erhi.biz.ICarBiz#logUser(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean logUser(String strName, String strPwd) {
		
		return mCarDaoImpl.logUser(strName, strPwd);
	}

	/*
	 * 管理员登录
	 * 
	 * @see com.erhi.biz.ICarBiz#logAdmin(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean logAdmin(String strName, String strPwd) {
		
		return mCarDaoImpl.logAdmin(strName, strPwd);
	}

	/*
	 * 注册时查询用户名是否已经存在 先查询管理员账户,后查询用户账户
	 * 
	 * @see com.erhi.biz.ICarBiz#isSame(java.lang.String)
	 */
	@Override
	public boolean isSame(String strName) {
		// TODO Auto-generated method stub
		return mCarDaoImpl.isSame(strName);
	}

	@Override
	public ArrayList<Car> searchAsce() {
		// TODO Auto-generated method stub
		return mCarDaoImpl.searchAsce();
	}

	@Override
	public ArrayList<Car> searchDesc() {
		// TODO Auto-generated method stub
		return mCarDaoImpl.searchDesc();
	}

	@Override
	public void searchType(int typeId) {
		mCarDaoImpl.searchType(typeId);
		
	}

	@Override
	public void searchBrand(int brandId) {
		mCarDaoImpl.searchBrand(brandId);
		
	}

	@Override
	public ArrayList<UserCar> searchAll() {
		// TODO Auto-generated method stub
		return mCarDaoImpl.searchAll();
	}


	@Override
	public ArrayList<Car> searchAdmin() {
		// TODO Auto-generated method stub
		return mCarDaoImpl.searchAdmin();
	}

	@Override
	public boolean addCar(int bd, int td, String strCarName, String strCarNum, String strCarEm,
			String strCarColor, String strCarValue, String strCarPrice, String outsput1, String outsput2) {
		return mCarDaoImpl.addCar(bd,td,strCarName,strCarNum,strCarEm,strCarColor,strCarValue,strCarPrice,outsput1,outsput2);
	}

	@Override
	public void adminSeID(int carID) {
		// TODO Auto-generated method stub
		mCarDaoImpl.adminSeID(carID);
	}

	
	@Override
	public boolean alterAdmin(int sid, String strPrice, String outsput1) {
		// TODO Auto-generated method stub
		return mCarDaoImpl.alterAdmin(sid,strPrice,outsput1);
	}

	@Override
	public boolean isRent(int sc) {
		// TODO Auto-generated method stub
		return mCarDaoImpl.isRent(sc);
	}

//	@Override
//	public int getUserId(String strName) {
//		// TODO Auto-generated method stub
//		return mCarDaoImpl.getUserId(strName);
//	}

	@Override
	public double getCarPrice(int sc) {
		// TODO Auto-generated method stub
		return mCarDaoImpl.getCarPrice(sc);
	}

	@Override
	public boolean setUserRecord(int sc, int uid, double d, int sr) {
		return mCarDaoImpl.setUserRecord(sc, uid, d, sr);
	}


	@Override
	public boolean backCar(int cid) {
		// TODO Auto-generated method stub
		return mCarDaoImpl.backCar(cid);
	}

	@Override
	public void userSearchRecord(int id) {
		mCarDaoImpl.userSearchRecord(id);
		
	}

	@Override
	public void searchAdminRecords() {
		// TODO Auto-generated method stub
		 mCarDaoImpl.searchAdminRecords();
	}

	@Override
	public void showrentresult(int id) {
		// TODO Auto-generated method stub
		mCarDaoImpl.showrentresult(id);
	}

	@Override
	public boolean isBack(int sc, int uid) {
		// TODO Auto-generated method stub
		return mCarDaoImpl.isBack(sc,uid);
	}

	@Override
	public User getUser(String strName) {
		
		return mCarDaoImpl.getUser(strName);
		
	}


	

}
