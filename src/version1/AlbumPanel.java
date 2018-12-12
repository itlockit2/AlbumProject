package version1;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.util.Map;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;


public class AlbumPanel extends JPanel {

	// ���� ��ư �⺻ �̹���
	ImageIcon leftButtonBasicImage = new ImageIcon("images/leftButtonBasicImage.png");
	// ���� ��ư Enter �̹���
	ImageIcon leftButtonEnterImage = new ImageIcon("images/leftButtonEnterImage.png");
	// ������ ��ư �⺻ �̹���
	ImageIcon rightButtonBasicImage = new ImageIcon("images/rightButtonBasicImage.png");
	// ������ ��ư Enter �̹���
	ImageIcon rightButtonEnterImage = new ImageIcon("images/rightButtonEnterImage.png");
	ImageIcon mainTitleButtonImage = new ImageIcon("images/mainTitleButton.png");
	ImageIcon mainTitleButtonEnterImage = new ImageIcon("images/mainTitleEnterButton.png");
	ImageIcon changeBackgroundButtonImage = new ImageIcon("images/changeBackgroundButton.png");
	ImageIcon changeBackgroundEnterImage = new ImageIcon("images/changeBackgroundEnterButton.png");
	ImageIcon subTitleButtonImage = new ImageIcon("images/subtitleBasicButton.png");
	ImageIcon subTitleEnterImage = new ImageIcon("images/subtitleEmterButton.png");

	// CardLayout�� ���� ������ Panel
	JPanel firstPanel;
	JPanel secondPanel;
	JPanel thirdPanel;
	// CardLayout ��ü ����
	CardLayout card = new CardLayout();
	JPanel cardPanel;
	int coverImageXPoint[][] = new int[4][19];
	int coverImageYPoint[][] = new int[4][19];
	String randomImage[];
	
	String mainTitle = "�� ���� �Դϴ�.";
	String subTitle = "�� ���� �Դϴ�.";
	

