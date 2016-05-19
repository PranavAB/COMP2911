import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Random;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * Provides static functions to drawing mazes to the console.
 *
 */
public class UI extends JFrame {
	
	// This exists purely for the JFrame.
	private static final long serialVersionUID = -5356384863762278628L;
	
	// The maze image.
	private BufferedImage mazeImg;
	
	// The uncollected coin image.
	private BufferedImage coinImg;
	
	// The partially collected coin image.
	private BufferedImage coinPartialImg;

	// The mazeGame to be used.
	private MazeGame game;
	
	// The mazeProgram itself.
	private MazeProgram program;
	
	// The config for controls and user preferences.
	private Config config;
	
	// The size of tiles used in the program (both width and height).
	private int tileSize;
	
	// A tonne of JComponents for the UI. They're all named appropriately.
	private JPanel mazeViewLeftPanel;
	private JPanel mazeViewRightPanel;
	private JLabel timeLabel;
	private JLabel moveLabel;
	private UIMazeViewComponent mazeViewImageComponent;
	private JPanel mazeViewButtonPanel;
	private JButton mazeViewQuitButton;
	private JPanel mainMenuPanel;
	private JPanel mainMenuContentPanel;
	private JButton mainMenuExitButton;
	private JPanel mainPanel;
	private JPanel mazeViewPanel;
	private JButton mainMenuUserSettingsButton;
	private JButton mainMenuCustomGameButton;
	private JButton mainMenuInstantActionButton;
	private JPanel mainMenuButtonPanelCentral;
	private JLabel mainMenuTitleLabel;
	private JPanel mainMenuButtonPanel;
	private CardLayout mainPanelCardLayout;
	private JLabel mazeViewPauseLabel;
	private JPanel customGameSetupPanel;
	private JTextField customGameSetupSeedField;
	private JTextField customGameSetupSizeXField;
	private JTextField customGameSetupSizeYField;
	private JLabel customGameSetupSizeLabel;
	private JPanel customGameSetupPlayerPanel;
	private JPanel customGameSetupDefaultPanel;
	private JPanel customGameSetupButtonPanel;
	private JLabel seedLabel;
	private JLabel customGameSetupDifficultyLabel;
	private JRadioButton customGameSetupDifficultyEasyRadio;
	private JRadioButton customGameSetupDifficultyMediumRadio;
	private JRadioButton customGameSetupDifficultyHardRadio;
	private JRadioButton customGameSetupDiffcultyCustomRadio;
	private JRadioButton customGameSetupPlayers1Radio;
	private JRadioButton customGameSetupPlayers2Radio;
	private JPanel userSettingsPanel;
	private JButton userSettingsPlayer1LeftButton;
	private JButton userSettingsPlayer1DownButton;
	private JButton userSettingsPlayer1RightButton;
	private JPanel changeKeyBindingPanel;
	private JLabel changeKeyBindingChangeLabel;
	private JLabel changeKeyBindingPreviousLabel;
	private JButton userSettingsPlayer1UpButton;
	private JPanel userSettingsButtonPanel;
	private JButton userSettingsBackButton;
	private JPanel userSettingsPlayer2Panel;
	private JButton userSettingsPlayer2UpButton;
	private JButton userSettingsPlayer2LeftButton;
	private JButton userSettingsPlayer2DownButton;
	private JButton userSettingsPlayer2RightButton;
	private JPanel userSettingsPlayer1ImagePanel;
	private JComponent userSettingsPlayer1Image;
	private JButton userSettingsPlayer1ImageChooseButton;
	private JButton userSettingsPlayer1ImageRandomiseButton;
	private JPanel userSettingsPlayer2ImagePanel;
	private JComponent userSettingsPlayer2Image;
	private JButton userSettingsPlayer2ImageChooseButton;
	private JButton userSettingsPlayer2ImageRandomiseButton;
	private JPanel customGameSetupStrategyPanel;
	private JLabel customGameSetupStrategyLabel;
	private JRadioButton customGameSetupDFSRadio;
	private JLabel coinLabel;
	private JRadioButton customGameSetupRingRadio;
	private JButton mainMenuLeaderboardButton;
	private JPanel leaderboardPanel;
	private JTable leaderboardDataTable;
	private JScrollPane leaderboardDataPanel;
	private JPanel leaderboardButtonPanel;
	private JButton leaderboardBackButton;
	private JButton leaderboardChallengeButton;
	private JButton leaderboardDeleteEntryButton;
	
	/**
	 * Creates the whole UI system for the program. It initialises every
	 * frame related to the program.
	 * @param mazeProgram
	 * The mazeProgram that runs this.
	 * @param cfg
	 * The config that contains user-specific details.
	 */
	@SuppressWarnings("serial")
	public UI(MazeProgram mazeProgram, Config cfg) {
		this.tileSize = 16;
		this.program = mazeProgram;
		this.config = cfg;
		this.coinImg = drawCoinImg();
		this.coinPartialImg = drawCoinPartialImg();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 450);
		setMinimumSize(new Dimension(480, 360));
		setTimeText("00:00:000");
		setMoveText("0 moves");
		setSeedText("00000000");
		getContentPane().setLayout(new CardLayout(0, 0));
		
		mainPanel = new JPanel();
		getContentPane().add(mainPanel, "name_187122148572434");
		mainPanelCardLayout = new CardLayout(0, 0);
		mainPanel.setLayout(mainPanelCardLayout);
		
		mazeViewPanel = new JPanel();
		mainPanel.add(mazeViewPanel, "mazeViewPanel");
		SpringLayout sl_mazeViewPanel = new SpringLayout();
		mazeViewPanel.setLayout(sl_mazeViewPanel);
		
		mazeViewLeftPanel = new JPanel();
		sl_mazeViewPanel.putConstraint(SpringLayout.NORTH, mazeViewLeftPanel, 10, SpringLayout.NORTH, mazeViewPanel);
		sl_mazeViewPanel.putConstraint(SpringLayout.WEST, mazeViewLeftPanel, 10, SpringLayout.WEST, mazeViewPanel);
		sl_mazeViewPanel.putConstraint(SpringLayout.SOUTH, mazeViewLeftPanel, -10, SpringLayout.SOUTH, mazeViewPanel);
		mazeViewPanel.add(mazeViewLeftPanel);
		
		SpringLayout sl_mazeViewLeftPanel = new SpringLayout();
		mazeViewLeftPanel.setLayout(sl_mazeViewLeftPanel);
		
		mazeViewImageComponent = new UIMazeViewComponent(mazeImg, coinImg, coinPartialImg, game);
		sl_mazeViewLeftPanel.putConstraint(SpringLayout.NORTH, mazeViewImageComponent, 0, SpringLayout.NORTH, mazeViewLeftPanel);
		sl_mazeViewLeftPanel.putConstraint(SpringLayout.WEST, mazeViewImageComponent, 0, SpringLayout.WEST, mazeViewLeftPanel);
		sl_mazeViewLeftPanel.putConstraint(SpringLayout.SOUTH, mazeViewImageComponent, 0, SpringLayout.SOUTH, mazeViewLeftPanel);
		sl_mazeViewLeftPanel.putConstraint(SpringLayout.EAST, mazeViewImageComponent, 0, SpringLayout.EAST, mazeViewLeftPanel);
		mazeViewLeftPanel.add(mazeViewImageComponent);
		
		mazeViewRightPanel = new JPanel();
		sl_mazeViewPanel.putConstraint(SpringLayout.EAST, mazeViewRightPanel, -10, SpringLayout.EAST, mazeViewPanel);
		sl_mazeViewPanel.putConstraint(SpringLayout.EAST, mazeViewLeftPanel, -10, SpringLayout.WEST, mazeViewRightPanel);
		
