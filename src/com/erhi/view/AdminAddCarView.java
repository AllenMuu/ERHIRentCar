package com.erhi.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.erhi.biz.ICarBiz;
import com.erhi.bizimpl.CarBizImpl;

public class AdminAddCarView {
	private ButtonGroup g1,g2;
	private Label blank1,blank2,blank3,blank4;
	private JButton comfirm,back;
	private ICarBiz mICarBizimpl;
	private JFrame jf;
	private JRadioButton s1,s2,s3,s4;
	private JTextField brandIdTxt,typeIdTxt,carNameTxt,carNumTxt,carEmTxt,carColorTxt,carValueTxt,carPriceTxt;
	private JLabel brandId,brand,typeId,type,carName,carNum,carEm,carColor,carValue,carPrice,status,userable;
	public AdminAddCarView() {
		mICarBizimpl = new CarBizImpl();
		jf = new JFrame("二嗨租车--添加车辆");
		brandId= new JLabel("请输入品牌编号");
		brand = new JLabel("1.标志  2.大众  3.奥迪  4.奔驰  5.宝马");
		typeId = new JLabel("请输入类型编号");
		type = new JLabel("1.紧凑型   2.舒适型  3.SUV 4.精英型");
		carName = new JLabel("请输入汽车型号");
		carNum = new JLabel("请输入汽车牌照");
		carEm = new JLabel("请输入汽车排量");
		carColor = new JLabel("请输入汽车颜色");
		carValue = new JLabel("请输入汽车价格");
		carPrice = new JLabel("请输入每日租金");
		status = new JLabel("是否上架");
		userable = new JLabel("是否可借");
		brandIdTxt = new JTextField(20);
		typeIdTxt = new JTextField(20);
		carNameTxt = new JTextField(20);
		carNumTxt = new JTextField(20);
		carEmTxt = new JTextField(20);
		carColorTxt = new JTextField(20);
		carValueTxt = new JTextField(20);
		carPriceTxt = new JTextField(20);
		blank1 = new Label();
		blank2 = new Label();
		blank3 = new Label();
		blank4 = new Label();
		comfirm = new JButton("确认");
		back = new JButton("返回");
		s1 = new JRadioButton("下架");
		s2 = new JRadioButton("上架",true);
		s3 = new JRadioButton("不可借");
		s4 = new JRadioButton("可借",true);
	    Toolkit kit=Toolkit.getDefaultToolkit();
	    Dimension screenSize=kit.getScreenSize();
		int x=screenSize.height;
	    int y=screenSize.width;
	    int xx=(x-600)/2;
	    int yy=(y-500)/2;
		jf.setSize(600,500);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLocation(yy,xx);
		JPanel p = new JPanel(new GridLayout(15,2));
		jf.setContentPane(p);
		jf.setResizable(false);
		
		
		g1 = new ButtonGroup();
		g2 = new ButtonGroup();
		g1.add(s1);
		g1.add(s2);
		g2.add(s3);
		g2.add(s4);
		//1.2
		p.add(brandId);
		p.add(brandIdTxt);
		p.add(brand);
		p.add(blank1);
		//3.4
		p.add(typeId);
		p.add(typeIdTxt);
		p.add(type);
		p.add(blank2);
		//5
		p.add(carName);
		p.add(carNameTxt);
		//6
		p.add(carNum);
		p.add(carNumTxt);
		//7
		p.add(carEm);
		p.add(carEmTxt);
		//8
		p.add(carColor);
		p.add(carColorTxt);
		//9
		p.add(carValue);
		p.add(carValueTxt);
		//10
		p.add(carPrice);
		p.add(carPriceTxt);
		//11.12
		p.add(status);
		p.add(blank3);
		p.add(s1);
		p.add(s2);
		//13.14
		p.add(userable);
		p.add(blank4);
		p.add(s3);
		p.add(s4);
		
		p.add(comfirm);
		p.add(back);
		
		comfirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String strBrandID = brandIdTxt.getText();
				if (strBrandID == null || strBrandID.equals("")) {
					JOptionPane.showMessageDialog(null, "品牌编号不能为空！");
					return;
				}
				if (!strBrandID.equals("1") &&!strBrandID.equals("2") &&!strBrandID.equals("3") &&!strBrandID.equals("4") &&!strBrandID.equals("5") ) {
					JOptionPane.showMessageDialog(null, "品牌编号输入错误！");
					return;
				}
				
				String strtypeId = typeIdTxt.getText();
				if (strtypeId == null || strtypeId.equals("")) {
					JOptionPane.showMessageDialog(null, "类型编号不能为空！");
					return;
				}
				if (!strtypeId.equals("1") &&!strtypeId.equals("2") &&!strtypeId.equals("3") &&!strtypeId.equals("4")  ) {
					JOptionPane.showMessageDialog(null, "类型编号输入错误！");
					return;
				}
				
				String strCarName = carNameTxt.getText();
				if (strCarName == null || strCarName.equals("")) {
					JOptionPane.showMessageDialog(null, "型号不能为空！");
					return;
				}
				
				String strCarNum = carNumTxt.getText();
				if (strCarNum == null || strCarNum.equals("")) {
					JOptionPane.showMessageDialog(null, "牌照不能为空！");
					return;
				}
				
				String strCarEm = carEmTxt.getText();
				if (carEmTxt == null || carEmTxt.equals("")) {
					JOptionPane.showMessageDialog(null, "排量不能为空！");
					return;
				}
				
				String strCarColor = carColorTxt.getText();
				if (carColorTxt == null || carColorTxt.equals("")) {
					JOptionPane.showMessageDialog(null, "颜色不能为空！");
					return;
				}
				
				String strCarValue = carValueTxt.getText();
				if (carValueTxt == null || carValueTxt.equals("")) {
					JOptionPane.showMessageDialog(null, "价值不能为空！");
					return;
				}
				
				String strCarPrice = carPriceTxt.getText();
				if (carPriceTxt == null || carPriceTxt.equals("")) {
					JOptionPane.showMessageDialog(null, "租金不能为空！");
					return;
				}
				String outsput1 = null;
				String outsput2 = null;
				if(s1.isSelected()){
					 outsput1 = "下架";
				}else if(s2.isSelected()){
					 outsput1 = "上架";
				}
				
				if(s3.isSelected()){
					 outsput2 = "不可借";
				}else if(s4.isSelected()){
					 outsput2 = "可借";
				}
//				System.out.println(outsput1);
				int bd = Integer.parseInt(strBrandID);
				int td = Integer.parseInt(strtypeId);
				
				if (mICarBizimpl.addCar(bd,td,strCarName,strCarNum,strCarEm,strCarColor,strCarValue,strCarPrice,outsput1,outsput2)) {
				
					JOptionPane.showMessageDialog(null, "增加成功！");//弹出对话框
					new AdminView();
					jf.dispose();
				}
//				jf.setVisible(true);
			}});
		
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new AdminView();
				jf.dispose();
			}
		});
	}
}
