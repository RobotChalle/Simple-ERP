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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.OrderDAO;
import dto.EstimateDTO;
import dto.OrderDTO;

public class Order implements ActionListener {
	OrderDAO odao = new OrderDAO();
	JFrame frame = new JFrame();
	JTextField orderNumTF = new JTextField(10);
	JTextField corpNameTF = new JTextField(10);
	JTextField orderDateTF = new JTextField(10);
	private OrderDTO temp = new OrderDTO();
	ArrayList<OrderDTO> order = null;

	public ArrayList<OrderDTO> orderList() {
		order = odao.getList();
		return order;
	}

	public void OrderAdd() {
		JLabel title = new JLabel("발주서 입력");
		JButton inputB = new JButton("입력");
		JPanel northP = new JPanel();
		JPanel northP_row1 = new JPanel();
		JPanel northP_row2 = new JPanel();
		JPanel northP_row3 = new JPanel();
		JPanel northP_row4 = new JPanel();
		JLabel orderNumJL = new JLabel("발주서 번호");
		JLabel corpNameJL = new JLabel("거래처명");
		JLabel orderDateJL = new JLabel("발주 날짜(2020-01-01)");
		northP.setLayout(new GridLayout(4, 1));
		northP.add(northP_row1);
		northP.add(northP_row2);
		northP.add(northP_row3);
		northP.add(northP_row4);
		northP_row1.add(title);
		northP_row2.add(orderNumJL);
		northP_row2.add(orderNumTF);
		northP_row3.add(corpNameJL);
		northP_row3.add(corpNameTF);
		northP_row4.add(orderDateJL);
		northP_row4.add(orderDateTF);
		Object[] obj = new Object[] { "품명", "수량", "단가"};
		DefaultTableModel model = new DefaultTableModel(obj , 5);
		JTable table = new JTable(model);

		// 메서드 사용하여 셀 값 변경 처리
		new CellListener(table, () -> {
			int row = table.getSelectedRow();
			int column = table.getSelectedColumn();
			Object value = table.getValueAt(row, column);
			if (column == 0) {
				temp.setProductName(row, value.toString());
			} else if (column == 1) {
				temp.setQuantity(row, Integer.parseInt(value.toString()));
			} else if (column == 2) {
				temp.setPrice(row, Integer.parseInt(value.toString()));
			}
		});
		int sum = 0;
		for(int i = 0; i<5; i++) {
			sum = sum + (temp.getPrice(i)*temp.getQuantity(i));
			temp.setTotalPrice(i, sum);
		}
		inputB.addActionListener(this);
		frame.add(new JScrollPane(table), "Center");
		frame.add(inputB, "South");
		frame.add(northP, "North");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();// 프레임 조정
		frame.setVisible(true);	
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		temp.setTradCorpName(corpNameTF.getText());
		temp.setOrderNum(Integer.parseInt(orderNumTF.getText()));
		temp.setOrderDate(orderDateTF.getText());
		odao.add(temp);
		frame.setVisible(false);
	}
}
