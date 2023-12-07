package 자바수업17일차_도서관리;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Identity;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class login_system {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		Login l = new Login();
	}

}
class winMain extends Frame implements ActionListener{
	
	
	Font font = new Font("TimesRoman",Font.BOLD,20);
	Font font2 = new Font("TimesRoman",Font.BOLD,30);
	Label lbWin = new Label("메인화면");
	Button btnList = new Button("회원리스트");
	Button btnSearch = new Button("회원검색");
	Button btnMod = new Button("회원수정");
	Button btnDelete = new Button("회원삭제");
	Button btnClose = new Button("닫기");
	public winMain() {
		super("메인창");
		setSize(400,500);
		this.init();
		this.start();
		
		setLocation(1000,100);
		setVisible(true);
	}
	void init() {
		setLayout(null);
		
		this.add(lbWin);
		lbWin.setBounds(140,50,150,40);
		lbWin.setFont(font2);
		this.add(btnList);
		btnList.setBounds(110,120,180,40);
		btnList.setFont(font);
		this.add(btnSearch);
		btnSearch.setBounds(110,170,180,40);
		btnSearch.setFont(font);
		this.add(btnMod);
		btnMod.setBounds(110,220,180,40);
		btnMod.setFont(font);
		this.add(btnDelete);
		btnDelete.setBounds(110,270,180,40);
		btnDelete.setFont(font);
		this.add(btnClose);
		btnClose.setBounds(150,370,100,40);
		btnClose.setFont(font);
		
	}
	void closeView() {
		this.setVisible(false);
	}
	void start() {
		btnClose.addActionListener(this);
		btnDelete.addActionListener(this);
		btnList.addActionListener(this);
		btnMod.addActionListener(this);
		btnSearch.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				closeView();
			}
		});
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnList) {
			winList wl = new winList();
			
		}
		else if(e.getSource() == btnSearch) {
		
			Search s = new Search();
		}
		else if(e.getSource() == btnMod) {
			
			Mod m = new Mod();
		}
		else if(e.getSource() == btnDelete) {
		
			Delete d = new Delete();
		}
		else if(e.getSource() == btnClose) {
			closeView();
		}
		
	}
}
class winList extends Frame implements ActionListener{
	String url = "jdbc:mysql://localhost:3306/java_db?useUnicode=true&characterEncoding=utf8";
	String id = "root";
	String pass = "qwer";
	private Connection dc;
	Statement stmt = null;
	Font font = new Font("TimesRoman",Font.BOLD,20);
	Font font15 = new Font("TimesRoman",Font.BOLD,15);
	Label lbList = new Label("전체회원리스트");
	Button btnBack= new Button("뒤로가기");
	Button btnInsert= new Button("회원등록");
	TextArea txtArea = new TextArea();
	public winList() {
		super("전체회원리스트창");
		setSize(500,550);
		this.init2();
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
		dataMember();
	}
	void init2() {
		this.setLayout(null);
		this.add(lbList);
		lbList.setBounds(160,50,150,40);
		lbList.setFont(font);
		this.add(txtArea);
		txtArea.setBounds(45,110,400,350);
		txtArea.setFont(font15);
		this.add(btnBack);
		btnBack.setBounds(190,480,100,40);
		btnBack.setFont(font);
		this.add(btnInsert);
		btnInsert.setBounds(330,60,100,30);
	}
	void dataMember() {
		String query = "select * from tb_info";	
		try {
			stmt = dc.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			String data="";			
			while (rs.next()) {
				data += rs.getString("id")+"/"+rs.getString("nick")
				+"/"+rs.getString("name")+"/"+rs.getString("hp")+"\n";
			}
			txtArea.setText(data);
			rs.close();
			stmt.close();
		} catch (SQLException ee) {
			System.err.println("login 처리 실패!!");
		}
	}
	
