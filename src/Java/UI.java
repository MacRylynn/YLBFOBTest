package Java;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageProducer;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.JTextComponent;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

public class UI {

	JFileChooser jfc = new JFileChooser();// 文件选择器

	protected static boolean isopen = true;// 判断当前窗口是否打开的标志

	private JFrame frame;

	private String pathString;

	private JTextField textField;

	private JTextField textField_1;

	private JTextField textField_2;

	private JTextField textField_3;

	private JTextField textField_4;

	private JTextField textField_5;

	private JTextField textField_6;

	private JTextField jtf;// 批量检测用的

	private JProgressBar progressBar;

	private JProgressBar progressBar_1, progressBar_2, progressBar_3, progressBar_4;

	private JLabel label_2;

	private String path = null;

	private boolean isCloudCalc = false;

	private boolean isCloudCalcOpen = false; // 默认是开启云计算

	private JProgressBar progressBar_5;

	private int exeNum = 4;

	JMenuItem menuItem = null;
	JMenuItem menuItem_4 = new JMenuItem("\u6539\u53D8\u754C\u9762\u98CE\u683C");

	private boolean batch = false;// 批量检测是静止单张检测及再次按批量检测
	private JRadioButton radioButton_2;
	private JRadioButton radioButton;
	private JRadioButton radioButton_1;
	private ButtonGroup bg;
	ImageIcon icon;

	/**
	 * 
	 * Launch the application.
	 * 
	 */

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			@Override

			public void run() {

				try {

					UI window = new UI();
					Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

					window.frame.setVisible(true);
					// frame的宽大概是(944,650)
					int xPos = (int) screenSize.getWidth() / 2 - 472;
					int yPos = (int) screenSize.getHeight() / 2 - 325;
					window.frame.setLocation(xPos, yPos);

					window.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				} catch (Exception e) {

					e.printStackTrace();

				}

				InitGlobalFont(new Font("微软雅黑", Font.PLAIN, 12));
				try {
					JFrame.setDefaultLookAndFeelDecorated(true);

					org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();

					BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencySmallShadow;

					org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();

					UIManager.put("RootPane.setupButtonVisible", false);

					BeautyEyeLNFHelper.translucencyAtFrameInactive = false;

				} catch (Exception e) {

					System.err.println("set skin fail!");

				}

			}

		});

	}

	private static void InitGlobalFont(Font font) {

		FontUIResource fontRes = new FontUIResource(font);

		for (Enumeration<Object> keys = UIManager.getDefaults().keys(); keys.hasMoreElements();) {

			Object key = keys.nextElement();

			Object value = UIManager.get(key);

			if (value instanceof FontUIResource) {

				UIManager.put(key, fontRes);

			}

		}

	}

	/**
	 * 
	 * Create the application.
	 * 
	 */

	public UI() {

		initialize();

	}

	/**
	 * 
	 * Initialize the contents of the frame.
	 * 
	 */

	private void initialize() {

		UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("微软雅黑", Font.BOLD, 13)));

		// 设置文本显示效果

		UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("微软雅黑", Font.BOLD, 13)));

		// 整体的窗口

		frame = new JFrame("新日暮里");

		frame.setResizable(false);

		frame.getContentPane().setBackground(Color.LIGHT_GRAY);

		ImageIcon image = new ImageIcon(".\\src\\app.png");

		frame.setIconImage(image.getImage());

		frame.getContentPane().setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));

		frame.getContentPane().setLayout(null);

		// 主界面的Panel
		MyJPanel panel = new MyJPanel();
//		{
//			@Override
//			public void paintComponent(Graphics g) {
//				ImageIcon icon = new ImageIcon(".\\background\\background1.jpg");
//
//				// 图片随窗体大小而变化
//				g.drawImage(icon.getImage(), 0, 0, frame.getSize().width, frame.getSize().height, frame);
//			}
//		};
		panel.setBounds(0, 0, 944, 631);
		panel.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JTextArea txtrCopyright = new JTextArea();
		txtrCopyright.setBackground(Color.LIGHT_GRAY);
		txtrCopyright.setFont(new Font("微软雅黑 Light", Font.BOLD, 13));
		txtrCopyright.setText("Copyright © 2018 XRML All Rights Reserved");
		txtrCopyright.setBounds(317, 597, 310, 20);
		panel.add(txtrCopyright);

		textField_4 = new JTextField();

		textField_4.setHorizontalAlignment(SwingConstants.CENTER);

		textField_4.setToolTipText("");

		textField_4.setBounds(682, 88, 164, 30);

		textField_4.setBackground(Color.LIGHT_GRAY);

		textField_4.setForeground(new Color(255, 255, 255));

		textField_4.setFont(new Font("微软雅黑", Font.PLAIN, 12));

		textField_4.setEditable(false);

		textField_4.setText("\u6D4B\u8BD5\u7ED3\u679C\uFF08\u6309\u6982\u7387\u6392\u5E8F\uFF09");

		textField_4.setColumns(10);

		panel.add(textField_4);

		// 单张图测试

		textField_7 = new JTextField();
		textField_7.setToolTipText("点击查看大图");

		drag(textField_7);

		textField_7.setBounds(15, 133, 570, 428);

		textField_7.setOpaque(false);

		textField_7.setColumns(10);

		textField_7.addFocusListener(new FocusListener() {

			@Override

			public void focusGained(FocusEvent e) {

				if ("请将待测试图片或文件夹拖拽于此".equalsIgnoreCase(jtf.getText())) {

					textField_7.setText("");

				}

			}

			@Override

			public void focusLost(FocusEvent e) {

				if ("".equals(jtf.getText())) {

					textField_7.setText("请将待测试图片或文件夹拖拽于此");

				}

			}

		});

		panel.add(textField_7);

		drag(textField_7);

		btnNewButton = new JButton("\u5355\u5F20\u68C0\u6D4B");

		btnNewButton.setToolTipText("\u8BF7\u9009\u62E9\u56FE\u7247\u540E\u70B9\u51FB\u5F00\u542F\u68C0\u6D4B");

		btnNewButton.setBackground(Color.WHITE);

		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));

		btnNewButton.setBounds(625, 315, 120, 60);

		btnNewButton.addActionListener(new ActionListener() {

			@Override

			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub
				if (batch) {
					JOptionPane.showMessageDialog(panel, "请等待批量检测完");
					return;
				}

				System.out.println(label_2.getIcon().toString());

				if (label_2.getIcon() == null || label_2.getIcon().toString().contains("icon.png")) {

					JOptionPane.showMessageDialog(frame, "请拖拽图片到软件红色区域", " 警告", JOptionPane.INFORMATION_MESSAGE,

							new ImageIcon(".\\src\\warning.png"));

					return;

				}

				textField_7.setText("");

				String testName = testNameGet.getText().toString();
				if (testName != null) {
					System.out.println(label_2.getIcon());
					test(label_2.getIcon().toString(), testName);
					JOptionPane.showMessageDialog(frame, "检测完毕，请点击“确认”结束", " 说明", JOptionPane.INFORMATION_MESSAGE,

							new ImageIcon(".\\src\\success.png"));
				}

				pathString = path;

				path = null;

			}

		});

		panel.add(btnNewButton);

		label_2 = new JLabel("");

		label_2.setForeground(Color.DARK_GRAY);

		label_2.setToolTipText("\u70B9\u51FB\u67E5\u770B\u5927\u56FE");

		label_2.setHorizontalAlignment(SwingConstants.CENTER);

		label_2.setBounds(15, 133, 570, 428);

		label_2.setBackground(Color.DARK_GRAY);

		label_2.setIcon(new ImageIcon(".\\src\\icon.png"));

		panel.add(label_2);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(Color.WHITE);
		menuBar.setBackground(Color.LIGHT_GRAY);

		menuBar.setBounds(0, 0, 994, 41);

		panel.add(menuBar);

		// 显示结果的panel

		JPanel panel_1 = new JPanel();

		panel_1.setBounds(595, 133, 335, 166);

		panel_1.setBackground(new Color(65, 105, 225));

