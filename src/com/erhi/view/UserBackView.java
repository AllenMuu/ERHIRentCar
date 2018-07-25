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

public class UserBackView {
	private JButton comfirm, back, userName, userPwd;
	private JTextField carIdtxt, userNametxt;
	private JPasswordField userPwdtxt;
	private JFrame jf;
	private JLabel carId;
	private static ICarBiz mICarBizimpl;

	public UserBackView(User user) {
		mICarBizimpl = new CarBizImpl();
		jf = new JFrame("二嗨租车--还车界面");
		// userName = new JButton("请再次输入您的账号");
		// userPwd = new JButton("请再次输入您的密码");
		carId = new JLabel("请输入汽车编号");
		carIdtxt = new JTextField(20);
		comfirm = new JButton("确认");
		back = new JButton("返回");
		// userNametxt = new JTextField(20);
		// userPwdtxt = new JPasswordField(20);
		// userPwdtxt.setEchoChar('*');

		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int x = screenSize.height;
		int y = screenSize.width;
		int xx = (x - 600) / 2;
		int yy = (y - 500) / 2;
		jf.setSize(600, 500);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLocation(yy, xx);
		JPanel p = new JPanel(new GridLayout(2, 2));
		jf.setContentPane(p);
		jf.setResizable(false);

		// p.add(userName);
		// p.add(userNametxt);
		// p.add(userPwd);
		// p.add(userPwdtxt);
		p.add(carId);
		p.add(carIdtxt);

		p.add(comfirm);
		p.add(back);

		comfirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// String strName = userNametxt.getText();
				// if (strName == null || strName.equals("")) {
				// JOptionPane.showMessageDialog(null, "用户名不能为空！");
				// return;
				// }
				// String strPwd = new String(userPwdtxt.getPassword());
				// if (strPwd == null || strPwd.equals("")) {
				// JOptionPane.showMessageDialog(null, "密码不能为空！");
				// return;
				// }
				String strCarID = carIdtxt.getText();
				if (strCarID == null || strCarID.equals("")) {
					JOptionPane.showMessageDialog(null, "汽车编号不能为空！");
					return;
				}
				int sc = Integer.parseInt(strCarID);
				int uid = user.getUserId();
				if (mICarBizimpl.isBack(sc, uid)) {
					if (mICarBizimpl.backCar(sc)) {
						JOptionPane.showMessageDialog(null, "还车成功！");
						new UserView(user);
						jf.dispose();
					} else {
						JOptionPane.showMessageDialog(null, "还车失败！");
						return;
					}

				} else {
					JOptionPane.showMessageDialog(null, "您未借过这辆车！");
					return;
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
