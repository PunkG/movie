package ticket;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class MyTicketSale extends JFrame implements Runnable // 主程序开端
{
    private Thread m_TicketSale = null;
    private Graphics m_Graphics;
    private Image m_Images[];
    private int m_nCurrImage = 0;
    private boolean m_fAllLoaded = false;
    private final int NUM_IMAGES = 9;
    JTextField textField1, textField2;
    Clock1 pp;
    JTextField tRemain;
    JTextField tTotal;
    JTextField tSold;
    int remain = 200;
    int sold = 0;
    int total = 0;
    JButton mybutton;
    Dimension butd;
    private boolean m_fStandAlone = false;
    private Component buttonPanel;
    JPanel panel_left;
    JPanel panel_right;
    JPanel panel_left_up;
    JPanel panel_left_middle;
    JPanel panel_left_down;
    JPanel panel_left_up_up;
    JPanel panel_left_up_down;
    JPanel panel_left_middle_up;
    JPanel panel_left_middle_down;

    public static void main(String[] args) {
        MyTicketSale myTicketSale = new MyTicketSale();
        myTicketSale.setVisible(true);
    }

    public MyTicketSale() {
        super();

        initPanels();
        initTextFields();

//		setFont(new Font("TimesRoman", Font.BOLD, 10));
//		textField1 = new JTextField();
//		textField2 = new JTextField();
//		//TODO PANEL RIGHT add(textField1);
//		//TODO PANEL RIGHT add(textField2);
//		textField1.setFont(new Font("TimesRoman", Font.BOLD, 15));
//		textField2.setFont(new Font("TimesRoman", Font.BOLD, 15));
//		pp = new Clock1(textField1, textField2); // 日期和时钟

        tRemain = new JTextField(Integer.toString(remain) + "张", 10);
        tRemain.setEditable(false);
        add(tRemain);
        tRemain.setFont(new Font("TimesRoman", Font.BOLD, 15));
        tRemain.reshape(400, 160, 80, 20);
        tSold = new JTextField(Integer.toString(sold) + "张", 10);
        tSold.setEditable(false);
        tSold.setFont(new Font("TimesRoman", Font.BOLD, 15));
        add(tSold);
        tSold.reshape(100, 160, 80, 20);
        tTotal = new JTextField(Integer.toString(total) + "元", 10);
        tTotal.setEditable(false);
        add(tTotal);
        tTotal.setFont(new Font("TimesRoman", Font.BOLD, 15));
        tTotal.reshape(640, 160, 80, 30);
        m_Graphics = getGraphics();


        TextField myTextField2 = new TextField("《少年》");
        myTextField2.setForeground(Color.blue);
        myTextField2.setFont(new Font("TimesRoman", Font.BOLD, 30));
        add(myTextField2);
        myTextField2.reshape(300, 60, 150, 40);
        TextField myTextField3 = new TextField("售出票:");
        myTextField3.setFont(new Font("TimesRoman", Font.BOLD, 15));
        add(myTextField3);
        myTextField3.reshape(10, 160, 80, 20);
        TextField myTextField4 = new TextField("剩余票:");
        myTextField4.setFont(new Font("TimesRoman", Font.BOLD, 15));
        add(myTextField4);
        myTextField4.reshape(300, 160, 80, 20);
        TextField myTextField5 = new TextField("总金额:");
        myTextField5.setFont(new Font("TimesRoman", Font.BOLD, 15));
        add(myTextField5);
        myTextField5.reshape(550, 160, 80, 20);

        TextField myTextField6 = new TextField(
                "票价：1--3排 10元；4--6排 5元；7--10排 2元");
        myTextField6.setForeground(Color.blue);
        myTextField6.setFont(new Font("TimesRoman", Font.BOLD, 15));
        add(myTextField6);
        myTextField6.reshape(5, 195, 500, 20);

        this.setBounds(new Rectangle(0, 0, 1024, 600));
        this.setLayout(null);
        this.getContentPane().add(panel_left);
        this.getContentPane().add(panel_right);
        this.setBackground(Color.orange);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        startTimer();
    }

    private void initTextFields() {
        JTextField latestMovieTextField = new JTextField();
        latestMovieTextField.setText("最新电影：");
        latestMovieTextField.setForeground(Color.RED);
        panel_left_up_up.add(latestMovieTextField);
    }

    private void initPanels() {
        panel_left = new JPanel();
        panel_right = new JPanel();
        panel_left.setBounds(new Rectangle(0, 0, 1024, 600));

        panel_left.setLayout(new GridLayout(3, 1));
        panel_right.setLayout(new GridLayout(1, 1));

        panel_left_up = new JPanel();
        panel_left_up.setLayout(new GridLayout(2, 1));
        panel_left_middle = new JPanel();
        panel_left_middle.setLayout(new GridLayout(2, 1));
        panel_left_down = new JPanel();
        panel_left_down.setLayout(new GridLayout(1, 1));
        panel_left_down.add(createButtonPanel());
        panelAdd(panel_left, panel_left_up, panel_left_middle, panel_left_down);


        panel_left_up_up = new JPanel();
        panel_left_up_up.setLayout(new GridLayout(1, 1));
        panel_left_up_down = new JPanel();
        panel_left_up_down.setLayout(new GridLayout(1, 1));
        panelAdd(panel_left_up, panel_left_up_up, panel_left_up_down);

        panel_left_middle_up = new JPanel();
        panel_left_middle_up.setLayout(new GridLayout(1, 3));
        panel_left_middle_down = new JPanel();
        panel_left_middle_down.setLayout(new GridLayout(1, 1));
        panelAdd(panel_left_middle, panel_left_middle_up, panel_left_middle_down);
    }

    private void panelAdd(JPanel panel, Component... component) {
        for (Component component1 : component) {
            panel.add(component1);
        }
    }

    public JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(10, 20));
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 20; j++) {
                JButton myButton = new JButton(i + "排" + j + "列");
                myButton.addActionListener(buttonActionHandler);
                buttonPanel.add(myButton);
            }
        }
        return buttonPanel;
    }

    public void destroy() {
        pp.stop();
    }

    private void displayImage(Graphics g) {
        if (!m_fAllLoaded)
            return;
        g.drawImage(m_Images[m_nCurrImage], 1100, 100, 400, 600, null); // 放动画

    }

   
    public void paint(Graphics g) {
        super.paint(g);
        if (m_fAllLoaded) {
            displayImage(g);
        } else
            g.drawString("Loading images...", 10, 20);

    }

    private ActionListener buttonActionHandler = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            int price = 0;
            if (e.getSource() instanceof JButton) {
                JButton button = (JButton) e.getSource();
                String text = button.getText();
                switch (text.charAt(0)) // 设定价格
                {
                    case '1': {
                        if (text.charAt(1) == '0') {
                            price = 2;
                        } else
                            price = 10;
                    }
                    break;
                    case '2':
                        price = 10;
                        break;
                    case '3':
                        price = 10;
                        break;
                    case '4':
                        price = 5;
                        break;
                    case '5':
                        price = 5;
                        break;
                    case '6':
                        price = 5;
                        break;
                    case '7':
                        price = 2;
                        break;
                    case '8':
                        price = 2;
                        break;
                    case '9':
                        price = 2;
                        break;
                    default:
                        return;//TODO 考虑返回值
                }
                button.setText("已售出"); // 标明卖出
                button.setEnabled(false);
                remain--;
                sold++;
                total += price;
                tRemain.setText(remain + "张");
                tSold.setText(sold + "张");
                tTotal.setText(total + "元");
            }
            return;
        }
    };

    public void startTimer() {
        if (m_TicketSale == null) {
            m_TicketSale = new Thread(this);
            m_TicketSale.start();
        }
    }

    public void stop() {
        if (m_TicketSale != null) {
//            m_TicketSale.stop();
            m_TicketSale = null;
        }
    }

    public void run() {
        m_nCurrImage = 0;
        //m_Graphics=getGraphics();
        if (!m_fAllLoaded) {

            m_Images = new Image[NUM_IMAGES];
            MediaTracker tracker = new MediaTracker(this);
            String strImage;

            
            try {
            	 for (int i = 1; i <= NUM_IMAGES; i++) {
                     strImage = "./images/movie" + ((i < 10) ? "0" : "") + i + ".jpg";
                     // if (m_fStandAlone)
                     // m_Images[i - 1] = Toolkit.getDefaultToolkit().getImage(
                     // strImage);
                     // else{
                     // URL url = getDocumentBase();
                     // String s=strImage;
                     // m_Images[i - 1] = getImage(getDocumentBase(), strImage);
                     //
                     // }
                    // m_Images[i - 1] = Toolkit.getDefaultToolkit().getImage(strImage);
                     m_Images[i - 1]=ImageIO.read(new File(strImage));
                     ImageIcon img = new ImageIcon(strImage);
                     tracker.addImage(m_Images[i - 1], 0);
                 }
                tracker.waitForAll();
                m_fAllLoaded = !tracker.isErrorAny();
            } catch (InterruptedException e) {
                stop();
            }catch(IOException ioe){
            	stop();
            }
            if (!m_fAllLoaded) {
                stop();
                m_Graphics.drawString("Error loading images!", 10, 10);
                return;
            }
        }
        while (true) {
            try {
                displayImage(m_Graphics);
                m_nCurrImage++;
                m_nCurrImage %= NUM_IMAGES;

                Thread.sleep(3000);
            } catch (InterruptedException e) {
                stop();
            }
        }
    }

}
