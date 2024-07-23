package erp;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.EstimateDAO;
import dto.EstimateDTO;

public class Estimate implements ActionListener {
	private static final String Action = null;
	EstimateDAO edao = new EstimateDAO();
	JFrame frame = new JFrame();
	JTextField estNumTF = new JTextField(10);
	JTextField corpNameTF = new JTextField(10);
	JTextField estimateDateTF = new JTextField(10);
	private EstimateDTO temp = new EstimateDTO();
	ArrayList<EstimateDTO> est = null;

	public ArrayList<EstimateDTO> EstimateList() {
		est = edao.getList();
		return est;
	}

	public void EstimateAdd() {
		JLabel title = new JLabel("판매 입력");
		JButton inputB = new JButton("입력");
		JPanel northP = new JPanel();
		JPanel northP_row1 = new JPanel();
		JPanel northP_row2 = new JPanel();
		JPanel northP_row3 = new JPanel();
		JPanel northP_row4 = new JPanel();
		JLabel estNumJL = new JLabel("판매 번호");
		JLabel corpNameJL = new JLabel("업체명");
		JLabel estimateDateJL = new JLabel("판매 날짜(2020-01-01)");
		northP.setLayout(new GridLayout(4, 1));
		northP.add(northP_row1);
		northP.add(northP_row2);
		northP.add(northP_row3);
		northP.add(northP_row4);
		northP_row1.add(title);
		northP_row2.add(estNumJL);
		northP_row2.add(estNumTF);
		northP_row3.add(corpNameJL);
		northP_row3.add(corpNameTF);
		northP_row4.add(estimateDateJL);
		northP_row4.add(estimateDateTF);
		Object[] obj = new Object[] { "품명", "수량", "단가", "공급가" };
		DefaultTableModel model = new DefaultTableModel(obj, 5);
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
				temp.setUnitPrice(row, Integer.parseInt(value.toString()));
			} else if (column == 3) {
				temp.setSupplyPrice(row, Integer.parseInt(value.toString()));
			} 
		});
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
		temp.setEstCorp(corpNameTF.getText());
		temp.setEstNum(Integer.parseInt(estNumTF.getText()));
		temp.setEstDate(estimateDateTF.getText());
		edao.add(temp);
		frame.setVisible(false);
	}
}
