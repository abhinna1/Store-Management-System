package SMS;

import java.awt.*;
import java.awt.event.*;
import java.util.regex.*;
import javax.swing.*;
import javax.swing.table.*;
public class Test2 extends JFrame {
    private JTable table;
    private TableModel model;
    public Test2() {
        setTitle("FilterTable Test");
        Object rows[][] = {{"Adithya", "Content Developer", 25000}, {"Jai", "SME", 30000},  {"Chaitanya", "Java Engineer", 45000}, {"Ramesh", "Scala Developer", 40000}, {"Ravi", "SAP  Consultant", 70000}};
        Object columns[] = {"Name", "Designation", "Salary"};
        model = new DefaultTableModel(rows, columns) {
            public Class getColumnClass(int column) {
                Class returnValue;
                if((column >= 0) && (column < getColumnCount())) {
                    returnValue = getValueAt(0, column).getClass();
                } else {
                    returnValue = Object.class;
                }
                return returnValue;
            }
        };
        table = new JTable(model);
        final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
        table.setRowSorter(sorter);
        add(new JScrollPane(table), BorderLayout.CENTER);
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Filter");
        panel.add(label, BorderLayout.WEST);
        final JTextField filterText = new JTextField("");
        panel.add(filterText, BorderLayout.CENTER);
        add(panel, BorderLayout.NORTH);
        JButton button = new JButton("Filter");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = filterText.getText();
                if(text.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    try {
                        sorter.setRowFilter(RowFilter.regexFilter(text));
                    } catch(PatternSyntaxException pse) {
                        System.out.println("Bad regex pattern");
                    }
                }
            }
        });
        add(button, BorderLayout.SOUTH);
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public static void main(String args[]) {
        new Test2();
    }
}