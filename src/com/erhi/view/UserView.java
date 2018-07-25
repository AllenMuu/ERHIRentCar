package com.erhi.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.erhi.bean.User;
import com.erhi.biz.ICarBiz;
import com.erhi.bizimpl.CarBizImpl;
import com.erhi.tools.JfTool;

public class UserView {
	private JPanel p;
	private JButton searchCarJb, backCarJb,rentCarJb,checkRentJb,backJb;
//	private JFrame jf;
	private static ICarBiz mICarBizimpl;
	public UserView(User user) {
//		jf = new JFrame("二嗨租车");
		mICarBizimpl = new CarBizImpl();
		p = new JPanel(null);
		searchCarJb = new JButton("查询汽车");
		rentCarJb = new JButton("租赁汽车");
		backCarJb = new JButton("归还汽车");
		checkRentJb = new JButton("查看租赁记录");
		backJb = new JButton("返回");
		
		searchCarJb.setBounds(200, 60, 150, 50);
		rentCarJb.setBounds(200, 120, 150, 50);
		backCarJb.setBounds(200, 180, 150, 50);
		checkRentJb.setBounds(200, 240, 150, 50);
		backJb.setBounds(400, 260, 75,30);
		
		p.add(searchCarJb);
		p.add(rentCarJb);
		p.add(backCarJb);
		p.add(checkRentJb);
		p.add(backJb);
		
		JfTool.jf8.add(p);
		JfTool.jf8.setTitle("二嗨租车");
		JfTool.jf8.setSize(550, 400);
		JfTool.jf8.setLocation(500, 300);
		JfTool.jf8.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JfTool.jf8.setVisible(true);
		
		searchCarJb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new UserSearchView(user);
				JfTool.jf8.dispose();
			}
		});
		rentCarJb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new UserRentView(user);
				JfTool.jf8.dispose();
			}
		});
		backCarJb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new UserBackView(user);
				JfTool.jf8.dispose();
			}
		});
		checkRentJb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int id = user.getUserId();
				mICarBizimpl.userSearchRecord(id);
				JfTool.jf8.dispose();
			}
		});
		backJb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Login();
				JfTool.jf8.dispose();
			}
		});
	}
}
