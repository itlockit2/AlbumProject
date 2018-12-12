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

	// Frame�� �߰� �� menubarPanel
	JPanel menubarPanel;
	// Frame�� �߰� �� JTabbedPane
	JTabbedPane pane;
	// mouse�� X, Y ��ǥ
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

		// local file ���� ��Ʈ�� �ҷ��ͼ� ����
		try {
			mainTitleFont = Font
					.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("font/Maplestory_Bold.ttf")))
					.deriveFont(Font.BOLD, 40);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error");
		}

		// local file ���� ��Ʈ�� �ҷ��ͼ� ����
		try {
			subTitleFont = Font
					.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("font/Maplestory_Bold.ttf")))
					.deriveFont(Font.BOLD, 30);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error");
		}
		Container c = getContentPane();
		// Frame�� Title ����
		setTitle("Year Album");
		// JFrame ���� �� ���� ���α׷��� ����ǵ��� ��
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// ȭ�� ũ�⸦ ���Ƿ� �������� ���ϰ� ����
		setResizable(true);
		// �޴��ٰ� ������ �ʰԲ� ����
		setUndecorated(false);
		// menubarPanel ��ü ����
		menubarPanel = new MenubarPanel();
		// menubar�� ���� Mouse Event ó��
		menubarPanel.addMouseListener(new MouseAdapter() {
			// ���콺�� �Է������� ������Ʈ���� ���콺�� x��ǥ�� y��ǥ�� �����´�
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		// menuBar�� �巡�� ������ �̺�Ʈ ó���� ���ش�.
		menubarPanel.addMouseMotionListener(new MouseMotionAdapter() {
			// ���콺�� �Է������� ��ũ��(�����)���� ���콺�� x��ǥ�� y��ǥ�� �����´�
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				// ��ũ������ ���콺�� ��ǥ�� ������Ʈ���� ���콺�� ��ǥ�� ���� ����â�� ��ġ�̴�.
				setLocation(x - mouseX, y - mouseY);
			}
		});

		// JTabbedPane �߰�
		pane = new TabbedPane();

		// Frame�� menubarPanel �߰�
		add(menubarPanel, BorderLayout.NORTH);
		// Frame�� JTabbedPane �߰�
		add(pane, BorderLayout.CENTER);
		setSize(1366, 768);
		setLocationRelativeTo(null);
		setResizable(true);
		// �޴��ٰ� ������ �ʰԲ� ����
		setUndecorated(true);
		setVisible(true);

	}

	public static void main(String args[]) {

		// ��Ƽ������� ����
		System.setProperty("awt.useSystemAAFontSettings", "on");
		System.setProperty("swing.aatext", "true");
		new MainFrame();
	}

}
