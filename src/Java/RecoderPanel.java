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
	private static JPanel jp; // �����
	private static TableModel m;
	private static JScrollPane jsp; // �������м�
	private static JPanel sp; // ������е������
	private static JPanel xp; // ������е������
	// finalRowsλ�ܼ�¼��
	private static CopyOnWriteArrayList<CopyOnWriteArrayList<String>> finalRows;// ���rowȡ���󲻱䣬�Թ�����ʹ��
	private static CopyOnWriteArrayList<CopyOnWriteArrayList<String>> nowRows;// ��ǰ��rows,ƽ��=finalRows����ѯ��=selectedRows
	private static CopyOnWriteArrayList<CopyOnWriteArrayList<String>> rows;
	private static boolean flag = true;// ��֤finalRowsֻ��һ�θ�ֵ;
	private static int pageNow = 1; // ��ǰҳ
	private static int rowCount; // �ܼ�¼��
	private static int pageCount; // ��ҳ��
	private static int pageSize = 15;// ÿҳ20����¼
	static JPanel panel = null;// �������
	static CardLayout card = null;
	private ReportPanel report = null;
	// �������
	JLabel jl;
	JLabel jb;
	JTextField jtf;
	JButton jb2;
	JButton jb3;
	JButton jb4;
	JButton jb5;
	JButton jb6;
	JTextField go;
	JButton jb7;// ��ת
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

		// sp�����
		jb = new JLabel("��������");
		jtf = new JTextField(10);
		jb2 = new JButton("��ѯ");
		jb3 = new JButton("����");
		jb6 = new JButton("ˢ��");
		// xp�����
		rowCount = finalRows.size();
		pageCount = rowCount / pageSize + 1;
		jb4 = new JButton("��һҳ");
		jl = new JLabel("��" + pageNow + "ҳ ����" + pageCount + "ҳ��");
		jb5 = new JButton("��һҳ");
		go = new JTextField(3);
		jb7 = new JButton("��ת");

		button = new JButton("�鿴����");
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
	 * ���ע�����
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
						// �ҵ���
						selectedRows.add(row);
					}
				}
				TableModel.setRows(selectedRows);
				jtb.repaint();
				pageNow = 1; // ��ǰҳ
				rowCount = selectedRows.size();
				pageCount = rowCount / pageSize + 1;

				nowRows = selectedRows;
				showPage(pageNow, pageSize, nowRows);
				if (selectedRows.size() == 0)
					JOptionPane.showMessageDialog(null, "�޼�¼");
			}

		});
		jb3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				nowRows = finalRows;
				pageNow = 1; // ��ǰҳ
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
					JOptionPane.showMessageDialog(RecoderPanel.this, "��ѡ��һ��");
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
	 * ��ҳ��ʾ
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
		jl.setText("��" + pageNow + "ҳ ����" + pageCount + "ҳ��");
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

		// ��ʾ���Ա���ҳ��
		ReportPanel report = new ReportPanel(name, time, result, p, path, imageName);
		this.panel.add(report, "2");
		this.card.show(panel, "2");
	}
}
