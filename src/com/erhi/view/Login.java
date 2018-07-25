package com.erhi.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.erhi.bean.User;
import com.erhi.biz.ICarBiz;
import com.erhi.bizimpl.CarBizImpl;
import com.erhi.tools.JfTool;

public class Login {

	private JPanel p;
	private JLabel name, password;
	// 声明文本框
	private JTextField txtName;
	// 声明一个密码框
	private JPasswordField txtPwd;
	// 声明两个按钮
	private JButton login, register;
	private static ICarBiz mICarBizimpl;
	public Login() {
		mICarBizimpl = new CarBizImpl();
		p = new JPanel(null);
		name = new JLabel("用户名");
		password = new JLabel("密码");
		login = new JButton("登录");
		register = new JButton("注册");
		txtName = new JTextField(20);
		txtPwd = new JPasswordField(20);
		// 设置密码框显示的字符为*
		txtPwd.setEchoChar('*');
		// 设置该组件的对齐方式为向右对齐
		name.setHorizontalAlignment(SwingConstants.RIGHT);
		password.setHorizontalAlignment(SwingConstants.RIGHT);
		p.add(name);
		p.add(txtName);
		p.add(password);
		p.add(txtPwd);
		p.add(login);
		p.add(register);

		// 登录增加事件监听
		login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String strName = txtName.getText();
				if (strName == null || strName.equals("")) {
					JOptionPane.showMessageDialog(null, "用户名不能为空！");
					return;
				}
				String strPwd = new String(txtPwd.getPassword());
				if (strPwd == null || strPwd.equals("")) {
					JOptionPane.showMessageDialog(null, "密码不能为空！");
					return;
				}
				
				if (mICarBizimpl.logAdmin(strName, strPwd)) {
					JOptionPane.showMessageDialog(null, "管理员登录成功");
					new AdminView();
					JfTool.jf.dispose();
				} else if (mICarBizimpl.logUser(strName, strPwd)) {
					User user = mICarBizimpl.getUser(strName);
					JOptionPane.showMessageDialog(null, "欢迎来到二嗨租车！");
					new UserView(user);
					JfTool.jf.dispose();
				}else {
					JOptionPane.showMessageDialog(null, "登录失败");
					return;
				}

			}
		});
		// 注册增加事件监听
		register.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Register();	
				JfTool.jf.dispose();

			}
		});
		login.setBounds(80, 90, 80, 30);
		register.setBounds(200, 90, 80, 30);
		name.setBounds(10, 20, 80, 25);
		password.setBounds(10, 50, 80, 25);
		txtName.setBounds(100, 25, 165, 25);
		txtPwd.setBounds(100, 50, 165, 25);

		
		// 将整块面板定义在中间
		JfTool.jf.add(p, BorderLayout.CENTER);

		JfTool.jf.setTitle("二嗨租车");
		// 设置初始位置
		JfTool.jf.setLocation(500, 300);
		JfTool.jf.setSize(350, 200);
		JfTool.jf.setVisible(true);
		JfTool.jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
	}
}
