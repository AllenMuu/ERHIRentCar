package com.erhi.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import com.erhi.biz.ICarBiz;
import com.erhi.bizimpl.CarBizImpl;


/**
 * 注册界面
 * 
 * @author Administrator
 *
 */
public class Register {
	// 声明组件
	private JPanel p;
	private JLabel lbName,lblPwd,lbRePwd;
	// 声明文本框
	private JTextField txtName;
	// 声明两个密码框
	private JPasswordField txtPwd, txtRePwd;
	//声明两个按钮
	private JButton btnReg, btnCancel;
	private static ICarBiz mICarBizimpl;
	private JFrame jf;
	public  Register() {
		mICarBizimpl = new CarBizImpl();
		jf = new JFrame("注册新用户");
		// 创建面板，面板布局为NULL
		p = new JPanel(null);
		// 实例化5个标签
		lbName = new JLabel("用户名");
		lblPwd = new JLabel("密   码");
		lbRePwd = new JLabel("确认密码");
		// 显示信息的标签
		//lbIMsg = new JLabel();
		// 设置标签的文字是红色
		//lbIMsg.setForeground(Color.RED);
		// 创建一个长度为20 的文本框
		txtName = new JTextField(20);
		// 创建两个密码框长度为20
		txtPwd = new JPasswordField(20);
		txtRePwd = new JPasswordField(20);
		// 设置密码框显示的字符为*
		txtPwd.setEchoChar('*');
		txtRePwd.setEchoChar('*');
		// 创建两个按钮
		btnReg = new JButton("注册");
		btnCancel = new JButton("清空");
		
		lbName.setBounds(30, 30, 60, 25);
		txtName.setBounds(95, 30, 120, 25);
		lblPwd.setBounds(30, 60, 60, 25);
		txtPwd.setBounds(95, 60, 120, 25);
		lbRePwd.setBounds(30, 90, 60, 25);
		txtRePwd.setBounds(95, 90, 120, 25);
		btnReg.setBounds(60, 150, 60, 25);
		btnCancel.setBounds(150, 150, 60, 25);

		// 添加所有组件
		p.add(lbName);
		p.add(txtName);
		p.add(txtPwd);
		p.add(lblPwd);
		p.add(txtRePwd);
		p.add(lbRePwd);
		p.add(btnReg);
		p.add(btnCancel);
		
		jf.add(p);
		jf.setSize(280, 280);
		jf.setLocation(200, 100);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		
		
		// 注册监听
		btnReg.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 设置信息标签为空 清楚原来的历史信息
				//lbIMsg.setText("");
				// 获取用户输入的用户名
				String strName = txtName.getText();
				if (strName == null || strName.equals("")) {
					JOptionPane.showMessageDialog(null, "用户名不能为空！");
					return;
				}
				
				if (mICarBizimpl.isSame(strName)) {
					JOptionPane.showMessageDialog(null, "用户名已经存在！");
					return;
				}
				// 获取密码
				String strPwd = new String(txtPwd.getPassword());
				if (strPwd == null || strPwd.equals("")) {
					JOptionPane.showMessageDialog(null, "密码不能为空！");
					return;
				}
				String strRePwd = new String(txtRePwd.getPassword());
				if (strRePwd == null || strRePwd.equals("")) {
					JOptionPane.showMessageDialog(null, "确认密码不能为空！");
					return;
				}

				// 判断确认密码是否跟密码相同
				if (!strRePwd.equals(strPwd)) {
					JOptionPane.showMessageDialog(null, "确认密码跟密码不同！");
					return;
				}
				if (mICarBizimpl.addUser(strName,strPwd)) {
					JOptionPane.showMessageDialog(null, "注册成功！");//弹出对话框
					new Login();
					jf.dispose();
				}else {
					JOptionPane.showMessageDialog(null, "注册失败！");
				}
				jf.setVisible(true);
				jf.dispose();
			}
		});

		// 取消按钮的事件处理
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 清空所有文本信息
				txtName.setText("");
				txtPwd.setText("");
				txtRePwd.setText("");

			}
		});
		
	}
}