		mazeViewPauseLabel = new JLabel("Paused");
		mazeViewPauseLabel.setEnabled(false);
		mazeViewPauseLabel.setVisible(false);
		mazeViewPauseLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		sl_mazeViewLeftPanel.putConstraint(SpringLayout.NORTH, mazeViewPauseLabel, 0, SpringLayout.NORTH, mazeViewLeftPanel);
		sl_mazeViewLeftPanel.putConstraint(SpringLayout.WEST, mazeViewPauseLabel, 0, SpringLayout.WEST, mazeViewLeftPanel);
		sl_mazeViewLeftPanel.putConstraint(SpringLayout.SOUTH, mazeViewPauseLabel, 0, SpringLayout.SOUTH, mazeViewLeftPanel);
		sl_mazeViewLeftPanel.putConstraint(SpringLayout.EAST, mazeViewPauseLabel, 0, SpringLayout.EAST, mazeViewLeftPanel);
		mazeViewPauseLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mazeViewLeftPanel.add(mazeViewPauseLabel);
		sl_mazeViewPanel.putConstraint(SpringLayout.NORTH, mazeViewRightPanel, 10, SpringLayout.NORTH, mazeViewPanel);
		sl_mazeViewPanel.putConstraint(SpringLayout.WEST, mazeViewRightPanel, -210, SpringLayout.EAST, mazeViewPanel);
		sl_mazeViewPanel.putConstraint(SpringLayout.SOUTH, mazeViewRightPanel, -10, SpringLayout.SOUTH, mazeViewPanel);
		mazeViewPanel.add(mazeViewRightPanel);
		mazeViewRightPanel.setLayout(new GridLayout(8, 1, 0, 0));
		
		timeLabel = new JLabel("Time Text");
		timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mazeViewRightPanel.add(timeLabel);
		
		moveLabel = new JLabel("Move Text");
		moveLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mazeViewRightPanel.add(moveLabel);
		
		coinLabel = new JLabel("Coin Text");
		coinLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mazeViewRightPanel.add(coinLabel);
		mazeViewRightPanel.add(new JPanel());
		mazeViewRightPanel.add(new JPanel());
		mazeViewRightPanel.add(new JPanel());
		
		seedLabel = new JLabel("Seed Text");
		seedLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mazeViewRightPanel.add(seedLabel);
		