//		panel.add(panel_1);

		panel_1.setLayout(new GridLayout(0, 2, 0, 0));

		textField_5 = new JTextField();

		textField_5.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		panel_1.add(textField_5);

		textField_5.setEditable(false);

		textField_5.setColumns(10);

		progressBar = new JProgressBar();

		progressBar.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		progressBar.setMinimum(0);

		progressBar.setMaximum(10000);

		progressBar.setStringPainted(true);

		progressBar.setBorderPainted(true);

		Color color1 = new Color(255, 0, 0);

		progressBar.setForeground(color1);

		progressBar.setBackground(Color.WHITE);

		panel_1.add(progressBar);

		progressBar_1 = new JProgressBar();

		progressBar_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		progressBar_1.setMinimum(0);

		progressBar_1.setMaximum(10000);

		progressBar_1.setStringPainted(true);

		progressBar_1.setBorderPainted(true);

		Color color2 = new Color(243, 46, 3);

		textField = new JTextField();

		textField.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		panel_1.add(textField);

		textField.setEditable(false);

		textField.setColumns(10);

		progressBar_1.setForeground(color2);

		progressBar_1.setBackground(Color.WHITE);

		panel_1.add(progressBar_1);

		progressBar_2 = new JProgressBar();

		progressBar_2.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		progressBar_2.setMinimum(0);

		progressBar_2.setMaximum(10000);

		progressBar_2.setStringPainted(true);

		progressBar_2.setBorderPainted(true);

		Color color3 = new Color(252, 77, 39);

		textField_1 = new JTextField();

		textField_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		panel_1.add(textField_1);

		textField_1.setEditable(false);

		textField_1.setColumns(10);

		progressBar_2.setForeground(color3);

		progressBar_2.setBackground(Color.WHITE);

		panel_1.add(progressBar_2);

		progressBar_3 = new JProgressBar();

		progressBar_3.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		progressBar_3.setMinimum(0);

		progressBar_3.setMaximum(10000);

		progressBar_3.setStringPainted(true);

		progressBar_3.setBorderPainted(true);

		Color color4 = new Color(253, 123, 96);

		textField_2 = new JTextField();

		textField_2.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		panel_1.add(textField_2);

		textField_2.setEditable(false);

		textField_2.setColumns(10);

		progressBar_3.setForeground(color4);

		progressBar_3.setBackground(Color.WHITE);

		panel_1.add(progressBar_3);

		progressBar_4 = new JProgressBar();

		progressBar_4.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		progressBar_4.setMinimum(0);

		progressBar_4.setMaximum(10000);

		progressBar_4.setStringPainted(true);

		progressBar_4.setBorderPainted(true);

		Color color5 = new Color(253, 128, 125);

		textField_3 = new JTextField();

		textField_3.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		panel_1.add(textField_3);

		textField_3.setEditable(false);

		textField_3.setColumns(10);

		progressBar_4.setForeground(color5);

		progressBar_4.setBackground(Color.WHITE);

		panel_1.add(progressBar_4);

		textField_7.addMouseListener(new MouseAdapter() {

			@Override

			public void mouseClicked(MouseEvent arg0) {

				// TODO Auto-generated method stub

				if (label_2.getIcon() != null) {
					System.out.println(label_2.getIcon());
					JFrame frame1 = new JFrame("显示原图");

					// 设置在屏幕的位置

					frame1.setLocation(50, 0);

					ImageIcon image = new ImageIcon(".\\src\\app.png");

					frame1.setIconImage(image.getImage());

					// 窗体大小

					frame1.setSize(1280, 960);

					// 显示窗体

					frame1.setVisible(true);

					JPanel jp = new JPanel();

					frame1.getContentPane().add(jp);

					JLabel jb = new JLabel();

					jb.setBounds(0, 0, 1280, 960);

					jp.add(jb);

//					ImageIcon ic = null;
//
//					try {
//						
//						BufferedImage bi = ImageIO.read(new File(label_2.getIcon().toString()));
//
//
//
//						ImageProducer producer = bi.getSource();
//
//
//
//						Toolkit toolkit = Toolkit.getDefaultToolkit();
//
//
//
//						Image image2 = toolkit.createImage(producer);
//
//
//
//						ic = new ImageIcon(image2);
//					
//						
//						
//
//
//					} catch (Exception e) {
//
//						e.printStackTrace();
//
//					}
					BufferedImage bi;
					try {
						bi = ImageIO.read(new File(label_2.getIcon().toString()));
						ImageProducer producer = bi.getSource();

						Toolkit toolkit = Toolkit.getDefaultToolkit();

						Image image2 = toolkit.createImage(producer);

						ImageIcon bmp = new ImageIcon(image2);
						image.setDescription(label_2.getIcon().toString());

//						ImageIcon image2 = new ImageIcon(label_2.getIcon().toString());

//						System.out.println(label_2.getIcon().toString());

						// 放大图片
						bmp.setImage(bmp.getImage().getScaledInstance(1280, 960, Image.SCALE_DEFAULT));

						jb.setIcon(bmp);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {

					JOptionPane.showMessageDialog(frame, "请先拖入图片并检测", " 警告", JOptionPane.INFORMATION_MESSAGE,

							new ImageIcon(".\\src\\warning.png"));

				}

			}

		});

		JMenu menu = new JMenu("\u8BB0\u5F55\u67E5\u8BE2");

		menu.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

		menuBar.add(menu);

		JMenuItem menuItem_3 = new JMenuItem("搜索记录");
		menuItem_3.setIcon(new ImageIcon(".\\src\\search.png"));

		menuItem_3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

		menuItem_3.addActionListener(new ActionListener() {

			@Override

			public void actionPerformed(ActionEvent e) {

				// 创建新的窗口

				if (isopen) {

					RecoderPanel recoderPanel = new RecoderPanel();
					Dimension screenSizeNew = Toolkit.getDefaultToolkit().getScreenSize();

					// frame的宽大概是(944,650)
					int xPosNew = (int) screenSizeNew.getWidth() / 2 - 450;
					int yPosNew = (int) screenSizeNew.getHeight() / 2 - 300;

					recoderPanel.setLocation(xPosNew, yPosNew);

					ImageIcon image = new ImageIcon(".\\src\\app.png");

					recoderPanel.setIconImage(image.getImage());

				}

				isopen = false;

			}

		});

		menu.add(menuItem_3);

		JMenu menu_1 = new JMenu("\u8BBE\u7F6E");

		menu_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

		menuBar.add(menu_1);

		menuItem = new JMenuItem("开启云计算");
		menuItem.setIcon(new ImageIcon(".\\src\\cloud.png"));
		menuItem.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

		menuItem.addActionListener(new ActionListener() {

			@Override

			public void actionPerformed(ActionEvent e) {

				if (isCloudCalcOpen) {

					isCloudCalc = true;

					menuItem.setText("开启云计算");

					menuItem.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

					isCloudCalcOpen = false;

				} else {

					isCloudCalc = false;

					isCloudCalcOpen = true;

					menuItem.setText("关闭云计算");

					menuItem.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

				}

			}

		});

		menu_1.add(menuItem);

		JMenu menu_2 = new JMenu("\u5E2E\u52A9");

		menu_2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

		menuBar.add(menu_2);

		JMenuItem menuItem_1 = new JMenuItem("\u67E5\u770B\u4F7F\u7528\u8BF4\u660E");
		menuItem_1.setIcon(new ImageIcon(".\\src\\expressl.png"));
		menuItem_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

		menuItem_1.addActionListener(new ActionListener() {

			@Override

			public void actionPerformed(ActionEvent e) {

				// 显示版本信息

				// 设置按钮显示效果

				JOptionPane.showMessageDialog(null, "\r\n" + "软件用途：FOB卡检测\n" + "软件使用步骤：\n" + "一、测试模式选择\r\n"

						+ "【单张检测模式】\r\n" + "将需要测试的图片拖拽到软件中红色背景图片处后，输入测试者的姓名或者编号,然后点击“单张检测”；\r\n"

						+ "请等待至检测完成，弹出对话框后点击“确认”完成；\n" + "在【单张检测模式】检测之后，点击中间的被检测图片可以查看原图。\n" + "【批量检测模式】\r\n"

						+ "将需要检测的图片文件夹拖拽到下方的输入框内，输入测试者的姓名或者编号，然后点击“批量测试”；\r\n" + "在【批量检测模式】下，请等待检测进度到达100%表示检测完成。\n"

						+ "二、检测结果和记录\r\n" + "检测结果会按照可能性从大到小排列，概率最大的即为软件最终检测出来的结果；\n"

						+ "检测之后的结果会保存下来，可以通过界面的“记录查询”->“搜索”来搜索、查看检测的记录；\n" + "选中感兴趣的结果后点击“查看详情”获取检测具体情况。\r\n"
						+ "三、结果对照\r\n" + "L：代表卡污染；\r\n"

						+ "N：代表阴性；\r\n" + "P：代表阳性；\r\n" + "WP：代表弱阳；\r\n" + "Y：代表图片异常（FOB卡片损坏或残缺）。\r\n" + "四、注意事项：\n"

						+ "请确保图片和软件文件夹放置的路径没有中文字符或空格；\n" + "请确保电脑的网络连接正常；\n" + "处理速度的快慢主要受电脑配置的影响；\n"

						+ "本地批量处理时默认开启4个线程，可以通过面板上面选择同时处理图片的线程数目，更好地适应不同CPU；\n"
						+ "可以通过设置里面的“开启云计算”使用云计算来批量处理图片，避免本地计算机CPU使用率过高", "使用说明", JOptionPane.INFORMATION_MESSAGE,

						new ImageIcon(".\\src\\express.png"));

			}

		});

		menu_2.add(menuItem_1);

		JMenuItem menuItem_2 = new JMenuItem("\u7248\u672C\u4FE1\u606F");
		menuItem_2.setIcon(new ImageIcon(".\\src\\versionl.png"));
		menuItem_2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

		menuItem_2.addActionListener(new ActionListener() {

			@Override

			public void actionPerformed(ActionEvent e) {

				// 显示版本信息

				JOptionPane.showMessageDialog(null, "Version1.0 ", " 版本号  CopyRight XRML",

						JOptionPane.INFORMATION_MESSAGE, new ImageIcon(".\\src\\version.png"));

			}

		});

		menu_2.add(menuItem_2);

		textField_6 = new JTextField();
		textField_6.setHorizontalAlignment(SwingConstants.CENTER);

		textField_6.setText("\u6D4B\u8BD5\u539F\u56FE");

		textField_6.setForeground(Color.WHITE);

		textField_6.setFont(new Font("微软雅黑", Font.PLAIN, 22));

		textField_6.setEditable(false);

		textField_6.setColumns(10);

		textField_6.setBackground(Color.LIGHT_GRAY);

		textField_6.setBounds(255, 66, 100, 35);

		panel.add(textField_6);

		panel.add(panel_1);

		// 批量检测模块

		JButton jb = new JButton("批量检测");
		jb.setBounds(783, 315, 120, 60);

		panel.add(jb);

		jb.setBackground(Color.WHITE);

		jb.setToolTipText(

				"请将图片文件夹拖至下框后点击。");

		jb.setFont(new Font("微软雅黑", Font.PLAIN, 16));

		jtf = new JTextField(30);
		jtf.setToolTipText("请把图片文件夹拖拽到此位置之后点击“批量检测”");
		jtf.setBounds(595, 404, 335, 66);

		panel.add(jtf);

		jtf.setForeground(Color.LIGHT_GRAY);

		jtf.setFont(new Font("微软雅黑", Font.PLAIN, 22));

		jtf.setText("\u8BF7\u5C06\u5F85\u6D4B\u8BD5\u56FE\u7247\u6587\u4EF6\u5939\u62D6\u62FD\u4E8E\u6B64");

		jtf.addFocusListener(new FocusListener() {

			@Override

			public void focusGained(FocusEvent e) {

				if ("请将待测试图片或文件夹拖拽于此".equalsIgnoreCase(jtf.getText())) {

					jtf.setText("");

				}

			}

			@Override

			public void focusLost(FocusEvent e) {

				if ("".equals(jtf.getText())) {

					jtf.setText("请将待测试图片或文件夹拖拽于此");

				}

			}

		});

		drag(jtf);

		progressBar_5 = new JProgressBar();
		progressBar_5.setToolTipText("批量检测的进度条");
		progressBar_5.setBounds(595, 386, 335, 20);
		panel.add(progressBar_5);
		Color color = new Color(0, 255, 0);
		progressBar_5.setForeground(color);

		testNameGet = new JTextField();
		testNameGet.setToolTipText("请在此输入测试者姓名或者编号");
		testNameGet.setForeground(Color.LIGHT_GRAY);
		testNameGet.setFont(new Font("微软雅黑 Light", Font.PLAIN, 14));
		testNameGet.setText("TEST ID");
		testNameGet.setBounds(595, 523, 335, 38);
		testNameGet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				testNameGet.setText("");
			}
		});
		panel.add(testNameGet);
		testNameGet.setColumns(10);

		jb.addActionListener(new ActionListener() {

			@Override

			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub

				if (jtf.getText().trim().equals("请将待测试图片文件夹拖拽于此") || "".equals(jtf.getText().trim())) {

					JOptionPane.showMessageDialog(frame, "请拖拽图片文件夹到软件下方方框内", " 警告", JOptionPane.INFORMATION_MESSAGE,

							new ImageIcon(".\\src\\warning.png"));

					return;

				}
				if (batch) {
					return;// 如果此时正在批量检测，不允许再次检测。
				}

				String testName = testNameGet.getText().toString();
				System.out.println(testName + "***************************");
				if (testName != null) {
					String s = null;
					Enumeration<AbstractButton> radioBtns = bg.getElements();
					while (radioBtns.hasMoreElements()) {
						AbstractButton btn = radioBtns.nextElement();
						if (btn.isSelected()) {
							s = btn.getText().trim().substring(0, 1);
							break;
						}
					}
					exeNum = Integer.parseInt(s);
					System.out.println(exeNum);
					radioButton.setEnabled(false);
					radioButton_1.setEnabled(false);
					radioButton_2.setEnabled(false);
					test(jtf.getText().trim(), testName);

				}

				pathString = null;

				// }

			}

		});

		menuItem_4.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
		menuItem_4.setIcon(new ImageIcon(".\\src\\change.png"));
		menuItem_4.addActionListener(new ActionListener() {

			@Override

			public void actionPerformed(ActionEvent e) {

				MyJPanel.addI();
				panel.repaint();
			}

		});
		menu_1.add(menuItem_4);

