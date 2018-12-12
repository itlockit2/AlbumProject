package version1;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

// ImagePanel 
public class ImagePanel extends JPanel {
	ImageIcon filePlusButton = new ImageIcon("images/filePlusButton.png");
	ImageIcon filePlusEnterButton = new ImageIcon("images/filePlusEnterButton.png");
	ImageIcon fileDeleteButton = new ImageIcon("images/fileDeleteButton.png");
	ImageIcon fileDeleteEnterButton = new ImageIcon("images/fileDeleteEnterButton.png");

	static String homeDirectory = "C:\\Users\\RNC\\eclipse-workspace\\album\\userImages";
	static File imageFolder = new File(homeDirectory);
	static public String[] imageList = imageFolder.list();

	BtnPanel btnPanel = new BtnPanel();
	JButton addFileBtn;
	JButton deleteFileBtn;
	JScrollPane scroll;
	JoinPanel joinPanel = new JoinPanel();

	// ImagePanel 생성자
	public ImagePanel() {

		// FlowLayout 설정
		// setLayout(new FlowLayout());
		setLayout(new BorderLayout());

		// JoinPanel에 Scroll바를 붙임
		scroll = new JScrollPane(joinPanel);
		// 수평 바를 사용하지 않도록 설정
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		// 항상, 수직 바만 사용하도록 설정
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		// ImageListPanel의 Size 설정
		// scroll.setPreferredSize(new Dimension(500, 400));

		// ButtonPanel 객체 생성
		btnPanel = new BtnPanel();
		// ButtonPanel의 size 설정
		btnPanel.setPreferredSize(new Dimension(170, 500));

		// ImagePanel에 ButtonPanel과 scroll 판넬 추가

		add(scroll, BorderLayout.CENTER);
		add(btnPanel, BorderLayout.EAST);
	}

	public void rePaint() {

		homeDirectory = "C:\\Users\\RNC\\eclipse-workspace\\album\\userImages";
		imageFolder = new File(homeDirectory);
		imageList = imageFolder.list();
		remove(scroll);
		remove(joinPanel);
		joinPanel = new JoinPanel();
		scroll = new JScrollPane(joinPanel);
		add(scroll, BorderLayout.CENTER);
		revalidate();
		repaint();
	}

	// ButtonPanel 클래스
	class BtnPanel extends JPanel {

		// ButtonPanel 생성자
		BtnPanel() {
			setLayout(new GridLayout(0, 1));

			// JButton 객체 생성
			addFileBtn = new JButton(filePlusButton);
			deleteFileBtn = new JButton(fileDeleteButton);
			setButtonEvent(addFileBtn,filePlusButton,filePlusEnterButton);
			setButtonEvent(deleteFileBtn,fileDeleteButton,fileDeleteEnterButton);
			setButtonUI(addFileBtn);
			setButtonUI(deleteFileBtn);
			
			setBackground(MainFrame.MAINCOLOR);
			// Box에 파일 추가 Button 추가
			add(addFileBtn);
			add(deleteFileBtn);
			deleteFileBtn.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					JFileChooser chooser = new JFileChooser();

					chooser.setAcceptAllFileFilterUsed(false);
					FileNameExtensionFilter PNGFilter = new FileNameExtensionFilter("PNG & JPG File", "png", "jpg");
					chooser.addChoosableFileFilter(PNGFilter);

					int ret = chooser.showOpenDialog(null);

					if (ret != JFileChooser.APPROVE_OPTION) {
						JOptionPane.showMessageDialog(null, "Not Choose File Path", "Warning",
								JOptionPane.WARNING_MESSAGE);
						return;
					}

					String filePath = chooser.getSelectedFile().getPath();
					System.out.println(filePath);

					String ChoosedFile = chooser.getSelectedFile().getName();

					fileCopy(filePath, homeDirectory + "\\" + ChoosedFile);
					rePaint();
				}
			});

			addFileBtn.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					JFileChooser chooser = new JFileChooser();

					chooser.setAcceptAllFileFilterUsed(false);
					FileNameExtensionFilter PNGFilter = new FileNameExtensionFilter("PNG & JPG File", "png", "jpg");
					chooser.addChoosableFileFilter(PNGFilter);

					int ret = chooser.showOpenDialog(null);

					if (ret != JFileChooser.APPROVE_OPTION) {
						JOptionPane.showMessageDialog(null, "Not Choose File Path", "Warning",
								JOptionPane.WARNING_MESSAGE);
						return;
					}

					String filePath = chooser.getSelectedFile().getPath();
					System.out.println(filePath);

					String ChoosedFile = chooser.getSelectedFile().getName();

					fileCopy(filePath, homeDirectory + "\\" + ChoosedFile);
					rePaint();
				}
			});
		}

		void fileCopy(String filePath, String outFolder) {
			try {
				FileInputStream fis = new FileInputStream(filePath);
				FileOutputStream fos = new FileOutputStream(outFolder);

				int data = 0;
				while ((data = fis.read()) != -1) {
					fos.write(data);
				}
				fis.close();
				fos.close();

			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}

	// 각각의 Image에 대한 정보를 담고 있는 Class
	class ImageListPanel extends JPanel {

		ImageListPanel(String imagePath) {
			// Layout 설정
			setLayout(new BorderLayout());
			// Label의 중앙 정렬
			ImageIcon imageIcon = new ImageIcon(homeDirectory + "\\" + imagePath);
			JLabel imageLabel = new JLabel(imageIcon);
			imageLabel.setPreferredSize(new Dimension(200, 200));
			// Image를 그려주는 Panel 및 fileName Panel 추가

			// f.getName()을 통해 Label 생성
			JLabel fileName = new JLabel(imagePath);
			fileName.setHorizontalAlignment(SwingConstants.CENTER);

			add(imageLabel, BorderLayout.CENTER);
			add(fileName, BorderLayout.SOUTH);
		}
	}

	// 합친(Image와 filename)Panel을 그려주는 Panel
	class JoinPanel extends JPanel {
		// 200개를 생성하기 위해 배열 생성
		ImageListPanel listPanel[] = new ImageListPanel[200];

		// GridLayout으로 설정
		JoinPanel() {
			// GridLayout 설정
			setLayout(new GridLayout(0, 5, 5, 5));
			// 반복문을 통해 ImageListPanel을 만들어줌
			for (int j = 0; j < imageList.length; j++) {
				listPanel[j] = new ImageListPanel(imageList[j]);
				add(listPanel[j]);
			}
		}
	}

	// ButtonUI 설정 메소드
	public void setButtonUI(JButton button) {
		// 외곽선 제거
		button.setBorderPainted(false);
		// 내용 체우기 제거
		button.setContentAreaFilled(false);
		// 포커스 되었을시 테두리 제거
		button.setFocusPainted(false);
	}
	
	// ButtonEvent 설정 메소드
		public void setButtonEvent(JButton button, ImageIcon basicImage, ImageIcon enterImage) {
			button.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					// Entered이미지로 변경 시켜준다.
					button.setIcon(enterImage);
					// 커서의 모양을 바꿔준다
					button.setCursor(new Cursor(Cursor.HAND_CURSOR));
					// 효과음 재생
				}

				// 마우스가 버튼에 나갔을때 이벤트 처리
				@Override
				public void mouseExited(MouseEvent e) {
					button.setIcon(basicImage);
					// 커서의 모양을 바꿔준다
					button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
			});
		}

}
