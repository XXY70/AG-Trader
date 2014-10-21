package main;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

@SuppressWarnings("serial")
public class MainFrameOld extends JFrame {
	private static Dimension windowSize;

	private static JLabel depotLabel;
	private static String[] depotNames;
	private static JList<String> depotList;
	private static JScrollPane depotPane;

	private static JLabel newAGsLabel;
	private static String[] newAGsListNames;
	private static JList<String> newAGsList;
	private static JScrollPane newAGsPane;

	private static JButton btnNewStory;
	private static JButton btnImportStory;
	private static JButton btnNewChapter;
	private static JButton btnEditChapter;
	private static JButton btnUploadStory;

	public MainFrameOld() {
		super("AG-Manager");

		ListListener lsListener = new ListListener();
		ButtonListener bListener = new ButtonListener();
		depotNames = AGActions.getDepot();
		depotList = new JList<String>(depotNames);
		depotList.addListSelectionListener(lsListener);

		newAGsListNames = new String[0];
		newAGsList = new JList<String>(newAGsListNames);

		setVisible(true);
		windowSize = new Dimension(570, 490);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		setSize(windowSize);
		setMinimumSize(windowSize);
		setPreferredSize(windowSize);

		depotLabel = new JLabel("Depot:");
		depotLabel.setBounds(30, 20, 80, 15);
		add(depotLabel);

		depotPane = new JScrollPane(depotList);
		depotPane.setBounds(20, 40, 225, 300);
		add(depotPane);

		newAGsLabel = new JLabel("Neue AGs:");
		newAGsLabel.setBounds(270, 20, 80, 15);
		add(newAGsLabel);

		newAGsPane = new JScrollPane(newAGsList);
		newAGsPane.setBounds(260, 40, 300, 300);
		add(newAGsPane);

		btnNewStory = new JButton("Kaufen");
		btnNewStory.addActionListener(bListener);
		btnNewStory.setBounds(30, 365, 160, 25);
		add(btnNewStory);

		btnImportStory = new JButton("Verkaufen");
		btnImportStory.addActionListener(bListener);
		btnImportStory.setBounds(30, 395, 160, 25);
		add(btnImportStory);

		btnUploadStory = new JButton("Story hochladen");
		btnUploadStory.addActionListener(bListener);
		btnUploadStory.setBounds(30, 425, 160, 25);
		add(btnUploadStory);

		btnNewChapter = new JButton("Neuer Teil");
		btnNewChapter.addActionListener(bListener);
		btnNewChapter.setBounds(430, 370, 125, 25);
		add(btnNewChapter);

		btnEditChapter = new JButton("Bearbeiten");
		btnEditChapter.addActionListener(bListener);
		btnEditChapter.setBounds(430, 400, 125, 25);
		add(btnEditChapter);

		repaint();
	}

	private void reloadStories() {
		depotNames = AGActions.getDepot();
		depotList.setListData(depotNames);
	}

	private class ListListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			String selected = depotList.getSelectedValue();
			newAGsListNames = AGActions.getNewAgs();
			newAGsList.setListData(newAGsListNames);
		}

	}

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getSource() == btnNewStory) {

			}
		}
	}
}
