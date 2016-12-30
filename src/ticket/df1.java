package ticket;
import java.awt.*;
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
 import java.awt.event.WindowAdapter;
 import java.awt.event.WindowEvent;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.ObjectInputStream;
 import java.io.ObjectOutputStream;
 import java.util.ArrayList;
 
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
 
public class df1 implements ActionListener {
	ArrayList<User> list = new ArrayList<User>();
	JTextField jtf1 = new JTextField(14);
	JTextField jtf2 = new JTextField(14);
	JTextField jtf3 = new JTextField(14);
	JTextField jtf4 = new JTextField(14);
	JFrame jf;
	boolean match1 = false;
 
	public df1() throws Exception {
		createGUI();
		}
 //当事件发生时，自动调用actionPerformed()方法
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		if ("注册".equals(str)) {
			User user = new User();
			user.setUserName(jtf1.getText());
			user.setPassWord(jtf2.getText());
			user.setEmail(jtf4.getText());
			String rePassWord = jtf3.getText();
			if (!(user.getPassWord().equalsIgnoreCase(rePassWord))) {
				jtf2.setText("密码输入错误");
				jtf3.setText("密码输入错误");
				} else {
					jf.setTitle("注册成功" + "欢迎您" + user.getUserName());
					jtf2.setText("******");
					jtf3.setText("******");
					list.add(user);
					}
			} else if ("登录".equals(str)) {
				try {
					readFromFile();
					} catch (Exception e1) {
 
						e1.printStackTrace();}}}
 //createGui()方法，用于生成图形用户界面
	public void createGUI() {
		jf = new JFrame("用户注册");
 //当用户关闭窗口时，多个用户信息自动保存到本地文件系统(通过调用writeToFile()方法)
		jf.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {

				try {
					writeToFile();
					} catch (IOException e1) {
						e1.printStackTrace();
						}
				System.exit(0);
				}
			});
		jf.setLayout(new GridLayout(4, 2));
		JPanel jp1 = new JPanel();
		jf.add(jp1);
		JLabel jl1 = new JLabel("User Name:");
		jp1.add(jl1);jp1.add(jtf1);
		JPanel jp2 = new JPanel();
		jf.add(jp2);
		JLabel jl2 = new JLabel("Pass Word:");
		jp2.add(jl2);jp2.add(jtf2);
		JPanel jp3 = new JPanel();
		jf.add(jp3);
		JLabel jl3 = new JLabel("ReInputPwd:");
		jp3.add(jl3);
		jp3.add(jtf3);
		JPanel jp4 = new JPanel();
		jf.add(jp4);
		JLabel jl4 = new JLabel("E-mail:");
		jp4.add(jl4);jp4.add(jtf4);
		JPanel jp5 = new JPanel();
		jf.add(jp5);
		JButton jb51 = new JButton("注册");
		jb51.addActionListener(this);
		JButton jb52 = new JButton("登录");
		jb52.addActionListener(this);
		jp5.add(jb51);jp5.add(jb52);
		jf.setSize(500, 300);
		jf.setVisible(true);
		JButton jb53 = new JButton("购票");
		jb53.addActionListener(new ActionListener() {


			public void actionPerformed(ActionEvent e) {
				if(match1)
				{
				MyFirstJFrame m=new MyFirstJFrame(); 
				m.setVisible(true);
				jf.dispose();}
				}

		});
		jp5.add(jb53);
		}
 
//readFromFile()方法用于从文件中读取用户对象信息，并将用户对象添加进线性表（list)
	public void readFromFile() throws IOException, Exception {
		FileInputStream fis = new FileInputStream(new File(
				"d:\\user.txt"));
		ObjectInputStream ois = new ObjectInputStream(fis);
		User read = null;
		try {
			while (true) {
				read = (User) ois.readObject();
				if (read == null) {
					break;
					}
				list.add(read);
				}
			} catch (Exception e) {
				
			}
 // System.out.println(list);
		User temp = new User(jtf1.getText(), jtf2.getText(), "q");
		match(temp);
		ois.close();
		}
	//writeToFile()方法，实现用户信息保存功能。
	public void writeToFile() throws IOException {
		File file = new File("d:\\user.txt");
		file.createNewFile();
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
				file));
		for (User usr : list) {
			oos.writeObject(usr);
			}
		oos.flush();
		oos.close();
		}
 
	//当用户试图登录时，match()方法可以将用户输入与保存的信息，进行匹配。
	//如果用户名和密码正确，登录成功；否则，登录失败。
	public void match(User user) {
		
		for (User usr : list) {
			if (usr.getUserName().equals(user.getUserName())
					&& usr.getPassWord().equals(user.getPassWord())) {

				jf.setTitle("登录成功");
				match1 = true;
 
			break;
			}
			}
		if (!match1) {
			jf.setTitle("用户名或密码错误，请重新输入！");
			jtf1.setText("");
			jtf2.setText("");
			jtf3.setText("");
			jtf4.setText("");
			jtf1.requestFocus();
			}
		}  
 
	public static void main(String[] args) throws Exception {
		new df1();
		}
	}
@SuppressWarnings("serial")
class User implements java.io.Serializable {
	private String userName;
	private String passWord;
	private String email;
	public User() {
		
	}
	public User(String userName, String passWord, String email) {

		super();

		this.setUserName(userName);

		this.setPassWord(passWord);
		this.setEmail(email);
		}
	public String getEmail() {
		return email;
		}
	public void setEmail(String email) {
		this.email = email;
		}
	public String getPassWord() {
		return passWord;
		}

	public void setPassWord(String passWord) {

		this.passWord = passWord;
		}

	public String getUserName() {
		return userName;
		}

	public void setUserName(String userName) {
		this.userName = userName;
		}
 
	public String toString(){
		return userName+":"+passWord+":"+email;
		}
	}






 class MyFirstJFrame extends JFrame {
    
	   
   
    
   
    public MyFirstJFrame() {
        InitialComponent();
    }
    
   
    public void InitialComponent(){

         setLayout(null);
        setSize(480, 360);
         setLocationRelativeTo(null);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        

        panel = new JPanel();
        panel.setSize(this.getWidth(), this.getHeight());
        panel.setLocation(0,0);
        panel.setLayout(null);
        

        table = new JTable(new DefaultTableModel(new Object[][]{{"少年"},{"长城"},{"你的名字"},{"碟中谍"}}, new String[]{"测试行1","测试行2"}){
          
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        
      
        table.getColumnModel().getColumn(1).setCellRenderer(new TableCellRenderer(){

           
            @Override
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus,
                    int row, int column) {
                JCheckBox ck = new JCheckBox();
              
                ck.setSelected(isSelected);
            
                ck.setHorizontalAlignment((int) 0.5f);
                return ck;
            }});
        
        
        table.setSize(panel.getWidth(),panel.getHeight() - 90);
        table.setLocation(0, 0);
        
        
        btn = new JButton("Test");
        btn.setSize(80,40);
        btn.setLocation((panel.getWidth()) / 2 - 40, panel.getHeight() - 80);
        
      
    
        btn.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
            	MyTicketSale myTicketSale = new MyTicketSale();
        		myTicketSale.setVisible(true);
            }});
        
        panel.add(table);
        panel.add(btn);
        this.add(panel);    
        
    }
    
    
    private JPanel panel;
    private JTable table;
    private JButton btn;
}