	void closeView() {
		this.setVisible(false);
	}
	void start() {
		btnBack.addActionListener(this);
		btnInsert.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				closeView();
			}
		});
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnBack) {
			dispose();
			winMain w = new winMain();
		}
		else if(e.getSource() == btnInsert) {
			dispose();
			join join = new join();
		}
		
	}
	
}
class Login extends Frame implements ActionListener{
	String url = "jdbc:mysql://localhost:3306/java_db?useUnicode=true&characterEncoding=utf8";
	String id = "root";
	String pass = "qwer";
	int  memberCnt;
	private Connection dc;
	Font font = new Font("TimesRoman",Font.BOLD,20);
	Font font10 = new Font("TimesRoman",Font.BOLD,10);
	Font font15 = new Font("TimesRoman",Font.BOLD,15);
	Label lbTitle = new Label("로그인");
	Label lbId = new Label("아이디:");
	Label lbPw = new Label("패스워드 :");
	TextField textId = new TextField();
	TextField textPw = new TextField();
	Button btnLogin = new Button("로그인");
	Button btnNew = new Button("회원가입");
	public Login() {
		super("로그인창");
		setSize(300,400);
		this.init3();
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
	}
	void init3(){
		setLayout(null);
		this.add(lbTitle);
		lbTitle.setBounds(110,50,100,40);
		lbTitle.setFont(font);
		this.add(lbId);
		lbId.setBounds(50,120,50,30);
		this.add(lbPw);
		lbPw.setBounds(50,170,70,30);
		this.add(btnLogin);
		btnLogin.setBounds(110,300,80,40);
		this.add(btnNew);
		btnNew.setBounds(110,250,80,40);
		this.add(textId);
		textId.setBounds(120,120,100,30);
		this.add(textPw);
		textPw.setBounds(120,170,100,30);
		textPw.setEchoChar('*');
	
	
	}
	void closeView() {
				this.setVisible(false);
	}
	void start() {
		btnLogin.addActionListener(this);
		btnNew.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	    
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnLogin) {
			
			String id = textId.getText();
			String pw = textPw.getText();
			if(textId.getText().equals("")){
				msgDlg("아이디를입력하시오.");
				return;
			}
			if(textPw.getText().equals("")){
				msgDlg("패스워드를입력하시오.");
				return;
			}
			loginMember(id, pw);
			
			
			
		}
		
		else if(e.getSource() == btnNew) {
			
			join j = new join();
		}
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
	public	void loginMember(String loginid, String pw) {
		boolean loginCheck = false;
		String query = "select * from tb_info where id = ? and pw = ?";
		try {
			PreparedStatement pstmt = dc.prepareStatement(query);
			pstmt.setString(1, loginid);
			pstmt.setString(2, pw);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				msgDlg("로그인성공!!!");
				librarymain lib = new librarymain();
				this.setVisible(false);
				loginCheck = true;
			}
			rs.close();
			pstmt.close();
		} catch (SQLException ee) {
			System.err.println("login 처리 실패!!");
		}
		if(loginCheck == false) {
			msgDlg("로그인실패!");
		}
	
	}

}

class Search extends Frame implements ActionListener{
	String url = "jdbc:mysql://localhost:3306/java_db?useUnicode=true&characterEncoding=utf8";
	String id = "root";
	String pass = "qwer";
	private Connection dc;

	
	Font font = new Font("TimesRoman",Font.BOLD,20);
	Font font10 = new Font("TimesRoman",Font.BOLD,10);
	Label lbTitle = new Label("회원검색");
	Label lbId = new Label("아이디:");
	Label lbName = new Label("이름:");
	Label lbNick = new Label("닉네임:");
	Label lbPh = new Label("연락처:");
	
	TextField tfId = new TextField();
	TextField tfNick = new TextField();
	TextField tfName = new TextField();
	TextField tfPh = new TextField();
	
