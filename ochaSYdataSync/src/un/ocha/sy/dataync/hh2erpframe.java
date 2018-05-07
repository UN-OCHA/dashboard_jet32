package un.ocha.sy.dataync;



import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.Date;
import java.util.Calendar;
import java.util.prefs.Preferences;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import oracle.jdeveloper.layout.XYConstraints;
import oracle.jdeveloper.layout.XYLayout;

import org.gui.JCalendarCombo;


public class hh2erpframe extends JFrame {
    private JMenuBar menuBar = new JMenuBar();

    private JMenu menuFile = new JMenu();

    private JMenuItem menuFileExit = new JMenuItem();

    private JMenu menuHelp = new JMenu();

    private JMenuItem menuHelpAbout = new JMenuItem();

    private XYLayout xYLayout1 = new XYLayout();

    private JTabbedPane jTabbedPane1 = new JTabbedPane();

    private JPanel jPanel1 = new JPanel();

    private JPanel jPanel2 = new JPanel();

    private XYLayout xYLayout2 = new XYLayout();

    private XYLayout xYLayout3 = new XYLayout();

    private JPanel jPanel3 = new JPanel();

    private JPanel jPanel4 = new JPanel();

    private XYLayout xYLayout4 = new XYLayout();

    private XYLayout xYLayout5 = new XYLayout();

    private JLabel jLabel1 = new JLabel();

    private JLabel jLabel2 = new JLabel();

    private JLabel jLabel3 = new JLabel();

    private JLabel jLabel4 = new JLabel();

    private JLabel jLabel5 = new JLabel();

    private JTextField jTextField1 = new JTextField();

    private JTextField jTextField2 = new JTextField();

    private JTextField jTextField3 = new JTextField();

    private JTextField jTextField4 = new JTextField();

    private JPasswordField jPasswordField1 = new JPasswordField();

    private JButton jButton1 = new JButton();

    private JButton jButton2 = new JButton();

    private JLabel jLabel6 = new JLabel();

    private JLabel jLabel7 = new JLabel();

    private JPasswordField jPasswordField2 = new JPasswordField();

    private JTextField jTextField5 = new JTextField();

    private JLabel jLabel8 = new JLabel();

    private JTextField jTextField6 = new JTextField();

    private JProgressBar jProgressBar1 = new JProgressBar();

    private JProgressBar jProgressBar2 = new JProgressBar();

    private JButton jButton3 = new JButton();

    private JButton jButton4 = new JButton();
    
    private final JCalendarCombo jCalendarCombo = new JCalendarCombo(JCalendarCombo.SUNDAY, true, 2000, 2100, false);
    private final JCalendarCombo jCalendarCombofrom = new JCalendarCombo(JCalendarCombo.SUNDAY , true, 2000, 2100, false);
    private final JCalendarCombo jCalendarComboto = new JCalendarCombo(JCalendarCombo.SUNDAY , true, 2000, 2100, false);
    
    private JButton jButton5 = new JButton();

    public JTextField transnm = new JTextField();

    private JLabel jLabel9 = new JLabel();

    public JProgressBar jProgressBar3 = new JProgressBar();

    private JLabel jLabel10 = new JLabel();

    private JLabel jLabel11 = new JLabel();

    public JTextField transnm1 = new JTextField();

    public JTextField transnm2 = new JTextField();

    private JPanel jPanel5 = new JPanel();

    private XYLayout xYLayout6 = new XYLayout();

    private JLabel jLabel12 = new JLabel();

    private JPanel jPanel6 = new JPanel();


    private XYLayout xYLayout7 = new XYLayout();

    private JLabel jLabel13 = new JLabel();

    private JLabel jLabel14 = new JLabel();

    private JButton jButton6 = new JButton();

    public JTextArea jTextArea1 = new JTextArea();

    private JTextField jTextField7 = new JTextField();

    private JTextField jTextField8 = new JTextField();

    private JLabel jLabel15 = new JLabel();