		mazeViewButtonPanel = new JPanel();
		mazeViewRightPanel.add(mazeViewButtonPanel);
		mazeViewButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton mazeViewPauseButton = new JButton("Pause");
		mazeViewPauseButton.setToolTipText("Pauses or unpauses the game. No cheating!");
		mazeViewButtonPanel.add(mazeViewPauseButton);
		mazeViewPauseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				program.togglePauseGame();
			}
		});
		
		mazeViewQuitButton = new JButton("Quit");
		mazeViewQuitButton.setToolTipText("Quits back to the main menu.");
		mazeViewButtonPanel.add(mazeViewQuitButton);
		
		mazeViewQuitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Quit?", JOptionPane.YES_NO_OPTION);
				if (dialogResult == JOptionPane.YES_OPTION) {
					program.exitMazeGame();
				}
			}
		});
		
		mainMenuPanel = new JPanel();
		mainPanel.add(mainMenuPanel, "mainMenuPanel");
		SpringLayout sl_mainMenuPanel = new SpringLayout();
		mainMenuPanel.setLayout(sl_mainMenuPanel);
		
		mainMenuContentPanel = new JPanel();
		sl_mainMenuPanel.putConstraint(SpringLayout.NORTH, mainMenuContentPanel, 10, SpringLayout.NORTH, mainMenuPanel);
		sl_mainMenuPanel.putConstraint(SpringLayout.WEST, mainMenuContentPanel, 10, SpringLayout.WEST, mainMenuPanel);
		sl_mainMenuPanel.putConstraint(SpringLayout.SOUTH, mainMenuContentPanel, -10, SpringLayout.SOUTH, mainMenuPanel);
		sl_mainMenuPanel.putConstraint(SpringLayout.EAST, mainMenuContentPanel, -10, SpringLayout.EAST, mainMenuPanel);
		mainMenuPanel.add(mainMenuContentPanel);
		SpringLayout sl_mainMenuContentPanel = new SpringLayout();
		mainMenuContentPanel.setLayout(sl_mainMenuContentPanel);
		
		mainMenuButtonPanel = new JPanel();
		sl_mainMenuContentPanel.putConstraint(SpringLayout.NORTH, mainMenuButtonPanel, -150, SpringLayout.SOUTH, mainMenuContentPanel);
		sl_mainMenuContentPanel.putConstraint(SpringLayout.WEST, mainMenuButtonPanel, 0, SpringLayout.WEST, mainMenuContentPanel);
		sl_mainMenuContentPanel.putConstraint(SpringLayout.SOUTH, mainMenuButtonPanel, 0, SpringLayout.SOUTH, mainMenuContentPanel);
		sl_mainMenuContentPanel.putConstraint(SpringLayout.EAST, mainMenuButtonPanel, 0, SpringLayout.EAST, mainMenuContentPanel);
		mainMenuContentPanel.add(mainMenuButtonPanel);
		mainMenuButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		mainMenuButtonPanelCentral = new JPanel();
		mainMenuButtonPanel.add(mainMenuButtonPanelCentral);
		mainMenuButtonPanelCentral.setLayout(new GridLayout(5, 1, 0, 0));
		
		mainMenuInstantActionButton = new JButton("Instant Action");
		mainMenuButtonPanelCentral.add(mainMenuInstantActionButton);
		mainMenuInstantActionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				program.instantAction();
			}
		});
		
		mainMenuCustomGameButton = new JButton("Custom Game");
		mainMenuButtonPanelCentral.add(mainMenuCustomGameButton);
		mainMenuCustomGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanel("customGameSetupPanel");
			}
		});
		
		mainMenuUserSettingsButton = new JButton("User Settings");
		mainMenuButtonPanelCentral.add(mainMenuUserSettingsButton);
		
		mainMenuLeaderboardButton = new JButton("Leaderboard");
		mainMenuButtonPanelCentral.add(mainMenuLeaderboardButton);
		mainMenuLeaderboardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanel("leaderboardPanel");
			}
		});
		
		mainMenuExitButton = new JButton("Exit");
		mainMenuButtonPanelCentral.add(mainMenuExitButton);
		
		mainMenuTitleLabel = new JLabel("Maze Game");
		sl_mainMenuContentPanel.putConstraint(SpringLayout.NORTH, mainMenuTitleLabel, 0, SpringLayout.NORTH, mainMenuContentPanel);
		sl_mainMenuContentPanel.putConstraint(SpringLayout.WEST, mainMenuTitleLabel, 0, SpringLayout.WEST, mainMenuContentPanel);
		sl_mainMenuContentPanel.putConstraint(SpringLayout.SOUTH, mainMenuTitleLabel, 0, SpringLayout.NORTH, mainMenuButtonPanel);
		sl_mainMenuContentPanel.putConstraint(SpringLayout.EAST, mainMenuTitleLabel, 0, SpringLayout.EAST, mainMenuContentPanel);
		mainMenuTitleLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		mainMenuTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		//mainMenuTitleLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		mainMenuContentPanel.add(mainMenuTitleLabel);
		sl_mazeViewPanel.putConstraint(SpringLayout.NORTH, mainMenuPanel, 0, SpringLayout.NORTH, mazeViewPanel);
		sl_mazeViewPanel.putConstraint(SpringLayout.WEST, mainMenuPanel, 0, SpringLayout.WEST, mazeViewPanel);
		sl_mazeViewPanel.putConstraint(SpringLayout.SOUTH, mainMenuPanel, 0, SpringLayout.SOUTH, mazeViewPanel);
		sl_mazeViewPanel.putConstraint(SpringLayout.EAST, mainMenuPanel, 0, SpringLayout.EAST, mazeViewPanel);
		
		mainMenuExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
			
		});
		
		customGameSetupPanel = new JPanel();
		mainPanel.add(customGameSetupPanel, "customGameSetupPanel");
		SpringLayout sl_customGameSetupPanel = new SpringLayout();
		customGameSetupPanel.setLayout(sl_customGameSetupPanel);
		
		customGameSetupDefaultPanel = new JPanel();
		sl_customGameSetupPanel.putConstraint(SpringLayout.NORTH, customGameSetupDefaultPanel, 10, SpringLayout.NORTH, customGameSetupPanel);
		sl_customGameSetupPanel.putConstraint(SpringLayout.WEST, customGameSetupDefaultPanel, 10, SpringLayout.WEST, customGameSetupPanel);
		sl_customGameSetupPanel.putConstraint(SpringLayout.SOUTH, customGameSetupDefaultPanel, 50, SpringLayout.NORTH, customGameSetupPanel);
		sl_customGameSetupPanel.putConstraint(SpringLayout.EAST, customGameSetupDefaultPanel, -10, SpringLayout.EAST, customGameSetupPanel);
		customGameSetupPanel.add(customGameSetupDefaultPanel);
		
		ButtonGroup customGameDifficultyGroup = new ButtonGroup();
		
		customGameSetupDifficultyLabel = new JLabel("Difficulty:");
		customGameSetupDefaultPanel.add(customGameSetupDifficultyLabel);
		
		customGameSetupDifficultyEasyRadio = new JRadioButton("Easy");
		customGameSetupDifficultyEasyRadio.setSelected(true);
		customGameSetupDefaultPanel.add(customGameSetupDifficultyEasyRadio);
		customGameDifficultyGroup.add(customGameSetupDifficultyEasyRadio);
		customGameSetupDifficultyEasyRadio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customGameSetupSizeXField.setText("10");
				customGameSetupSizeXField.setEnabled(false);
				customGameSetupSizeYField.setText("10");
				customGameSetupSizeYField.setEnabled(false);
				customGameSetupDFSRadio.setSelected(true);
				customGameSetupDFSRadio.setEnabled(false);
				customGameSetupRingRadio.setSelected(false);
				customGameSetupRingRadio.setEnabled(false);
			}
		});
		
		customGameSetupDifficultyMediumRadio = new JRadioButton("Medium");
		customGameSetupDefaultPanel.add(customGameSetupDifficultyMediumRadio);
		customGameDifficultyGroup.add(customGameSetupDifficultyMediumRadio);
		customGameSetupDifficultyMediumRadio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customGameSetupSizeXField.setText("25");
				customGameSetupSizeXField.setEnabled(false);
				customGameSetupSizeYField.setText("20");
				customGameSetupSizeYField.setEnabled(false);
				customGameSetupDFSRadio.setSelected(true);
				customGameSetupDFSRadio.setEnabled(false);
				customGameSetupRingRadio.setSelected(false);
				customGameSetupRingRadio.setEnabled(false);
			}
		});
		
		customGameSetupDifficultyHardRadio = new JRadioButton("Hard");
		customGameSetupDefaultPanel.add(customGameSetupDifficultyHardRadio);
		customGameDifficultyGroup.add(customGameSetupDifficultyHardRadio);
		customGameSetupDifficultyHardRadio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customGameSetupSizeXField.setText("40");
				customGameSetupSizeXField.setEnabled(false);
				customGameSetupSizeYField.setText("30");
				customGameSetupSizeYField.setEnabled(false);
				customGameSetupDFSRadio.setSelected(true);
				customGameSetupDFSRadio.setEnabled(false);
				customGameSetupRingRadio.setSelected(false);
				customGameSetupRingRadio.setEnabled(false);
			}
		});
		
		customGameSetupDiffcultyCustomRadio = new JRadioButton("Custom");
		customGameSetupDefaultPanel.add(customGameSetupDiffcultyCustomRadio);
		customGameDifficultyGroup.add(customGameSetupDiffcultyCustomRadio);
		customGameSetupDiffcultyCustomRadio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customGameSetupSizeXField.setEnabled(true);
				customGameSetupSizeYField.setEnabled(true);
				customGameSetupDFSRadio.setEnabled(true);
				customGameSetupRingRadio.setEnabled(true);
			}
		});
		
		JPanel customGameSetupSizePanel = new JPanel();
		sl_customGameSetupPanel.putConstraint(SpringLayout.NORTH, customGameSetupSizePanel, 10, SpringLayout.SOUTH, customGameSetupDefaultPanel);
		sl_customGameSetupPanel.putConstraint(SpringLayout.WEST, customGameSetupSizePanel, 10, SpringLayout.WEST, customGameSetupPanel);
		sl_customGameSetupPanel.putConstraint(SpringLayout.SOUTH, customGameSetupSizePanel, 50, SpringLayout.SOUTH, customGameSetupDefaultPanel);
		sl_customGameSetupPanel.putConstraint(SpringLayout.EAST, customGameSetupSizePanel, -10, SpringLayout.EAST, customGameSetupPanel);
		customGameSetupPanel.add(customGameSetupSizePanel);
		customGameSetupSizePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		customGameSetupSizeLabel = new JLabel("Size:");
		customGameSetupSizePanel.add(customGameSetupSizeLabel);
		
		customGameSetupSizeXField = new JTextField();
		customGameSetupSizeXField.setEnabled(false);
		customGameSetupSizeXField.setText("10");
		customGameSetupSizePanel.add(customGameSetupSizeXField);
		customGameSetupSizeXField.setColumns(5);
		
		JLabel customGameSetupSizeByLabel = new JLabel("by");
		customGameSetupSizePanel.add(customGameSetupSizeByLabel);
		
		customGameSetupSizeYField = new JTextField();
		customGameSetupSizeYField.setEnabled(false);
		customGameSetupSizeYField.setText("10");
		customGameSetupSizePanel.add(customGameSetupSizeYField);
		customGameSetupSizeYField.setColumns(5);
		
		customGameSetupStrategyPanel = new JPanel();
		sl_customGameSetupPanel.putConstraint(SpringLayout.NORTH, customGameSetupStrategyPanel, 10, SpringLayout.SOUTH, customGameSetupSizePanel);
		sl_customGameSetupPanel.putConstraint(SpringLayout.WEST, customGameSetupStrategyPanel, 10, SpringLayout.WEST, customGameSetupPanel);
		sl_customGameSetupPanel.putConstraint(SpringLayout.SOUTH, customGameSetupStrategyPanel, 50, SpringLayout.SOUTH, customGameSetupSizePanel);
		sl_customGameSetupPanel.putConstraint(SpringLayout.EAST, customGameSetupStrategyPanel, -10, SpringLayout.EAST, customGameSetupPanel);
		customGameSetupPanel.add(customGameSetupStrategyPanel);
		
		JPanel customGameSetupSeedPanel = new JPanel();
		sl_customGameSetupPanel.putConstraint(SpringLayout.NORTH, customGameSetupSeedPanel, 10, SpringLayout.SOUTH, customGameSetupStrategyPanel);
		sl_customGameSetupPanel.putConstraint(SpringLayout.WEST, customGameSetupSeedPanel, 10, SpringLayout.WEST, customGameSetupPanel);
		sl_customGameSetupPanel.putConstraint(SpringLayout.SOUTH, customGameSetupSeedPanel, 50, SpringLayout.SOUTH, customGameSetupStrategyPanel);

		
		ButtonGroup customGameStrategyGroup = new ButtonGroup();
		
		customGameSetupStrategyLabel = new JLabel("Strategy:");
		customGameSetupStrategyPanel.add(customGameSetupStrategyLabel);
		
		customGameSetupDFSRadio = new JRadioButton("DFS");
		customGameSetupDFSRadio.setSelected(true);
		customGameSetupStrategyPanel.add(customGameSetupDFSRadio);
		sl_customGameSetupPanel.putConstraint(SpringLayout.EAST, customGameSetupSeedPanel, -10, SpringLayout.EAST, customGameSetupPanel);
		customGameSetupPanel.add(customGameSetupSeedPanel);
		customGameSetupSeedPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		customGameStrategyGroup.add(customGameSetupDFSRadio);
		customGameSetupDFSRadio.setEnabled(false);
		
		customGameSetupRingRadio = new JRadioButton("Ring (NOT FINISHED)");
		customGameSetupStrategyPanel.add(customGameSetupRingRadio);
		customGameStrategyGroup.add(customGameSetupRingRadio);
		customGameSetupRingRadio.setEnabled(false);
		
		JLabel customGameSetupSeedLabel = new JLabel("Seed:");
		customGameSetupSeedPanel.add(customGameSetupSeedLabel);
		
		customGameSetupSeedField = new JTextField();
		customGameSetupSeedPanel.add(customGameSetupSeedField);
		customGameSetupSeedField.setColumns(10);
		
		customGameSetupPlayerPanel = new JPanel();
		sl_customGameSetupPanel.putConstraint(SpringLayout.NORTH, customGameSetupPlayerPanel, 10, SpringLayout.SOUTH, customGameSetupSeedPanel);
		sl_customGameSetupPanel.putConstraint(SpringLayout.WEST, customGameSetupPlayerPanel, 10, SpringLayout.WEST, customGameSetupPanel);
		sl_customGameSetupPanel.putConstraint(SpringLayout.SOUTH, customGameSetupPlayerPanel, 50, SpringLayout.SOUTH, customGameSetupSeedPanel);
		sl_customGameSetupPanel.putConstraint(SpringLayout.EAST, customGameSetupPlayerPanel, -10, SpringLayout.EAST, customGameSetupPanel);
		customGameSetupPanel.add(customGameSetupPlayerPanel);
		
		JLabel customGameSetupPlayersLabel = new JLabel("Players:");
		customGameSetupPlayerPanel.add(customGameSetupPlayersLabel);
		
		ButtonGroup customGamePlayerButtonGroup = new ButtonGroup();
		
		customGameSetupPlayers1Radio = new JRadioButton("1");
		customGameSetupPlayers1Radio.setSelected(true);
		customGameSetupPlayerPanel.add(customGameSetupPlayers1Radio);
		customGamePlayerButtonGroup.add(customGameSetupPlayers1Radio);
		
		customGameSetupPlayers2Radio = new JRadioButton("2");
		customGameSetupPlayerPanel.add(customGameSetupPlayers2Radio);
		customGamePlayerButtonGroup.add(customGameSetupPlayers2Radio);
		
		customGameSetupButtonPanel = new JPanel();
		sl_customGameSetupPanel.putConstraint(SpringLayout.NORTH, customGameSetupButtonPanel, -50, SpringLayout.SOUTH, customGameSetupPanel);
		sl_customGameSetupPanel.putConstraint(SpringLayout.WEST, customGameSetupButtonPanel, 10, SpringLayout.WEST, customGameSetupPanel);
		sl_customGameSetupPanel.putConstraint(SpringLayout.SOUTH, customGameSetupButtonPanel, -10, SpringLayout.SOUTH, customGameSetupPanel);
		sl_customGameSetupPanel.putConstraint(SpringLayout.EAST, customGameSetupButtonPanel, -10, SpringLayout.EAST, customGameSetupPanel);
		customGameSetupPanel.add(customGameSetupButtonPanel);
		
		JButton customGameSetupBackButton = new JButton("Back");
		customGameSetupButtonPanel.add(customGameSetupBackButton);
		sl_customGameSetupPanel.putConstraint(SpringLayout.SOUTH, customGameSetupBackButton, -79, SpringLayout.SOUTH, customGameSetupPanel);
		sl_customGameSetupPanel.putConstraint(SpringLayout.WEST, customGameSetupBackButton, 0, SpringLayout.WEST, customGameSetupSeedPanel);
		
		JButton customGameSetupPlayButton = new JButton("Play");
		customGameSetupButtonPanel.add(customGameSetupPlayButton);
		sl_customGameSetupPanel.putConstraint(SpringLayout.EAST, customGameSetupPlayButton, 0, SpringLayout.EAST, customGameSetupSeedPanel);
		customGameSetupPlayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO: Handle the exceptions here.
				int width = Integer.parseInt(customGameSetupSizeXField.getText());
				int height = Integer.parseInt(customGameSetupSizeYField.getText());
				long seed = 0;
				
				if (customGameSetupSeedField.getText().equals("")) {
					seed = 0;
				} else {
					seed = Long.parseLong(customGameSetupSeedField.getText());
				}
				
				MazeGenerationStrategy strategy = MazeGenerationStrategy.NONE;
				if (customGameSetupDFSRadio.isSelected()) {
					strategy = MazeGenerationStrategy.DEPTHFIRSTSEARCH;
				} else if (customGameSetupRingRadio.isSelected()) {
					strategy = MazeGenerationStrategy.RING;
				}
				
				int players = 0;
				if (customGameSetupPlayers1Radio.isSelected()) {
					players = 1;
				} else if (customGameSetupPlayers2Radio.isSelected()) {
					players = 2;
				}
				program.customGameSetup(width, height, strategy, seed, players);
			}
		});
		
		sl_customGameSetupPanel.putConstraint(SpringLayout.NORTH, customGameSetupPlayButton, 0, SpringLayout.NORTH, customGameSetupBackButton);
		
		userSettingsPanel = new JPanel();
		mainPanel.add(userSettingsPanel, "userSettingsPanel");
		SpringLayout sl_userSettingsPanel = new SpringLayout();
		userSettingsPanel.setLayout(sl_userSettingsPanel);
		
		JPanel userSettingsPlayer1Panel = new JPanel();
		sl_userSettingsPanel.putConstraint(SpringLayout.NORTH, userSettingsPlayer1Panel, 10, SpringLayout.NORTH, userSettingsPanel);
		sl_userSettingsPanel.putConstraint(SpringLayout.WEST, userSettingsPlayer1Panel, 10, SpringLayout.WEST, userSettingsPanel);
		sl_userSettingsPanel.putConstraint(SpringLayout.SOUTH, userSettingsPlayer1Panel, 80, SpringLayout.NORTH, userSettingsPanel);
		sl_userSettingsPanel.putConstraint(SpringLayout.EAST, userSettingsPlayer1Panel, -10, SpringLayout.EAST, userSettingsPanel);
		userSettingsPanel.add(userSettingsPlayer1Panel);
		userSettingsPlayer1Panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel userSettingsPlayer1Label = new JLabel("Player 1 - ");
		userSettingsPlayer1Panel.add(userSettingsPlayer1Label);
		
		JLabel userSettingsPlayer1UpLabel = new JLabel("Up:");
		userSettingsPlayer1Panel.add(userSettingsPlayer1UpLabel);
		
		userSettingsPlayer1UpButton = new JButton("Player1Up");
		userSettingsPlayer1Panel.add(userSettingsPlayer1UpButton);
		userSettingsPlayer1UpButton.setText(KeyEvent.getKeyText(config.getKeyBinding(ControlCode.PLAYER1UP)));
		userSettingsPlayer1UpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeKeyBinding(ControlCode.PLAYER1UP, config.getKeyBinding(ControlCode.PLAYER1UP));
			}
		});
		
		JLabel userSettingsPlayer1LeftLabel = new JLabel("Left:");
		userSettingsPlayer1Panel.add(userSettingsPlayer1LeftLabel);
		
		userSettingsPlayer1LeftButton = new JButton("Player1Left");
		userSettingsPlayer1Panel.add(userSettingsPlayer1LeftButton);
		userSettingsPlayer1LeftButton.setText(KeyEvent.getKeyText(config.getKeyBinding(ControlCode.PLAYER1LEFT)));
		userSettingsPlayer1LeftButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeKeyBinding(ControlCode.PLAYER1LEFT, config.getKeyBinding(ControlCode.PLAYER1LEFT));
			}
		});
		
		JLabel userSettingsPlayer1DownLabel = new JLabel("Down:");
		userSettingsPlayer1Panel.add(userSettingsPlayer1DownLabel);
		
		userSettingsPlayer1DownButton = new JButton("Player1Down");
		userSettingsPlayer1Panel.add(userSettingsPlayer1DownButton);
		userSettingsPlayer1DownButton.setText(KeyEvent.getKeyText(config.getKeyBinding(ControlCode.PLAYER1DOWN)));
		userSettingsPlayer1DownButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeKeyBinding(ControlCode.PLAYER1DOWN, config.getKeyBinding(ControlCode.PLAYER1DOWN));
			}
		});
		
		JLabel userSettingsPlayer1RightLabel = new JLabel("Right:");
		userSettingsPlayer1Panel.add(userSettingsPlayer1RightLabel);
		
		userSettingsPlayer1RightButton = new JButton("Player1Right");
		userSettingsPlayer1Panel.add(userSettingsPlayer1RightButton);
		userSettingsPlayer1RightButton.setText(KeyEvent.getKeyText(config.getKeyBinding(ControlCode.PLAYER1RIGHT)));
		userSettingsPlayer1RightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeKeyBinding(ControlCode.PLAYER1RIGHT, config.getKeyBinding(ControlCode.PLAYER1RIGHT));
			}
		});
		
		userSettingsPlayer1ImagePanel = new JPanel();
		sl_userSettingsPanel.putConstraint(SpringLayout.NORTH, userSettingsPlayer1ImagePanel, 10, SpringLayout.SOUTH, userSettingsPlayer1Panel);
		sl_userSettingsPanel.putConstraint(SpringLayout.WEST, userSettingsPlayer1ImagePanel, 10, SpringLayout.WEST, userSettingsPanel);
		sl_userSettingsPanel.putConstraint(SpringLayout.SOUTH, userSettingsPlayer1ImagePanel, 50, SpringLayout.SOUTH, userSettingsPlayer1Panel);
		sl_userSettingsPanel.putConstraint(SpringLayout.EAST, userSettingsPlayer1ImagePanel, -10, SpringLayout.EAST, userSettingsPanel);
		userSettingsPanel.add(userSettingsPlayer1ImagePanel);
		
		userSettingsPlayer2Panel = new JPanel();
		sl_userSettingsPanel.putConstraint(SpringLayout.NORTH, userSettingsPlayer2Panel, 10, SpringLayout.SOUTH, userSettingsPlayer1ImagePanel);
		sl_userSettingsPanel.putConstraint(SpringLayout.WEST, userSettingsPlayer2Panel, 10, SpringLayout.WEST, userSettingsPanel);
		sl_userSettingsPanel.putConstraint(SpringLayout.SOUTH, userSettingsPlayer2Panel, 80, SpringLayout.SOUTH, userSettingsPlayer1ImagePanel);
		userSettingsPlayer1ImagePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel userSettingsPlayer1ImageLabel = new JLabel("Player 1 - Image:");
		userSettingsPlayer1ImagePanel.add(userSettingsPlayer1ImageLabel);
		
		userSettingsPlayer1Image = new JComponent() {
			protected void paintComponent(Graphics g) {
				g.drawImage(config.getPlayer1Image(), 0, 0, null);
			}
		};
		userSettingsPlayer1Image.setPreferredSize(new Dimension(14, 14));
		userSettingsPlayer1ImagePanel.add(userSettingsPlayer1Image);
		
		userSettingsPlayer1ImageChooseButton = new JButton("Choose");
		userSettingsPlayer1ImagePanel.add(userSettingsPlayer1ImageChooseButton);
		userSettingsPlayer1ImageChooseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.showOpenDialog(null);
				try {
					config.setPlayer1Image(ImageIO.read(fileChooser.getSelectedFile()));
				} catch (IOException e1) {
					
				}
				userSettingsPlayer1Image.repaint();
			}
		});
		
		userSettingsPlayer1ImageRandomiseButton = new JButton("Randomise");
		userSettingsPlayer1ImagePanel.add(userSettingsPlayer1ImageRandomiseButton);
		sl_userSettingsPanel.putConstraint(SpringLayout.EAST, userSettingsPlayer2Panel, -10, SpringLayout.EAST, userSettingsPanel);
		userSettingsPanel.add(userSettingsPlayer2Panel);
		userSettingsPlayer1ImageRandomiseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				config.setRandomPlayer1Image();
				userSettingsPlayer1Image.repaint();
			}
		});
		
		JLabel userSettingsPlayer2Label = new JLabel("Player 2");
		userSettingsPlayer2Panel.add(userSettingsPlayer2Label);
		
		JLabel userSettingsPlayer2UpLabel = new JLabel("Up:");
		userSettingsPlayer2Panel.add(userSettingsPlayer2UpLabel);
		
		userSettingsPlayer2UpButton = new JButton("Player2Up");
		userSettingsPlayer2Panel.add(userSettingsPlayer2UpButton);
		userSettingsPlayer2UpButton.setText(KeyEvent.getKeyText(config.getKeyBinding(ControlCode.PLAYER2UP)));
		userSettingsPlayer2UpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeKeyBinding(ControlCode.PLAYER2UP, config.getKeyBinding(ControlCode.PLAYER2UP));
			}
		});
		
		JLabel userSettingsPlayer2LeftLabel = new JLabel("Left:");
		userSettingsPlayer2Panel.add(userSettingsPlayer2LeftLabel);
		
		userSettingsPlayer2LeftButton = new JButton("Player2Left");
		userSettingsPlayer2Panel.add(userSettingsPlayer2LeftButton);
		userSettingsPlayer2LeftButton.setText(KeyEvent.getKeyText(config.getKeyBinding(ControlCode.PLAYER2LEFT)));
		userSettingsPlayer2LeftButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeKeyBinding(ControlCode.PLAYER2LEFT, config.getKeyBinding(ControlCode.PLAYER2LEFT));
			}
		});
		
		JLabel userSettingsPlayer2DownLabel = new JLabel("Down:");
		userSettingsPlayer2Panel.add(userSettingsPlayer2DownLabel);
		
		userSettingsPlayer2DownButton = new JButton("Player2Down");
		userSettingsPlayer2Panel.add(userSettingsPlayer2DownButton);
		userSettingsPlayer2DownButton.setText(KeyEvent.getKeyText(config.getKeyBinding(ControlCode.PLAYER2DOWN)));
		userSettingsPlayer2DownButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeKeyBinding(ControlCode.PLAYER2DOWN, config.getKeyBinding(ControlCode.PLAYER2DOWN));
			}
		});
		
		JLabel userSettingsPlayer2RightLabel = new JLabel("Right:");
		userSettingsPlayer2Panel.add(userSettingsPlayer2RightLabel);
		
		userSettingsPlayer2RightButton = new JButton("Player2Right");
		userSettingsPlayer2Panel.add(userSettingsPlayer2RightButton);
		userSettingsPlayer2RightButton.setText(KeyEvent.getKeyText(config.getKeyBinding(ControlCode.PLAYER2RIGHT)));
		userSettingsPlayer2RightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeKeyBinding(ControlCode.PLAYER2RIGHT, config.getKeyBinding(ControlCode.PLAYER2RIGHT));
			}
		});
		
		userSettingsPlayer2ImagePanel = new JPanel();
		sl_userSettingsPanel.putConstraint(SpringLayout.NORTH, userSettingsPlayer2ImagePanel, 10, SpringLayout.SOUTH, userSettingsPlayer2Panel);
		sl_userSettingsPanel.putConstraint(SpringLayout.WEST, userSettingsPlayer2ImagePanel, 10, SpringLayout.WEST, userSettingsPanel);
		sl_userSettingsPanel.putConstraint(SpringLayout.SOUTH, userSettingsPlayer2ImagePanel, 50, SpringLayout.SOUTH, userSettingsPlayer2Panel);
		sl_userSettingsPanel.putConstraint(SpringLayout.EAST, userSettingsPlayer2ImagePanel, -10, SpringLayout.EAST, userSettingsPanel);
		userSettingsPanel.add(userSettingsPlayer2ImagePanel);
		userSettingsPlayer2ImagePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel userSettingsPlayer2ImageLabel = new JLabel("Player 2 - Image:");
		userSettingsPlayer2ImagePanel.add(userSettingsPlayer2ImageLabel);
		
		userSettingsPlayer2Image = new JComponent() {
			protected void paintComponent(Graphics g) {
				g.drawImage(config.getPlayer2Image(), 0, 0, null);
			}
		};
		userSettingsPlayer2Image.setPreferredSize(new Dimension(14, 14));
		userSettingsPlayer2ImagePanel.add(userSettingsPlayer2Image);
		
		userSettingsPlayer2ImageChooseButton = new JButton("Choose");
		userSettingsPlayer2ImagePanel.add(userSettingsPlayer2ImageChooseButton);
		userSettingsPlayer2ImageChooseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.showOpenDialog(null);
				// TODO: Handle exceptions.
				try {
					config.setPlayer2Image(ImageIO.read(fileChooser.getSelectedFile()));
				} catch (IOException e1) {
					
				}
				userSettingsPlayer2Image.repaint();
			}
		});
		
		userSettingsPlayer2ImageRandomiseButton = new JButton("Randomise");
		userSettingsPlayer2ImagePanel.add(userSettingsPlayer2ImageRandomiseButton);
		userSettingsPlayer2ImageRandomiseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				config.setRandomPlayer2Image();
				userSettingsPlayer2Image.repaint();
			}
		});
		
		userSettingsButtonPanel = new JPanel();
		sl_userSettingsPanel.putConstraint(SpringLayout.NORTH, userSettingsButtonPanel, -50, SpringLayout.SOUTH, userSettingsPanel);
		sl_userSettingsPanel.putConstraint(SpringLayout.WEST, userSettingsButtonPanel, 10, SpringLayout.WEST, userSettingsPanel);
		sl_userSettingsPanel.putConstraint(SpringLayout.SOUTH, userSettingsButtonPanel, -10, SpringLayout.SOUTH, userSettingsPanel);
		sl_userSettingsPanel.putConstraint(SpringLayout.EAST, userSettingsButtonPanel, -10, SpringLayout.EAST, userSettingsPanel);
		userSettingsPanel.add(userSettingsButtonPanel);
		userSettingsButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		userSettingsBackButton = new JButton("Back");
		userSettingsButtonPanel.add(userSettingsBackButton);
		userSettingsBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanel("mainMenuPanel");
			}
		});
		
		changeKeyBindingPanel = new JPanel();
		mainPanel.add(changeKeyBindingPanel, "changeKeyBindingPanel");
		SpringLayout sl_changeKeyBindingPanel = new SpringLayout();
		changeKeyBindingPanel.setLayout(sl_changeKeyBindingPanel);
		
		changeKeyBindingChangeLabel = new JLabel("Change key: <KEY>");
		sl_changeKeyBindingPanel.putConstraint(SpringLayout.NORTH, changeKeyBindingChangeLabel, 10, SpringLayout.NORTH, changeKeyBindingPanel);
		sl_changeKeyBindingPanel.putConstraint(SpringLayout.WEST, changeKeyBindingChangeLabel, 10, SpringLayout.WEST, changeKeyBindingPanel);
		sl_changeKeyBindingPanel.putConstraint(SpringLayout.SOUTH, changeKeyBindingChangeLabel, 160, SpringLayout.NORTH, changeKeyBindingPanel);
		sl_changeKeyBindingPanel.putConstraint(SpringLayout.EAST, changeKeyBindingChangeLabel, -10, SpringLayout.EAST, changeKeyBindingPanel);
		changeKeyBindingChangeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		changeKeyBindingChangeLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		changeKeyBindingPanel.add(changeKeyBindingChangeLabel);
		
		changeKeyBindingPreviousLabel = new JLabel("Previously: <OLD_KEY>");
		sl_changeKeyBindingPanel.putConstraint(SpringLayout.NORTH, changeKeyBindingPreviousLabel, 10, SpringLayout.SOUTH, changeKeyBindingChangeLabel);
		changeKeyBindingPreviousLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		changeKeyBindingPreviousLabel.setHorizontalAlignment(SwingConstants.CENTER);
		sl_changeKeyBindingPanel.putConstraint(SpringLayout.WEST, changeKeyBindingPreviousLabel, 10, SpringLayout.WEST, changeKeyBindingPanel);
		sl_changeKeyBindingPanel.putConstraint(SpringLayout.SOUTH, changeKeyBindingPreviousLabel, -10, SpringLayout.SOUTH, changeKeyBindingPanel);
		sl_changeKeyBindingPanel.putConstraint(SpringLayout.EAST, changeKeyBindingPreviousLabel, -10, SpringLayout.EAST, changeKeyBindingPanel);
		changeKeyBindingPanel.add(changeKeyBindingPreviousLabel);
		
		leaderboardPanel = new JPanel();
		mainPanel.add(leaderboardPanel, "leaderboardPanel");
		SpringLayout sl_leaderboardPanel = new SpringLayout();
		leaderboardPanel.setLayout(sl_leaderboardPanel);
		
		leaderboardDataPanel = new JScrollPane();
		sl_leaderboardPanel.putConstraint(SpringLayout.NORTH, leaderboardDataPanel, 10, SpringLayout.NORTH, leaderboardPanel);
		sl_leaderboardPanel.putConstraint(SpringLayout.WEST, leaderboardDataPanel, 10, SpringLayout.WEST, leaderboardPanel);
		sl_leaderboardPanel.putConstraint(SpringLayout.EAST, leaderboardDataPanel, -10, SpringLayout.EAST, leaderboardPanel);
		leaderboardPanel.add(leaderboardDataPanel);
		
		leaderboardButtonPanel = new JPanel();
		sl_leaderboardPanel.putConstraint(SpringLayout.NORTH, leaderboardButtonPanel, -50, SpringLayout.SOUTH, leaderboardPanel);
		sl_leaderboardPanel.putConstraint(SpringLayout.SOUTH, leaderboardDataPanel, -10, SpringLayout.NORTH, leaderboardButtonPanel);
		
		String[] leaderboardTableHeader = {"Strategy", "Width", "Height", "Time", "Moves", "Coins", "Seed"};
		String[][] leaderboardTableContent = config.getLeaderboardTable();
		
		// TODO: Add the ability to sort this via the top.
		leaderboardDataTable = new JTable(new DefaultTableModel(leaderboardTableContent, leaderboardTableHeader));
		leaderboardDataTable.setDefaultEditor(Object.class, null);
		leaderboardDataTable.setFillsViewportHeight(true);
		
		leaderboardDataTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (((ListSelectionModel)e.getSource()).getMaxSelectionIndex() == -1) {
					leaderboardChallengeButton.setEnabled(false);
					leaderboardDeleteEntryButton.setEnabled(false);
				} else {
					leaderboardChallengeButton.setEnabled(true);
					leaderboardDeleteEntryButton.setEnabled(true);
				}
			}
		});
		
		leaderboardDataPanel.setViewportView(leaderboardDataTable);
		sl_leaderboardPanel.putConstraint(SpringLayout.WEST, leaderboardButtonPanel, 10, SpringLayout.WEST, leaderboardPanel);
		sl_leaderboardPanel.putConstraint(SpringLayout.SOUTH, leaderboardButtonPanel, -10, SpringLayout.SOUTH, leaderboardPanel);
		sl_leaderboardPanel.putConstraint(SpringLayout.EAST, leaderboardButtonPanel, -10, SpringLayout.EAST, leaderboardPanel);
		leaderboardPanel.add(leaderboardButtonPanel);
		leaderboardButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		leaderboardBackButton = new JButton("Back");
		leaderboardButtonPanel.add(leaderboardBackButton);
		leaderboardBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanel("mainMenuPanel");
			}
		});
		
		leaderboardChallengeButton = new JButton("Challenge");
		leaderboardButtonPanel.add(leaderboardChallengeButton);
		leaderboardChallengeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] selection = config.getLeaderboardTable()[leaderboardDataTable.getSelectedRow()];
				// TODO: Move this part into its own function, it gets called a few times.
				customGameSetupSizeXField.setEnabled(true);
				customGameSetupSizeYField.setEnabled(true);
				customGameSetupDFSRadio.setEnabled(true);
				customGameSetupRingRadio.setEnabled(true);
				
				switch (MazeGenerationStrategy.fromString(selection[0])) {
				case DEPTHFIRSTSEARCH:
					customGameSetupDFSRadio.setSelected(true);
					break;
				case RING:
					customGameSetupRingRadio.setSelected(true);
					break;
				default:
					break;
				}
				customGameSetupDiffcultyCustomRadio.setSelected(true);
				customGameSetupSizeXField.setText(selection[1]);
				customGameSetupSizeYField.setText(selection[2]);
				customGameSetupSeedField.setText(selection[6]);
				switchPanel("customGameSetupPanel");
			}
		});
		leaderboardChallengeButton.setEnabled(false);
		
		leaderboardDeleteEntryButton = new JButton("Delete Entry");
		leaderboardButtonPanel.add(leaderboardDeleteEntryButton);
		leaderboardDeleteEntryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO: Add a confirmation box.
				config.deleteLeaderboardEntry(leaderboardDataTable.getSelectedRow());
				((DefaultTableModel)leaderboardDataTable.getModel()).removeRow(leaderboardDataTable.getSelectedRow());
			}
		});
		leaderboardDeleteEntryButton.setEnabled(false);
		
		customGameSetupBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanel("mainMenuPanel");
			}
		});
		
		mainMenuUserSettingsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanel("userSettingsPanel");
			}
		});
		
		// Finally, the program should start with the main menu.
		switchPanel("mainMenuPanel");
	}
	
	/**
	 * Draws the current game's maze.
	 */
	public void drawMazeSwingOne() {
		// Modify this value to your liking.
		final int wallSize = 1;
		
		Maze m = game.getMaze();
		
		BufferedImage image = new BufferedImage(tileSize * m.getWidth(), tileSize * m.getHeight(), BufferedImage.TYPE_INT_ARGB);
		
		// Modify these to your liking.
		Color wall = Color.BLACK;
		Color space = Color.WHITE;
		Color start = new Color(200, 200, 255);
		Color end = new Color(255, 200, 200);
		
		// TODO: This needs to be refactored, it's too copy-paste.
		// x and y are the map co-ordinates, a and b are the current tile pixel position.
		for (int y = 0; y < m.getHeight(); y++) {
			for (int x = 0; x < m.getWidth(); x++) {
				for (int b = 0; b < tileSize; b++) {
					for (int a = 0; a < tileSize; a++) {
						// If in the corners of a tile.
						if ((a < wallSize || a >= tileSize - wallSize) && (b < wallSize || b >= tileSize - wallSize)) {
							image.setRGB(x * tileSize + a, y * tileSize + b, wall.getRGB());
						} else if (a < wallSize) { // If on the left side.
							if (m.isConnected(x,  y, Direction.LEFT)) {
								image.setRGB(x * tileSize + a, y * tileSize + b, space.getRGB());
							} else {
								image.setRGB(x * tileSize + a, y * tileSize + b, wall.getRGB());
							}
						} else if (a >= tileSize - wallSize) { // If on the right side.
							if (m.isConnected(x, y, Direction.RIGHT)) {
								image.setRGB(x * tileSize + a, y * tileSize + b, space.getRGB());
							} else {
								image.setRGB(x * tileSize + a, y * tileSize + b, wall.getRGB());
							}
						} else if (b < wallSize) { // If on the top side.
							if (m.isConnected(x, y, Direction.UP)) {
								image.setRGB(x * tileSize + a, y * tileSize + b, space.getRGB());
							} else {
								image.setRGB(x * tileSize + a, y * tileSize + b, wall.getRGB());
							}
						} else if (b >= tileSize - wallSize) { // If on the bottom side.
							if (m.isConnected(x, y, Direction.DOWN)) {
								image.setRGB(x * tileSize + a, y * tileSize + b, space.getRGB());
							} else {
								image.setRGB(x * tileSize + a, y * tileSize + b, wall.getRGB());
							}
						} else { // In the centre.
							if (m.getStart().equals(new Coord(x, y))) {
								image.setRGB(x * tileSize + a, y * tileSize + b, start.getRGB());	
							} else if (m.getEnd().equals(new Coord(x, y))) {
								image.setRGB(x * tileSize + a, y * tileSize + b, end.getRGB());
							} else {
								image.setRGB(x * tileSize + a, y * tileSize + b, space.getRGB());
							}
						}
					}
				}
			}
		}
		mazeImg = image;
		mazeViewImageComponent.setMazeImg(mazeImg);
	}

	/**
	 * Sets the label text for time.
	 * @param timeText
	 * The string to show.
	 */
	public void setTimeText(String timeText) {
		if (this.timeLabel != null) {
			this.timeLabel.setText(timeText);
		}
	}
	
	/**
	 * Sets the label text for total moves.
	 * @param moveText
	 * The string to show.
	 */
	public void setMoveText(String moveText) {
		if (this.moveLabel != null) {
			this.moveLabel.setText(moveText);
		}
	}
	
	/**
	 * Sets the label text for the game's seed.
	 * @param seedText
	 * The string to show.
	 */
	public void setSeedText(String seedText){
		if (this.seedLabel != null) {
			this.seedLabel.setText(seedText);
		}
	}
	
	/**
	 * Sets the label text for the game's coins.
	 * @param coinText
	 * The string to show.
	 */
	public void setCoinText(String coinText){
		if (this.coinLabel != null) {
			this.coinLabel.setText(coinText);
		}
	}
	
	/**
	 * Switches the main panel to the one with a given name.
	 * @param panelName
	 * The name of the panel.
	 */
	public void switchPanel(String panelName) {
		mainPanelCardLayout.show(mainPanel, panelName);
	}
	
	/**
	 * Stores a given maze in the UI for easy reference.
	 * It also gives it to the mazeViewImageComponent.
	 * @param g
	 * The game in question (can be null).
	 */
	public void setGame(MazeGame g) {
		this.game = g;
		mazeViewImageComponent.setGame(g);
	}
	
	/**
	 * Hides the maze screen and pauses the game.
	 * @param willBePaused
	 * Whether the game should be paused or not.
	 */
	public void showPause(boolean willBePaused) {
		if (willBePaused) {
			mazeViewImageComponent.setEnabled(false);
			mazeViewImageComponent.setVisible(false);
			mazeViewPauseLabel.setEnabled(true);
			mazeViewPauseLabel.setVisible(true);
		} else {
			mazeViewImageComponent.setEnabled(true);
			mazeViewImageComponent.setVisible(true);
			mazeViewPauseLabel.setEnabled(false);
			mazeViewPauseLabel.setVisible(false);
		}
	}

	/**
	 * Prints the map to the console using a specific character for walls and spaces.
	 * It draws walls as their own spots in the grid.
	 * @param m
	 * The maze to be drawn.
	 */
	public static void printMazeASCII(Maze m) {
		
		// You can easily modify the appearance here.
		final char wallChar = '#';
		final char spaceChar = '.';
		final String wall = Character.toString(wallChar);
		final String space = Character.toString(spaceChar);
		
		// Firstly, the top wall is printed.
		for (int i = 0; i < m.getWidth() * 2 + 1; i++) {
			System.out.print(wall);
		}
		
		System.out.println();
			
		// For each y row, we have two loops.
		for (int y = 0; y < m.getHeight(); y++) {
			// Left wall is printed.
			System.out.print(wall);
			// Then, each space is printed, followed by whether it has a
			// connection to the right. This will always draw the right wall.
			for (int x = 0; x < m.getWidth(); x++) {
				System.out.print(space);
				if (m.isConnected(x, y, Direction.RIGHT)) {
					System.out.print(space);
				} else {
					System.out.print(wall);
				}
			}
			System.out.println();
			
			// Another left wall is printed.
			System.out.print(wall);
			// Finally, each space's down connection is printed, followed by
			// a wall.
			for (int x = 0; x < m.getWidth(); x++) {
				if (m.isConnected(x, y, Direction.DOWN)) {
					System.out.print(space);
				} else {
					System.out.print(wall);
				}
				System.out.print(wall);
			}
			System.out.println();
		}
	}
	
	/**
	 * Creates a random RGB colour.
	 * @return
	 * The colour generated.
	 */
	public static Color getRandomColor() {
		Random rand = new Random();
		// TODO: This can give really bad colours sometimes (close to white for example). Make it create interesting colours (or just force set primary colours).
		return new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
	}
	
	/**
	 * Presents a UI panel and handles changing a key binding.
	 * @param keyBinding
	 * The key to change.
	 * @param previousKey
	 * The previous key this binding was set to.
	 */
	private void changeKeyBinding(ControlCode keyBinding, int previousKey) {
		final ControlCode finalKeyBinding = keyBinding;
		
		switchPanel("changeKeyBindingPanel");
		
		changeKeyBindingChangeLabel.setText("Change key: " + keyBinding.toString());
		changeKeyBindingPreviousLabel.setText("Previous: " + KeyEvent.getKeyText(previousKey));
		
		changeKeyBindingPanel.grabFocus();
		
		changeKeyBindingPanel.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() != KeyEvent.VK_ESCAPE) {
					config.setKeyBinding(finalKeyBinding, e.getKeyCode());
					switch (finalKeyBinding) {
					case PLAYER1UP:
						userSettingsPlayer1UpButton.setText(KeyEvent.getKeyText(e.getKeyCode()));
						break;
					case PLAYER1RIGHT:
						userSettingsPlayer1RightButton.setText(KeyEvent.getKeyText(e.getKeyCode()));
						break;
					case PLAYER1DOWN:
						userSettingsPlayer1DownButton.setText(KeyEvent.getKeyText(e.getKeyCode()));
						break;
					case PLAYER1LEFT:
						userSettingsPlayer1LeftButton.setText(KeyEvent.getKeyText(e.getKeyCode()));
						break;
					case PLAYER2UP:
						userSettingsPlayer2UpButton.setText(KeyEvent.getKeyText(e.getKeyCode()));
						break;
					case PLAYER2RIGHT:
						userSettingsPlayer2RightButton.setText(KeyEvent.getKeyText(e.getKeyCode()));
						break;
					case PLAYER2DOWN:
						userSettingsPlayer2DownButton.setText(KeyEvent.getKeyText(e.getKeyCode()));
						break;
					case PLAYER2LEFT:
						userSettingsPlayer2LeftButton.setText(KeyEvent.getKeyText(e.getKeyCode()));
						break;
					default:
					}
				}
				
				switchPanel("userSettingsPanel");
				changeKeyBindingPanel.removeKeyListener(this);
			}

			public void keyReleased(KeyEvent e) {}
		});
	}
	
	/**
	 * Draws the coin image.
	 * @return
	 * The coin image.
	 */
	private BufferedImage drawCoinImg() {
		BufferedImage img = new BufferedImage(tileSize, tileSize, BufferedImage.TYPE_INT_ARGB);
		
		for (int y = 0; y < tileSize; y++) {
			for (int x = 0; x < tileSize; x++) {
				if (x < 5 || x >= tileSize - 5 || y < 5 || y >= tileSize - 5) {
					img.setRGB(x, y, new Color(0, 0, 0, 0).getRGB());
				} else if (x == 5 || x == tileSize - 6 || y == 5 || y == tileSize - 6) {
					img.setRGB(x, y, new Color(32, 32, 32, 255).getRGB());
				} else {
					img.setRGB(x, y, new Color(250, 250, 90, 255).getRGB());
				}
			}
		}
		
		return img;
	}
	
	/**
	 * Draws the partial coin image.
	 * @return
	 * The partial coin image.
	 */
	private BufferedImage drawCoinPartialImg() {
		BufferedImage img = new BufferedImage(tileSize, tileSize, BufferedImage.TYPE_INT_ARGB);
		
		for (int y = 0; y < tileSize; y++) {
			for (int x = 0; x < tileSize; x++) {
				if (x < 5 || x >= tileSize - 5 || y < 5 || y >= tileSize - 5) {
					img.setRGB(x, y, new Color(0, 0, 0, 0).getRGB());
				} else if (x == 5 || x == tileSize - 6 || y == 5 || y == tileSize - 6) {
					img.setRGB(x, y, new Color(32, 32, 32, 255).getRGB());
				} else {
					img.setRGB(x, y, new Color(190, 190, 190, 255).getRGB());
				}
			}
		}
		return img;
	}
	
	/**
	 * Adds a row to the leaderboard table.
	 * @param row
	 * The table row to add.
	 */
	public void addLeaderboardRow(String[] row) {
		((DefaultTableModel)leaderboardDataTable.getModel()).addRow(row);
	}
}
