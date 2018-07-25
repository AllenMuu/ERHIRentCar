package com.erhi.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;

import com.erhi.bean.Car;
import com.erhi.bean.User;
import com.erhi.biz.ICarBiz;
import com.erhi.bizimpl.CarBizImpl;

public class UserAsceView {
	private JPanel contentPane;
    private JTable table;
    private String head[]=null;
    private Object [][]data=null;
    private ICarBiz mICarBizimpl;
    private JFrame jf;
    private JButton back;
	public  UserAsceView(User user){
		mICarBizimpl = new CarBizImpl();
		jf = new JFrame("二嗨租车--用户升序查询");
		back = new JButton("返回");
		back.setBounds(400, 200, 100, 50);
		contentPane = new JPanel();
		contentPane.add(back);
		jf.add(contentPane);
		jf.setSize(550, 300);
		jf.setLocation(200, 100);
		jf.setResizable(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		// 设置滚动条
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 100, 700, 250);

		table = new JTable();
		table.setRowHeight(30);
		scrollPane.setViewportView(table);

        head=new String[] {
            "汽车编号", "汽车名称", "排量", "品牌", "类型", "单价", "是否可租"
        };
        
        DefaultTableModel tableModel=new DefaultTableModel(queryData(),head){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1994579976818141870L;

			public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        table.setModel(tableModel);

        scrollPane.setViewportView(table);
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE)
        );
        gl_contentPane.setVerticalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
                    .addGap(66))
        );
        contentPane.setLayout(gl_contentPane);
        back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new UserSearchView(user);
				jf.dispose();
				
			}
		});
	}
	
	/*
	 * 生成表格
	 */
	public Object[][] queryData(){
		mICarBizimpl = new CarBizImpl();
        List<Car> list=mICarBizimpl.searchAsce();
        data=new Object[list.size()][head.length];

        for(int i=0;i<list.size();i++){
            for(int j=0;j<head.length;j++){
                data[i][0]=list.get(i).getCarId();
                data[i][1]=list.get(i).getCarName();
                data[i][2]=list.get(i).getEmission();
                data[i][3]=list.get(i).getBrandName();
                data[i][4]=list.get(i).getTypeName();
                data[i][5]=list.get(i).getPrice();
                data[i][6]=list.get(i).getStatus();
        
            }
        }
        return data;
    }
}