    private JLabel jLabel16 = new JLabel();

    private JTextField jTextField9 = new JTextField();

    private JLabel jLabel17 = new JLabel();
    private JTextField jTextField10 = new JTextField();
    private JLabel jLabel18 = new JLabel();


    public hh2erpframe() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setJMenuBar( menuBar );
        this.setSize(new Dimension(406, 346));
        this.setTitle("Ocha Syria Dashboard DataSync");
        this.getContentPane().setLayout(xYLayout1);
        this.setResizable(false);
        menuFile.setText( "File" );
        menuFileExit.setText( "Exit" );
        menuFileExit.addActionListener( new ActionListener() { public void actionPerformed( ActionEvent ae ) { fileExit_ActionPerformed( ae ); } } );
        menuHelp.setText( "Help" );
        menuHelpAbout.setText( "About" );
        menuHelpAbout.addActionListener( new ActionListener() { public void actionPerformed( ActionEvent ae ) { helpAbout_ActionPerformed( ae ); } } );
        jPanel1.setLayout(xYLayout2);
        jPanel2.setLayout(xYLayout3);
        jPanel3.setBorder(BorderFactory.createTitledBorder("MS Access DB"));
        jPanel3.setLayout(xYLayout4);
        jPanel4.setBorder(BorderFactory.createTitledBorder("DashBoard Server"));
        jPanel4.setLayout(xYLayout5);
        jLabel1.setText("Host :");
        jLabel1.setFont(new Font("Dialog", 1, 13));
        jLabel2.setText("Port :");
        jLabel2.setFont(new Font("Dialog", 1, 13));
        jLabel3.setText("SID :");
        jLabel3.setFont(new Font("Dialog", 1, 13));
        jLabel4.setText("Password :");
        jLabel4.setFont(new Font("Dialog", 1, 13));
        jLabel5.setText("User Name :");
        jLabel5.setFont(new Font("Dialog", 1, 13));
        jTextField1.setBackground(new Color(245, 235, 233));
        jTextField2.setBackground(new Color(245, 235, 233));
        jTextField3.setBackground(new Color(245, 235, 233));
        jTextField4.setBackground(new Color(245, 235, 233));
        jButton1.setText("Test Connection");
        jButton1.addActionListener(new ActionListener() {
                                       public void actionPerformed(ActionEvent e) {
                                           erptest(e);
                                       }
                                   }
        );
        jButton2.setText("Test Connection");
        jButton2.addActionListener(new ActionListener() {
                                       public void actionPerformed(ActionEvent e) {
                                           sdtest(e);
                                       }
                                   }
        );
        jLabel6.setText("User Name :");
        jLabel6.setFont(new Font("Dialog", 1, 13));
        jLabel7.setText("Password :");
        jLabel7.setFont(new Font("Dialog", 1, 13));
        jTextField5.setBackground(new Color(245, 235, 233));
        jLabel8.setText("Host :");
        jLabel8.setFont(new Font("Dialog", 1, 13));
        jTextField6.setBackground(new Color(245, 235, 233));
        jButton3.setText("Save");
        jButton3.setBackground(new Color(166, 161, 245));
        jButton3.addActionListener(new ActionListener() {
                                       public void actionPerformed(ActionEvent e) {
                                           savebotton(e);
                                       }
                                   }
        );
        jButton4.setText("Reload");
        jButton4.setBackground(new Color(166, 161, 245));
        jButton4.addActionListener(new ActionListener() {
                                       public void actionPerformed(ActionEvent e) {
                                           reloadbotton(e);
                                       }
                                   }
        );
        jButton5.setText("Post To ERP");
        jButton5.setToolTipText("null");
        jButton5.setFont(new Font("Dialog", 1, 13));
        jButton5.setBackground(new Color(245, 218, 245));
        jButton5.addActionListener(new ActionListener() {
                                       public void actionPerformed(ActionEvent e) {
                                           post2erpkey(e);
                                       }
                                   }
        );
        transnm.setBackground(new Color(240, 254, 255));
        transnm.setEnabled(false);
        jLabel9.setText("Transactions Posted to ERP");
        jLabel9.setLabelFor(transnm);
        jLabel9.setFont(new Font("Dialog", 1, 12));
        jLabel10.setText("Transaction Items Posted to ERP");
        jLabel10.setLabelFor(transnm);
        jLabel10.setFont(new Font("Dialog", 1, 12));
        jLabel11.setText("Transaction Descount Posted to ERP");
        jLabel11.setLabelFor(transnm);
        jLabel11.setFont(new Font("Dialog", 1, 12));
        jLabel11.setToolTipText("null");
        transnm1.setBackground(new Color(240, 254, 255));
        transnm1.setEnabled(false);
        transnm2.setBackground(new Color(240, 254, 255));
        transnm2.setEnabled(false);
        jPanel5.setLayout(xYLayout6);
        jPanel5.setBorder(BorderFactory.createTitledBorder("Counter"));
        jLabel12.setText("Select Date ");
        jLabel12.setToolTipText("null");
        jLabel12.setFont(new Font("Dialog", 1, 14));
        jLabel12.setForeground(new Color(29, 29, 223));
        jPanel6.setLayout(xYLayout7);
        jLabel13.setText("From");
        jLabel13.setToolTipText("null");
        jLabel13.setFont(new Font("Dialog", 1, 13));
        jLabel14.setText("To");
        jLabel14.setToolTipText("null");
        jLabel14.setFont(new Font("Dialog", 1, 13));
        jButton6.setText("Check Posted Data");
        jButton6.setToolTipText("null");
        jButton6.setFont(new Font("Dialog", 1, 12));
        jButton6.addActionListener(new ActionListener() {
                                       public void actionPerformed(ActionEvent e) {
                                           check_actionPerformed(e);
                                       }
                                   }
        );
        jTextArea1.setBorder(BorderFactory
                             .createTitledBorder("Unposted Transaction"));
        jTextArea1.setToolTipText("null");
        jTextField7.setBackground(new Color(245, 235, 233));
        jTextField8.setBackground(new Color(245, 235, 233));
        jLabel15.setText("Port :");
        jLabel15.setFont(new Font("Dialog", 1, 13));
        jLabel16.setText("SID :");
        jLabel16.setFont(new Font("Dialog", 1, 13));
        jTextField9.setBackground(new Color(245, 235, 233));
        
