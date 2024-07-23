package main;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.ClientMemberDAO;
import dto.ClientMemberDTO;
import erp.ERPmenu;


public class Login extends JFrame implements ActionListener {
	private ClientMemberDAO cdao = new ClientMemberDAO();
	ClientMemberDTO user = new ClientMemberDTO();
	ERPmenu erpMenu = new ERPmenu();

	JLabel title = new JLabel("고객 접속기");
	JPanel centerp = new JPanel();
	JPanel centerp_row1 = new JPanel();
	JPanel centerp_row2 = new JPanel();
	JPanel centerp_row3 = new JPanel();
	JPanel centerp_row4 = new JPanel();
	JButton b1 = new JButton("로그인");
	JLabel id = new JLabel("ID");
	JLabel pw = new JLabel("PW");
	JTextField idtf = new JTextField(10);
	JTextField pwtf = new JTextField(10);

	Login() {
		this.setBounds(500, 300, 400, 400);
		this.setBackground(Color.white);
		centerp.setLayout(new GridLayout(5, 1));
		centerp.add(centerp_row1);
		centerp.add(centerp_row2);
		centerp.add(centerp_row3);
		centerp.add(centerp_row4);
		centerp_row1.add(title);
		centerp_row2.add(id);
		centerp_row2.add(idtf);
		centerp_row3.add(pw);
		centerp_row3.add(pwtf);
		centerp_row4.add(b1);
		b1.addActionListener(this);
		this.add(centerp, "Center");
		this.setVisible(true);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		String getId = idtf.getText();
		String getPw = pwtf.getText();
		if (login(getId, getPw)) {
			this.setVisible(false);
		} else {
			System.out.println("아이디나 비밀번호가 다릅니다.");
		}
	}

	private boolean login(String id, String pw) {
		user = cdao.login(id, pw);
		try {
			if (user.getPw().equals(pw)) {
				erpMenu.menu(user.getId(),user.getCorp());
				return true;
			}
		} catch (Exception e) {
			System.out.println("오류");
		}
		return false;
	}
}
