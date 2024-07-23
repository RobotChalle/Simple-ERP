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

public class SalesMenu extends JFrame implements ActionListener{
	Estimate estimate = null;
	ArrayList<EstimateDTO> est = new ArrayList<>();
	JLabel title = new JLabel("영업 메뉴");
	JPanel centerP = new JPanel();
	JButton estLookB = new JButton("판매 조회");
	JButton estaddB = new JButton("판매 입력");
	
	public void SalesMenu(Estimate est) {
		estimate = est;
		this.setBounds(400, 400, 400, 400);
		centerP.setLayout(new GridLayout(3, 1));
		JPanel centerP_row1 = new JPanel();
		JPanel centerP_row2 = new JPanel();
		JPanel centerP_row3 = new JPanel();
		centerP.add(centerP_row1);
		centerP.add(centerP_row2);
		centerP.add(centerP_row3);
		centerP_row1.add(title);
		centerP_row2.add(estLookB);
		centerP_row3.add(estaddB);
		estLookB.addActionListener(this);
		estaddB.addActionListener(this);
		this.add(centerP,"Center");
		this.setVisible(true);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(estLookB)) {
			est = estimate.EstimateList();
			Object[][] data = new Object[est.size()][12];
            
            // 견적서 목록을 반복하면서 테이블에 표시할 배열 채우기
            for (int i = 0; i < est.size(); i++) {
                EstimateDTO dto = est.get(i);
                data[i][0] = dto.getEstNum();
                data[i][1] = dto.getEstCorp();
                data[i][2] = dto.getEstDate();
                data[i][3] = dto.getProductName(0);
                data[i][4] = dto.getQuantity(0);
                data[i][5] = dto.getUnitPrice(0);
                data[i][6] = dto.getSupplyPrice(0);
            }
            String[] columnNames = { "판매 번호", "업체명", "판매 날짜", "제품명", "수량","단가","공급가" };
            JTable table = new JTable(data, columnNames);
            JFrame tableFrame = new JFrame("판매 조회 결과");
            tableFrame.add(new JScrollPane(table));
            tableFrame.pack();
            tableFrame.setVisible(true);
		}else if(e.getSource().equals(estaddB)) {
			Estimate est= new Estimate();
			est.EstimateAdd();
		}
	}
}
