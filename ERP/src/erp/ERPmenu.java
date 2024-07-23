package erp;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ERPmenu extends JFrame implements ActionListener {
	SalesMenu smenu = new SalesMenu();
	OrderMenu omenu = new OrderMenu();
	StockList stock = new StockList();
	Statistic stat = new Statistic();
	Estimate est = new Estimate();
	JPanel centerP = new JPanel();
	JPanel centerP_row1 = new JPanel();
	JPanel centerP_row2 = new JPanel();
	JPanel centerP_row3 = new JPanel();
	JPanel centerP_row4 = new JPanel();
	JPanel centerP_row5 = new JPanel();
	JPanel centerP_row6 = new JPanel();
	JButton salesB = new JButton("영업");
	JButton orderB = new JButton("발주");
	JButton stockB = new JButton("재고");
	JButton statisticB = new JButton("통계");
	
	public void menu(String id, String corp) {
		JLabel idL = new JLabel("아이디 : "+id);
		JLabel corpL = new JLabel(corp +"사 ERP");
		this.setBounds(500, 300, 400, 400);
		this.setBackground(Color.white);
		centerP.setLayout(new GridLayout(6, 1));
		centerP.add(centerP_row1);
		centerP.add(centerP_row2);
		centerP.add(centerP_row3);
		centerP.add(centerP_row4);
		centerP.add(centerP_row5);
		centerP.add(centerP_row6);
		centerP_row1.add(corpL);
		centerP_row2.add(idL);
		centerP_row3.add(salesB);
		centerP_row4.add(orderB);
		centerP_row5.add(stockB);
		centerP_row6.add(statisticB);
		salesB.addActionListener(this);
		orderB.addActionListener(this);
		stockB.addActionListener(this);
		statisticB.addActionListener(this);
		this.add(centerP, "Center");
		this.setVisible(true);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(salesB)) {
			smenu.SalesMenu(est);
		}else if(e.getSource().equals(orderB)) {
			omenu.OrderMenu();
		}else if(e.getSource().equals(stockB)) {
			stock.StockList();
		}else if(e.getSource().equals(statisticB)) {
			stat.Statistic();
		}
	}
}
