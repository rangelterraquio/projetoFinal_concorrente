package rangel.projetofinal.views;

import javax.swing.JOptionPane;

public class View {

    public static  String showInputText(String titile, String msg){
        String input = JOptionPane.showInputDialog(null,msg,titile,JOptionPane.INFORMATION_MESSAGE);
        return input;
    }

    public static  Integer showInputNumber(String titile, String msg ) throws NumberFormatException{
        String input = JOptionPane.showInputDialog(null,msg,titile,JOptionPane.INFORMATION_MESSAGE);
        return  Integer.parseInt(input);
    }

    public static void showErrorMSG(String titile, String msg){
        JOptionPane.showMessageDialog(null, msg, titile, JOptionPane.ERROR_MESSAGE);
    }
    public static void showMsg(String titile, String msg){
        JOptionPane.showMessageDialog(null, msg, titile, JOptionPane.INFORMATION_MESSAGE);
    }
    

}
