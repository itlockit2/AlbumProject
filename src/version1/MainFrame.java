package version1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.font.TextAttribute;
import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class MainFrame extends JFrame {

	// Frame에 추가 할 menubarPanel
	JPanel menubarPanel;
	// Frame에 추가 할 JTabbedPane
	JTabbedPane pane;
	// mouse의 X, Y 좌표
	int mouseX;
	int mouseY;
	static Color MAINCOLOR = new Color(251, 132, 136);
	static Color SECOND_COLOR = new Color(255, 204, 204);
	static Font mainTitleFont;
	static Font subTitleFont;

	public static void changeSecondColor(Color color) {
		SECOND_COLOR = color;
	}

	public MainFrame() {

		// local file 에서 폰트를 불러와서 설정
		try {
			mainTitleFont = Font
					.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("font/Maplestory_Bold.ttf")))
					.deriveFont(Font.BOLD, 40);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error");
		}

		// local file 에서 폰트를 불러와서 설정
		try {
			subTitleFont = Font
					.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("font/Maplestory_Bold.ttf")))
					.deriveFont(Font.BOLD, 30);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error");
		}
		Container c = getContentPane();
		// Frame의 Title 설정
		setTitle("Year Album");
		// JFrame 종료 시 응용 프로그램도 종료되도록 함
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 화면 크기를 임의로 변경하지 못하게 설정
		setResizable(true);
		// 메뉴바가 보이지 않게끔 설정
		setUndecorated(false);
		// menubarPanel 객체 생성
		menubarPanel = new MenubarPanel();
		// menubar에 대한 Mouse Event 처리
		menubarPanel.addMouseListener(new MouseAdapter() {
			// 마우스를 입력했을때 컴포넌트내의 마우스의 x좌표와 y좌표를 가져온다
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		// menuBar를 드래그 했을때 이벤트 처리를 해준다.
		menubarPanel.addMouseMotionListener(new MouseMotionAdapter() {
			// 마우스를 입력했을때 스크린(모니터)내의 마우스의 x좌표와 y좌표를 가져온다
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				// 스크린내의 마우스의 좌표와 컴포넌트내의 마우스의 좌표의 차가 게임창의 위치이다.
				setLocation(x - mouseX, y - mouseY);
			}
		});

		// JTabbedPane 추가
		pane = new TabbedPane();

		// Frame에 menubarPanel 추가
		add(menubarPanel, BorderLayout.NORTH);
		// Frame에 JTabbedPane 추가
		add(pane, BorderLayout.CENTER);
		setSize(1366, 768);
		setLocationRelativeTo(null);
		setResizable(true);
		// 메뉴바가 보이지 않게끔 설정
		setUndecorated(true);
		setVisible(true);

	}

	public static void main(String args[]) {

		// 안티엘리어싱 설정
		System.setProperty("awt.useSystemAAFontSettings", "on");
		System.setProperty("swing.aatext", "true");
		new MainFrame();
	}

}
