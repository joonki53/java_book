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

class booklend extends Frame implements ActionListener{
	String url = "jdbc:mysql://localhost:3306/java_db?useUnicode=true&characterEncoding=utf8";
	String id = "root";
	String pass = "qwer";
	private Connection dc;
	Statement stmt = null;
	boolean joinIdFinalCheck = false;
	
	Font font20 = new Font("TimesRoman",Font.BOLD,20);
	Font font15 = new Font("TimesRoman",Font.BOLD,15);
	Font font10 = new Font("TimesRoman",Font.BOLD,10);
	Label lbTitle = new Label("도서대여");
	Label lbId = new Label("도서아이디:");
	Label lbName = new Label("도서이름:");
	Label lbcompany = new Label("출판사:");
	Label lbauthor = new Label("저자:");
	Label lbTitle2 = new Label("대여 도서 리스트");
	
	TextField tfId = new TextField();
	TextField tfcompany = new TextField();
	TextField tfName = new TextField();
	TextField tfauthor = new TextField();
	
	Button btnSearch = new Button("찾기");
	Button btnClose = new Button("닫기");
	Button btnCheck = new Button("대여가능확인");
	Button btnlend = new Button("대여");
	Button btnnew = new Button("새로고침");
	
	TextArea ta = new TextArea();
	 public booklend() {
		super("도서대여");
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
		dataBook();
	}
	void closeView() {
		this.setVisible(false);
	}
	void start() {
		btnClose.addActionListener(this);
		btnSearch.addActionListener(this);
		btnCheck.addActionListener(this);
		btnlend.addActionListener(this);
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
		this.add(btnCheck);
		btnCheck.setBounds(110,170,110,30);
		btnCheck.setFont(font15);
		this.add(btnlend);
		btnlend.setBounds(110,370,80,40);
		btnlend.setFont(font15);
		
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
	void idCheck(String id) {
		boolean idCheck  = false;
		String query = "select * from tb_lend where checkid = ? ";
		
		try {
			PreparedStatement pstmt = dc.prepareStatement(query);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				msgDlg("대여 불가!!");
				idCheck = true;
			}
			rs.close();
			pstmt.close();
		} catch (SQLException ee) {
			System.err.println("중복 처리 실패!!");
		}
		if(idCheck == false) {
			msgDlg("도서대여 가능!!");
			btnCheck.setEnabled(false);
    		tfId.setEnabled(false);
    	
    		joinIdFinalCheck =true;
		}
	}
	void lendBook(String checkid ,String bookname, String company,String author) {
		String query = "insert into tb_lend values (null, ?,?,?,?,now())";
			try {
				PreparedStatement pstmt = dc.prepareStatement(query);
				pstmt.setString(1, checkid);
				pstmt.setString(2, bookname);
				pstmt.setString(3, company);
				pstmt.setString(4, author);
				
				pstmt.executeUpdate();
				pstmt.close();
			} catch (SQLException ee) {
				System.err.println("도서 대여 실패!! : " + ee.toString());
				
			}
			
		}
	void dataBook() {
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
			
			
			String query = "select * from tb_book where bookid = ? ";
			
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
		else if(e.getSource() == btnCheck) {
			
			String id = tfId.getText();
			if(id.equals("")) {
				msgDlg("아이디를 입력하세요.");
				return;
			}
			idCheck(id);
			
		}
		else if(e.getSource() == btnlend) {
			String checkid = tfId.getText();
			String bookname = tfName.getText();
			String company = tfcompany.getText();
			String author = tfauthor.getText();
			
			if(checkid.equals("")) {
				msgDlg("도서아이디를 입력하세요.");
				return;
			}
			if(bookname.equals("")) {
				msgDlg("도서이름을 입력하세요.");
				return;
			}
			if(company.equals("")) {
				msgDlg("출판사를 입력하세요.");
				return;
			}
			if(author.equals("")) {
				msgDlg("저자를 입력하세요.");
				return;
			}
			
			lendBook(checkid, bookname, company, author);
			joinIdFinalCheck = false;
			msgDlg("대여완료");
			
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