package version1;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class TabbedPane extends JTabbedPane {

	public TabbedPane() {
		// ���߿� ���� �ٲ� �� �� 
		// TabbedPane ���� 
		addTab("Image", new ImagePanel());
		addTab("Album", new AlbumPanel());
	}

}
