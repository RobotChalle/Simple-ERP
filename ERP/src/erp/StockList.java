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
	//���ָ� �ϸ� ���ֵ� ��ǰ��� ������ �����Ѵ�.
	//�������� ������ �ȸ��� ������ �����Ѵ�.
	//���ֽÿ� �̹� ����� ���� �̸��� ������ �ִٸ� ������ �߰����ش�.
	//���������� �߰��� ��ġ�� �߿䵵�� �־��ش�.
	
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
        String[] columnNames = {"��ǰ��", "��ġ" ,"����ȸ��","��ü����"};
        JTable table = new JTable(data, columnNames);
        JFrame tableFrame = new JFrame("��� ��Ȳ");
        tableFrame.add(new JScrollPane(table));
        tableFrame.pack();
        tableFrame.setVisible(true);
	}
}
