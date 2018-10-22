package Java;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class RecoderPanel extends JFrame {
	private static JTable jtb;
	private static JPanel jp; // 总面板
	private static TableModel m;
	private static JScrollPane jsp; // 中面板的中间
	private static JPanel sp; // 总面板中的上面板
	private static JPanel xp; // 总面板中的上面板
	// finalRows位总记录数
	private static CopyOnWriteArrayList<CopyOnWriteArrayList<String>> finalRows;// 这个row取到后不变，以供返回使用
	private static CopyOnWriteArrayList<CopyOnWriteArrayList<String>> nowRows;// 当前的rows,平常=finalRows，查询是=selectedRows
	private static CopyOnWriteArrayList<CopyOnWriteArrayList<String>> rows;
	private static boolean flag = true;// 保证finalRows只有一次赋值;
	private static int pageNow = 1; // 当前页
	private static int rowCount; // 总记录数
	private static int pageCount; // 总页数
	private static int pageSize = 15;// 每页20条记录
	static JPanel panel = null;// 总总面板
	static CardLayout card = null;
	private ReportPanel report = null;
	// 组件定义
	JLabel jl;
	JLabel jb;
	JTextField jtf;
	JButton jb2;
	JButton jb3;
	JButton jb4;
	JButton jb5;
	JButton jb6;
	JTextField go;
	JButton jb7;// 跳转
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JButton button;

	public static void main(String[] args) {
		new RecoderPanel();
	}

	public RecoderPanel() {
		card = new CardLayout();
		panel = new JPanel(card);
		sp = new JPanel();
		xp = new JPanel();
		jp = new JPanel(new BorderLayout());
		m = new TableModel();
		jtb = new JTable(m);
		jtb.setRowHeight(25);
		jsp = new JScrollPane(jtb);
		jp.add(jsp);
		finalRows = TableModel.getRows();
		nowRows = finalRows;

		// sp的组件
		jb = new JLabel("输入姓名");
		jtf = new JTextField(10);
		jb2 = new JButton("查询");
		jb3 = new JButton("返回");
		jb6 = new JButton("刷新");
		// xp的组件
		rowCount = finalRows.size();
		pageCount = rowCount / pageSize + 1;
		jb4 = new JButton("上一页");
		jl = new JLabel("第" + pageNow + "页 （共" + pageCount + "页）");
		jb5 = new JButton("下一页");
		go = new JTextField(3);
		jb7 = new JButton("跳转");

		button = new JButton("查看详情");
		register(jb2, jb3, jb4, jb5, jb7, button);
		showPage(pageNow, pageSize, nowRows);

		sp.add(jb);
		sp.add(jtf);
		sp.add(jb2);
		sp.add(jb3);
		xp.add(jb4);
		xp.add(jl);
		xp.add(jb5);
		xp.add(go);
		xp.add(jb7);
//		xp.add(jb6);

		jp.add(sp, BorderLayout.NORTH);

		label_1 = new JLabel("        ");
		sp.add(label_1);

		label_2 = new JLabel(" ");
		sp.add(label_2);

		sp.add(button);

		label = new JLabel("");
		sp.add(label);
		jp.add(xp, BorderLayout.SOUTH);
		panel.add(jp, "1");

		getContentPane().add(panel);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(500, 300, 900, 600);
		this.setVisible(true);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				RecoderPanel.this.dispose();
				UI.isopen = true;
			}
		});
	}

	/**
	 * 组件注册监听
	 * 
	 * @param i
	 * @param jb2
	 * @param jb3
	 * @param jb4
	 * @param jb5
	 */
	private void register(JButton jb2, JButton jb3, JButton jb4, JButton jb5, JButton jb7, JButton button) {
		jb2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				String name = jtf.getText().trim();

				CopyOnWriteArrayList<CopyOnWriteArrayList<String>> selectedRows = new CopyOnWriteArrayList<CopyOnWriteArrayList<String>>();
				for (CopyOnWriteArrayList<String> row : finalRows) {
					if (row.get(0).equals(name)) {
						// 找到了
						selectedRows.add(row);
					}
				}
				TableModel.setRows(selectedRows);
				jtb.repaint();
				pageNow = 1; // 当前页
				rowCount = selectedRows.size();
				pageCount = rowCount / pageSize + 1;

				nowRows = selectedRows;
				showPage(pageNow, pageSize, nowRows);
				if (selectedRows.size() == 0)
					JOptionPane.showMessageDialog(null, "无记录");
			}

		});
		jb3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				nowRows = finalRows;
				pageNow = 1; // 当前页
				rowCount = nowRows.size();
				pageCount = rowCount / pageSize + 1;
				// TODO Auto-generated method stub
				pageNow = 1;
				showPage(pageNow, pageSize, nowRows);
			}

		});
		jb4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (pageNow > 1) {
					pageNow = pageNow - 1;
					showPage(pageNow, pageSize, nowRows);
				} else {
					pageNow = pageCount;
					showPage(pageNow, pageSize, nowRows);
				}

			}

		});
		jb5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				pageNow = pageNow % pageCount + 1;
				showPage(pageNow, pageSize, nowRows);
			}

		});
		jb7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				String page = go.getText().trim();

				System.out.println(page);
				pageNow = Integer.parseInt(page);
				if (pageNow <= pageCount)
					showPage(pageNow, pageSize, nowRows);
			}

		});
		button.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int d = (pageNow - 1) * pageSize + jtb.getSelectedRow();
				if (d != -1)
					RecoderPanel.this.showResultPanel(finalRows.get(d));
				else
					JOptionPane.showMessageDialog(RecoderPanel.this, "请选中一行");
			}

		});
//		jb6.addActionListener(new ActionListener(){
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//			
//				m=new TableModel();
//				jp.remove(jsp);
//				jtb=new JTable(m);				
//				jsp=new JScrollPane(jtb);
//				jtb.setRowHeight(25);
//				jp.add(jsp);
//				jp.repaint();
//				
//				finalRows=TableModel.getRows();
//				
//				pageNow=1;
//				showPage(pageNow,pageSize);
//			}
//			
//		});
	}

	/**
	 * 分页显示
	 * 
	 * @param pageNow
	 * @param pageSize
	 */
	private void showPage(int pageNow, int pageSize, List<CopyOnWriteArrayList<String>> finalRows) {
		rows = new CopyOnWriteArrayList<>();
		for (int i = (pageNow - 1) * pageSize; i < pageNow * pageSize; i++) {
			if (i == finalRows.size())
				break;
			CopyOnWriteArrayList<String> row = finalRows.get(i);
			if (row != null)
				rows.add(row);
		}
		TableModel.setRows(rows);
		jl.setText("第" + pageNow + "页 （共" + pageCount + "页）");
		jl.repaint();
		jtb.repaint();
	}

	private void showResultPanel(List<String> row) {

		String name = row.get(0);
		String time = row.get(1);
		String result = row.get(2);
		String p = row.get(3);
		String path = ".\\TestResult\\" + result + "\\Tra" + row.get(4);
		System.out.println(path);
		String imageName = row.get(4);

		// 显示测试报告页面
		ReportPanel report = new ReportPanel(name, time, result, p, path, imageName);
		this.panel.add(report, "2");
		this.card.show(panel, "2");
	}
}