	Button btnSearch = new Button("찾기");
	Button btnClose = new Button("닫기");
	public Search() {
		super("회원검색창");
		setSize(300,500);
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
	}
	void closeView() {
		this.setVisible(false);
	}
	void start() {
		btnClose.addActionListener(this);
		btnSearch.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				closeView();
			}
		});
	}
	void init4() {
		setLayout(null);
		this.add(lbTitle);
		lbTitle.setBounds(110,50,100,40);
		lbTitle.setFont(font);
		this.add(lbId);
		lbId.setBounds(50,120,50,30);
		this.add(lbNick);
		lbNick.setBounds(50,220,70,30);
		this.add(lbName);
		lbName.setBounds(50,270,70,30);
		this.add(lbPh);
		lbPh.setBounds(50,320,70,30);
		
		this.add(btnSearch);
		btnSearch.setBounds(220,120,50,30);
		this.add(btnClose);
		btnClose.setBounds(120,450,80,40);
		
		this.add(tfId);
		tfId.setBounds(120,120,100,30);
		this.add(tfNick);
		tfNick.setBounds(120,220,100,30);
		this.add(tfName);
		tfName.setBounds(120,270,100,30);
		this.add(tfPh);
		tfPh.setBounds(120,320,100,30);
		
	}
	void msgDlg(String msg) {
		final Dialog dlg = new Dialog(this, "OK", true);
		Button bt = new Button("확인");
		Label lb = new Label(msg);
		dlg.setLayout(null);
		dlg.add(bt);
		dlg.add(lb);
		bt.setFont(font10);
		lb.setFont(font10);
		bt.setBounds(140,150,100,30);
		lb.setBounds(80,70,150,30);
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
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == btnSearch) {
			String searchid = tfId.getText();
			if(searchid.equals("")) {
				msgDlg("검색아이디를 입력하세요.");
				return;
			}
			boolean idCheck = false;
			String id = tfId.getText();
			
			
			String query = "select * from tb_info where id = ? ";
			
			try {
				PreparedStatement pstmt = dc.prepareStatement(query);
				pstmt.setString(1, id);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					tfNick.setText(rs.getString("nick"));
	        		tfName.setText(rs.getString("name"));
	        		tfPh.setText(rs.getString("hp"));	
					
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
	}
}
class Mod extends Frame implements ActionListener{
	String url = "jdbc:mysql://localhost:3306/java_db?useUnicode=true&characterEncoding=utf8";
	String id = "root";
	String pass = "qwer";
	private Connection dc;
	
	int editIndex;
	String tempData[][]= new String[100][5];
	int cnt = 0;
	Font font = new Font("TimesRoman",Font.BOLD,20);
	Font font10 = new Font("TimesRoman",Font.BOLD,10);
	Label lbTitle = new Label("회원수정");
	Label lbId = new Label("아이디:");
	Label lbPw = new Label("비밀번호:");
	Label lbName = new Label("이름:");
	Label lbNick = new Label("닉네임:");
	Label lbPh = new Label("연락처:");
	
	TextField tfId = new TextField();
	TextField tfPw = new TextField();
	TextField tfNick = new TextField();
	TextField tfName = new TextField();
	TextField tfPh = new TextField();
	
