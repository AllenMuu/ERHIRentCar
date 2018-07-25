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

public class UserRentView {
	private JButton comfirm, back, userName, userPwd;
	private JTextField carIdtxt, rentTimetxt, userNametxt;
	private JPasswordField userPwdtxt;
	private JFrame jf;
	private JLabel carId, rentTime;
	private ICarBiz mICarBizimpl;

	public UserRentView(User user) {
		mICarBizimpl = new CarBizImpl();
		jf = new JFrame("二嗨租车--租车界面");
		// userName = new JButton("请再次输入您的账号");
		// userPwd = new JButton("请再次输入您的密码");
		carId = new JLabel("请输入汽车编号");
		rentTime = new JLabel("请输入租车时间");
		carIdtxt = new JTextField(20);
		rentTimetxt = new JTextField(20);
		// userNametxt = new JTextField(20);
		// userPwdtxt = new JPasswordField(20);
		// userPwdtxt.setEchoChar('*');

		comfirm = new JButton("确认");
		back = new JButton("返回");

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
		JPanel p = new JPanel(new GridLayout(3, 2));
		jf.setContentPane(p);
		jf.setResizable(true);

		// p.add(userName);
		// p.add(userNametxt);
		// p.add(userPwd);
		// p.add(userPwdtxt);
		p.add(carId);
		p.add(carIdtxt);
		p.add(rentTime);
		p.add(rentTimetxt);
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
				String strRentTime = rentTimetxt.getText();
				if (strRentTime == null || strRentTime.equals("")) {
					JOptionPane.showMessageDialog(null, "租车时间不能为空！");
					return;
				}
				int sr = Integer.parseInt(strRentTime);
				if(sr<=0) {
					JOptionPane.showMessageDialog(null, "输入时间错误！");
					return;
				}
				int uid = user.getUserId();
				if (mICarBizimpl.isRent(sc)) {
					// 得到单价
					double price = mICarBizimpl.getCarPrice(sc);
					if (mICarBizimpl.setUserRecord(sc, uid, price * sr, sr)) {
						JOptionPane.showMessageDialog(null, "租车成功！");
						mICarBizimpl.showrentresult(uid);
						jf.dispose();
					} else {
						JOptionPane.showMessageDialog(null, "租车失败！");
						return;
					}
				} else {
					JOptionPane.showMessageDialog(null, "当前车辆不可租！");
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
