package net.proteanit.sql;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

/**
 * Built-in utility to convert a JDBC ResultSet into a Swing TableModel.
 * Provides drop-in compatibility for net.proteanit.sql.DbUtils without external JAR dependencies.
 */
public class DbUtils {

    public static TableModel resultSetToTableModel(ResultSet rs) {
        if (rs == null) {
            return new DefaultTableModel();
        }
        try {
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            Vector<String> columnNames = new Vector<>();

            for (int column = 1; column <= columnCount; column++) {
                columnNames.add(metaData.getColumnLabel(column));
            }

            Vector<Vector<Object>> data = new Vector<>();
            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    row.add(rs.getObject(columnIndex));
                }
                data.add(row);
            }

            return new DefaultTableModel(data, columnNames) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // Display as read-only view
                }
            };
        } catch (SQLException e) {
            e.printStackTrace();
            return new DefaultTableModel();
        }
    }
}
