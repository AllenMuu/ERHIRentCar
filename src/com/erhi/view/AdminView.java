package com.erhi.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.erhi.biz.ICarBiz;
import com.erhi.bizimpl.CarBizImpl;
import com.erhi.tools.JfTool;

public class AdminView {

	private JPanel p;
	//声明5个按钮
	private JButton checkCarJb, addCarJb,alterCarJb,checkRentJb,searchIdJb,backJb;
//	private JFrame jf;
	private  ICarBiz mICarBizimpl;
	public AdminView() {
		mICarBizimpl = new CarBizImpl();
//		jf = new JFrame("二嗨租车管理员界面");
		p = new JPanel(null);
		checkCarJb = new JButton("查询所有汽车");
		searchIdJb = new JButton("ID查询汽车");
		addCarJb = new JButton("增加汽车信息");
		checkRentJb = new JButton("查看租赁记录");
		alterCarJb = new JButton("修改汽车信息");
		backJb = new JButton("返回");
		
		checkCarJb.setBounds(70, 60, 100, 50);
		searchIdJb.setBounds(220, 150, 100, 50);
		addCarJb.setBounds(370, 60, 100, 50);
		checkRentJb.setBounds(70, 150, 100, 50);
		alterCarJb.setBounds(220, 60, 100, 50);
		backJb.setBounds(370, 150, 100, 50);
		
		p.add(checkCarJb);
		p.add(searchIdJb);
		p.add(addCarJb);
		p.add(checkRentJb);
		p.add(alterCarJb);
		p.add(backJb);
		
		JfTool.jf2.add(p);
		JfTool.jf2.setSize(550, 300);
		JfTool.jf2.setLocation(500, 300);
		JfTool.jf2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JfTool.jf2.setVisible(true);
		JfTool.jf2.setTitle("二嗨租车管理员界面");
		/*
		 * 查询所有汽车监听
		 */
		checkCarJb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new AdminSeAllView();
				JfTool.jf2.dispose();
			}
		});
		
		/*
		 * Id 查询信息监听
		 */
		searchIdJb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new AdminSearchID();
				JfTool.jf2.dispose();
			}
		});
		
		/*
		 * 添加汽车信息
		 */
		addCarJb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new AdminAddCarView();
				JfTool.jf2.dispose();
			}
		});
		
		/*
		 * 查看租赁记录
		 */
		checkRentJb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mICarBizimpl.searchAdminRecords();

				JfTool.jf2.dispose();
			}
		});
		
		/*
		 * 修改汽车信息
		 */
		alterCarJb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new AdminAlterCarView();
				JfTool.jf2.dispose();
			}
		});
		
		/*
		 * 返回
		 */
		backJb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Login();
				JfTool.jf2.dispose();
			}
		});
	}	
}
