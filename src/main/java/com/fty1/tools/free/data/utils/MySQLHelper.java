package com.fty1.tools.free.data.utils;

import com.fty1.tools.free.data.kernel.RowData;
import com.fty1.tools.free.data.kernel.RowDataType;
import com.fty1.tools.free.data.kernel.RowValue;
import com.fty1.tools.free.data.kernel.SQL;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


@Slf4j
public class MySQLHelper {

    public static List<List<RowData>> navicatSQL(DataSource dataSource, SQL sql) {
        if (Objects.isNull(dataSource) || Objects.isNull(sql)) {
            return Collections.emptyList();
        }
        String finalSQLText = sql.finalSQLText();
        if (StringUtils.isEmpty(finalSQLText)) {
            return Collections.emptyList();
        }
        List<List<RowData>> result = new ArrayList<>();
        try (Connection con = dataSource.getConnection()) {
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql.finalSQLText());
            if (Objects.isNull(rs) || !rs.next()) {
                return Collections.emptyList();
            }
            ResultSetMetaData meta = rs.getMetaData();
            int count = meta.getColumnCount();
            while (rs.next()) {

                List<RowData> rowData = new ArrayList<>();
                for (int i = 0; i < count; i++) {
                    System.out.printf(String.valueOf(rs.getObject(i)));
                    RowData cell = new RowData();
                    cell.setIndex(i);
                    cell.setColumn(meta.getColumnName(i));
                    RowValue value = new RowValue();
                    value.setRowValue(rs.getObject(i));
                    value.setRowType(countRowTypeByTypes(meta.getColumnType(i)));
                    cell.setValue(value);
                    rowData.add(cell);
                }
                result.add(rowData);
            }
            if (!stm.isClosed()) {
                stm.close();
            }
            return result;
        } catch (SQLException e) {
            log.error("SQL:{} execute Error", finalSQLText, e);
        }
        return Collections.emptyList();
    }

    private static RowDataType countRowTypeByTypes(int columnType) {
        switch (columnType) {
            case Types.BIT:
            case Types.TINYINT:
            case Types.SMALLINT:
            case Types.INTEGER:
            case Types.BIGINT:
            case Types.REAL:
            case Types.FLOAT:
            case Types.DOUBLE:
            case Types.NUMERIC:
            case Types.DECIMAL: {
                return RowDataType.NUMBER;
            }

            case Types.BOOLEAN: {
                return RowDataType.BOOLEAN;
            }
            default: {
                return RowDataType.STRING;
            }
        }
    }

}
