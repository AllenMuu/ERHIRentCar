package com.erhi.view;

import java.awt.Dimension;
import java.awt.GridLayout;
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
import com.erhi.biz.ICarBiz;
import com.erhi.bizimpl.CarBizImpl;
import com.erhi.tools.JfTool;

public class UserBrandView {
	private ButtonGroup g;
	private JButton comfirm,back;
	private ICarBiz mICarBizimpl;
	private JRadioButton s1,s2,s3,s4,s5;
	private JLabel choose;
	public UserBrandView(User user) {
		mICarBizimpl = new CarBizImpl();
//		jf = new JFrame("二嗨租车--类型查询");
		choose= new JLabel("请选择类型");
		comfirm = new JButton("确认");
		back = new JButton("返回");
		s1 = new JRadioButton("标志");
		s2 = new JRadioButton("大众");
		s3 = new JRadioButton("奥迪");
		s4 = new JRadioButton("奔驰");
		s5 = new JRadioButton("宝马");
		
		Toolkit kit=Toolkit.getDefaultToolkit();
	    Dimension screenSize=kit.getScreenSize();
		int x=screenSize.height;
	    int y=screenSize.width;
	    int xx=(x-600)/2;
	    int yy=(y-500)/2;
	    JfTool.jf5.setTitle("二嗨租车--类型查询");
		JfTool.jf5.setSize(600,500);
		JfTool.jf5.setVisible(true);
		JfTool.jf5.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JfTool.jf5.setLocation(yy,xx);
		JPanel p = new JPanel(new GridLayout(5,2));
		JfTool.jf5.setContentPane(p);
		JfTool.jf5.setResizable(false);
		
		g = new ButtonGroup();
		
		g.add(s1);
		g.add(s2);
		g.add(s3);
		g.add(s4);
		g.add(s5);
		
		p.add(choose);
		
		p.add(s1);
		p.add(s2);
		p.add(s3);
		p.add(s4);
		p.add(s5);
		
		p.add(comfirm);
		p.add(back);
		
		comfirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int outsput = 0;
				if (s1.isSelected()) {
					outsput=1;
				}else if (s2.isSelected()) {
					outsput=2;
				}else if (s3.isSelected()) {
					outsput=3;
				}else if (s4.isSelected()) {
					outsput=4;
				}else if (s5.isSelected()) {
					outsput=5;
				}
				mICarBizimpl.searchBrand(outsput);
				JfTool.jf5.dispose();
			}
		});
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new UserSearchView(user);
				JfTool.jf5.dispose();
			}
		});
	}
}
