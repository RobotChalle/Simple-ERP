package erp;

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

import dao.OrderDAO;
import dao.StockDAO;
import dto.OrderDTO;
import dto.StockDTO;

public class StockList  extends JFrame {
	//발주를 하면 발주된 상품명과 수량을 저장한다.
	//영업으로 물건이 팔리면 수량이 감소한다.
	//발주시에 이미 저장된 같은 이름의 정보가 있다면 수량만 추가해준다.
	//재고관리에서 추가로 위치와 중요도를 넣어준다.
	
	public void StockList() {
		StockDAO sdao = new StockDAO();
		ArrayList<StockDTO> stockList = null;
		StockDTO temp = new StockDTO();
		stockList = sdao.getList();
		Object[][] data = new Object[stockList.size()][12];
        for (int i = 0; i < stockList.size(); i++) {
            StockDTO dto = stockList.get(i);
            data[i][0] = dto.getProductName();
            data[i][1] = dto.getLoc();
            data[i][2] = dto.getProducCorpName();
            data[i][3] = dto.getTotalQuantity();
        }
        String[] columnNames = {"제품명", "위치" ,"공급회사","전체수량"};
        JTable table = new JTable(data, columnNames);
        JFrame tableFrame = new JFrame("재고 현황");
        tableFrame.add(new JScrollPane(table));
        tableFrame.pack();
        tableFrame.setVisible(true);
	}
}
