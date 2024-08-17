package Vistas.recursos;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class RenderTable extends DefaultTableCellRenderer{

    @Override
    public Component getTableCellRendererComponent(JTable table, Object objetoRenderizado, boolean isSelected, boolean hasFocus, int row, int column) {
        if(objetoRenderizado instanceof JButton){
            return(JButton)objetoRenderizado;
        }
        return super.getTableCellRendererComponent(table, objetoRenderizado, isSelected, hasFocus, row, column); 
    }

}
