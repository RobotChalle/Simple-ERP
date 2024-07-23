package erp;

import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;


public class CellListener implements TableModelListener {
    private JTable table;
    private Runnable action;

    public CellListener(JTable t, Runnable act) {
        table = t;
        action = act;
        table.getModel().addTableModelListener(this);
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        int row = e.getFirstRow();
        int column = e.getColumn();
        if (row >= 0 && column >= 0) {
            // 셀 값이 변경되었을 때
            action.run();
        }
    }
}