	Button btnSearch = new Button("찾기");
	Button btnSuc = new Button("수정완료");
	Button btnClose = new Button("닫기");
	public Mod() {
		super("회원수정창");
		setSize(300,500);
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
	}
	void closeView() {
		this.setVisible(false);
	}
	void start() {
		btnClose.addActionListener(this);
		btnSearch.addActionListener(this);
		btnSuc.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				closeView();
			}
		});
	}
	void init4() {
		setLayout(null);
		this.add(lbTitle);
		lbTitle.setBounds(110,50,100,40);
		lbTitle.setFont(font);
		this.add(lbId);
		lbId.setBounds(50,120,50,30);
		this.add(lbPw);
		lbPw.setBounds(50,220,50,30);
		this.add(lbNick);
		lbNick.setBounds(50,270,70,30);
		this.add(lbName);
		lbName.setBounds(50,320,70,30);
		this.add(lbPh);
		lbPh.setBounds(50,370,70,30);
		
		this.add(btnSearch);
		btnSearch.setBounds(220,120,50,30);
		this.add(btnSuc);
		btnSuc.setBounds(120,400,80,40);
		this.add(btnClose);
		btnClose.setBounds(120,450,80,40);
		
		this.add(tfId);
		tfId.setBounds(120,120,100,30);
		this.add(tfPw);
		tfPw.setBounds(120,220,100,30);
		this.add(tfNick);
		tfNick.setBounds(120,270,100,30);
		this.add(tfName);
		tfName.setBounds(120,320,100,30);
		this.add(tfPh);
		tfPh.setBounds(120,370,100,30);
		
	}
	void msgDlg(String msg) {
		final Dialog dlg = new Dialog(this, "OK", true);
		Button bt = new Button("확인");
		Label lb = new Label(msg);
		dlg.setLayout(null);
		dlg.add(bt);
		dlg.add(lb);
		bt.setFont(font10);
		lb.setFont(font10);
		bt.setBounds(140,150,100,30);
		lb.setBounds(80,70,150,30);
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
	void seach() {
		String searchid = tfId.getText();
		if(searchid.equals("")) {
			msgDlg("검색아이디를 입력하세요.");
			return;
		}
		boolean idCheck = false;
		String id = tfId.getText();
		String query = "select * from tb_info where id = ? ";
		
		try {
			PreparedStatement pstmt = dc.prepareStatement(query);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				tfNick.setText(rs.getString("nick"));
        		tfName.setText(rs.getString("name"));
        		tfPh.setText(rs.getString("hp"));	
				
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
	void editMember() {
		String id = tfId.getText();
		String pw = tfPw.getText();
		String nick = tfNick.getText();
		String name = tfName.getText();
		String hp = tfPh.getText();
		if(id.equals("")){msgDlg("아이디를 입력하세요.");return;	}
		if(pw.equals("")){msgDlg("패스워드를 입력하세요.");return; }
		if(nick.equals("")){msgDlg("닉네임을 입력하세요.");return; }
		if(name.equals("")){msgDlg("이름를 입력하세요.");return; }
		if(hp.equals("")){msgDlg("연락처를 입력하세요.");return; }
		String query = "update tb_info set pw = ?, nick = ? ,name = ? , hp = ? where id = ?";
		try {
			PreparedStatement pstmt = dc.prepareStatement(query);
			pstmt.setString(1, pw);
			pstmt.setString(2, nick);
			pstmt.setString(3, name);
			pstmt.setString(4, hp);
			pstmt.setString(5, id);
			
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException ee) {
			System.err.println("수정실패!!!");
			
		}
		tfId.setText("");
		tfPw.setText("");
		tfNick.setText("");
		tfName.setText("");
		tfPh.setText("");	
		msgDlg("정보수정완료!");  
		
		tfId.setEnabled(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == btnSearch) {
			seach();
		}
		else if(e.getSource() == btnSuc) {
			editMember();
		}
			
		
		else if(e.getSource() == btnClose) {
			closeView();
		}
	}
}
class Delete extends Frame implements ActionListener{
	String url = "jdbc:mysql://localhost:3306/java_db?useUnicode=true&characterEncoding=utf8";
	String id = "root";
	String pass = "qwer";
	private Connection dc;
	
	int cnt = 0;
	int delindex;
	String tempData[][] = new String[100][5];
	Font font = new Font("TimesRoman",Font.BOLD,20);
	Font font10 = new Font("TimesRoman",Font.BOLD,10);
	Label lbTitle = new Label("회원삭제");
	Label lbId = new Label("아이디:");
	Label lbName = new Label("이름:");
	Label lbNick = new Label("닉네임:");
	Label lbPh = new Label("연락처:");
	
	TextField tfId = new TextField();
	TextField tfNick = new TextField();
	TextField tfName = new TextField();
	TextField tfPh = new TextField();
	
	Button btnSearch = new Button("찾기");
	Button btnDelete = new Button("삭제하기");
	Button btnClose = new Button("닫기");
	public Delete() {
		super("회원삭제창");
		setSize(300,500);
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
	}
	void closeView() {
		this.setVisible(false);
	}
	void start() {
		btnClose.addActionListener(this);
		btnSearch.addActionListener(this);
		btnDelete.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				closeView();
			}
		});
	}
	void init4() {
		setLayout(null);
		this.add(lbTitle);
		lbTitle.setBounds(110,50,100,40);
		lbTitle.setFont(font);
		this.add(lbId);
		lbId.setBounds(50,120,50,30);
		this.add(lbNick);
		lbNick.setBounds(50,220,70,30);
		this.add(lbName);
		lbName.setBounds(50,270,70,30);
		this.add(lbPh);
		lbPh.setBounds(50,320,70,30);
		
		this.add(btnSearch);
		btnSearch.setBounds(220,120,50,30);
		this.add(btnDelete);
		btnDelete.setBounds(120,400,80,40);
		this.add(btnClose);
		btnClose.setBounds(120,450,80,40);
		
		this.add(tfId);
		tfId.setBounds(120,120,100,30);
		this.add(tfNick);
		tfNick.setBounds(120,220,100,30);
		this.add(tfName);
		tfName.setBounds(120,270,100,30);
		this.add(tfPh);
		tfPh.setBounds(120,320,100,30);
		
	}

	void deleteMember() {
		String id = tfId.getText();
		if(id.equals("")){
			msgDlg("삭제아이디를 입력하세요.");
			return;	
		}
		String query = "delete from tb_info where id = ?";
		try {
			PreparedStatement pstmt = dc.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException ee) {
			System.err.println("회원 삭제 실패!!");
			
		}
		msgDlg("삭제완료!");
		tfId.setText("");
		tfNick.setText("");
		tfName.setText("");
		tfPh.setText("");
		
	}
	void seach() {
		String searchid = tfId.getText();
		if(searchid.equals("")) {
			msgDlg("검색아이디를 입력하세요.");
			return;
		}
		boolean idCheck = false;
		String id = tfId.getText();
		
		
		String query = "select * from tb_info where id = ? ";
		
		try {
			PreparedStatement pstmt = dc.prepareStatement(query);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				tfNick.setText(rs.getString("nick"));
        		tfName.setText(rs.getString("name"));
        		tfPh.setText(rs.getString("hp"));	
				
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
	
	void msgDlg(String msg) {
		final Dialog dlg = new Dialog(this, "OK", true);
		Button bt = new Button("확인");
		Label lb = new Label(msg);
		dlg.setLayout(null);
		dlg.add(bt);
		dlg.add(lb);
		bt.setFont(font10);
		lb.setFont(font10);
		bt.setBounds(140,150,100,30);
		lb.setBounds(80,70,150,30);
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
	



			
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnClose) { 
				closeView();
			}

		else if(e.getSource() == btnSearch) { 
				seach(); 
			}
		else if(e.getSource() == btnDelete)  { 
			 	deleteMember();
			}
		}
	}
