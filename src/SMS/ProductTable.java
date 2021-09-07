package SMS;
// Packages to import
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductTable implements ActionListener {

    JFrame f;
    JTable j;
    public ProductTable(JFrame f){
        this.f = f;
    }


    // Constructor
    public JScrollPane getTable()
    {
        // Data to be displayed in the JTable
        String[][] data = {
                { "Kundan Kumar Jha", "4031", "CSE" },
                { "Anand Jha", "6014", "IT" }
        };

        // Column Names
        String[] columnNames = { "Name", "Roll Number", "Department" };

        // Initializing the JTable
        j = new JTable(data, columnNames);
        j.setBounds(30, 400, 200, 300);

        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(j);
        return sp;
    }

    // Driver  method

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}