//		JMenuItem menuItem_6 = new JMenuItem("增加处理线程");
//		menuItem_6.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
//		menuItem_6.addActionListener(new ActionListener() {
//
//			@Override
//
//			public void actionPerformed(ActionEvent e) {
//
//				exeNum = 6;
//			}
//
//		});
//		menu_1.add(menuItem_6);

//		JMenuItem menuItem_7 = new JMenuItem("减少处理线程");
//		menuItem_7.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
//		menu_1.add(menuItem_7);
//		menuItem_7.addActionListener(new ActionListener() {
//
//			@Override
//
//			public void actionPerformed(ActionEvent e) {
//
//				exeNum = 2;
//			}
//
//		});
		frame.setBounds(100, 100, 950, 660);

		Font f = new Font("宋体", Font.PLAIN, 14);
		JLabel label = new JLabel("速度：");
		label.setFont(new Font("微软雅黑 Light", Font.PLAIN, 14));
		label.setBounds(599, 483, 72, 18);
		panel.add(label);

		radioButton = new JRadioButton("2线并发");
		radioButton.setBounds(646, 479, 81, 27);
		panel.add(radioButton);
		radioButton.setFont(new Font("微软雅黑 Light", Font.PLAIN, 14));

		radioButton_1 = new JRadioButton("4线并发");
		radioButton_1.setBounds(733, 479, 81, 27);
		panel.add(radioButton_1);
		radioButton_1.setFont(new Font("微软雅黑 Light", Font.PLAIN, 14));

		radioButton_2 = new JRadioButton("6线并发");
		radioButton_2.setBounds(822, 479, 81, 27);
		panel.add(radioButton_2);
		radioButton_2.setFont(new Font("微软雅黑 Light", Font.PLAIN, 14));

		bg = new ButtonGroup();
		bg.add(radioButton);
		bg.add(radioButton_1);
		bg.add(radioButton_2);
		radioButton_1.setSelected(true);

	}

	/**
	 * 
	 * * 上传图片
	 * 
	 * 
	 * 
	 * @param urlStr *
	 * 
	 * @param textMap *
	 * 
	 * @param fileMap *
	 * 
	 * @param contentType 没有传入文件类型默认采用application/octet-stream *
	 * 
	 *                    contentType非空采用filename匹配默认的图片类型 *
	 * 
	 * @return 返回response数据
	 * 
	 */

	public String imageUpLoad(String urlStr, Map<String, String> textMap, Map<String, String> fileMap,

			String contentType) throws SocketTimeoutException {

		String res = "";

		HttpURLConnection conn = null; // boundary就是request头和上传文件内容的分隔符

		String BOUNDARY = "----WebKitFormBoundary7MA4YWxkTrZu0gW";

		try {

			URL url = new URL(urlStr);

			conn = (HttpURLConnection) url.openConnection();

			conn.setConnectTimeout(5000);

			conn.setReadTimeout(30000);

			conn.setDoOutput(true);

			conn.setDoInput(true);

			conn.setUseCaches(false);

			conn.setRequestMethod("POST");

			conn.setRequestProperty("Connection", "Keep-Alive");

			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");

			conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

			OutputStream out = new DataOutputStream(conn.getOutputStream()); // text

			if (textMap != null) {

				StringBuffer strBuf = new StringBuffer();

				Iterator iter = textMap.entrySet().iterator();

				while (iter.hasNext()) {

					Map.Entry entry = (Map.Entry) iter.next();

					String inputName = (String) entry.getKey();

					String inputValue = (String) entry.getValue();

					if (inputValue == null) {

						continue;

					}

					strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");

					strBuf.append("Content-Disposition: form-data; name=\"image" + inputName + "\"\r\n\r\n");

					strBuf.append(inputValue);

//					System.out.println(inputName + "," + inputValue);

				}

				out.write(strBuf.toString().getBytes());

			} // file

			if (fileMap != null) {

				Iterator iter = fileMap.entrySet().iterator();

				while (iter.hasNext()) {

					Map.Entry entry = (Map.Entry) iter.next();

					String inputValue = (String) entry.getValue();

					if (inputValue == null) {

						continue;

					}

					File file = new File(inputValue);

					String filename = file.getName(); // 没有传入文件类型，同时根据文件获取不到类型，默认采用application/octet-stream

					contentType = new MimetypesFileTypeMap().getContentType(file); // contentType非空采用filename匹配默认的图片类型

					if (!"".equals(contentType)) {

						if (filename.endsWith(".png")) {

							contentType = "image/png";

						} else if (filename.endsWith(".jpg") || filename.endsWith(".jpeg")

								|| filename.endsWith(".jpe")) {

							contentType = "image/jpeg";

						} else if (filename.endsWith(".gif")) {

							contentType = "image/gif";

						} else if (filename.endsWith(".ico")) {

							contentType = "image/image/x-icon";

						} else {

							contentType = "image/bmp";

						}

					}

					if (contentType == null || "".equals(contentType)) {

						contentType = "application/octet-stream";

					}

					StringBuffer strBuf = new StringBuffer();

					strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");

					strBuf.append("Content-Disposition: form-data; name=\"image\";filename=\"" + filename + "\"\r\n");

//					System.out.println(filename);

					strBuf.append("Content-Type:" + contentType + "\r\n\r\n");

					out.write(strBuf.toString().getBytes());

					DataInputStream in = new DataInputStream(new FileInputStream(file));

					int bytes = 0;

					byte[] bufferOut = new byte[1024];

					while ((bytes = in.read(bufferOut)) != -1) {

						out.write(bufferOut, 0, bytes);

					}

					in.close();

				}

			}

			byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();

			out.write(endData);

			out.flush();

			out.close(); // 读取返回数据

			StringBuffer strBuf = new StringBuffer();

			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String line = null;

			while ((line = reader.readLine()) != null) {

				strBuf.append(line).append("\n");

			}

			res = strBuf.toString();

			reader.close();

			reader = null;

		} catch (Exception e) {

			System.out.println("发送POST请求出错。" + urlStr);
			SocketTimeoutException se = new SocketTimeoutException();
			se.initCause(e);
			throw se;

		} finally {

			if (conn != null) {

				conn.disconnect();

				conn = null;

			}

		}

		return res;

	}

	/*
	 * 
	 * 用服务器处理本地的图片
	 * 
	 */

	public String cloudCalc(String path) throws IOException {

		String url = "http://47.106.157.157/predict";

		Map<String, String> textMap = new HashMap<String, String>(); // 普通参数：可以设置多个input的name，value

		textMap.put("name", "testname");

		// 文件：设置file的name，路径

		Map<String, String> fileMap = new HashMap<String, String>();

		fileMap.put("upfile", path);

		String contentType = "";// image/png

		String result = imageUpLoad(url, textMap, fileMap, contentType);

		return result;

	}

	/*
	 * 云计算的时候固定开启4个线程去计算 没有开启云计算的时候开业手动根据电脑去调整线程数
	 */

	ExecutorService exe = isCloudCalcOpen ? Executors.newFixedThreadPool(exeNum) : Executors.newFixedThreadPool(exeNum);

	private JButton btnNewButton;

	private JTextField textField_7;

	private static double size = 0;// 总共要测试的图片数

	private static double now = 0;// 当前在测试第几张
	private JMenuItem menuItem_5;
	private JTextField testNameGet;

	private void test(String path, String testName) {

		System.out.println(exeNum);
		size = 0;

		now = 0; // 新的批量测试

//		System.out.println(path);

		File dir = new File(path);

		// 判断是单张图片还是文件夹

		if (!dir.isDirectory()) {

			try {
				if (isCloudCalcOpen)
					test(dir, testName, true);
				else
					test(dir, testName, false);
			} catch (SocketTimeoutException e) {
				// TODO Auto-generated catch block
				try {
					test(dir, testName, false);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			return;

		}

		File[] images = dir.listFiles();

		size = images.length;
		batch = true;
		try {

			for (int i = 0; i < images.length; i++) {
				final int j = i;

				File image = images[i];

				// 每一副图片都开启一个线程去执行

				Thread t = new Thread(new Runnable() {

					@Override

					public void run() {

						// TODO Auto-generated method stub
						try {
//							if(!isCloudCalcOpen&&j%2==0)
							if (!isCloudCalcOpen)
								test(image, testName, false);
							// 云计算开启的话就不启用本地了
							else
								test(image, testName, true);
						} catch (SocketTimeoutException e) {
							// 云服务器连不上了 就换本地服务器
							try {
								test(image, testName, false);
							} catch (Exception ee) {

							}

						}

					}

				});

				exe.submit(t);

			}

			// 启动一个线程去处理测试进度

			Thread t = new Thread() {

				@Override

				public void run() {

					String s = Math.round((now / size) * 100) + "%";

					while (!s.equals("100%")) {

						s = Math.round((now / size) * 100) + "%";

						Font f = new Font("微软雅黑", Font.PLAIN, 40);

						jtf.setFont(f);

						jtf.setBackground(Color.black);

						jtf.setText("检测进度：" + s);
						progressBar_5.setValue((int) Math.round((now / size) * 100));

						try {

							TimeUnit.MILLISECONDS.sleep(200);

						} catch (InterruptedException e) {

							// TODO Auto-generated catch block

							e.printStackTrace();

						}

					}

					jtf.setFont(new Font("微软雅黑", Font.PLAIN, 22));
					jtf.setText("\u8BF7\u5C06\u5F85\u6D4B\u8BD5\u56FE\u7247\u6587\u4EF6\u5939\u62D6\u62FD\u4E8E\u6B64");
					jtf.setBackground(Color.white);
					batch = false;// 可以进行单张检测了

					radioButton.setEnabled(true);
					radioButton_1.setEnabled(true);
					radioButton_2.setEnabled(true);
				}

			};

			t.start();

		} catch (Exception e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

	}

	/**
	 * 
	 * 单张图片测试
	 * 
	 * @param f
	 * @throws SocketTimeoutException
	 * 
	 */

	private void test(File f, String testName, boolean cloudSwitch) throws SocketTimeoutException {
//		//跳过非图片文件
//		String[] imageType=new String[]{"BMP","JPG","JPEG","PNG","PCD","PSD","DXF","TIFF","PCX","bmp","jpg","jpeg","png","pcd","psd","dxf","tiff","pcx"};
//		
//		
//		String  postfix=f.getName().split("\\.")[f.getName().split("\\.").length-1];
//		//System.out.println(postfix+"\\.\\\\\\\\\\\\\\\\\\\\\\\\");
//		boolean b=false;
//		for(int i=0;i<imageType.length;i++){
//			if(postfix.equals(imageType[i])){
//				b=true;break;
//			}
//		}
//		if(!b)return;

		String fileName = "Tra" + f.getName();

		cutImage(160, 0, 960, 960, f.getAbsolutePath(), fileName);

		Process pro;
		String property = null;// 性质
		String p = null;// 概率的字符串
		String orgString = null;
		String propertyString = new String();// 性质
		double ip = 0;// 最大概率值
		List<String> list;// 用来存放预测结果

		String ppp = null;// 用于计算时结果为L时
		String pollution = "0";// 是否污染
		try {

			if (cloudSwitch) {
				// 使用云计算
				String result = cloudCalc(f.getAbsolutePath());
				if (!result.startsWith("{\"POST\"")) {
					throw new Exception();
				}
				String[] re = result.split("\"label\":");
				String p1 = re[1].split("}")[0];
				String p2 = re[2].split("}")[0];
				String p3 = re[3].split("}")[0];
				String p4 = re[4].split("}")[0];
				String p5 = re[5].split("}")[0];
				property = p1.split(",")[0];
				ppp = p2.split(",")[0];
				ip = Double.parseDouble(p1.split(":")[1].substring(0, 5));

				list = new ArrayList<>();
				list.add(p1.split(",\"probability\"")[0] + p1.split(",\"probability\"")[1]);
				list.add(p2.split(",\"probability\"")[0] + p2.split(",\"probability\"")[1]);
				list.add(p3.split(",\"probability\"")[0] + p3.split(",\"probability\"")[1]);
				list.add(p4.split(",\"probability\"")[0] + p4.split(",\"probability\"")[1]);
				list.add(p5.split(",\"probability\"")[0] + p5.split(",\"probability\"")[1]);

				int lIndex = result.indexOf("L");

				String ts = result.substring(lIndex + 18, lIndex + 19);
				if (ts.equals("}")) {
					// [L (0): 1, WP (3): 0.932879, P (2): 0.02666, N (1): 0.0256061, Y (4):
					// 4.12693e-11]
					int i = Integer.parseInt(result.substring(lIndex + 17, lIndex + 18));
					if (i >= 1)
						pollution = "1";
				} else {
					String s1 = result.substring(lIndex + 17, lIndex + 18);
					int is1 = Integer.parseInt(s1);
					System.out.println(lIndex);
					System.out.println(result);

					if (is1 == 0) {
						String s2 = result.substring(lIndex + 19, lIndex + 20);
						int is2 = Integer.parseInt(s2);
						if (is2 >= 5) {
							// System.out.println(is2+" 云计算");
							pollution = "1";
						}
					}
				}

				property = property.substring(1, 2);

			} else {
				// System.out.println("不启用云计算&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&fileName"
				// + fileName);
				pro = Runtime.getRuntime().exec(

						"classify.exe -g FOB.pb -l imagenet_slim_labels.txt -iw 224 -ih 224 -il input_4 -ol  output_node0 -im 39.50070953369140625,39.50070953369140625,39.50070953369140625 -is 55.029804229736328125,54.22267913818359375,57.02275848388671875 -i "

								+ ".\\Transit\\" + fileName);

				BufferedReader br = new BufferedReader(new InputStreamReader(pro.getInputStream()));

				String str;

				list = new ArrayList<String>();

				while ((str = br.readLine()) != null) {

					list.add(str);

				}

				pro.destroy();
				// System.out.println(list);

				// list.get(0)就是最大的概率

				orgString = list.get(0);

				p = orgString.split("\\:")[1];// 概率值

				ip = Double.parseDouble(p);

				property = list.get(0).substring(0, 1);
				ppp = list.get(1).substring(0, 1);

				br.close();
				String ss = list.toString();// ss是测试的所有输出字符串

				int lIndex = ss.indexOf("L");
				String s = ss.substring(lIndex + 8, lIndex + 9);
				if (s.equals(",")) {
					// [L (0): 1, WP (3): 0.932879, P (2): 0.02666, N (1): 0.0256061, Y (4):
					// 4.12693e-11]
					int i = Integer.parseInt(ss.substring(lIndex + 7, lIndex + 8));
					if (i >= 1)
						pollution = "1";
				} else {
					String ts = ss.substring(lIndex + 7, lIndex + 8);
					int i = Integer.parseInt(ts);
					if (i == 0) {
						String te = ss.substring(lIndex + 9, lIndex + 10);
						int pollu = Integer.parseInt(te);// L概率的小数点后一位
						if (pollu >= 5)
							pollution = "1";
					}

				}

//				System.out.println(ss.indexOf("L"));
//				System.out.println(ss);

			}
			// System.out.println(property+" W图测试");
			switch (property) {

			case "P":

				propertyString = "P";

				break;

			case "N":

				propertyString = "N";

				break;

			case "W":

				propertyString = "WP";

				break;

			case "L":
				pollution = "1";
				if (cloudSwitch) {
					// System.out.println(ppp+"W图测试");
					if (ppp.equals("W")) {
						propertyString = "WP";
					} else {
						propertyString = ppp.substring(1, 2);
					}
					break;
				} else {
					if (ppp.equals("W")) {
						propertyString = "WP";
					} else {
						propertyString = ppp.substring(0, 1);
					}
					break;
				}
			case "Y":
				pollution = "Y";
				propertyString = "Y";

				break;

			default:

				break;

			}

			if (cloudSwitch)
				showResult(f, list, true);
			else
				showResult(f, list, false);

			// 将结果写入out.csv及groud_truth.csv

			Date date = new Date();

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			String dateString = formatter.format(date);// 时间

			String result = testName + "," + dateString + "," + propertyString + "," + ip * 100 + "%" + ","

					+ f.getName() + "\r\n";

			// System.out.println(result);

			File fil = new File(".\\out.csv");
			if (!fil.exists()) {
				fil.createNewFile();
				// 写进表头
				FileWriter pw = new FileWriter(fil, true);
				String Tab = "Name" + "," + "Time" + "," + "Result" + "," + "Confidence" + "," + "ImageName";
				pw.write(Tab + "\r\n");
				pw.close();
			}

			FileWriter pw = new FileWriter(fil, true);

			File file = new File(".\\groud_truth.csv");
			if (!file.exists()) {
				String tab = "Name" + "," + "Value" + "," + "Pollution";
				file.createNewFile();
				FileWriter pw2 = new FileWriter(file, true);
				pw2.write(tab + "\r\n");// 第一次创建，写入表头
				pw2.close();
			}
			FileWriter pw2 = new FileWriter(file, true);
			String result2 = f.getName() + "," + propertyString + "," + pollution + "\r\n";
			pw2.write(result2);
			pw.write(result);
			pw2.close();

			pw.flush();

			pw.close();

			// 将图片分到对应的文件夹

			copy(f, propertyString, fileName);

			File fi = new File(".\\Transit\\" + fileName);

			fi.delete();

			now++;

//			//System.out.println("*********************当前进度："+Math.round((now/size)*100)+"%");
//			label_2.setIcon(null);
		} catch (Exception e) {

			// TODO Auto-generated catch block
			SocketTimeoutException se = new SocketTimeoutException();
			se.initCause(e);
			throw se;
//			e.printStackTrace();

		}

	}

	private void showResult(File f, List<String> list, boolean cloudSwitch) {

//System.out.println("===================="+list);

//		ImageIcon ic = null;
//
		try {

			BufferedImage bi = ImageIO.read(new File(f.getAbsolutePath()));

			ImageProducer producer = bi.getSource();

			Toolkit toolkit = Toolkit.getDefaultToolkit();

			Image image2 = toolkit.createImage(producer);

			ImageIcon image = new ImageIcon(image2);
			image.setDescription(f.getAbsolutePath());
			image.setImage(image.getImage().getScaledInstance(570, 428, Image.SCALE_DEFAULT));
			label_2.setIcon(image);

		} catch (IOException e) {

			e.printStackTrace();

		}

		double result1 = Double.parseDouble(list.get(0).split("\\:")[1]);

		if (result1 > 1)

			result1 = 0;

		progressBar.setValue((new Double(result1 * 10000)).intValue());

		double result2 = Double.parseDouble(list.get(1).split("\\:")[1]);

		if (result2 > 1)

			result2 = 0;

		progressBar_1.setValue((new Double(result2 * 10000)).intValue());

		double result3 = Double.parseDouble(list.get(2).split("\\:")[1]);

		if (result3 > 1)

			result3 = 0;

		progressBar_2.setValue((new Double(result3 * 10000)).intValue());

//		System.out.println(list.get(3));

//		System.out.println(list.get(3).substring(7, 12));

		double result4 = Double.parseDouble(list.get(3).split("\\:")[1]);

		if (result4 > 1)

			result4 = 0;

		progressBar_3.setValue((new Double(result4 * 10000)).intValue());

		double result5 = Double.parseDouble(list.get(4).split("\\:")[1]);

		if (result5 > 1)

			result5 = 0;

		progressBar_4.setValue((new Double(result5 * 10000)).intValue());

		String s = "From local:	";
		if (cloudSwitch) {
			s = "From cloud:	";
		}
		textField_5.setText(s + list.get(0).split(":")[0]);

		textField.setText(s + list.get(1).split(":")[0]);

		textField_1.setText(s + list.get(2).split(":")[0]);

		textField_2.setText(s + list.get(3).split(":")[0]);

		textField_3.setText(s + list.get(4).split(":")[0]);

	}

	// 复制图片

	private void copy(File f, String propertyString, String fileName) {

		try {

			FileInputStream fis = new FileInputStream(f);

			File dir = new File(".\\TestResult\\" + propertyString);

			if (!dir.exists()) {

				dir.mkdirs();

			}

			File fi = new File(".\\TestResult\\" + propertyString + "\\" + fileName);

			FileOutputStream fos = new FileOutputStream(fi);

			byte[] buf = new byte[1024];

			int n = 0;

			while ((n = fis.read(buf)) != -1) {

				fos.write(buf);

			}

			fos.close();

			fis.close();

		} catch (Exception e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

	}

	/**
	 * 
	 * 切割图片
	 * 
	 * 
	 * 
	 * @param x       截点横坐标 (从左开始计数)
	 * 
	 * @param y       截点纵坐标 (从上开始计数)
	 * 
	 * @param width   截取的宽度
	 * 
	 * @param height  截取的长度
	 * 
	 * @param oldpath 图片位置
	 * 
	 * @param newpath 新生成的图片位置
	 * 
	 */

	public static void cutImage(int x, int y, int width, int height, String oldpath, String newpath) {

		FileInputStream is = null;

		ImageInputStream iis = null;

		File dir = new File(".\\Transit");

		if (!dir.exists())

			dir.mkdirs();

		// 这个是获取图片扩展名的方法，比如：jpg。我这里有现成的，如果没有，自己实现

		String imgType = oldpath.substring(oldpath.lastIndexOf(".") + 1);

//		System.out.println(imgType);

		try {

			is = new FileInputStream(oldpath);

			Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName(imgType);

			ImageReader reader = it.next();

			iis = ImageIO.createImageInputStream(is);

			reader.setInput(iis, true);

			ImageReadParam param = reader.getDefaultReadParam();

			Point p = new Point();

			p.setLocation(x, y);

			Dimension d = new Dimension();

			d.setSize(width, height);

			Rectangle rect = new Rectangle(p, d);

			param.setSourceRegion(rect);

			BufferedImage bi = reader.read(0, param);

			ImageIO.write(bi, imgType, new File(".\\Transit\\" + newpath));

			is.close();

			iis.close();

		} catch (Exception e) {

			// System.out.println(e);

		}

	}

	/**
	 * 
	 * 实现拖拽的方法
	 * 
	 * @param component 拖拽目的地组件
	 * 
	 */

	public void drag(final Component component)// 定义的拖拽方法

	{

		// panel表示要接受拖拽的控件

		new DropTarget(component, DnDConstants.ACTION_COPY_OR_MOVE, new DropTargetAdapter() {

			@Override

			public void drop(DropTargetDropEvent dtde)// 重写适配器的drop方法

			{

				try {

					if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor))// 如果拖入的文件格式受支持

					{

						dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);// 接收拖拽来的数据

						@SuppressWarnings("unchecked")

						java.util.List<File> list = (java.util.List<File>) (dtde.getTransferable()

								.getTransferData(DataFlavor.javaFileListFlavor));

						dragResponse(list, component);

						dtde.dropComplete(true);// 指示拖拽操作已完成

					} else {

						dtde.rejectDrop();// 否则拒绝拖拽来的数据

					}

				} catch (Exception e) {

					e.printStackTrace();

				}

			}

			/*
			 * 
			 * 默认实现
			 * 
			 */

			protected void dragResponse(java.util.List<File> list, Component component) {

				String filePath = ((list.get(0))).getAbsolutePath();

				if (component instanceof JTextComponent) {

					JTextComponent text = (JTextComponent) component;

					// 把文本框的内容设置为拖拽文件的全路径
					text.setText(filePath);
					String s = textField_7.getText().trim();
					//
					if (!s.equals("")) {
						textField_7.setText("");
						// 初始化
						progressBar.setValue((new Double(0 * 10000)).intValue());
						progressBar_1.setValue((new Double(0 * 10000)).intValue());
						progressBar_2.setValue((new Double(0 * 10000)).intValue());
						progressBar_3.setValue((new Double(0 * 10000)).intValue());
						progressBar_4.setValue((new Double(0 * 10000)).intValue());

						BufferedImage bi;
						try {
							bi = ImageIO.read(new File(filePath));
							ImageProducer producer = bi.getSource();

							Toolkit toolkit = Toolkit.getDefaultToolkit();

							Image image2 = toolkit.createImage(producer);

							ImageIcon image = new ImageIcon(image2);
							image.setDescription(filePath);
							image.setImage(image.getImage().getScaledInstance(570, 428, Image.SCALE_DEFAULT));

							label_2.setIcon(image);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}

			}

		});
	}
}
