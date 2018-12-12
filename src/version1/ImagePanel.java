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

	// ImagePanel ������
	public ImagePanel() {

		// FlowLayout ����
		// setLayout(new FlowLayout());
		setLayout(new BorderLayout());

		// JoinPanel�� Scroll�ٸ� ����
		scroll = new JScrollPane(joinPanel);
		// ���� �ٸ� ������� �ʵ��� ����
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		// �׻�, ���� �ٸ� ����ϵ��� ����
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		// ImageListPanel�� Size ����
		// scroll.setPreferredSize(new Dimension(500, 400));

		// ButtonPanel ��ü ����
		btnPanel = new BtnPanel();
		// ButtonPanel�� size ����
		btnPanel.setPreferredSize(new Dimension(170, 500));

		// ImagePanel�� ButtonPanel�� scroll �ǳ� �߰�

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

	// ButtonPanel Ŭ����
	class BtnPanel extends JPanel {

		// ButtonPanel ������
		BtnPanel() {
			setLayout(new GridLayout(0, 1));

			// JButton ��ü ����
			addFileBtn = new JButton(filePlusButton);
			deleteFileBtn = new JButton(fileDeleteButton);
			setButtonEvent(addFileBtn,filePlusButton,filePlusEnterButton);
			setButtonEvent(deleteFileBtn,fileDeleteButton,fileDeleteEnterButton);
			setButtonUI(addFileBtn);
			setButtonUI(deleteFileBtn);
			
			setBackground(MainFrame.MAINCOLOR);
			// Box�� ���� �߰� Button �߰�
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

	// ������ Image�� ���� ������ ��� �ִ� Class
	class ImageListPanel extends JPanel {

		ImageListPanel(String imagePath) {
			// Layout ����
			setLayout(new BorderLayout());
			// Label�� �߾� ����
			ImageIcon imageIcon = new ImageIcon(homeDirectory + "\\" + imagePath);
			JLabel imageLabel = new JLabel(imageIcon);
			imageLabel.setPreferredSize(new Dimension(200, 200));
			// Image�� �׷��ִ� Panel �� fileName Panel �߰�

			// f.getName()�� ���� Label ����
			JLabel fileName = new JLabel(imagePath);
			fileName.setHorizontalAlignment(SwingConstants.CENTER);

			add(imageLabel, BorderLayout.CENTER);
			add(fileName, BorderLayout.SOUTH);
		}
	}

	// ��ģ(Image�� filename)Panel�� �׷��ִ� Panel
	class JoinPanel extends JPanel {
		// 200���� �����ϱ� ���� �迭 ����
		ImageListPanel listPanel[] = new ImageListPanel[200];

		// GridLayout���� ����
		JoinPanel() {
			// GridLayout ����
			setLayout(new GridLayout(0, 5, 5, 5));
			// �ݺ����� ���� ImageListPanel�� �������
			for (int j = 0; j < imageList.length; j++) {
				listPanel[j] = new ImageListPanel(imageList[j]);
				add(listPanel[j]);
			}
		}
	}

	// ButtonUI ���� �޼ҵ�
	public void setButtonUI(JButton button) {
		// �ܰ��� ����
		button.setBorderPainted(false);
		// ���� ü��� ����
		button.setContentAreaFilled(false);
		// ��Ŀ�� �Ǿ����� �׵θ� ����
		button.setFocusPainted(false);
	}
	
	// ButtonEvent ���� �޼ҵ�
		public void setButtonEvent(JButton button, ImageIcon basicImage, ImageIcon enterImage) {
			button.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					// Entered�̹����� ���� �����ش�.
					button.setIcon(enterImage);
					// Ŀ���� ����� �ٲ��ش�
					button.setCursor(new Cursor(Cursor.HAND_CURSOR));
					// ȿ���� ���
				}

				// ���콺�� ��ư�� �������� �̺�Ʈ ó��
				@Override
				public void mouseExited(MouseEvent e) {
					button.setIcon(basicImage);
					// Ŀ���� ����� �ٲ��ش�
					button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
			});
		}

}
