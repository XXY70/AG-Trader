package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setTitle("AG-Auto-Trader");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 650, 450);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmTest = new JMenuItem("Test");
		JLabel lblStatus;
		mntmTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// lblStatus.setText("Status: Working !!! LONG STATUS !!!");
			}
		});
		mnFile.add(mntmTest);
		JPanel pContent = new JPanel();
		pContent.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pContent);
		pContent.setLayout(new BorderLayout(0, 0));

		JSplitPane statusPane = new JSplitPane();
		pContent.add(statusPane, BorderLayout.SOUTH);

		lblStatus = new JLabel("Status: Ready");
		lblStatus.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				// statusPane.resetToPreferredSizes();
			}
		});
		statusPane.setLeftComponent(lblStatus);

		JProgressBar progressBar = new JProgressBar();
		statusPane.setRightComponent(progressBar);

		JPanel pMain = new JPanel();
		pContent.add(pMain, BorderLayout.CENTER);
		pMain.setLayout(new GridLayout(1, 2, 0, 0));

		JTabbedPane buttonsPane = new JTabbedPane(JTabbedPane.TOP);
		pMain.add(buttonsPane);

		JPanel pGeneral = new JPanel();
		buttonsPane.addTab("General", null, pGeneral, null);
		GridBagLayout gbl_pGeneral = new GridBagLayout();
		gbl_pGeneral.columnWidths = new int[] { 4 };
		gbl_pGeneral.rowHeights = new int[] { 8 };
		gbl_pGeneral.columnWeights = new double[] { Double.MIN_VALUE };
		gbl_pGeneral.rowWeights = new double[] { Double.MIN_VALUE };
		pGeneral.setLayout(gbl_pGeneral);

		JPanel pAutopilot = new JPanel();
		buttonsPane.addTab("Autopilot", null, pAutopilot, null);
		GridBagLayout gbl_pAutopilot = new GridBagLayout();
		gbl_pAutopilot.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 4, 0 };
		gbl_pAutopilot.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 30, 30,
				30 };
		gbl_pAutopilot.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0 };
		gbl_pAutopilot.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0 };
		pAutopilot.setLayout(gbl_pAutopilot);

		JToggleButton tglbtnStart = new JToggleButton("Start");
		tglbtnStart.setVerticalAlignment(SwingConstants.TOP);
		tglbtnStart.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_tglbtnStart = new GridBagConstraints();
		gbc_tglbtnStart.anchor = GridBagConstraints.PAGE_START;
		gbc_tglbtnStart.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnStart.gridx = 0;
		gbc_tglbtnStart.gridy = 0;
		pAutopilot.add(tglbtnStart, gbc_tglbtnStart);

		JCheckBox chckbxSearchNewAGs = new JCheckBox("Search for new AGs");
		GridBagConstraints gbc_chckbxSearchNewAGs = new GridBagConstraints();
		gbc_chckbxSearchNewAGs.gridwidth = 4;
		gbc_chckbxSearchNewAGs.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxSearchNewAGs.gridx = 0;
		gbc_chckbxSearchNewAGs.gridy = 2;
		pAutopilot.add(chckbxSearchNewAGs, gbc_chckbxSearchNewAGs);

		JTabbedPane listsPane = new JTabbedPane(JTabbedPane.TOP);
		pMain.add(listsPane);

		JPanel pDepot = new JPanel();
		listsPane.addTab("Depot", null, pDepot, null);

		JScrollPane scrollPane_1 = new JScrollPane();
		pDepot.add(scrollPane_1);

		JPanel pNewAGs = new JPanel();
		listsPane.addTab("New AGs", null, pNewAGs, null);

		JScrollPane scrollPane = new JScrollPane();
		pNewAGs.add(scrollPane);
	}
}