	public AlbumPanel() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 19; j++) {
				coverImageXPoint[i][j] = 10 + j * 50;
				coverImageYPoint[i][j] = 440 + (i * 50);
			}
		}
		// BorderLayout ����
		setLayout(new BorderLayout());
		// cardLayout ���̾ƿ��� ���� ��ü ����
		cardPanel = new cardPanel();
		// cardPanel�� �߰�, BorderLayout CENTER
		add(cardPanel, BorderLayout.CENTER);
	}
	public void rePaint() {
		remove(cardPanel);
		cardPanel = new cardPanel();
		add(cardPanel, BorderLayout.CENTER);
		repaint();
		revalidate();
	}

	// cardLayout�� ���� cardPanel Ŭ����
	class cardPanel extends JPanel {

		cardPanel() {
			// CardLayout ����
			setLayout(card);

			// ������ Panel ��ü ����
			firstPanel = new FirstPanel();
			secondPanel = new SecondPanel();
			thirdPanel = new ThirdPanel();

			// CardLayout�� Panel �߰�
			add(firstPanel, "first");
			add(secondPanel, "second");
			add(thirdPanel, "third");
		}
	}

	// ù ��° ȭ��
	class FirstPanel extends JPanel {
		JLabel mainTitleLabel = new JLabel(mainTitle);
		JLabel subTitleLabel = new JLabel(subTitle);
		FirstContentPanel mainPanel = new FirstContentPanel();

		FirstPanel() {
			mainTitleLabel.setFont(MainFrame.mainTitleFont);
			Font font = mainTitleLabel.getFont();
			Map attributes = font.getAttributes();
			attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
			mainTitleLabel.setFont(font.deriveFont(attributes));

			subTitleLabel.setFont(MainFrame.subTitleFont);
			subTitleLabel.setLocation(80, 180);
			// Button�� ��ġ�� �������� �����Ƿ� layout�� null�� ����
			setLayout(new BorderLayout());
			RightButtonPanel rightButtonPanel = new RightButtonPanel();
			LeftButtonPanel leftButtonPanel = new LeftButtonPanel();
			mainPanel = new FirstContentPanel();
			// ButtonUI ����
			add(leftButtonPanel, BorderLayout.WEST);
			add(mainPanel, BorderLayout.CENTER);
			add(rightButtonPanel, BorderLayout.EAST);
		}

		class FirstContentPanel extends JPanel {
			public FirstContentPanel() {
				setLayout(null);
				setBackground(MainFrame.SECOND_COLOR);
				add(mainTitleLabel);
				add(subTitleLabel);
				mainTitleLabel.setBounds(78, 252, 1366, 40);
				subTitleLabel.setBounds(80, 319, 1366, 30);
				Border border = BorderFactory.createLineBorder(Color.WHITE, 3);
				setBorder(border);

				int imageSize = ImagePanel.imageList.length;
				randomImage = new String[imageSize];

				Random r = new Random(System.currentTimeMillis());
				for(int i = 0 ; i < imageSize; i++) {
					randomImage[i] = ImagePanel.imageList[i];
				}
				for (int i = 0; i < imageSize; i++) {
					
					int index1 = r.nextInt(imageSize);
					int index2 = r.nextInt(imageSize);
					String temp = randomImage[index1];
					randomImage[index1] = randomImage[index2];
					randomImage[index2] = temp;
				}
				
				
				int imageCount = 0;
				for (int i = 0; i < 4; i++) {
					for (int j = 0; j < 19; j++) {
						
						ImageIcon imageIcon = new ImageIcon(ImagePanel.homeDirectory + "\\" + randomImage[imageCount++]);
						Image image = imageIcon.getImage();
						Image newimg = image.getScaledInstance(47, 47,  java.awt.Image.SCALE_SMOOTH);
						imageIcon = new ImageIcon(newimg);
						
						JLabel img = new JLabel(imageIcon);
						int imageX = coverImageXPoint[i][j];
						int imageY = coverImageYPoint[i][j];
						if (imageX > 1100) {
							imageX = 1100;
						}
						Border imageBorder = BorderFactory.createLineBorder(Color.white);
						img.setBorder(imageBorder);
						img.setBounds(imageX, imageY, 47, 47);
						add(img);
					}
				}
				addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						System.out.println(e.getX() + " , " + e.getY());
					}
				});
			}
		}

		class LeftButtonPanel extends JPanel {
			// ȭ�� ��� �� Button��
			JButton leftButton = new JButton(leftButtonBasicImage);

			public LeftButtonPanel() {
				setLayout(new GridLayout(0, 1));
				setButtonUI(leftButton);
				setButtonEvent(leftButton, leftButtonBasicImage, leftButtonEnterImage);
				setBackground(MainFrame.MAINCOLOR);
				Border border = BorderFactory.createLineBorder(Color.BLACK, 3);
				setBorder(border);
				add(leftButton);
			}
		}

		class RightButtonPanel extends JPanel {
			JButton rightButton = new JButton(rightButtonBasicImage);
			JButton titleButton = new JButton(mainTitleButtonImage);
			JButton subtitleButton = new JButton(subTitleButtonImage);
			JButton backgroundButton = new JButton(changeBackgroundButtonImage);

			public RightButtonPanel() {
				setLayout(new GridLayout(0, 1));
				// rightButton Ŭ�� �� ���� Panel�� ��µǵ��� ����
				rightButton.addMouseListener(new MouseAdapter() {
					// ��ư �Է� �� cardLayout�� �Է� �� ���� �ǳڷ� �Ѿ���� ��
					public void mousePressed(MouseEvent e) {
						card.next(FirstPanel.this.getParent());
					}
				});
				titleButton.addMouseListener(new MouseAdapter() {
					// ��ư �Է� �� cardLayout�� �Է� �� ���� �ǳڷ� �Ѿ���� ��
					public void mousePressed(MouseEvent e) {
						String str = JOptionPane.showInputDialog(null, "������ �Է��ϼ���");
						if (str != null) {
							mainTitle = str;
							mainTitleLabel.setText(mainTitle);
						}
					}
				});

				subtitleButton.addMouseListener(new MouseAdapter() {
					// ��ư �Է� �� cardLayout�� �Է� �� ���� �ǳڷ� �Ѿ���� ��
					public void mousePressed(MouseEvent e) {
						String str = JOptionPane.showInputDialog(null, "�������� �Է��ϼ���");
						if (str != null) {
							subTitle = str;
							subTitleLabel.setText(subTitle);
						}
					}
				});

				backgroundButton.addMouseListener(new MouseAdapter() {
					// ��ư �Է� �� cardLayout�� �Է� �� ���� �ǳڷ� �Ѿ���� ��
					public void mousePressed(MouseEvent e) {
						JColorChooser chooser = new JColorChooser();
						
						Color tempcolor  = chooser.showDialog(null, "���ȭ�� ���� �����ϼ���", Color.RED);
						if (tempcolor != null) {
							MainFrame.changeSecondColor(tempcolor);
							System.out.println("change");
							rePaint();
						}
					}
				});

				setButtonUI(titleButton);
				setButtonUI(subtitleButton);
				setButtonUI(backgroundButton);
				setButtonUI(rightButton);

				setButtonEvent(titleButton, mainTitleButtonImage, mainTitleButtonEnterImage);
				setButtonEvent(subtitleButton, subTitleButtonImage, subTitleEnterImage);
				setButtonEvent(rightButton, rightButtonBasicImage, rightButtonEnterImage);
				setButtonEvent(backgroundButton, changeBackgroundButtonImage, changeBackgroundEnterImage);

				add(titleButton);
				add(subtitleButton);
				add(backgroundButton);
				add(rightButton);
				setBackground(MainFrame.MAINCOLOR);
				Border border = BorderFactory.createLineBorder(Color.BLACK, 3);
				setBorder(border);
			}
		}
	}
	class ImageDrawPanel extends JPanel{
		ImagePoint imagePoint[] = {new ImagePoint(78,33,124,190),
				new ImagePoint(212,33,257,390),
				new ImagePoint(78,232,124,232),				
				new ImagePoint(78,472,124,146),
				new ImagePoint(212,432,257,188),				
				new ImagePoint(508,33,126,175),
				new ImagePoint(641,33,256,390),
				new ImagePoint(508,223,126,200),
				new ImagePoint(508,431,397,197)
		};
		public ImageDrawPanel() {
			// Ŭ�� �� ���콺 �̺�Ʈ ó��
			setLayout(null);
			int imageCount = 0;
			for(int i = 0 ; i < imagePoint.length ; i++) {
				ImagePoint select = imagePoint[i];
				
				ImageIcon imageIcon = new ImageIcon(ImagePanel.homeDirectory + "\\" + randomImage[imageCount++]);
				Image image = imageIcon.getImage();
				Image newimg = image.getScaledInstance(select.width, select.height,  java.awt.Image.SCALE_SMOOTH);
				imageIcon = new ImageIcon(newimg);
				
				Border border = BorderFactory.createLineBorder(Color.white,3);
				JLabel selectLabel = new JLabel(imageIcon);
				selectLabel.setBorder(border);
				selectLabel.setBounds(select.x,select.y,select.width,select.height);
				add(selectLabel);
			}
			setBackground(MainFrame.SECOND_COLOR);		
			
		}
	}

	// ���� ȭ�鿡�� �Ѿ�� ����� Second Panel
	class SecondPanel extends JPanel {
		LeftButtonPanel leftButtonPanel = new LeftButtonPanel();
		RightButtonPanel rightButtonPanel = new RightButtonPanel();
		ImageDrawPanel imagePanel = new ImageDrawPanel();
		SecondPanel() {
			// ��ư�� ��ġ�� �������� �����Ƿ� ������ ����
			setLayout(new BorderLayout());
			// �ǳڿ� ��ư �߰�
			add(leftButtonPanel, BorderLayout.WEST);
			add(imagePanel,BorderLayout.CENTER);
			add(rightButtonPanel, BorderLayout.EAST);
		}
		

		class LeftButtonPanel extends JPanel {
			// ȭ�� ��� �� Button��
			JButton leftButton = new JButton(leftButtonBasicImage);

			public LeftButtonPanel() {
				setLayout(new GridLayout(0, 1));
				setButtonUI(leftButton);
				setButtonEvent(leftButton, leftButtonBasicImage, leftButtonEnterImage);
				setBackground(MainFrame.MAINCOLOR);
				Border border = BorderFactory.createLineBorder(Color.BLACK, 3);
				setBorder(border);
				add(leftButton);

				// Ŭ�� �� ���콺 �̺�Ʈ ó��
				leftButton.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						card.previous(SecondPanel.this.getParent());
					}
				});
			}
		}

		class RightButtonPanel extends JPanel {
			// ȭ�� ��� �� Button��
			JButton rightButton = new JButton(rightButtonBasicImage);

			public RightButtonPanel() {
				setButtonEvent(rightButton, rightButtonBasicImage, rightButtonEnterImage);
				setButtonUI(rightButton);
				setLayout(new GridLayout(0, 1));
				setBackground(MainFrame.MAINCOLOR);
				Border border = BorderFactory.createLineBorder(Color.BLACK, 3);
				setBorder(border);
				add(rightButton);

				rightButton.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						card.next(SecondPanel.this.getParent());
					}
				});
			}
		}
		public ImageDrawPanel getDrawPanel() {
			return imagePanel;
		}
	}

	class ThirdPanel extends JPanel {
		LeftButtonPanel leftButtonPanel = new LeftButtonPanel();
		RightButtonPanel rightButtonPanel = new RightButtonPanel();

		ThirdPanel() {

			// ��ư�� ��ġ�� �������� �����Ƿ� ������ ����
			setLayout(new BorderLayout());
			// Ŭ�� �� ���콺 �̺�Ʈ ó��

			// �ǳڿ� ��ư �߰�
			add(leftButtonPanel, BorderLayout.WEST);
			add(rightButtonPanel, BorderLayout.EAST);
		}

		class LeftButtonPanel extends JPanel {
			// ȭ�� ��� �� Button��
			JButton leftButton = new JButton(leftButtonBasicImage);

			public LeftButtonPanel() {
				setLayout(new GridLayout(0, 1));
				setButtonUI(leftButton);
				setButtonEvent(leftButton, leftButtonBasicImage, leftButtonEnterImage);
				setBackground(MainFrame.MAINCOLOR);
				Border border = BorderFactory.createLineBorder(Color.BLACK, 3);
				setBorder(border);
				add(leftButton);

				// Ŭ�� �� ���콺 �̺�Ʈ ó��
				leftButton.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						card.previous(ThirdPanel.this.getParent());
					}
				});
			}
		}

		class RightButtonPanel extends JPanel {
			// ȭ�� ��� �� Button��
			JButton rightButton = new JButton(rightButtonBasicImage);

			public RightButtonPanel() {
				setButtonEvent(rightButton, rightButtonBasicImage, rightButtonEnterImage);
				setButtonUI(rightButton);
				setLayout(new GridLayout(0, 1));
				setBackground(MainFrame.MAINCOLOR);
				Border border = BorderFactory.createLineBorder(Color.BLACK, 3);
				setBorder(border);
				add(rightButton);

				rightButton.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						card.next(ThirdPanel.this.getParent());
					}
				});
			}
		}
	}
	class ImagePoint{
		int x;
		int y;
		int width;
		int height;
		public ImagePoint(int x, int y, int width, int height) {
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
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