        jLabel17.setText("CIN :");
        jLabel17.setFont(new Font("Dialog", 1, 15));
        jLabel17.setToolTipText("null");
        jLabel18.setText("S&D Location ID:");
        jLabel18.setToolTipText("null");
        jLabel18.setFont(new Font("Tahoma", 1, 13));
        menuFile.add( menuFileExit );
        menuBar.add( menuFile );
        menuHelp.add( menuHelpAbout );
        menuBar.add( menuHelp );
        
        jCalendarCombo.setDateFormat(JCalendarCombo.DAY,JCalendarCombo.MONTH,JCalendarCombo.YEAR_BIG,'/');
        Calendar calendar =  Calendar.getInstance();
     //   calendar.set(calendar.get(calendar.YEAR),calendar.get(calendar.MONTH), calendar.get(calendar.DAY_OF_MONTH ));
      //  jCalendarCombo.setDate(calendar);
      jCalendarCombo.setSelectedDate(calendar.get(calendar.YEAR),calendar.get(calendar.MONTH)+1, calendar.get(calendar.DAY_OF_MONTH ));
        
  
       
        jCalendarCombofrom.setDateFormat(JCalendarCombo.DAY,JCalendarCombo.MONTH,JCalendarCombo.YEAR_BIG,'/');
        Calendar calendarfrom =  Calendar.getInstance();
    //    calendarfrom.set(calendarfrom.get(calendarfrom.YEAR),calendarfrom.get(calendarfrom.MONTH), calendarfrom.get(calendarfrom.DAY_OF_MONTH ));
        jCalendarCombofrom.setDate(calendarfrom);
        
