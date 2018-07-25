package com.erhi.daoimpl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.erhi.bean.Admin;
import com.erhi.bean.Car;
import com.erhi.bean.Record;
import com.erhi.bean.User;
import com.erhi.bean.UserCar;
import com.erhi.dao.ICarDao;
import com.erhi.tools.DBHelper;
import com.erhi.tools.JfTool;
import com.erhi.tools.ResultSetHandler;
import com.erhi.tools.Tem;
import com.erhi.tools.Template;
import com.erhi.view.Login;

public class CarDaoImpl implements ICarDao {
	private String head[] = null;
	private Object[][] data = null;
	private JButton bBack;
	private Template tp = null;

	public CarDaoImpl() {
		bBack = new JButton("返回");
		tp = new Template();
	}

	/*
	 * 增加用户
	 * 
	 * @see com.erhi.dao.ICarDao#addUser(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean addUser(String name, String passward) {
		Template t = new Template();
		return t.updateSQL("insert into user_car(user_id,user_name,user_pwd)values(u_seq.nextval,?,?)", name, passward);
	}

	/*
	 * 用户登录
	 * 
	 * @see com.erhi.dao.ICarDao#logUser(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean logUser(String strName, String strPwd) {
		ArrayList<User> list = new ArrayList<>();
		Connection conn = DBHelper.getInstance().getConnection();
		String sql = "select user_name ,user_pwd from user_car ";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rset = ps.executeQuery();
			while (rset.next()) {
				User user = new User();
				user.setUserName(rset.getString("user_name"));
				user.setUserPwd(rset.getString("user_pwd"));
				list.add(user);
			}
		} catch (SQLException e) {

		}
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getUserName().equals(strName) && list.get(i).getUserPwd().equals(strPwd)) {
				return true;
			}
		}
		return false;
	}

	/*
	 * 管理员登录
	 * 
	 * @see com.erhi.dao.ICarDao#logAdmin(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean logAdmin(String strName, String strPwd) {
		ArrayList<Admin> list = new ArrayList<>();
		Connection conn = DBHelper.getInstance().getConnection();
		String sql = "select admin_name ,admin_pwd from admin_car ";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rset = ps.executeQuery();
			while (rset.next()) {
				Admin admin = new Admin();
				admin.setAdminName(rset.getString("admin_name"));
				admin.setAdminPwd(rset.getString("admin_pwd"));
				list.add(admin);
			}
		} catch (SQLException e) {
		}
		for (Admin admin : list) {
			if (admin.getAdminName().equals(strName) && admin.getAdminPwd().equals(strPwd)) {
				return true;
			}

		}
		return false;
	}

	/*
	 * 判断重名
	 * 
	 * @see com.erhi.dao.ICarDao#isSame(java.lang.String)
	 */
	@Override
	public boolean isSame(String strName) {
		ArrayList<Admin> list1 = new ArrayList<>();
		ArrayList<User> list2 = new ArrayList<>();
		Connection conn = DBHelper.getInstance().getConnection();
		String sql1 = "select admin_name from admin_car";
		String sql2 = "select user_name from user_car";
		try {
			PreparedStatement ps1 = conn.prepareStatement(sql1);
			PreparedStatement ps2 = conn.prepareStatement(sql2);
			ResultSet rset1 = ps1.executeQuery();
			ResultSet rset2 = ps2.executeQuery();
			while (rset1.next()) {
				Admin admin = new Admin();
				admin.setAdminName(rset1.getString("admin_name"));
				list1.add(admin);
			}
			while (rset2.next()) {
				User user = new User();
				user.setUserName(rset2.getString("user_name"));
				list2.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Admin admin : list1) {
			if (admin.getAdminName().equals(strName)) {
				return true;
			}
		}
		for (User user : list2) {
			if (user.getUserName().equals(strName)) {
				return true;
			}
		}
		return false;
	}

	/*
	 * 用户升序
	 * 
	 * @see com.erhi.dao.ICarDao#searchAsce()
	 */
	@Override
	public ArrayList<Car> searchAsce() {
		Tem<ArrayList<Car>> t = new Tem<>();
		ArrayList<Car> list = t.testQuery(
				"select e.car_id ,e.car_name,e.car_emission,b.brand_name,t.type_name,e.car_price,e.car_status from brand b ,type_t t,carinfor e where e.brand_id = b.brand_id and e.type_id = t.type_id and car_useable = '上架' order by e.car_price asc",
				new ResultSetHandler<ArrayList<Car>>() {
					@Override
					public ArrayList<Car> handler(ResultSet rSet) {
						ArrayList<Car> arr = new ArrayList<>();
						try {
							while (rSet.next()) {
								Car car = new Car();
								car.setCarId(rSet.getInt("car_id"));
								car.setCarName(rSet.getString("car_name"));
								car.setEmission(rSet.getString("car_emission"));
								car.setBrandName(rSet.getString("brand_name"));
								car.setTypeName(rSet.getString("type_name"));
								car.setPrice(rSet.getDouble("car_price"));
								car.setStatus(rSet.getString("car_status"));

								arr.add(car);
							}

						} catch (SQLException e) {
							e.printStackTrace();
						}
						return arr;
					}
				});
		return list;
	}

	/*
	 * 用户降序
	 * 
	 * @see com.erhi.dao.ICarDao#searchDesc()
	 */
	@Override
	public ArrayList<Car> searchDesc() {
		Tem<ArrayList<Car>> t = new Tem<>();
		ArrayList<Car> list = t.testQuery(
				"select e.car_id ,e.car_name,e.car_emission,b.brand_name,t.type_name,e.car_price,e.car_status  from brand b ,type_t t,carinfor e where e.brand_id = b.brand_id and e.type_id = t.type_id and car_useable = '上架' order by e.car_price desc",
				new ResultSetHandler<ArrayList<Car>>() {
					@Override
					public ArrayList<Car> handler(ResultSet rSet) {
						ArrayList<Car> arr = new ArrayList<>();
						try {
							while (rSet.next()) {
								Car car = new Car();
								car.setCarId(rSet.getInt("car_id"));
								car.setCarName(rSet.getString("car_name"));
								car.setEmission(rSet.getString("car_emission"));
								car.setBrandName(rSet.getString("brand_name"));
								car.setTypeName(rSet.getString("type_name"));
								car.setPrice(rSet.getDouble("car_price"));
								car.setStatus(rSet.getString("car_status"));

								arr.add(car);
							}

						} catch (SQLException e) {
							e.printStackTrace();
						}
						return arr;
					}
				});
		return list;
	}

	/*
	 * 用户类型搜索
	 * 
	 * @see com.erhi.dao.ICarDao#searchType(int)
	 */
	@Override
	public void searchType(int typeId) {
		JPanel p = new JPanel();
		JFrame jf = new JFrame("二嗨租车-类型查询");
		JButton bBack = new JButton();
		bBack.setBounds(400, 200, 100, 50);
		p.add(bBack);
		jf.add(p);
		jf.setSize(550, 300);
		jf.setLocation(200, 100);
		
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		// 设置滚动条
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 100, 700, 250);

		JTable table = new JTable();
		table.setRowHeight(30);
		scrollPane.setViewportView(table);

		head = new String[] {"汽车编号", "汽车名称", "排量", "品牌", "类型", "单价", "是否可租"};

		DefaultTableModel tableModel = new DefaultTableModel(queryTypeData(typeId), head) {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				// 表格不可编辑
				return false;
			}
		};
		table.setModel(tableModel);

