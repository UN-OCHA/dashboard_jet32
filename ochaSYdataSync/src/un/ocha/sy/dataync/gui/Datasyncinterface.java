package un.ocha.sy.dataync.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.BoxLayout;
import javax.swing.JTextArea;
import javax.swing.JInternalFrame;
import java.awt.Toolkit;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;

import un.ocha.sy.dataync.json.Gene_Json;
import un.ocha.sy.dataync.json.MSaccess_db;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.prefs.Preferences;

import javax.swing.JPasswordField;
import javax.swing.border.LineBorder;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.NewCookie;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import javax.swing.JTextField;

public class Datasyncinterface extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Datasyncinterface frame = new Datasyncinterface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JTextField DBlocation;
	private JPasswordField passwordField;
	private JButton btnTest;
	private JButton button;
	private JPanel panel_4;
	private JTextField textPane;
	private JTextField textPane_1;
	private JTextArea textPane_2;
	private JTextArea textPane_3;
	private JButton btnStopTheDashboard;
	private JScrollPane scrollPane;

	/**
	 * Create the frame.
	 */
	public Datasyncinterface() {
		setTitle("UN OCHA Syria Dashboard DataSync");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Datasyncinterface.class.getResource("/un/ocha/sy/dataync/resources/cropped-UN-OHRLLS_Logo-Icon-32x32.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 824, 446);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{817, 0};
		gridBagLayout.rowHeights = new int[]{411, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		getContentPane().add(panel, gbc_panel);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 817, GroupLayout.PREFERRED_SIZE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 411, GroupLayout.PREFERRED_SIZE)
		);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Settings", new ImageIcon(Datasyncinterface.class.getResource("/com/sun/java/swing/plaf/windows/icons/FloppyDrive.gif")), panel_1, null);
		
		JButton btnNewButton = new JButton("Select DB");
		
		btnNewButton.setToolTipText("Find the path to Ms access DB");
		btnNewButton.setIcon(new ImageIcon(Datasyncinterface.class.getResource("/com/sun/java/swing/plaf/windows/icons/TreeOpen.gif")));
		
		JTextPane txtpnMsaccessDbPath = new JTextPane();
		txtpnMsaccessDbPath.setEditable(false);
		txtpnMsaccessDbPath.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtpnMsaccessDbPath.setBackground(SystemColor.menu);
		txtpnMsaccessDbPath.setText("MSAccess DB Location");
		
		 DBlocation = new JTextField();
		 DBlocation.setEditable(false);
		
		btnTest = new JButton("Test");
		btnTest.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			
			try {
				
				MSaccess_db testdbcon = new MSaccess_db(getDBlocation().getText());
				testdbcon.ms_connaction.createStatement().execute("SELECT * FROM [01_online_iao_delivered]");
				geterror_log().setText("Database setting is correct.....ok");
				testdbcon.ms_connaction.close();
			} catch (Exception e1) {
				e1.printStackTrace();
				geterror_log().setText("Error.....please check the MSAccess database file location .....");
			}
			
			}
		});
		
		panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2, true), "OnLine Dashboard Setting", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		
		JButton btnNewButton_1 = new JButton("Save Setting");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Preferences p = Preferences.userRoot();
				
				p.put("dboardurl", getdashboardURL().getText());
		        p.put("dblocation", getDBlocation().getText());
		        p.put("dboardpw", getPasswordField().getText());
		        p.put("dboardusername", getusername().getText());
				
			 
			}
		});
		
		JButton btnReloadSetting = new JButton("Reload setting");
		btnReloadSetting.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 Preferences p = Preferences.userRoot();
				 getdashboardURL().setText(p.get("dboardurl","")); 
				 getDBlocation().setText(p.get("dblocation",""));
				 getPasswordField().setText(p.get("dboardpw","")); 
				 getusername().setText(p.get("dboardusername",""));
				
				
			}
		});
		
		textPane_2 = new JTextArea();
		textPane_2.setEditable(false);
		textPane_2.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Test Log", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(202)
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
					.addGap(114)
					.addComponent(btnReloadSetting, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(179, Short.MAX_VALUE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(55)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 745, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(txtpnMsaccessDbPath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(DBlocation, GroupLayout.PREFERRED_SIZE, 368, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
							.addComponent(btnNewButton)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnTest)
							.addGap(14))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(textPane_2, GroupLayout.PREFERRED_SIZE, 745, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(56)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(DBlocation, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
							.addGap(18))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
									.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
									.addComponent(btnTest))
								.addComponent(txtpnMsaccessDbPath, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(17)))
					.addGap(18)
					.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textPane_2, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1)
						.addComponent(btnReloadSetting))
					.addContainerGap())
		);
		
		passwordField = new JPasswordField();
		
		textPane_1 = new JTextField();
		
		JTextPane txtpnPassword = new JTextPane();
		txtpnPassword.setEditable(false);
		txtpnPassword.setText("PassWord");
		txtpnPassword.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtpnPassword.setBackground(SystemColor.menu);
		
		JTextPane txtpnUserName = new JTextPane();
		txtpnUserName.setEditable(false);
		txtpnUserName.setText("User Name");
		txtpnUserName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtpnUserName.setBackground(SystemColor.menu);
		
		textPane = new JTextField();
		textPane.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textPane.setToolTipText("For Example \"https://iaoperation.ochasyria.org/dashboard/\"");
		
		button = new JButton("Test");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
						Client client = Client.create();

		        WebResource webResource = client.resource(getdashboardURL().getText());

				Builder test = webResource.path("webapi/rs/login")
									.header("ocha_user", getusername().getText())
									.header("ocha_user_pw", getPasswordField().getText());
				
				 ClientResponse dfdfd = test.head();
				 String ocha_tkn = null;
				 List<NewCookie> cookielist = dfdfd.getCookies();
				 for (   NewCookie cok :cookielist) {
					if (cok.getName().equals("ocha-tkn")) 
						ocha_tkn=cok.getValue();
					
				}
				
				 if (ocha_tkn!=null) {
				geterror_log().setText( "Test complete successfully....");	
				} else {
				geterror_log().setText("Error.....please check internet connection and/or (URL/User name /password). " );
				}
				 
				} catch (Exception e2) {
//					geterror_log().setText("Error.....please check internet connection and/or (URL/User name /password). " );
					e2.printStackTrace();
				}
			

				 
				 
			}
		});
		
		JTextPane txtpnDashboardUrl = new JTextPane();
		txtpnDashboardUrl.setEditable(false);
		txtpnDashboardUrl.setText("Dashboard URL");
		txtpnDashboardUrl.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtpnDashboardUrl.setBackground(SystemColor.menu);
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_4.createSequentialGroup()
					.addContainerGap(28, Short.MAX_VALUE)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.TRAILING)
						.addComponent(txtpnPassword, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtpnUserName, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtpnDashboardUrl, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_4.createSequentialGroup()
							.addComponent(textPane, GroupLayout.PREFERRED_SIZE, 328, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(button))
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 328, GroupLayout.PREFERRED_SIZE)
						.addComponent(textPane_1, GroupLayout.PREFERRED_SIZE, 328, GroupLayout.PREFERRED_SIZE))
					.addGap(47))
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_4.createSequentialGroup()
					.addContainerGap(59, Short.MAX_VALUE)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.TRAILING)
						.addComponent(button)
						.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
							.addComponent(txtpnDashboardUrl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(textPane, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addComponent(txtpnUserName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textPane_1, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(passwordField, Alignment.LEADING)
						.addComponent(txtpnPassword, Alignment.LEADING))
					.addGap(21))
		);
		panel_4.setLayout(gl_panel_4);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				final JFileChooser fc = new JFileChooser();
				        int returnVal = fc.showOpenDialog(null );

				        if (returnVal == JFileChooser.APPROVE_OPTION) {
				            File file = fc.getSelectedFile();
				            getDBlocation().setText(file.getPath());
				            //This is where a real application would open the file.
				            
				        } 
				   
			}
		});
		panel_1.setLayout(gl_panel_1);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Data Sync", new ImageIcon(Datasyncinterface.class.getResource("/javax/swing/plaf/metal/icons/ocean/hardDrive.gif")), panel_2, null);
		
		JButton btnDbBackupFor = new JButton("Online Dashboard DB Backup");
		btnDbBackupFor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {

					Client client = Client.create();

					
					WebResource webResource = client.resource(getdashboardURL().getText());

					Builder test = webResource.path("webapi/rs/login")
										.header("ocha_user", getusername().getText())
										.header("ocha_user_pw", getPasswordField().getText());
					
					 ClientResponse dfdfd = test.head();
					 String ocha_tkn = null;
					 List<NewCookie> cookielist = dfdfd.getCookies();
					 for (   NewCookie cok :cookielist) {
						if (cok.getName().equals("ocha-tkn")) {
							ocha_tkn=cok.getValue();
						}
						
					}
					 
					 Cookie auth = new Cookie("ocha-tkn", ocha_tkn);
			        
			        //webapi/Rs_DS_BK_2_DS
					 //webapi/Rs_DS_DS_2_BK
			      
						ClientResponse response = webResource.path("webapi/Rs_DS_DS_2_BK").type("application/json").cookie(auth).head();

					
					datasynclog().setText( datasynclog().getText()+ " Http code : "+ response.getStatus() +" URL webapi/Rs_DS_DS_2_BK # "+ response.getEntity(String.class) +"\n");
					
					
				
			        
				  } catch (Exception ee) {

					  datasynclog().setText( ee.getMessage());

				  }
			}
		});
		
		JButton btnDbRestoreTo = new JButton("DB Restore to Online Dashboard");
		btnDbRestoreTo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				try {

					Client client = Client.create();

					
					WebResource webResource = client.resource(getdashboardURL().getText());

					Builder test = webResource.path("webapi/rs/login")
										.header("ocha_user", getusername().getText())
										.header("ocha_user_pw", getPasswordField().getText());
					
					 ClientResponse dfdfd = test.head();
					 String ocha_tkn = null;
					 List<NewCookie> cookielist = dfdfd.getCookies();
					 for (   NewCookie cok :cookielist) {
						if (cok.getName().equals("ocha-tkn")) {
							ocha_tkn=cok.getValue();
						}
						
					}
					 
					 Cookie auth = new Cookie("ocha-tkn", ocha_tkn);
			        
			        //webapi/Rs_DS_BK_2_DS
					 //webapi/Rs_DS_DS_2_BK
			      
						ClientResponse response = webResource.path("webapi/Rs_DS_BK_2_DS").type("application/json").cookie(auth).head();

					
					datasynclog().setText( datasynclog().getText()+ " Http code : "+ response.getStatus() +" URL webapi/Rs_DS_BK_2_DS # "+ response.getEntity(String.class)+"\n" );
					
					
				
			        
				  } catch (Exception ee) {

					  datasynclog().setText( ee.getMessage());

				  }
			}
		});
		
		JButton btnUploadDataTo = new JButton("1 - Upload data to Online Dashboard");
		btnUploadDataTo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				

				try {

					Client client = Client.create();

					
					MSaccess_db tmp_db = new MSaccess_db(getDBlocation().getText());
					
					String[][] input= new Gene_Json(tmp_db).json_Cup;
					
					tmp_db.ms_connaction.close();
					
					WebResource webResource = client.resource(getdashboardURL().getText());

					Builder test = webResource.path("webapi/rs/login")
										.header("ocha_user", getusername().getText())
										.header("ocha_user_pw", getPasswordField().getText());
					
					 ClientResponse dfdfd = test.head();
					 String ocha_tkn = null;
					 List<NewCookie> cookielist = dfdfd.getCookies();
					 for (   NewCookie cok :cookielist) {
						if (cok.getName().equals("ocha-tkn")) {
							ocha_tkn=cok.getValue();
						}
						
					}
					 
					 Cookie auth = new Cookie("ocha-tkn", ocha_tkn);
			        
			        
			        for (int i = 0; i < 10; i++) {
						ClientResponse response = webResource.path(input[i][0]).type("application/json").cookie(auth)
					   .post(ClientResponse.class, input[i][1]);

					if (response.getStatus() != 200) {
					//	throw new RuntimeException("Failed : HTTP error code : "
					//	     + response.getStatus() +response.getLocation()+"  table : "+i );
					}
					datasynclog().setText( datasynclog().getText()+ " Http code : "+ response.getStatus() +" URL"+ input[i][0] +" Row# "+ response.getEntity(String.class) +"\n");
					
					
					}
			        
				  } catch (Exception ee) {

					ee.printStackTrace();

				  }
			}
		});
		
		JButton btnDatasync = new JButton("2 - DataSync");
		btnDatasync.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {

					Client client = Client.create();

					
					WebResource webResource = client.resource(getdashboardURL().getText());

					Builder test = webResource.path("webapi/rs/login")
										.header("ocha_user", getusername().getText())
										.header("ocha_user_pw", getPasswordField().getText());
					
					 ClientResponse dfdfd = test.head();
					 String ocha_tkn = null;
					 List<NewCookie> cookielist = dfdfd.getCookies();
					 for (   NewCookie cok :cookielist) {
						if (cok.getName().equals("ocha-tkn")) {
							ocha_tkn=cok.getValue();
						}
						
					}
					 
					 Cookie auth = new Cookie("ocha-tkn", ocha_tkn);
			        
			        //webapi/Rs_DS_BK_2_DS
					 //webapi/Rs_DS_DS_2_BK
			      
						ClientResponse response = webResource.path("webapi/Rs_DS_Sync_2_DS").type("application/json").cookie(auth).head();

					
					datasynclog().setText( datasynclog().getText()+ " Http code : "+ response.getStatus() +" URL webapi/Rs_DS_Sync_2_DS # "+ response.getEntity(String.class)+"\n" );
					
					
				
			        
				  } catch (Exception ee) {

					  datasynclog().setText( ee.getMessage());

				  }
				
			}
		});
		
		btnStopTheDashboard = new JButton("Stop The Dashboard");
		btnStopTheDashboard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				try {

					Client client = Client.create();

					
					WebResource webResource = client.resource(getdashboardURL().getText());

					Builder test = webResource.path("webapi/rs/login")
										.header("ocha_user", getusername().getText())
										.header("ocha_user_pw", getPasswordField().getText());
					
					 ClientResponse dfdfd = test.head();
					 String ocha_tkn = null;
					 List<NewCookie> cookielist = dfdfd.getCookies();
					 for (   NewCookie cok :cookielist) {
						if (cok.getName().equals("ocha-tkn")) {
							ocha_tkn=cok.getValue();
						}
						
					}
					 
					 Cookie auth = new Cookie("ocha-tkn", ocha_tkn);
			        
			        //webapi/Rs_DS_BK_2_DS
					 //webapi/Rs_DS_DS_2_BK
			      
						ClientResponse response = webResource.path("webapi/Rs_DS_stop").type("application/json").cookie(auth).head();

					
					datasynclog().setText( datasynclog().getText()+ " Http code : "+ response.getStatus() + response.getEntity(String.class)+"\n" );
					
					
				
			        
				  } catch (Exception ee) {

					  datasynclog().setText( ee.getMessage());

				  }
			}
		});
		
		scrollPane = new JScrollPane();
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(126)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addComponent(btnUploadDataTo, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnDbBackupFor, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE))
							.addGap(61)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnDbRestoreTo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnDatasync, GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(274)
							.addComponent(btnStopTheDashboard, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
					.addContainerGap(80, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 665, GroupLayout.PREFERRED_SIZE)
					.addGap(67))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(55)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnDbBackupFor)
						.addComponent(btnDbRestoreTo))
					.addGap(62)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnUploadDataTo)
						.addComponent(btnDatasync))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnStopTheDashboard)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		textPane_3 = new JTextArea();
		scrollPane.setViewportView(textPane_3);
		textPane_3.setAutoscrolls(false);
		textPane_3.setEditable(false);
		textPane_3.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Operations Log", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		panel_2.setLayout(gl_panel_2);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("About", null, panel_3, null);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 3, true), "Author", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(188)
					.addComponent(panel_6, GroupLayout.PREFERRED_SIZE, 436, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(188, Short.MAX_VALUE))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(76)
					.addComponent(panel_6, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(127, Short.MAX_VALUE))
		);
		
		JLabel lblEmailMhdtameroutlookcom = new JLabel("Email       : mhd.tamer@outlook.com");
		lblEmailMhdtameroutlookcom.setFont(new Font("Tahoma", Font.BOLD, 17));
		
		JLabel lblAuthorMt = new JLabel("Author    : MT IS Consultancy");
		lblAuthorMt.setFont(new Font("Tahoma", Font.BOLD, 17));
		
		JLabel lblVersion = new JLabel("Version   : 0.9.92 ");
		lblVersion.setFont(new Font("Tahoma", Font.BOLD, 17));
		
		JLabel lblNewLabel = new JLabel("Title        : UN OCHA Syria Dashboard DataSync ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		GroupLayout gl_panel_6 = new GroupLayout(panel_6);
		gl_panel_6.setHorizontalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addComponent(lblVersion)
						.addComponent(lblAuthorMt)
						.addComponent(lblEmailMhdtameroutlookcom))
					.addContainerGap(67, Short.MAX_VALUE))
		);
		gl_panel_6.setVerticalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblAuthorMt)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblEmailMhdtameroutlookcom)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblVersion)
					.addContainerGap(35, Short.MAX_VALUE))
		);
		panel_6.setLayout(gl_panel_6);
		panel_3.setLayout(gl_panel_3);
		panel.setLayout(gl_panel);
		Preferences p = Preferences.userRoot();
		getdashboardURL().setText(p.get("dboardurl","")); 
		getDBlocation().setText(p.get("dblocation",""));
		getPasswordField().setText(p.get("dboardpw","")); 
		getusername().setText(p.get("dboardusername",""));
		panel_4.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{textPane_1, passwordField, button, textPane}));
		
		
	}
	public  JTextField getdashboardURL() {
		return textPane;
	}
	public  JTextField getusername() {
		return textPane_1;
	}
	public JPasswordField getPasswordField() {
		return passwordField;
	}
	public  JTextField getDBlocation() {
		return DBlocation;
	}
	public JTextArea geterror_log() {
		return textPane_2;
	}
	public JTextArea datasynclog() {
		return textPane_3;
	}
}