        jCalendarComboto.setDateFormat(JCalendarCombo.DAY,JCalendarCombo.MONTH,JCalendarCombo.YEAR_BIG,'/');
        Calendar calendarto =  Calendar.getInstance();
   //     calendarto.set(calendarto.get(calendarto.YEAR),calendarto.get(calendarto.MONTH), calendarto.get(calendarto.DAY_OF_MONTH ));
        jCalendarComboto.setDate(calendarto);

        jCalendarCombo.setFont(new Font("Dialog", 1, 14));
        jCalendarCombo.setBackground(new Color(239, 221, 245));
        
        jCalendarCombofrom.setFont(new Font("Dialog", 1, 14));
        jCalendarCombofrom.setBackground(new Color(239, 221, 245));
        
        jCalendarComboto.setFont(new Font("Dialog", 1, 14));
        jCalendarComboto.setBackground(new Color(239, 221, 245));
        
        jPanel5.add(jLabel10, new XYConstraints(5, 28, 235, 25));
        jPanel5.add(jLabel9, new XYConstraints(5, 0, 235, 25));
        jPanel5.add(jLabel11, new XYConstraints(5, 55, 235, 20));
        jPanel5.add(transnm1, new XYConstraints(260, 28, 60, 20));
        jPanel5.add(transnm, new XYConstraints(260, 0, 60, 20));
        jPanel5.add(transnm2, new XYConstraints(260, 55, 60, 20));
        jPanel1.add(jLabel12, new XYConstraints(70, 35, 85, 25));
        jPanel1.add(jPanel5, new XYConstraints(15, 75, 365, 105));
        jPanel1.add(jProgressBar3, new XYConstraints(10, 235, 380, 15));
        jPanel1.add(jCalendarCombo, new XYConstraints(160, 35, 160, 25));


        jPanel6.add(jTextArea1, new XYConstraints(10, 150, 375, 110));
        jPanel6.add(jButton6, new XYConstraints(120, 115, 140, 25));
        jPanel6.add(jLabel14, new XYConstraints(75, 70, 45, 20));
        jPanel6.add(jLabel13, new XYConstraints(75, 20, 45, 20));
        jPanel6.add(jCalendarCombofrom, new XYConstraints(145, 20, 160, 25));
        jPanel6.add(jCalendarComboto, new XYConstraints(145, 68, 160, 25));
        jPanel1.add(jButton5, new XYConstraints(125, 185, 160, 35));
        jPanel4.add(jProgressBar1, new XYConstraints(5, 165, 175, 10));
        jPanel4.add(jButton1, new XYConstraints(25, 135, 135, 25));
        jPanel4.add(jPasswordField1, new XYConstraints(85, 105, 95, 20));
        jPanel4.add(jTextField4, new XYConstraints(85, 75, 95, 20));
        jPanel4.add(jTextField3, new XYConstraints(50, 50, 130, 20));
        jPanel4.add(jTextField2, new XYConstraints(50, 25, 130, 20));
        jPanel4.add(jTextField1, new XYConstraints(50, -2, 130, 20));
        jPanel4.add(jLabel5, new XYConstraints(5, 75, 85, 20));
        jPanel4.add(jLabel4, new XYConstraints(5, 105, 75, 20));
        jPanel4.add(jLabel3, new XYConstraints(5, 50, 35, 20));
        jPanel4.add(jLabel2, new XYConstraints(5, 25, 35, 20));
        jPanel4.add(jLabel1, new XYConstraints(5, 0, 35, 18));
        jPanel2.add(jLabel18, new XYConstraints(200, 215, 125, 20));
        jPanel2.add(jTextField10, new XYConstraints(330, 215, 55, 20));
        jPanel2.add(jLabel17, new XYConstraints(10, 215, 40, 20));
        jPanel2.add(jButton4, new XYConstraints(235, 240, 120, 25));
        jPanel2.add(jButton3, new XYConstraints(40, 240, 120, 25));
        jPanel2.add(jPanel4, new XYConstraints(0, 5, 195, 205));
        jPanel2.add(jPanel3, new XYConstraints(195, 5, 200, 205));
        jPanel2.add(jTextField9, new XYConstraints(130, 215, 50, 20));
        jPanel3.add(jTextField8, new XYConstraints(60, 50, 125, 20));
        jPanel3.add(jTextField7, new XYConstraints(60, 25, 125, 20));
        jPanel3.add(jButton2, new XYConstraints(30, 135, 135, 25));
        jPanel3.add(jLabel6, new XYConstraints(5, 75, 85, 20));
        jPanel3.add(jLabel7, new XYConstraints(5, 105, 75, 20));
        jPanel3.add(jPasswordField2, new XYConstraints(90, 105, 95, 20));
        jPanel3.add(jTextField5, new XYConstraints(90, 75, 95, 20));
        //jPanel3.add(jLabel8, new XYConstraints(5, 0, 50, 20));
        //jPanel3.add(jTextField6, new XYConstraints(60, 0, 125, 20));
        //jPanel3.add(jProgressBar2, new XYConstraints(10, 165, 175, 10));
        jPanel3.add(jLabel15, new XYConstraints(5, 25, 35, 20));
        jPanel3.add(jLabel16, new XYConstraints(5, 50, 35, 20));
        jTabbedPane1.addTab("Post to ERP", jPanel1);
  //      jTabbedPane1.addTab("Check", jPanel6);
        jTabbedPane1.addTab("Setting", jPanel2);
       