		GroupLayout gl_contentPane = new GroupLayout(p);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(scrollPane,
				GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
						.addGap(66)));
		p.setLayout(gl_contentPane);

		bBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JfTool.jf4.setVisible(true);
				jf.dispose();
				
			}
		});

		
	}
	/*
	 * 类型搜索结果转换成二维数组
	 */
	public Object[][] queryTypeData(int typeId) {

		String sql = 
				"select e.car_id ,e.car_name,e.car_emission,b.brand_name,t.type_name,e.car_price,e.car_status,t.type_id from brand b ,type_t t,carinfor e where e.brand_id = b.brand_id and e.type_id = t.type_id and car_useable = '上架' order by e.car_id";
		List<Car> list = new ArrayList<Car>();
		Object[][] data = null;
		try {
			Connection conn = DBHelper.getInstance().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rSet = ps.executeQuery();
			while (rSet.next()) {
				Car car = new Car();
				car.setCarId(rSet.getInt("car_id"));
				car.setCarName(rSet.getString("car_name"));
				car.setEmission(rSet.getString("car_emission"));
				car.setBrandName(rSet.getString("brand_name"));
				car.setTypeName(rSet.getString("type_name"));
				car.setPrice(rSet.getDouble("car_price"));
				car.setStatus(rSet.getString("car_status"));
				
				car.setTypeId(rSet.getInt("type_id"));
				list.add(car);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		data = new Object[list.size()][head.length];
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getTypeId()==typeId) {
				 for(int j=0;j<head.length;j++){
		                data[i][0]=list.get(i).getCarId();
		                data[i][1]=list.get(i).getCarName();
		                data[i][2]=list.get(i).getEmission();
		                data[i][3]=list.get(i).getBrandName();
		                data[i][4]=list.get(i).getTypeName();
		                data[i][5]=list.get(i).getPrice();
		                data[i][6]=list.get(i).getStatus();
		            }
			}
			
		}
		return data;
	}

	

	/*
	 * 用户品牌搜索
	 * 
	 * @see com.erhi.dao.ICarDao#searchBrand(int)
	 */
	@Override
	public void searchBrand(int brandId) {
		JPanel p = new JPanel();
		JFrame jf = new JFrame("二嗨租车-类型查询");
		JButton bBack = new JButton();
		bBack.setBounds(400, 200, 100, 50);
		p.add(bBack);
		jf.add(p);
		jf.setSize(550, 300);
		jf.setLocation(200, 100);
		
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		// 设置滚动条
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 100, 700, 250);

		JTable table = new JTable();
		table.setRowHeight(30);
		scrollPane.setViewportView(table);

		head = new String[] {"汽车编号", "汽车名称", "排量", "品牌", "类型", "单价", "是否可租"};

		DefaultTableModel tableModel = new DefaultTableModel(queryBrandData(brandId), head) {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				// 表格不可编辑
				return false;
			}
		};
		table.setModel(tableModel);

		GroupLayout gl_contentPane = new GroupLayout(p);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(scrollPane,
				GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
						.addGap(66)));
		p.setLayout(gl_contentPane);

		bBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JfTool.jf5.setVisible(true);
				jf.dispose();
				
			}
		});		
	}
	
	/*
	 * 品牌搜索结果转换成二维数组
	 */
	public Object[][] queryBrandData(int brandId) {

		String sql = 
				"select e.car_id ,e.car_name,e.car_emission,b.brand_name,t.type_name,e.car_price,e.car_status,b.brand_id from brand b ,type_t t,carinfor e where e.brand_id = b.brand_id and e.type_id = t.type_id and car_useable = '上架' order by e.car_id";
		List<Car> list = new ArrayList<Car>();
		Object[][] data = null;
		try {
			Connection conn = DBHelper.getInstance().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rSet = ps.executeQuery();
			while (rSet.next()) {
				Car car = new Car();
				car.setCarId(rSet.getInt("car_id"));
				car.setCarName(rSet.getString("car_name"));
				car.setEmission(rSet.getString("car_emission"));
				car.setBrandName(rSet.getString("brand_name"));
				car.setTypeName(rSet.getString("type_name"));
				car.setPrice(rSet.getDouble("car_price"));
				car.setStatus(rSet.getString("car_status"));
				
				car.setBrandId(rSet.getInt("brand_id"));
				list.add(car);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		data = new Object[list.size()][head.length];
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getBrandId()==brandId) {
				 for(int j=0;j<head.length;j++){
		                data[i][0]=list.get(i).getCarId();
		                data[i][1]=list.get(i).getCarName();
		                data[i][2]=list.get(i).getEmission();
		                data[i][3]=list.get(i).getBrandName();
		                data[i][4]=list.get(i).getTypeName();
		                data[i][5]=list.get(i).getPrice();
		                data[i][6]=list.get(i).getStatus();
		            }
			}
			
		}
		return data;
	}
	


	/*
	 * 用户查询全部
	 * 
	 * @see com.erhi.dao.ICarDao#searchAll()
	 */
	@Override
	public ArrayList<UserCar> searchAll() {

		Tem<ArrayList<UserCar>> t = new Tem<>();
		ArrayList<UserCar> list = t.testQuery(
				"select e.car_id ,e.car_name,e.car_emission,b.brand_name,t.type_name,e.car_price,e.car_status  from brand b ,type_t t,carinfor e where e.type_id = t.type_id and e.brand_id = b.brand_id  and car_status = '可借'and car_useable = '上架' order by e.car_id ",
				new ResultSetHandler<ArrayList<UserCar>>() {
					@Override
					public ArrayList<UserCar> handler(ResultSet rSet) {
						ArrayList<UserCar> arr = new ArrayList<>();
						try {
							while (rSet.next()) {
								UserCar us = new UserCar();
								us.setCarId(rSet.getInt("car_id"));
								us.setCarName(rSet.getString("car_name"));
								us.setEmission(rSet.getString("car_emission"));
								us.setBrandName(rSet.getString("brand_name"));
								us.setTypeName(rSet.getString("type_name"));
								us.setPrice(rSet.getDouble("car_price"));
								us.setStatus(rSet.getString("car_status"));

								arr.add(us);
							}

						} catch (SQLException e) {
							e.printStackTrace();
						}
						return arr;
					}
				});
		return list;
	}

	/*
	 * 管理员查询车辆所有信息
	 * 
	 * @see com.erhi.dao.ICarDao#searchAdmin()
	 */
	@Override
	public ArrayList<Car> searchAdmin() {

		Tem<ArrayList<Car>> t = new Tem<>();

		ArrayList<Car> list = t.testQuery(
				"select e.car_id ,e.car_name,e.car_emission,b.brand_name,t.type_name,e.car_price,e.car_status ,car_useable from brand b ,type_t t,carinfor e where e.brand_id = b.brand_id and e.type_id = t.type_id order by e.car_id",
				new ResultSetHandler<ArrayList<Car>>() {

					@Override
					public ArrayList<Car> handler(ResultSet rSet) {
						ArrayList<Car> arr = new ArrayList<>();
						try {
							while (rSet.next()) {
								Car car = new Car();
								car.setCarId(rSet.getInt("car_id"));
								car.setCarName(rSet.getString("car_name"));
								car.setEmission(rSet.getString("car_emission"));
								car.setBrandName(rSet.getString("brand_name"));
								car.setTypeName(rSet.getString("type_name"));
								car.setPrice(rSet.getDouble("car_price"));
								car.setStatus(rSet.getString("car_status"));
								car.setUseable(rSet.getString("car_useable"));
								arr.add(car);
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return arr;
					}
				});
		return list;

	}

	/*
	 * 增加车辆
	 * 
	 * @see com.erhi.dao.ICarDao#addCar(java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean addCar(int bd, int td, String strCarName, String strCarNum, String strCarEm, String strCarColor,
			String strCarValue, String strCarPrice, String outsput1, String outsput2) {
		Template t = new Template();
		return t.updateSQL(
				"insert into carinfor(car_id,brand_id,type_id,car_name,car_number,car_emission,car_color,car_value,car_price,car_useable,car_status)values(carinf_seq.nextval,?,?,?,?,?,?,?,?,?,?)",
				bd, td, strCarName, strCarNum, strCarEm, strCarColor, strCarValue, strCarPrice, outsput1, outsput2);
	}

	/*
	 * 管理员根据ID查询
	 * 
	 * @see com.erhi.dao.ICarDao#adminSeID()
	 */
	
	@Override
	public void adminSeID(int carID) {
		JPanel p = new JPanel();
		JFrame jf = new JFrame("汽车查询表");
		JButton bBack = new JButton();
		bBack.setBounds(400, 200, 100, 50);
		p.add(bBack);
		jf.add(p);
		jf.setSize(550, 300);
		jf.setLocation(200, 100);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		// 设置滚动条
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 100, 700, 250);

		JTable table = new JTable();
		table.setRowHeight(30);
		scrollPane.setViewportView(table);

		head = new String[] {"汽车编号", "汽车名称", "排量", "品牌", "类型", "单价", "是否可租", "是否上架"};

		DefaultTableModel tableModel = new DefaultTableModel(queryCarData(carID), head) {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				// 表格不可编辑
				return false;
			}
		};
		table.setModel(tableModel);

		GroupLayout gl_contentPane = new GroupLayout(p);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(scrollPane,
				GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
						.addGap(66)));
		p.setLayout(gl_contentPane);

		bBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JfTool.jf7.setVisible(true);
				jf.dispose();
				
			}
		});

	}
	/*
	 * 管理员根据ID查询建立二维数组
	 */
	public Object[][] queryCarData(int carID) {

		String sql = 
				"select e.car_id ,e.car_name,e.car_emission,b.brand_name,t.type_name,e.car_price,e.car_status ,car_useable from brand b ,type_t t,carinfor e where e.brand_id = b.brand_id and e.type_id = t.type_id order by e.car_id";
		List<Car> list = new ArrayList<Car>();
		Object[][] data = null;
		try {
			Connection conn = DBHelper.getInstance().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rSet = ps.executeQuery();
			while (rSet.next()) {
				Car car = new Car();
				car.setCarId(rSet.getInt("car_id"));
				car.setCarName(rSet.getString("car_name"));
				car.setEmission(rSet.getString("car_emission"));
				car.setBrandName(rSet.getString("brand_name"));
				car.setTypeName(rSet.getString("type_name"));
				car.setPrice(rSet.getDouble("car_price"));
				car.setStatus(rSet.getString("car_status"));
				car.setUseable(rSet.getString("car_useable"));
				list.add(car);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		data = new Object[list.size()][head.length];
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getCarId()==carID) {
				 for(int j=0;j<head.length;j++){
		                data[i][0]=list.get(i).getCarId();
		                data[i][1]=list.get(i).getCarName();
		                data[i][2]=list.get(i).getEmission();
		                data[i][3]=list.get(i).getBrandName();
		                data[i][4]=list.get(i).getTypeName();
		                data[i][5]=list.get(i).getPrice();
		                data[i][6]=list.get(i).getStatus();
		                data[i][7]=list.get(i).getUseable();
		            }
			}
			
		}
		return data;
	}

	/*
	 * 管理员修改
	 * 
	 * @see com.erhi.dao.ICarDao#alterAdmin(int, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean alterAdmin(int sid, String strPrice, String outsput1) {
		Template t = new Template();
		return t.updateSQL("update carinfor set car_price = ?,car_useable = ? where car_id = ?", strPrice, outsput1,
				sid);
	}

	/*
	 * 用户检查是否可租
	 * 
	 * @see com.erhi.dao.ICarDao#isRent(int)
	 */
	@Override
	public boolean isRent(int sc) {
		Tem<List<Car>> t = new Tem<>();
		List<Car> list = t.testQuery("select car_id,car_status from carinfor where car_useable = '上架'",
				new ResultSetHandler<List<Car>>() {

					@Override
					public List<Car> handler(ResultSet rSet) {
						List<Car> list = new ArrayList<>();
						try {

							while (rSet.next()) {
								Car c = new Car();
								c.setCarId(rSet.getInt("car_id"));
								c.setStatus(rSet.getString("car_status"));
								list.add(c);
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}
						return list;
					}
				});

		for (Car car : list) {
			if (car.getCarId() == sc) {
				if (car.getStatus().equals("可借")) {
					return true;
				}
			}
		}

		return false;

	}

//	/*
//	 * 根据name得到ID
//	 * 
//	 * @see com.erhi.dao.ICarDao#getUserId(java.lang.String)
//	 */
//	@Override
//	public int getUserId(String strName) {
//		ArrayList<User> list = new ArrayList<>();
//		Connection conn = DBHelper.getInstance().getConnection();
//		String sql = "select user_id ,user_name from user_car ";
//
//		try {
//			PreparedStatement ps = conn.prepareStatement(sql);
//			ResultSet rset = ps.executeQuery();
//			while (rset.next()) {
//				User user = new User();
//				user.setUserId(rset.getInt("user_id"));
//				user.setUserName(rset.getString("user_name"));
//
//				list.add(user);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		int user_id = 0;
//		if (list != null) {
//			for (int i = 0; i < list.size(); i++) {
//				if (list.get(i).getUserName().equals(strName)) {
//					user_id = list.get(i).getUserId();
//					break;
//				}
//			}
//		}
//
//		return user_id;
//	}
//

	/*
	 * 根据ID得到单价
	 * 
	 * @see com.erhi.dao.ICarDao#getCarPrice(int)
	 */
	@Override
	public double getCarPrice(int sc) {
		Tem<List<Car>> t = new Tem<>();
		List<Car> list = t.testQuery("select car_id ,car_price from carinfor ", new ResultSetHandler<List<Car>>() {

			@Override
			public List<Car> handler(ResultSet rSet) {
				List<Car> list = new ArrayList<>();
				try {

					while (rSet.next()) {
						Car car = new Car();
						car.setCarId(rSet.getInt("car_id"));
						car.setPrice(rSet.getDouble("car_price"));
						list.add(car);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return list;
			}
		});
		double carPrice = 0;
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getCarId() == sc) {
					carPrice = list.get(i).getPrice();
					break;
				}
			}
		}
		return carPrice;
	}

	/*
	 * 把租车信息放入数据库
	 * 同时改变状态
	 * 
	 * @see com.erhi.dao.ICarDao#setUserRecord(int, int, double, int)
	 */
	@Override
	public boolean setUserRecord(int sc, int uid, double d, int sr) {
		Connection conn = null;
		try {
			conn = DBHelper.getInstance().getConnection();
			conn.setAutoCommit(false);
			String sql1 = "insert into record_car(record_id,car_id,user_id, money, return_date) values (rent_seq.nextval,?,?,?,sysdate+?)";
			String sql2 = "update carinfor set car_status ='不可借' where car_id = ?";
			String sql3 ="insert into return_record(return_user_id,return_car_id)values (?,?)";
			PreparedStatement ps1 = conn.prepareStatement(sql1);
			PreparedStatement ps2 = conn.prepareStatement(sql2);
			PreparedStatement ps3 = conn.prepareStatement(sql3);
			ps1.setInt(1, sc);
			ps1.setInt(2, uid);
			ps1.setDouble(3, d);
			ps1.setInt(4, sr);
			ps2.setInt(1, sc);
			ps3.setInt(1, uid);
			ps3.setInt(2, sc);

			int a = ps1.executeUpdate();
			int b = ps2.executeUpdate();
			int c = ps3.executeUpdate();
			conn.commit();
			if (a > 0 && b > 0&& c >0) {
				return true;
			}
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		return false;
	}


	/*
	 * 还车
	 * 
	 * @see com.erhi.dao.ICarDao#backCar(int)
	 */
	@Override
	public boolean backCar(int cid) {
		Connection conn = null ;
		try {
			conn = DBHelper.getInstance().getConnection();
			conn.setAutoCommit(false);
			String sql1 = "delete from return_record where return_car_id = ?";
			String sql2 = "update carinfor set car_status ='可借' where car_id = ?";
			PreparedStatement ps1 = conn.prepareStatement(sql1);
			PreparedStatement ps2 = conn.prepareStatement(sql2);
			
			ps1.setInt(1, cid);
			ps2.setInt(1, cid);
			
			
			int a = ps1.executeUpdate();
			int b = ps2.executeUpdate();
			
			conn.commit();
			
			if(a>0&&b>0) {
				return true;
			}
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			
		}
		return false;

	}
	
	/*
	 * 用户查询租车记录
	 * @see com.erhi.dao.ICarDao#userSearchRecord(int)
	 */
	@Override
	public void userSearchRecord(int id) {
		JPanel p = new JPanel();
		JFrame jf = new JFrame("用户查询租车记录");
		JButton bBack = new JButton();
		bBack.setBounds(400, 200, 100, 50);
		p.add(bBack);
		jf.add(p);
		jf.setSize(550, 300);
		jf.setLocation(200, 100);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		// 设置滚动条
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 100, 700, 250);

		JTable table = new JTable();
		table.setRowHeight(30);
		scrollPane.setViewportView(table);

		head = new String[] {"汽车编号", "汽车名称", "总价", "排量","品牌", "类型",  "租车时间", "还车时间"};

		DefaultTableModel tableModel = new DefaultTableModel(queryCarUserData(id), head) {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				// 表格不可编辑
				return false;
			}
		};
		table.setModel(tableModel);

		GroupLayout gl_contentPane = new GroupLayout(p);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(scrollPane,
				GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
						.addGap(66)));
		p.setLayout(gl_contentPane);

		bBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JfTool.jf8.setVisible(true);
				jf.dispose();
				
			}
		});

		
	}
	/*
	 * 用户查询租车记录建立二维数组
	 */
	public Object[][] queryCarUserData(int userID) {

		String sql = 
				"select u.user_id,i.car_id,car_name,money,car_emission,brand_name,type_name,start_date,return_date from record_car r,carinfor i,brand b,type_t t,user_car u where  r.car_id = i.car_id and r.user_id = u.user_id and i.brand_id = b.brand_id and i.type_id = t.type_id";
		List<Car> list = new ArrayList<Car>();
		List<User> list2 = new ArrayList<User>();
		List<Record> list3 = new ArrayList<Record>();
		Object[][] data = null;
		try {
			Connection conn = DBHelper.getInstance().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rSet = ps.executeQuery();
			while (rSet.next()) {
				User user = new User();
				Car car = new Car();
				Record r = new Record();
				user.setUserId(rSet.getInt("user_id"));
				car.setCarId(rSet.getInt("car_id"));
				car.setCarName(rSet.getString("car_name"));
				r.setRentTotalPrice(rSet.getDouble("money"));
				car.setEmission(rSet.getString("car_emission"));
				car.setBrandName(rSet.getString("brand_name"));
				car.setTypeName(rSet.getString("type_name"));
				r.setRentTime(rSet.getString("start_date"));
				r.setReturnTime(rSet.getString("return_date"));
				
				list.add(car);
				list2.add(user);
				list3.add(r);
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		data = new Object[list.size()][head.length];
		for (int i = 0; i < list.size(); i++) {
			if (list2.get(i).getUserId()==userID) {
				 for(int j=0;j<head.length;j++){
					
		            data[i][0]=list.get(i).getCarId();
	                data[i][1]=list.get(i).getCarName();
	                data[i][2]=list3.get(i).getRentTotalPrice();
	                data[i][3]=list.get(i).getEmission();
	                data[i][4]=list.get(i).getBrandName();
	                data[i][5]=list.get(i).getTypeName();
	                data[i][6]=list3.get(i).getRentTime();
	                data[i][7]=list3.get(i).getReturnTime();
		            }
			}
			
		}
		return data;
	}
	
	
/*管理员查看全部租赁记录
 * (non-Javadoc)
 * @see com.erhi.dao.ICarDao#searchAdminRecords()
 */
	@Override
	public void searchAdminRecords() {
		JPanel p = new JPanel();
		JFrame jf = new JFrame("用户查询租车记录");
		JButton bBack = new JButton();
		bBack.setBounds(400, 200, 100, 50);
		p.add(bBack);
		jf.add(p);
		jf.setSize(550, 300);
		jf.setLocation(200, 100);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		// 设置滚动条
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 100, 700, 250);

		JTable table = new JTable();
		table.setRowHeight(30);
		scrollPane.setViewportView(table);

		head = new String[] {"出租编号","汽车编号", "汽车名称", "用户编号","用户名称","单价","总价", "排量","品牌","类型", "租车时间","还车时间"};

		DefaultTableModel tableModel = new DefaultTableModel(queryCarAdminData(), head) {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				// 表格不可编辑
				return false;
			}
		};
		table.setModel(tableModel);

		GroupLayout gl_contentPane = new GroupLayout(p);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(scrollPane,
				GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
						.addGap(66)));
		p.setLayout(gl_contentPane);

		bBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JfTool.jf2.setVisible(true);
				jf.dispose();
				
			}
		});

		
	}
	/*
	 *管理员查询租车记录建立二维数组
	 */
	public Object[][] queryCarAdminData() {

		String sql = 
				"select record_id,i.car_id,car_name,u.user_id,user_name,car_price,money,car_emission,brand_name,type_name,start_date,return_date from record_car r,carinfor i,brand b,type_t t,user_car u where  r.car_id = i.car_id and r.user_id = u.user_id and i.brand_id = b.brand_id and i.type_id = t.type_id";
		List<Car> list = new ArrayList<Car>();
		List<User> list2 = new ArrayList<User>();
		List<Record> list3 = new ArrayList<Record>();
		Object[][] data = null;
		try {
			Connection conn = DBHelper.getInstance().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rSet = ps.executeQuery();
			while (rSet.next()) {
				User user = new User();
				Car car = new Car();
				Record r = new Record();
				r.setRentId(rSet.getInt("record_id"));
				car.setCarId(rSet.getInt("car_id"));
				car.setCarName(rSet.getString("car_name"));
				user.setUserId(rSet.getInt("user_id"));
				user.setUserName(rSet.getString("user_name"));
				car.setPrice(rSet.getDouble("car_price"));
				r.setRentTotalPrice(rSet.getDouble("money"));
				car.setEmission(rSet.getString("car_emission"));
				car.setBrandName(rSet.getString("brand_name"));
				car.setTypeName(rSet.getString("type_name"));
				r.setRentTime(rSet.getString("start_date"));
				r.setReturnTime(rSet.getString("return_date"));
				
				list.add(car);
				list2.add(user);
				list3.add(r);
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		data = new Object[list.size()][head.length];
		for (int i = 0; i < list.size(); i++) {
				 for(int j=0;j<head.length;j++){
					data[i][0]=list3.get(i).getRentId();
		            data[i][1]=list.get(i).getCarId();
	                data[i][2]=list.get(i).getCarName();
	                data[i][3]=list2.get(i).getUserId();
	                data[i][4]=list2.get(i).getUserName();
	                data[i][5]=list.get(i).getPrice();
	                data[i][6]=list3.get(i).getRentTotalPrice();
	                data[i][7]=list.get(i).getEmission();
	                data[i][8]=list.get(i).getBrandName();
	                data[i][9]=list.get(i).getTypeName();
	                data[i][10]=list3.get(i).getRentTime();
	                data[i][11]=list3.get(i).getReturnTime();
		            }	
		}
		return data;
	}

	
	/*
	 * 用户输出租车结果(non-Javadoc)
	 * @see com.erhi.dao.ICarDao#showrentresult()
	 */
	@Override
	public void showrentresult(int id) {
		JPanel p = new JPanel();
		JFrame jf = new JFrame("用户查询租车记录");
		JButton bBack = new JButton();
		bBack.setBounds(400, 200, 100, 50);
		p.add(bBack);
		jf.add(p);
		jf.setSize(550, 300);
		jf.setLocation(200, 100);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		// 设置滚动条
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 100, 700, 250);

		JTable table = new JTable();
		table.setRowHeight(30);
		scrollPane.setViewportView(table);

		head = new String[] {"出租编号","汽车编号", "汽车名称", "单价","总价", "排量","品牌","类型", "租车时间","还车时间"};

		DefaultTableModel tableModel = new DefaultTableModel(queryUserRentData(id), head) {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				// 表格不可编辑
				return false;
			}
		};
		table.setModel(tableModel);

		GroupLayout gl_contentPane = new GroupLayout(p);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(scrollPane,
				GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
						.addGap(66)));
		p.setLayout(gl_contentPane);

		bBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JfTool.jf8.setVisible(true);
				jf.dispose();
				
			}
		});	
	}
	
	
	/*
	 *用户当前租车记录建立二维数组
	 */
	public Object[][] queryUserRentData(int id) {

		String sql = 
				"select u.user_id,record_id,i.car_id,car_name,car_price,money,car_emission,brand_name,type_name,start_date,return_date from record_car r,carinfor i,brand b,type_t t,user_car u where  r.car_id = i.car_id and r.user_id = u.user_id and i.brand_id = b.brand_id and i.type_id = t.type_id and record_id =(select max(record_id) from record_car)";
		List<Car> list = new ArrayList<Car>();
		List<User> list2 = new ArrayList<User>();
		List<Record> list3 = new ArrayList<Record>();
		Object[][] data = null;
		try {
			Connection conn = DBHelper.getInstance().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rSet = ps.executeQuery();
			while (rSet.next()) {
				User user = new User();
				Car car = new Car();
				Record r = new Record();
				user.setUserId(rSet.getInt("user_id"));
				
				r.setRentId(rSet.getInt("record_id"));
				car.setCarId(rSet.getInt("car_id"));
				car.setCarName(rSet.getString("car_name"));
				car.setPrice(rSet.getDouble("car_price"));
				r.setRentTotalPrice(rSet.getDouble("money"));
				car.setEmission(rSet.getString("car_emission"));
				car.setBrandName(rSet.getString("brand_name"));
				car.setTypeName(rSet.getString("type_name"));
				r.setRentTime(rSet.getString("start_date"));
				r.setReturnTime(rSet.getString("return_date"));
				
				list.add(car);
				list2.add(user);
				list3.add(r);
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		data = new Object[list.size()][head.length];
		for (int i = 0; i < list.size(); i++) {
			if (list2.get(i).getUserId()==id) {
				 for(int j=0;j<head.length;j++){
						data[i][0]=list3.get(i).getRentId();
			            data[i][1]=list.get(i).getCarId();
		                data[i][2]=list.get(i).getCarName();
		                data[i][3]=list.get(i).getPrice();
		                data[i][4]=list3.get(i).getRentTotalPrice();
		                data[i][5]=list.get(i).getEmission();
		                data[i][6]=list.get(i).getBrandName();
		                data[i][7]=list.get(i).getTypeName();
		                data[i][8]=list3.get(i).getRentTime();
		                data[i][9]=list3.get(i).getReturnTime();
			            }	
			}	
		}
		return data;
	}

	/*
	 * 还车前判断(non-Javadoc)
	 * @see com.erhi.dao.ICarDao#isBack(int, int)
	 */
	@Override
	public boolean isBack(int sc, int uid) {
		Tem<List<Car>> t = new Tem<>();
		List<Car> list = t.testQuery("select return_user_id,return_car_id from return_record where return_user_id = ? and return_car_id = ? ",
				new ResultSetHandler<List<Car>>() {

					@Override
					public List<Car> handler(ResultSet rSet) {
						List<Car> list = new ArrayList<>();
						try {

							while (rSet.next()) {
								Car a = new Car();
								a.setCarId(rSet.getInt("return_car_id"));
								a.setUserId(rSet.getInt("return_user_id"));
								list.add(a);
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}
						return list;
					}
				},uid,sc);
	if(list.size()!=0) {
		return true;
	}
		
		return false;

	}
	
	/*
	 * 传递账户(non-Javadoc)
	 * @see com.erhi.dao.ICarDao#getUser(com.erhi.bean.User)
	 */
	@Override
	public User getUser(String strName) {
		User user = new User();
		Tem<User> temp= new Tem<User>();
		String sql = "select * from user_car where user_name=?";
		temp.testQuery(sql, new ResultSetHandler<User>() {
			
			@Override
			public User handler(ResultSet rSet) {
				try {
					rSet.next();
					user.setUserId(rSet.getInt("user_id"));
					user.setUserName(rSet.getString("user_name"));
					user.setUserPwd(rSet.getString("user_password"));

				} catch (SQLException e) {
					new Login();
				}
				

				return user;
			}
		}, strName);

		return user;

	}


}
