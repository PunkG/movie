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
 //���¼�����ʱ���Զ�����actionPerformed()����
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		if ("ע��".equals(str)) {
			User user = new User();
			user.setUserName(jtf1.getText());
			user.setPassWord(jtf2.getText());
			user.setEmail(jtf4.getText());
			String rePassWord = jtf3.getText();
			if (!(user.getPassWord().equalsIgnoreCase(rePassWord))) {
				jtf2.setText("�����������");
				jtf3.setText("�����������");
				} else {
					jf.setTitle("ע��ɹ�" + "��ӭ��" + user.getUserName());
					jtf2.setText("******");
					jtf3.setText("******");
					list.add(user);
					}
			} else if ("��¼".equals(str)) {
				try {
					readFromFile();
					} catch (Exception e1) {
 
						e1.printStackTrace();}}}
 //createGui()��������������ͼ���û�����
	public void createGUI() {
		jf = new JFrame("�û�ע��");
 //���û��رմ���ʱ������û���Ϣ�Զ����浽�����ļ�ϵͳ(ͨ������writeToFile()����)
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
		JButton jb51 = new JButton("ע��");
		jb51.addActionListener(this);
		JButton jb52 = new JButton("��¼");
		jb52.addActionListener(this);
		jp5.add(jb51);jp5.add(jb52);
		jf.setSize(500, 300);
		jf.setVisible(true);
		JButton jb53 = new JButton("��Ʊ");
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
 
//readFromFile()�������ڴ��ļ��ж�ȡ�û�������Ϣ�������û�������ӽ����Ա�list)
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
	//writeToFile()������ʵ���û���Ϣ���湦�ܡ�
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
 
	//���û���ͼ��¼ʱ��match()�������Խ��û������뱣�����Ϣ������ƥ�䡣
	//����û�����������ȷ����¼�ɹ������򣬵�¼ʧ�ܡ�
	public void match(User user) {
		
		for (User usr : list) {
			if (usr.getUserName().equals(user.getUserName())
					&& usr.getPassWord().equals(user.getPassWord())) {

				jf.setTitle("��¼�ɹ�");
				match1 = true;
 
			break;
			}
			}
		if (!match1) {
			jf.setTitle("�û���������������������룡");
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
        

        table = new JTable(new DefaultTableModel(new Object[][]{{"����"},{"����"},{"�������"},{"���е�"}}, new String[]{"������1","������2"}){
          
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

