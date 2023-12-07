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

public class Bookadd extends Frame implements ActionListener {
	String url = "jdbc:mysql://localhost:3306/java_db?useUnicode=true&characterEncoding=utf8";
	String id = "root";
	String pass = "qwer";
	private Connection dc;
	Statement stmt = null;
	Font font15 = new Font("TimesRoman",Font.BOLD,15);
	Font font20 = new Font("TimesRoman",Font.BOLD,20);
	Label lbTitle = new Label("도서추가");
	Label lbTitle2 = new Label("도서리스트");
	Label lbbookId = new Label("도서아이디:");
	Label lbbookName = new Label("도서이름:");
	Label lbbookCom = new Label("출판사:");
	Label lbbookAuth = new Label("저자:");
	TextArea ta = new TextArea();
	Button btnadd = new Button("추가");
	Button btnnew = new Button("새로고침");
	Button btncheck = new Button("중복체크");
	TextField tfid = new TextField();
	TextField tfname = new TextField();
	TextField tfcom = new TextField();
	TextField tfauthor = new TextField();
	boolean joinIdFinalCheck = false;	
	 public Bookadd() {
		super("도서추가");
		setSize(700,500);
		this.init();
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
	void init() {
		setLayout(null);
		this.add(lbTitle);
		lbTitle.setBounds(300,30,110,40);
		lbTitle.setFont(font20);

		this.add(btnadd);
		btnadd.setBounds(580,430,100,40);
		btnadd.setFont(font15);
		this.add(btnnew);
		btnnew.setBounds(450,430,90,30);
		btnnew.setFont(font15);
		this.add(btncheck);
		btncheck.setBounds(400,100,80,40);
		btncheck.setFont(font15);
		
		this.add(lbTitle2);
		lbTitle2.setBounds(30,100,120,40);
		lbTitle2.setFont(font15);
		
		this.add(ta);
		ta.setBounds(30,140,350,300);
		ta.setFont(font15);
		
		this.add(lbbookId);
		lbbookId.setBounds(400,140,90,40);
		lbbookId.setFont(font15);
		this.add(lbbookName);
		lbbookName.setBounds(400,200,90,40);
		lbbookName.setFont(font15);
		this.add(lbbookCom);
		lbbookCom.setBounds(400,260,90,40);
		lbbookCom.setFont(font15);
		this.add(lbbookAuth);
		lbbookAuth.setBounds(400,320,90,40);
		lbbookAuth.setFont(font15);
		
		this.add(tfid);
		tfid.setBounds(500,140,180,40);
		tfid.setFont(font15);
		this.add(tfname);
		tfname.setBounds(500,200,180,40);
		tfname.setFont(font15);
		this.add(tfcom);
		tfcom.setBounds(500,260,180,40);
		tfcom.setFont(font15);
		this.add(tfauthor);
		tfauthor.setBounds(500,320,180,40);
		tfauthor.setFont(font15);
		
		
		
	}
	void closeView() {
		this.setVisible(false);
	}
	void start() {
		btnadd.addActionListener(this);
		btnnew.addActionListener(this);
		btncheck.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				closeView();
			}
		});
	}
	void dataBook() {
		String query = "select * from tb_book";	
		try {
			stmt = dc.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			String data="";			
			while (rs.next()) {
				data += rs.getString("bookid")+"/"+rs.getString("bookname")
				+"/"+rs.getString("company")+"/"+rs.getString("author")+"\n";
			}
			ta.setText(data);
			rs.close();
			stmt.close();
		} catch (SQLException ee) {
			System.err.println("책추가 처리 실패!!");
		}
	}
	void insertBook(String bookid, String bookname, String company,String author) {
		String query = "insert into tb_book values (null, ?, ?, ?, ?)";
			try {
				PreparedStatement pstmt = dc.prepareStatement(query);
				pstmt.setString(1, bookid);
				pstmt.setString(2, bookname);
				pstmt.setString(3, company);
				pstmt.setString(4, author);
				
				pstmt.executeUpdate();
				pstmt.close();
			} catch (SQLException ee) {
				System.err.println("도서 추가 실패!! : " + ee.toString());
				
			}
			
		}
	void msgDlg(String msg) {
		final Dialog dlg = new Dialog(this, "안내", true);
		
		dlg.setLayout(null);
		
		Button bt = new Button("확인");
		Label lb = new Label(msg);
		
		dlg.add(bt);
		dlg.add(lb);
		bt.setFont(font15);
		lb.setFont(font15);
		bt.setBounds(145,150,100,30);
		lb.setBounds(140,70,150,30);
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
		String query = "select * from tb_book where bookid = ? ";
		
		try {
			PreparedStatement pstmt = dc.prepareStatement(query);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				msgDlg("아이디가 존재합니다.");
				idCheck = true;
			}
			rs.close();
			pstmt.close();
		} catch (SQLException ee) {
			System.err.println("중복 처리 실패!!");
		}
		if(idCheck == false) {
			msgDlg("아이디 추가가능!!");
			btncheck.setEnabled(false);
    		tfid.setEnabled(false);
    		//회원가입시 최종 아이디중복체크 검사여부 변수
    		joinIdFinalCheck =true;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnadd) {
			String bookid = tfid.getText();
			String bookname = tfname.getText();
			String company = tfcom.getText();
			String author = tfauthor.getText();
		
			
			
			
			if(bookid.equals("")) {
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
			
			
			
			insertBook(bookid, bookname, company , author);
		
			joinIdFinalCheck = false;
		msgDlg("추가완료");	
	
		}
		else if(e.getSource() == btnnew) {
			String query = "select * from tb_book";	
			try {
				stmt = dc.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				String data="";			
				while (rs.next()) {
					data += rs.getString("bookid")+"/"+rs.getString("bookname")
					+"/"+rs.getString("company")+"/"+rs.getString("author")+"\n";
				}
				ta.setText(data);
				rs.close();
				stmt.close();
			} catch (SQLException ee) {
				System.err.println("새로고침 처리 실패!!");
			}
		}
		else if(e.getSource() == btncheck) {
			
			String id = tfid.getText();
			if(id.equals("")) {
				msgDlg("아이디를 입력하세요.");
				return;
			}
			idCheck(id);
			
		}
	}
}
