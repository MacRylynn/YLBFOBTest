package Java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {
	private static CopyOnWriteArrayList<CopyOnWriteArrayList<String>> rows;

	private static CopyOnWriteArrayList<String> lines;
	InputStream in;
	BufferedReader br;
	String Tab = "Name" + "," + "Time" + "," + "Result" + "," + "Confidence" + "," + "ImageName";

	public TableModel() {
		rows = new CopyOnWriteArrayList<>();
		lines = new CopyOnWriteArrayList<String>();
		File file = new File(".\\out.csv");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 写进表头
			FileWriter pw;
			try {
				pw = new FileWriter(file, true);

				pw.write(Tab + "\r\n");
				pw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		try {
			in = new FileInputStream(file);
			br = new BufferedReader(new InputStreamReader(in));
			try {
				String oneLine = null;
				while ((oneLine = br.readLine()) != null) {
					if (oneLine.equals(Tab)) {
						continue;// 表头不读
					}
					String[] values = oneLine.split(",");// ,键间隔
					CopyOnWriteArrayList<String> row = new CopyOnWriteArrayList<>();
					for (int i = 0; i < values.length; i++) {
						row.add(values[i]);
					}
					rows.add(row);
				}
			} catch (Exception e) {

			} finally {
				try {
					br.close();
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		lines.add("姓名");
		lines.add("测试时间");
		lines.add("测试结果");
		lines.add("可信度");
		lines.add("图片名称");

	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return rows.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return lines.size();
	}

	// 行列定位值
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return rows.get(rowIndex).get(columnIndex);
	}

	// 列名
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return this.lines.get(column);
	}

	public static CopyOnWriteArrayList<CopyOnWriteArrayList<String>> getRows() {
		return rows;
	}

	public static void setRows(CopyOnWriteArrayList<CopyOnWriteArrayList<String>> rows) {
		TableModel.rows = rows;
	}

	public static CopyOnWriteArrayList<String> getLines() {
		return lines;
	}

	public static void setLines(CopyOnWriteArrayList<String> lines) {
		TableModel.lines = lines;
	}
}
