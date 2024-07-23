package erp;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import dto.EstimateDTO;
import dto.OrderDTO;

public class OrderMenu extends JFrame implements ActionListener {
	Order order = new Order();
	ArrayList<OrderDTO> ord = null;
	JLabel title = new JLabel("발주 메뉴");
	JPanel centerP = new JPanel();
	JButton orderLookB = new JButton("발주 조회");
	JButton orderAddB = new JButton("발주 입력");

	public void OrderMenu() {
		this.setBounds(400, 400, 400, 400);
		centerP.setLayout(new GridLayout(3, 1));
		JPanel centerP_row1 = new JPanel();
		JPanel centerP_row2 = new JPanel();
		JPanel centerP_row3 = new JPanel();
		centerP.add(centerP_row1);
		centerP.add(centerP_row2);
		centerP.add(centerP_row3);
		centerP_row1.add(title);
		centerP_row2.add(orderLookB);
		centerP_row3.add(orderAddB);
		orderLookB.addActionListener(this);
		orderAddB.addActionListener(this);
		this.add(centerP,"Center");
		this.setVisible(true);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
	}
	
	@Override	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(orderLookB)) {
			ord = order.orderList();
			Object[][] data = new Object[ord.size()][12];
            for (int i = 0; i < ord.size(); i++) {
                OrderDTO dto = ord.get(i);
                data[i][0] = dto.getOrderNum();
                data[i][1] = dto.getTradCorpName();
                data[i][2] = dto.getOrderDate();
                data[i][3] = dto.getProductName(0);
                data[i][4] = dto.getQuantity(0);
                data[i][5] = dto.getPrice(0);
                data[i][6] = dto.getTotalPrice(0);
            }
            // JTable의 열 이름 설정
            String[] columnNames = { "발주 번호", "거래처명", "발주 날짜", "제품명", "수량" ,"단가","합계 금액"};
            // JTable 생성 및 추가
            JTable table = new JTable(data, columnNames);
            JFrame tableFrame = new JFrame("발주서 조회 결과");
            tableFrame.add(new JScrollPane(table));
            tableFrame.pack();
            tableFrame.setVisible(true);
		}else if(e.getSource().equals(orderAddB)) {
			Order ord = new Order();
			ord.OrderAdd();
		}
	}
}
