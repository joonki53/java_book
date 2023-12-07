package 자바수업17일차_도서관리;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class bookreturn extends Frame implements ActionListener{
	String url = "jdbc:mysql://localhost:3306/java_db?useUnicode=true&characterEncoding=utf8";
	String id = "root";
	String pass = "qwer";
	private Connection dc;
	Statement stmt = null;
	
	
	Font font20 = new Font("TimesRoman",Font.BOLD,20);
	Font font15 = new Font("TimesRoman",Font.BOLD,15);
	Font font10 = new Font("TimesRoman",Font.BOLD,10);
	Label lbTitle = new Label("도서반납");
	Label lbId = new Label("도서아이디:");
	Label lbName = new Label("도서이름:");
	Label lbcompany = new Label("출판사:");
	Label lbauthor = new Label("저자:");
	
	
	TextField tfId = new TextField();
	TextField tfcompany = new TextField();
	TextField tfName = new TextField();
	TextField tfauthor = new TextField();
	
	Button btnSearch = new Button("찾기");
	Button btnClose = new Button("닫기");
	Button btnreturn = new Button("반납");
	Label lbTitle2 = new Label("대여 도서 리스트");
	Button btnnew = new Button("새로고침");
	TextArea ta = new TextArea();
	  public bookreturn() {
		super("도서반납");
		setSize(800,500);
		this.init4();
		this.start();
		setLocation(1000,100);
		setVisible(true);
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch (ClassNotFoundException ee) {
			return;
		}
		try {
			dc = DriverManager.getConnection(url, id, pass);
		} catch (SQLException ee) {
		}
		dataBook1();
	}
	void closeView() {
		this.setVisible(false);
	}
	void start() {
		btnClose.addActionListener(this);
		btnSearch.addActionListener(this);
		btnreturn.addActionListener(this);
		btnnew.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				closeView();
			}
		});
	}
	void init4() {
		setLayout(null);
		this.add(lbTitle);
		lbTitle.setBounds(105,50,100,40);
		lbTitle.setFont(font20);
		this.add(lbId);
		lbId.setBounds(30,120,85,30);
		lbId.setFont(font15);
		this.add(lbcompany);
		lbcompany.setBounds(30,270,70,30);
		lbcompany.setFont(font15);
		this.add(lbName);
		lbName.setBounds(30,220,70,30);
		lbName.setFont(font15);
		this.add(lbauthor);
		lbauthor.setBounds(30,320,70,30);
		lbauthor.setFont(font15);
		this.add(btnSearch);
		btnSearch.setBounds(220,120,50,30);
		btnSearch.setFont(font15);
		this.add(btnClose);
		btnClose.setBounds(110,420,80,40);
		btnClose.setFont(font15);
		this.add(btnreturn);
		btnreturn.setBounds(110,370,80,40);
		btnreturn.setFont(font15);
		
		this.add(tfId);
		tfId.setBounds(120,120,100,30);
		tfId.setFont(font15);
		this.add(tfcompany);
		tfcompany.setBounds(120,270,100,30);
		tfcompany.setFont(font15);
		this.add(tfName);
		tfName.setBounds(120,220,100,30);
		tfName.setFont(font15);
		this.add(tfauthor);
		tfauthor.setBounds(120,320,100,30);
		tfauthor.setFont(font15);
		
		this.add(ta);
		ta.setBounds(280,100,490,370);
		ta.setFont(font15);
		this.add(lbTitle2);
		lbTitle2.setBounds(450,70,200,30);
		lbTitle2.setFont(font20);
		this.add(btnnew);
		btnnew.setBounds(650,65,100,30);
		btnnew.setFont(font15);
		
		
	}
	void msgDlg(String msg) {
		final Dialog dlg = new Dialog(this, "안내", true);
		Button bt = new Button("확인");
		Label lb = new Label(msg);
		dlg.setLayout(null);
		dlg.add(bt);
		dlg.add(lb);
		bt.setFont(font15);
		lb.setFont(font15);
		bt.setBounds(165,150,100,30);
		lb.setBounds(115,70,150,30);
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dlg.setVisible(false);
			}
		});
		dlg.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				closeView();
			}
		});
		dlg.setLocation(650,400);
		dlg.setSize(400, 300);
		dlg.setVisible(true);
	}
	void deleteMember() {
		String id = tfId.getText();
		if(id.equals("")){
			msgDlg("삭제아이디를 입력하세요.");
			return;	
		}
		String query = "delete from tb_lend where checkid = ?";
		try {
			PreparedStatement pstmt = dc.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException ee) {
			System.err.println("회원 삭제 실패!!");
			
		}
		msgDlg("반납완료!");
		tfId.setText("");
		tfcompany.setText("");
		tfName.setText("");
		tfauthor.setText("");
		
	}
	void dataBook1() {
		String query = "select * from tb_lend";	
		try {
			stmt = dc.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			String data="";			
			while (rs.next()) {
				data += rs.getString("checkid")+"/"+rs.getString("bookname")
				+"/"+rs.getString("company")+"/"+rs.getString("author")+"/"+rs.getString("date_reg")+"\n";
			}
			ta.setText(data);
			rs.close();
			stmt.close();
		} catch (SQLException ee) {
			System.err.println("책추가 처리 실패!!");
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == btnSearch) {
			String searchid = tfId.getText();
			if(searchid.equals("")){
				msgDlg("검색아이디를 입력하세요.");
				return;
			}
			boolean idCheck = false;
			String id = tfId.getText();
			
			
			String query = "select * from tb_lend where checkid = ? ";
			
			try {
				PreparedStatement pstmt = dc.prepareStatement(query);
				pstmt.setString(1, id);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					tfcompany.setText(rs.getString("company"));
	        		tfName.setText(rs.getString("bookname"));
	        		tfauthor.setText(rs.getString("author"));	
					
					idCheck = true;
				}
				rs.close();
				pstmt.close();
			} catch (SQLException ee) {
				System.err.println("검색 처리 실패!!");
			}
			if(idCheck == false) {
				msgDlg("아이디 없음");
				
			}

		}
		else if(e.getSource() == btnClose) {
			closeView();
		}
		else if(e.getSource() == btnreturn) {
			
			deleteMember();
			msgDlg("반납완료");
			
			tfId.setText("");
			tfName.setText("");
			tfcompany.setText("");
			tfauthor.setText("");
	
		}
		else if(e.getSource() == btnnew) {
			String query = "select * from tb_lend";	
			try {
				stmt = dc.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				String data="";			
				while (rs.next()) {
					data += rs.getString("checkid")+"/"+rs.getString("bookname")
					+"/"+rs.getString("company")+"/"+rs.getString("author")+"/"+rs.getString("date_reg")+"\n";
				}
				ta.setText(data);
				rs.close();
				stmt.close();
			} catch (SQLException ee) {
				System.err.println("새로고침 처리 실패!!");
			}
		}
		
	}
}