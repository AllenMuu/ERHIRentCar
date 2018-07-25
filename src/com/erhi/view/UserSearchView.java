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
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.erhi.bean.User;

public class UserSearchView {
	private ButtonGroup g;
	private JFrame jf;
	private Label blank1,blank2;
	private JLabel checkStyle;
	private JButton back,comfirm;
	private JRadioButton s1,s2,s3,s4,s5;
	public UserSearchView(User user) {
		
		jf = new JFrame("二嗨租车--用户查询");
		checkStyle = new JLabel("查询方式");
		s1 = new JRadioButton("全部查询");
		s2 = new JRadioButton("价格降序");
		s3 = new JRadioButton("价格升序");
		s4 = new JRadioButton("类别查询");
		s5 = new JRadioButton("品牌查询");
		back = new JButton("返回");
		comfirm = new JButton("确认");
		Toolkit kit=Toolkit.getDefaultToolkit();
	    Dimension screenSize=kit.getScreenSize();
		int x=screenSize.height;
	    int y=screenSize.width;
	    int xx=(x-600)/2;
	    int yy=(y-500)/2;
		jf.setSize(600,500);
		jf.setVisible(true);
		jf.setLocation(yy,xx);
		JPanel p = new JPanel(new GridLayout(5,3));
		jf.setContentPane(p);
		jf.setResizable(false);
		
		g = new ButtonGroup();
		blank1 = new Label();
		blank2 = new Label();
		
		g.add(s1);
		g.add(s2);
		g.add(s3);
		g.add(s4);
		g.add(s5);
//		g.add(s6);
		
		p.add(checkStyle);
		p.add(blank1);
		
		p.add(s1);
		p.add(s2);
		
		p.add(s3);
		p.add(s4);
		
		p.add(s5);
//		p.add(s6);
		p.add(blank2);
		
		p.add(comfirm);
		p.add(back);
		
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new UserView(user);
				jf.dispose();
			}
		});
		comfirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (s1.isSelected()) {
					new USearchAllView(user);
					jf.dispose();
				}else if(s2.isSelected()) {
					new UserDescView(user);
					jf.dispose();
				}else if(s3.isSelected()) {
					new UserAsceView(user);
					jf.dispose();
				}else if(s4.isSelected()){
					new UserTypeView(user);
					jf.dispose();
				}else if (s5.isSelected()) {
					new UserBrandView(user);
					jf.dispose();
				}
				
			}
		});
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new UserView(user);
				jf.dispose();
				
			}
		});
		
		
		
		
	}
	
}
