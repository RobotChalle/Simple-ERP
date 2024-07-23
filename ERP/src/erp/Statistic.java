package erp;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import dao.StatisticDAO;
import dto.EstimateDTO;
import dto.OrderDTO;
import dto.StockDTO;

public class Statistic {
	StatisticDAO sdao = new StatisticDAO();
	ArrayList<StockDTO> sta = null;
	ArrayList<EstimateDTO> estList = null;
	ArrayList<OrderDTO> orderList = null;

	public void Statistic() {
		sta = sdao.getList();
		Object[][] data = new Object[sta.size()][12];
		for (int i = 0; i < sta.size(); i++) {
			StockDTO dto = sta.get(i);
			data[i][0] = dto.getProductName();
			data[i][1] = dto.getTotalQuantity();
		}
		// JTable의 열 이름 설정
		String[] columnNames = { "제품명", "수량" };
		// JTable 생성 및 추가
		JTable table1 = new JTable(data, columnNames);

		// 매입,매출 JTable로 표현
		estList = sdao.getEstList();
		orderList = sdao.getOrderList();

		Object[][] data2 = new Object[13][12];
		// JTable의 열 이름 설정

		for (int i = 1; i <= 12; i++) {
			int tempQuantity = 0;
			int tempSupplyPrice = 0;
			int tempUnit = 0;
			int tempTotalSalesPrice = 0;
			int tempTotalUnitPrice =0;
			int tempProfit = 0;
			int tempTotalBuyPrice = 0;
			for (int j = 0; j < estList.size(); j++) {
				if (estList.get(j) != null && estList.get(j).getEstDate() !=null) {
					LocalDate estDate = LocalDate.parse(estList.get(j).getEstDate());
					if (estDate.getMonthValue() == i) {
						tempQuantity = estList.get(j).getQuantity(0);
						tempSupplyPrice = estList.get(j).getSupplyPrice(0);
						tempUnit =tempQuantity*estList.get(j).getUnitPrice(0);
						tempTotalUnitPrice = tempTotalUnitPrice+tempUnit;
						tempProfit =  tempQuantity * tempSupplyPrice;
						tempTotalSalesPrice = tempTotalSalesPrice + tempProfit;
					}
				}
			}
			for (int k = 0; k < orderList.size(); k++) {
				if (orderList.get(k) != null && orderList.get(k).getOrderDate() !=null) {
					LocalDate orderDate = LocalDate.parse(orderList.get(k).getOrderDate());
					if (orderDate.getMonthValue() == i) {
						tempTotalBuyPrice = tempTotalBuyPrice + orderList.get(k).getTotalPrice(0);
					}
				}
			}
			data2[i-1][0] = (i + "월");
			data2[i-1][1] = tempTotalSalesPrice;
			data2[i-1][2] = tempTotalUnitPrice;
			data2[i-1][3] = tempTotalSalesPrice - tempTotalUnitPrice;
			data2[i-1][4] = tempTotalUnitPrice - tempTotalBuyPrice;
		}
		String[] columnNames2 = { "월" , "물품 판매액","물품 단가","순 이익","물품 구매 적자"};
		// JTable 생성 및 추가
		JTable table2 = new JTable(data2, columnNames2);
		JFrame tableFrame = new JFrame("회사 영업 이익");
		tableFrame.add(new JScrollPane(table1), "West");
		tableFrame.add(new JScrollPane(table2), "East");
		tableFrame.pack();
		tableFrame.setVisible(true);
	}
}