        this.getContentPane()
        .add(jTabbedPane1, new XYConstraints(0, 0, 400, 295));
        
        //read value
         Preferences p = Preferences.userRoot();
         jTextField1.setText(p.get("erphost",""));
         jTextField2.setText(p.get("erpport",""));
         jTextField3.setText(p.get("erpsid","")); 
         jTextField4.setText(p.get("erpusername",""));
         jPasswordField1.setText(p.get("erppassword",""));
         jTextField6.setText(p.get("sdhost",""));
         jTextField7.setText(p.get("sdport",""));
         jTextField8.setText(p.get("sdsid",""));
         jTextField5.setText(p.get("sdusername",""));
         jPasswordField2.setText(p.get("sdpassword",""));
         jTextField9.setText(p.get("CIN",""));
        jTextField10.setText(p.get("SDLOC",""));
        jTextField10.setBackground(new Color(255, 239, 246));
        jTextField10.setFont(new Font("Tahoma", 1, 13));
        jTextField9.setFont(new Font("Tahoma", 1, 13));

    }

    void fileExit_ActionPerformed(ActionEvent e) {
        System.exit(0);
    }

    void helpAbout_ActionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(this, new hh2erpframe_AboutBoxPanel1(), "About",
                                      JOptionPane.PLAIN_MESSAGE);
    }

    private void savebotton(ActionEvent e) {
        Preferences p = Preferences.userRoot();
        p.put("erphost", jTextField1.getText());
        p.put("erpport", jTextField2.getText());
        p.put("erpsid", jTextField3.getText());
        p.put("erpusername", jTextField4.getText());
        p.put("erppassword",ar2str( jPasswordField1.getPassword()));
        p.put("sdhost", jTextField6.getText());
        p.put("sdport", jTextField7.getText());
        p.put("sdsid", jTextField8.getText());
        p.put("sdusername", jTextField5.getText());
        p.put("sdpassword", ar2str(jPasswordField2.getPassword()));
        p.put("CIN", jTextField9.getText());
        p.put("SDLOC", jTextField10.getText());
    }

    private void reloadbotton(ActionEvent e) {
    
        Preferences p = Preferences.userRoot();
        jTextField1.setText(p.get("erphost","")); 
        jTextField2.setText(p.get("erpport",""));
        jTextField3.setText(p.get("erpsid","")); 
        jTextField4.setText(p.get("erpusername",""));
        jPasswordField1.setText(p.get("erppassword",""));
        jTextField6.setText(p.get("sdhost",""));
        jTextField7.setText(p.get("sdport",""));
        jTextField8.setText(p.get("sdsid",""));
        jTextField5.setText(p.get("sdusername",""));
        jPasswordField2.setText(p.get("sdpassword",""));
        jTextField9.setText(p.get("CIN",""));
        jTextField10.setText(p.get("SDLOC",""));
    }
    private String ar2str(char[] varr){
       String temps="";
        for(int i=0;i < varr.length;i++){
            temps=temps+varr[i];
        }
        return temps;
    }

    private void erptest(ActionEvent e) {
    try{        
        Class.forName ("oracle.jdbc.driver.OracleDriver");
        String urlerp;
        urlerp = "jdbc:oracle:thin:@"+jTextField1.getText()+":"+jTextField2.getText()+":"+jTextField3.getText();
        Connection conerp = DriverManager.getConnection(urlerp,jTextField4.getText(),ar2str( jPasswordField1.getPassword()));
        conerp.setAutoCommit(false);
        Statement stmtMax = conerp.createStatement();
        ResultSet rsMax = stmtMax.executeQuery("select username  from all_users");
        jProgressBar1.setValue(0);
        while (rsMax.next()) {
        jProgressBar1.setForeground(new Color(100-jProgressBar1.getValue(),244,10));
        jProgressBar1.setValue(jProgressBar1.getValue()+1); 
        }
        
        jProgressBar1.setValue(100);
        rsMax.close();
        stmtMax.close();
        conerp.close();
        
    }
    catch(Exception ex){
         jProgressBar1.setValue(0);
        jProgressBar1.setBackground(new Color(244,0,0));
    }
}

    private void sdtest(ActionEvent e) {
    try{        
        Class.forName ("oracle.jdbc.driver.OracleDriver");
        String urlsd;
        urlsd = "jdbc:oracle:thin:@"+jTextField6.getText()+":"+jTextField7.getText()+":"+jTextField8.getText();
        Connection consd = DriverManager.getConnection(urlsd,jTextField5.getText(), jPasswordField2.getPassword().toString());
        consd.setAutoCommit(false);
        Statement stmtMax = consd.createStatement();
        ResultSet rsMax = stmtMax.executeQuery("select username  from all_users");
        jProgressBar2.setValue(0);
        while (rsMax.next()) {
        jProgressBar2.setForeground(new Color(100-jProgressBar2.getValue(),244,10));
        jProgressBar2.setValue(jProgressBar2.getValue()+1); 
        }
        
        jProgressBar2.setValue(100);
        rsMax.close();
        stmtMax.close();
        consd.close();
        
    }
    catch(Exception ex){
         jProgressBar2.setValue(0);
        jProgressBar2.setBackground(new Color(244,0,0));
    }
 
}

    private void post2erpkey(ActionEvent e) {
        String  xx = jCalendarCombo.getSelectedMonth()+"/"+jCalendarCombo.getSelectedDay()+"/"+jCalendarCombo.getSelectedYear();
     // String xx =jCalendarCombo.getDate().get(Calendar.MONTH)+"/"+jCalendarCombo.getDate().get(Calendar.DAY_OF_MONTH)+"/"+jCalendarCombo.getDate().get(Calendar.YEAR);
        new hh2compass(xx,this).start();
    }

    private void check_actionPerformed(ActionEvent e) {
    
        String xxfrom =jCalendarCombofrom.getSelectedMonth()+"/"+jCalendarCombofrom.getSelectedDay()+"/"+jCalendarCombofrom.getSelectedYear();
        String xxto =jCalendarComboto.getSelectedMonth()+"/"+jCalendarComboto.getSelectedDay()+"/"+jCalendarComboto.getSelectedYear();
        
        new hh2compasscheck(xxfrom,xxto,this).start();

    
    }
}
