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
import javax.swing.JTextField;

import com.erhi.biz.ICarBiz;
import com.erhi.bizimpl.CarBizImpl;
import com.erhi.tools.JfTool;

public class AdminSearchID {
	private JButton comfirm, back;
	private JTextField carIdtxt;
	protected JLabel carId;
	private static ICarBiz mICarBizimpl;
	public AdminSearchID() {
		mICarBizimpl = new CarBizImpl();
//		jf = new JFrame("二嗨租车--根据ID查询");
		comfirm = new JButton("确认");
		back = new JButton("返回");
		carIdtxt = new JTextField(10);
		carId = new JLabel("请输入汽车编号");
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int x = screenSize.height;
		int y = screenSize.width;
		int xx = (x - 600) / 2;
		int yy = (y - 500) / 2;
		JfTool.jf7.setSize(600, 500);
		JfTool.jf7.setVisible(true);
		JfTool.jf7.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JfTool.jf7.setLocation(yy, xx);
		JPanel p = new JPanel(new GridLayout(2, 2));
		JfTool.jf7.add(p);
		JfTool.jf7.setResizable(false);
		JfTool.jf7.setTitle("二嗨租车--根据ID查询");
		
		p.add(carId);
		p.add(carIdtxt);
		
		p.add(comfirm);
		p.add(back);
		
		comfirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String strID = carIdtxt.getText();
				if (strID == null || strID.equals("")) {
					JOptionPane.showMessageDialog(null, "编号不能为空！");
					return;
				}
				int carId = Integer.parseInt(strID);
				mICarBizimpl.adminSeID(carId);
				JfTool.jf7.dispose();
			}
		});
		
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new AdminView();
				JfTool.jf7.dispose();
			}
		});

	}
	
}
