package SendMessage;

import javax.swing.*;

import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.*;

public class SendMessage extends JFrame {
    
    public static JTextArea target = new JTextArea("内容");
    public static JTextField num = new JTextField("次数");
    public static JTextField interval = new JTextField("间隔时间");

    public static JButton send = new JButton("发送");

    //初始化窗口
    void InitWd(){
        //窗口的设置
        this.setSize(400,260);
        this.setLocationRelativeTo(null);
        this.setTitle("自动发消息");
        this.setLayout(new BorderLayout());
        this.setResizable(false);

        
        
        //信息准备部分-----------------------------------------------(1)
        JPanel JP1 = new JPanel();
        JP1.setLayout(new BoxLayout(JP1, BoxLayout.Y_AXIS));

        JPanel JP1_1 = new JPanel();
        JPanel JP1_2 = new JPanel();
        JP1_1.setLayout(new FlowLayout(FlowLayout.CENTER));
        JP1_2.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        //组件的设置
        JLabel JL1 = new JLabel("发送内容");
        JLabel JL2 = new JLabel("发送间隔");

        Font JLFONT = new Font("黑体",Font.PLAIN,20);
        JL1.setFont(JLFONT);
        JL2.setFont(JLFONT);

        // target.setPreferredSize(new Dimension(200, 22));
        interval.setPreferredSize(new Dimension(200, 22));
        target.setLineWrap(true);
        target.setWrapStyleWord(true);
        
        JScrollPane jsc = new JScrollPane(target);
        jsc.setPreferredSize(new Dimension(200,30));
        jsc.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        //添加组件
        JP1_1.add(JL1);
        JP1_2.add(JL2);
        JP1_1.add(jsc);
        JP1_2.add(interval);
        
        
        JP1.add(JP1_1);
        JP1.add(JP1_2);
        this.add(JP1);
        
        //确定发送部分-----------------------------------------------(2)
        JPanel JP2 = new JPanel();
        JP2.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        //组件的设置
        JLabel JL3 = new JLabel("发送次数");
        JL3.setFont(JLFONT);

        num.setPreferredSize(new Dimension(50,22));

        
        
        //添加组件
        JP2.add(JL3);
        JP2.add(num);
        JP2.add(send);
        
        this.add(JP2,BorderLayout.SOUTH);  
        
        //关闭窗口，推出程序
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * @param target                    发送内容
     * @param num                       发送次数
     * @param interval                  发送间隔
     * @throws AWTException
     * @throws InterruptedException
     */
    void StartSend(String target,int num,int interval) throws AWTException, InterruptedException{
        // 获取系统剪贴板
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        // 封装文本内容
        Transferable trans = new StringSelection(target);//发送的内容
        // 把文本内容设置到系统剪贴板
        clipboard.setContents(trans, null);
        
        //Robot的主要目的是便于 Java 平台实现自动测试。
        Robot robot = new Robot();
        Thread.sleep(5000);//设置5秒后开始运行

        for (int i = 1; i <= num; i++) {
            robot.keyPress(KeyEvent.VK_CONTROL);//按下ctrl键
            robot.keyPress(KeyEvent.VK_V);//按下v键

            robot.keyRelease(KeyEvent.VK_V);//松开v键

            robot.keyPress(KeyEvent.VK_ENTER);//按下enter键

            robot.keyRelease(KeyEvent.VK_CONTROL);//松开ctrl键
            robot.keyRelease(KeyEvent.VK_ENTER);//松开enter键
            System.out.println("这是第"+i+"次");
            Thread.sleep(interval);//间隔时长
        }
        


    }
    
    public static void main(String[] args) {
        SendMessage sd = new SendMessage();
        sd.InitWd();

        //按钮监听
        ActionListener listener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    sd.StartSend(target.getText(),Integer.parseInt(num.getText()),Integer.parseInt(interval.getText()));
                } catch (Exception e1) {
                    System.out.println("here2");
                }
            }
        };

        send.addActionListener(listener);
    }
}
