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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.erhi.biz.ICarBiz;
import com.erhi.bizimpl.CarBizImpl;

public class AdminAlterCarView {
	private ButtonGroup g;
	private Label blank1;
	private JButton comfirm, back;
	private ICarBiz mICarBizimpl;
	private JFrame jf;
	private JRadioButton s1, s2;
	private JTextField carIdTxt, carPriceTxt;
	private JLabel carId, carPrice, caruse;

	public AdminAlterCarView() {
		mICarBizimpl = new CarBizImpl();
		jf = new JFrame("二嗨租车--修改车辆");
		carId = new JLabel("请输入要修改的汽车编号");
		carPrice = new JLabel("输入修改后的价格");
		caruse = new JLabel("是否上架");

		carIdTxt = new JTextField(20);
		carPriceTxt = new JTextField(20);

		comfirm = new JButton("确认");
		back = new JButton("返回");

		s1 = new JRadioButton("下架");
		s2 = new JRadioButton("上架", true);

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
		JPanel p = new JPanel(new GridLayout(6, 2));
		jf.setContentPane(p);
		jf.setResizable(false);
		blank1 = new Label();
		g = new ButtonGroup();
		g.add(s1);
		g.add(s2);

		p.add(carId);
		p.add(carIdTxt);

		p.add(carPrice);
		p.add(carPriceTxt);

		p.add(caruse);
		p.add(blank1);

		p.add(s1);
		p.add(s2);

		p.add(comfirm);
		p.add(back);
		
		comfirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String strCarID = carIdTxt.getText();
				if (strCarID == null || strCarID.equals("")) {
					JOptionPane.showMessageDialog(null, "编号不能为空！");
					return;
				}
				int sid = Integer.parseInt(strCarID);
				String strPrice = carPriceTxt.getText();
				if (strPrice == null || strPrice.equals("")) {
					JOptionPane.showMessageDialog(null, "价格不能为空！");
					return;
				}
				String outsput1 = null;
				if(s1.isSelected()){
					 outsput1 = "下架";
					 if(mICarBizimpl.isRent(sid)) {
							if (mICarBizimpl.alterAdmin(sid,strPrice,outsput1)) {
							JOptionPane.showMessageDialog(null, "修改成功！");
							 new AdminView();
							 jf.dispose();
							}
						}else {
							JOptionPane.showMessageDialog(null, "当前车辆已被借出无法修改");
							new AdminView();
							 jf.dispose();
						}
				}else if(s2.isSelected()){
					 outsput1 = "上架";
					 if (mICarBizimpl.alterAdmin(sid,strPrice,outsput1)) {
							JOptionPane.showMessageDialog(null, "修改成功！");
							 new AdminView();
							 jf.dispose();
					}else {
						JOptionPane.showMessageDialog(null, "修改错误!");
						new AdminView();
						 jf.dispose();
					}
				}		
			}
		});
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new AdminView();
				jf.dispose();				
			}
		});

	}
}
