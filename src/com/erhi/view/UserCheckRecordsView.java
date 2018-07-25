package com.erhi.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.erhi.bean.User;
import com.erhi.biz.ICarBiz;
import com.erhi.bizimpl.CarBizImpl;
import com.erhi.tools.JfTool;
/*
 * 无用
 */
public class UserCheckRecordsView {
	private JButton comfirm, back;
	private JTextField userNametxt;
	private JLabel userName,userPwd;
	private JPasswordField userPwdtxt;
	private static ICarBiz mICarBizimpl;
	public UserCheckRecordsView(User user) {
		mICarBizimpl = new CarBizImpl();
		user.getUserId();
//		jf = new JFrame("二嗨租车--查询租车记录");
//		userName= new JLabel("请输入用户名");
//		userNametxt = new JTextField(20);
//		userPwd = new JLabel("请输入密码");
//		userPwdtxt = new JPasswordField(20);
//		userPwdtxt.setEchoChar('*');
//		comfirm = new JButton("确认");
//		back = new JButton("返回");
//		
//	    Toolkit kit=Toolkit.getDefaultToolkit();
//	    Dimension screenSize=kit.getScreenSize();
//		int x=screenSize.height;
//	    int y=screenSize.width;
//	    int xx=(x-600)/2;
//	    int yy=(y-500)/2;
//	    JfTool.jf3.setTitle("二嗨租车--查询租车记录");
//		JfTool.jf3.setSize(600,500);
//		JfTool.jf3.setVisible(true);
//		JfTool.jf3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		JfTool.jf3.setLocation(yy,xx);
//		JPanel p = new JPanel(new GridLayout(3,2));
//		JfTool.jf3.setContentPane(p);
//		JfTool.jf3.setResizable(false);
//		
//		
//		p.add(userName);
//		p.add(userNametxt);
//		p.add(userPwd);
//		p.add(userPwdtxt);
//		
//		
//		p.add(comfirm);
//		p.add(back);
//		
//		comfirm.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				String strName = userNametxt.getText();
//				if (strName == null || strName.equals("")) {
//					JOptionPane.showMessageDialog(null, "用户名不能为空！");
//					return;
//				}
//				String strPwd = new String(userPwdtxt.getPassword());
//				if (strPwd == null || strPwd.equals("")) {
//					JOptionPane.showMessageDialog(null, "密码不能为空！");
//					return;
//				}
//				if (mICarBizimpl.logUser(strName, strPwd)) {
//					int id = mICarBizimpl.getUserId(strName);
					
					JfTool.jf3.dispose();
//				}else {
//					JOptionPane.showMessageDialog(null, "用户核对失败!");
//				}
//				
//				JfTool.jf3.dispose();
//			}
//		});
//		back.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				new UserView(user);
//				JfTool.jf3.dispose();
//				
//			}
//		});
//		
		
	}
}
