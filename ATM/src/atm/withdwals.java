/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package atm;
import java.sql.*;

import javax.swing.JOptionPane;

/**
 *
 * @author tvpil
 */
public class withdwals {

    public withdwals() {
    }
    
    public void withdraw(String accontN,double amount){
        
        
        
        try {
       Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "");
       Statement neww = connection.createStatement();
                        e dpAmt = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter the amount you wish to withdraw"));
                        
                        String rrr = "select * from accinfo where accNo =";
                        rrr+=accontN;
                        
                        ResultSet t = neww.executeQuery(rrr);
                        double withAmnt=0;
                        if(t.next()){
                            
                           withAmnt = t.getDouble("accBalance");
                            
                        }
                        
                        if(amount>withAmnt){
                            JOptionPane.showMessageDialog(null, "You have insufficient amount in the account");
                        }
                        else{
                            withAmnt-=amount;
                        
                            String nws = "update accinfo set accBalance=";
                            nws+=withAmnt;
                            nws+="  where accNo =";
                            nws+=accontN;
                            neww.execute(nws);        
                            
                            JOptionPane.showMessageDialog(null, "Transaction done succesfully.");
                        }
            
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }
    
    

                        
                        
}
