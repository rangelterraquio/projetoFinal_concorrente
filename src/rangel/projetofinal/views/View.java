package rangel.projetofinal.views;

import javax.swing.JOptionPane;


/**
 * @author Rangel Cardoso Dias;
 * @matricula UC18200693;
 */
public final class View {

    /**
     * @param titile - title's view
     * @param msg - msg's view
     * @return user input
     */
    public static  String showInputText(String titile, String msg){
        String input = JOptionPane.showInputDialog(null,msg,titile,JOptionPane.INFORMATION_MESSAGE);
        return input;
    }
    /**
     * @param titile - title's view
     * @param msg - msg's view
     * @return user input
     */
    public static  Integer showInputNumber(String titile, String msg ) throws NumberFormatException{
        String input = JOptionPane.showInputDialog(null,msg,titile,JOptionPane.INFORMATION_MESSAGE);
        return  Integer.parseInt(input);
    }
    /**
     * @param titile - title's view
     * @param msg - msg's view
     */
    public static void showErrorMSG(String titile, String msg){
        JOptionPane.showMessageDialog(null, msg, titile, JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * @param titile - title's view
     * @param msg - msg's view
     */
    public static void showMsg(String titile, String msg){
        JOptionPane.showMessageDialog(null, msg, titile, JOptionPane.INFORMATION_MESSAGE);
    }
    

}