class join extends Frame implements ActionListener{
	private Connection dc;
	String url = "jdbc:mysql://localhost:3306/java_db?useUnicode=true&characterEncoding=utf8";
	String id = "root";
	String pass = "qwer";
	Font font30 = new Font("TimesRoman",Font.BOLD,30);
	Font font25 = new Font("TimesRoman",Font.BOLD,25);
	Font font10 = new Font("TimesRoman",Font.BOLD,10);
	Font font15 = new Font("TimesRoman",Font.BOLD,15);
	Label lbTitle = new Label("회원가입");
	Label lbId = new Label("아이디:");
	Label lbPw = new Label("패스워드 :");
	Label lbName = new Label("이름 :");
	Label lbNic = new Label("닉네임 :");
	Label lbNum = new Label("연락처(PH) :");
	Button btJoin = new Button("회원가입");
	Button btCheck = new Button("중복체크");
	TextField textId = new TextField();
	TextField textPw = new TextField();
	TextField textName = new TextField();
	TextField textNick = new TextField();
	TextField textNum = new TextField();	

	int memberCnt = 0;
	boolean joinIdFinalCheck = false;
	
	join(){
		
		super("회원가입창");
		this.setSize(500,600);
		init();
		start();
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
		this.setLayout(null);
		this.add(lbTitle);
		lbTitle.setBounds(190,50,200,30);
		lbTitle.setFont(font30);
		this.add(lbId);
		lbId.setBounds(120,170,50,30);
		this.add(lbPw);
		lbPw.setBounds(120,230,70,30);
		this.add(lbNic);
		lbNic.setBounds(120,290,70,30);
		this.add(lbName);
		lbName.setBounds(120,350,70,30);
		this.add(lbNum);
		lbNum.setBounds(120,410,70,30);
	
		this.add(btJoin);
		btJoin.setBounds(190,520,120,40);
		this.add(btCheck);
		btCheck.setBounds(365,170,70,30);
		this.add(textId);
		textId.setBounds(195,165,150,40);
		this.add(textPw);
		textPw.setBounds(195,225,150,40);
		this.add(textName);
		textName.setBounds(195,285,150,40);
		this.add(textNick);
		textNick.setBounds(195,345,150,40);
		this.add(textNum);
		textNum.setBounds(195,405,150,40);
		
		textPw.setEchoChar('*');
	}
	void closeView() {
		this.setVisible(false);
	}
	void start() {
		btJoin.addActionListener(this);
		btCheck.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				closeView();
			}
		});
	}
	void idCheck(String id) {
		boolean idCheck  = false;
		String query = "select * from tb_INFO where id = ? ";
		
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
			System.err.println("login 처리 실패!!");
		}
		if(idCheck == false) {
			msgDlg("아이디 생성가능!!");
			btCheck.setEnabled(false);
    		textId.setEnabled(false);
    		//회원가입시 최종 아이디중복체크 검사여부 변수
    		joinIdFinalCheck =true;
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
void insertMember(String joinid, String pw, String nick,String name,String hp) {
	String query = "insert into tb_info values (null, ?, ?, ?, ?,?)";
		try {
			PreparedStatement pstmt = dc.prepareStatement(query);
			pstmt.setString(1, joinid);
			pstmt.setString(2, pw);
			pstmt.setString(3, nick);
			pstmt.setString(4, name);
			pstmt.setString(5, hp);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException ee) {
			System.err.println("회원 가입 실패!! : " + ee.toString());
			
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btJoin) {
			String id = textId.getText();
			String pw = textPw.getText();
			String name = textName.getText();
			String nick = textNick.getText();
			String num = textNum.getText();
			
			
			
			if(id.equals("")) {
				msgDlg("아이디를 입력하세요.");
				return;
			}
			if(pw.equals("")) {
				msgDlg("패스워드를 입력하세요.");
				return;
			}
			if(nick.equals("")) {
				msgDlg("닉네임을 입력하세요.");
				return;
			}
			if(name.equals("")) {
				msgDlg("이름을 입력하세요.");
				return;
			}
			if(num.equals("")) {
				msgDlg("연락처를 입력하세요.");
				return;
			}
			
//			if(joinIdFinalCheck == false){
//				msgDlg("중복체크하세요.");
//					return;
//			}
			
			insertMember(id, pw, nick, name, num);
		
		joinIdFinalCheck = false;	
		msgDlg("가입완료");	
		textId.setText("");
		textPw.setText("");
		textName.setText("");
		textNick.setText("");
		textNum.setText("");


	}
		else if(e.getSource() == btCheck) {
			
			String id = textId.getText();
			if(id.equals("")) {
				msgDlg("아이디를 입력하세요.");
				return;
			}
			idCheck(id);
			
		}
		
	}
}

