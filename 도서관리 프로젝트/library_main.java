package 자바수업17일차_도서관리;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class librarymain extends Frame implements ActionListener{
	String url = "jdbc:mysql://localhost:3306/java_db?useUnicode=true&characterEncoding=utf8";
	String id = "root";
	String pass = "qwer";
	private Connection dc;
	Font font15 = new Font("TimesRoman",Font.BOLD,15);
	Font font20 = new Font("TimesRoman",Font.BOLD,20);
	Label lbTitle = new Label("도서관 프로그램");
	
	Button btnMydata = new Button("내정보관리");
	Button btnBookadd = new Button("도서등록");
	Button btnBooklend = new Button("도서대여");
	Button btnBookSearch = new Button("도서검색");
	Button btnBookDelete = new Button("도서삭제");
	Button btnBookreturn = new Button("도서반납");
	
	
	public librarymain() {
		super("도서관");
		this.setSize(300,470);
		this.init();
		this.start();
		this.setLocation(1000,100);
		this.setVisible(true);
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch (ClassNotFoundException ee) {
			return;
		}
		try {
			dc = DriverManager.getConnection(url, id, pass);
		} catch (SQLException ee) {
		}
		
	}
	void init() {
		setLayout(null);
		this.add(lbTitle);
		lbTitle.setBounds(90,60,120,30);
		lbTitle.setFont(font15);
	
		this.add(btnMydata);
		btnMydata.setBounds(100,120,100,30);
		btnMydata.setFont(font15);
		this.add(btnBookadd);
		btnBookadd.setBounds(100,170,100,30);
		btnBookadd.setFont(font15);
		this.add(btnBookSearch);
		btnBookSearch.setBounds(100,220,100,30);
		btnBookSearch.setFont(font15);
		this.add(btnBookDelete);
		btnBookDelete.setBounds(100,270,100,30);
		btnBookDelete.setFont(font15);
		this.add(btnBooklend);
		btnBooklend.setBounds(100,320,100,30);
		btnBooklend.setFont(font15);
		this.add(btnBookreturn);
		btnBookreturn.setBounds(100,370,100,30);
		btnBookreturn.setFont(font15);
		
	}
	void start() {
		btnMydata.addActionListener(this);
		btnBookadd.addActionListener(this);
		btnBooklend.addActionListener(this);
		btnBookSearch.addActionListener(this);
		btnBookDelete.addActionListener(this);
		btnBookreturn.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
		
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnMydata) {
			winMain wMain = new winMain();
		}
		else if(e.getSource() == btnBookadd) {
			Bookadd bkadd = new Bookadd();
		}
		else if(e.getSource() == btnBookSearch) {
			booksearch bks = new booksearch();
		}
		else if(e.getSource() == btnBookDelete) {
			bookDelete bkdel = new bookDelete();
		}
		else if(e.getSource() == btnBooklend) {
			booklend bklend = new booklend();
		}
		else if(e.getSource() == btnBookreturn) {
			bookreturn bkreturn = new bookreturn();
		}
	}
	void closeView() {
		this.setVisible(false);
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
		lb.setBounds(100,70,250,30);
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
	
}
