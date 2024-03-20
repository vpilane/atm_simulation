
package atm;

import java.sql.*;
import java.util.Random;
import javax.swing.JOptionPane;

public class ATM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "");
            JOptionPane.showMessageDialog(null, "Welcome to Vincent Bank\n\n"
                    + "Select the service you wish to receive");
            int opt = Integer.parseInt(JOptionPane.showInputDialog("1 - Open a new account\n"
                    + "2 - log in into your account\n"
                    + "3 - deposit"));
            if(opt==1){
                Random rd = new Random();
               
                String typAc[] = {"Savings","Cheque"};

                int acT = JOptionPane.showOptionDialog(null, "select the account type you want to open", "Account Type", 1, opt,null, typAc, typAc[0]);
                int accNo = rd.nextInt(99999999-100000)+100000;
                String accHoldr = JOptionPane.showInputDialog("Enter the your full name");
                double balance = Double.parseDouble(JOptionPane.showInputDialog("Enter the amount you openning the account"));
                String acountT="";
                if(acT==0){
                    acountT = typAc[0];
                }
                else
                    acountT = typAc[1];
                PreparedStatement stt = connection.prepareStatement("insert into accinfo (accTyp,accNo,accHolder,accBalance) "
                    + "values(?,?,?,?)");
                stt.setString(1, acountT);
                stt.setInt(2, accNo);
                stt.setString(3, accHoldr);
                stt.setDouble(4, balance);
                
                stt.execute();
            }
            else if(opt==2){
                
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery("select * from accinfo");
                String acNum = JOptionPane.showInputDialog("Enter your account number or insert a card");
                int chehh=0;
                
                while(rs.next()){
                    if(acNum.equalsIgnoreCase(rs.getString("accNo"))){
                        //JOptionPane.showMessageDialog(null,option2+ " is a registered student.");
                        chehh=1;
                    }

                }
                
                if(chehh==1){
                    int opt2 = Integer.parseInt(JOptionPane.showInputDialog("Enter the transaction:\n"
                            + "1 - Balance\n"
                            + "2 - Deposit\n"
                            + "3 - Withdraw\n"
                            + "4 - Buy Prepaid airtime\n"
                            + "5 - Buy Electricity voucher\n"
                            + "6 - Pay municipal bills"));
                    if(opt2==1){
                        //Statement st = connection.createStatement();
                        //int stundN = Integer.parseInt(JOptionPane.showInputDialog("Enter the student number of the account balance you want view"));
                        Statement neww = connection.createStatement();
                        String rrr = "select * from accinfo where accNo =";
                        rrr+=acNum;
                        ResultSet t = neww.executeQuery(rrr);

                        while(t.next()){

                            System.out.println(t.getDouble("accBalance"));
                        }
                    }
                    else if(opt2==2){
                        Statement neww = connection.createStatement();
                        
                        //To view descriptions of statements
                        //Statement a = connection.
                        double dpAmt = Double.parseDouble(JOptionPane.showInputDialog(null, "Insert the money/enter the amount you want to deposit"));
                        
                        String rrr = "select * from accinfo where accNo =";
                        rrr+=acNum;
                        
                        ResultSet t = neww.executeQuery(rrr);
                        double withAmnt=0;
                        if(t.next()){
                            
                           withAmnt = t.getDouble("accBalance");
                            
                        }
                        
                        withAmnt+=dpAmt;
                        
                        String nws = "update accinfo set accBalance=";
                        nws+=withAmnt;
                        nws+="  where accNo =";
                        nws+=acNum;
                        neww.execute(nws);
                       
                        
                        //ResultSet t = neww.executeQuery(rrr);
                    }
                    else if(opt2==3){
                        Statement neww = connection.createStatement();
                        
                        //To view descriptions of statements
                        //Statement a = connection.
                        double dpAmt = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter the amount you wish to withdraw"));
                        
                        withdwals wtdw = new withdwals();
                        
                        wtdw.withdraw(acNum, dpAmt);
                        
                        
                    }
                    else if(opt2==4){
                        Statement neww = connection.createStatement();
                        
                        //To view descriptions of statements
                        //Statement a = connection.
                        double dpAmt = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter the airtime amount you wish to buy"));
                        
                        withdwals wtdw = new withdwals();
                        
                        wtdw.withdraw(acNum, dpAmt);
                        
                    }
                    else if(opt2==5){
                        Statement neww = connection.createStatement();
                        
                        //To view descriptions of statements
                        //Statement a = connection.
                        double dpAmt = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter the amount of electricity voucher you wish to buy"));
                        
                        withdwals wtdw = new withdwals();
                        
                        wtdw.withdraw(acNum, dpAmt);
                    }
                    else if(opt2==6){
                        Statement neww = connection.createStatement();
                        
                        //To view descriptions of statements
                        //Statement a = connection.
                        double dpAmt = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter the amount you wish to pay to your municipal bills"));
                        
                        withdwals wtdw = new withdwals();
                        
                        wtdw.withdraw(acNum, dpAmt);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Invalid transaction selection.");
                    }
                }
                else
                    JOptionPane.showMessageDialog(null, "The account does not exist.");
            }
            else if(opt==3){
                 
                Statement neww = connection.createStatement();
                String acNum = JOptionPane.showInputDialog("Enter your account number or insert a card");
                String sht = "select * from accinfo where accNo=";
                sht+=acNum;
                ResultSet rs = neww.executeQuery(sht);
                
                int chehh=0;
                
                if(rs.next()){
                        double dpAmt = Double.parseDouble(JOptionPane.showInputDialog(null, "Insert the money/enter the amount you want to deposit"));
                        
                        String rrr = "select * from accinfo where accNo =";
                        rrr+=acNum;
                        
                        ResultSet t = neww.executeQuery(rrr);
                        double withAmnt=0;
                        if(t.next()){
                            
                           withAmnt = t.getDouble("accBalance");
                            
                        }
                        
                        withAmnt+=dpAmt;
                        
                        String nws = "update accinfo set accBalance=";
                        nws+=withAmnt;
                        nws+="  where accNo =";
                        nws+=acNum;
                        neww.execute(nws);

                }
                else{
                    JOptionPane.showMessageDialog(null, "Wrong account number");
                }     
            }
            else
                JOptionPane.showMessageDialog(null, "Sorry, invalid input option");
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "There is an error. Fix and re-run!");
        }
    }
    
}
