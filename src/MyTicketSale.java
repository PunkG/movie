import java.applet.*;
import java.awt.*;
import java.util.*;
class clock2 implements Runnable            //ʱ���߳�
{
 Date m_date;
 Thread m_runner=null;
  TextField gr;
  TextField gt;
  public clock2(TextField r,TextField t)          // r��ȡ���ڡ� t��ȡʱ��
  {
    gr=r;
    gt=t;
    if(m_runner==null)
    {
      m_runner=new Thread(this);
      m_runner.start();
    }
  }
public void stop()
	{
		if (m_runner != null)
		{
			m_runner.stop();
			m_runner = null;
		}
	}
  public void run()
  {
    while(true)
    {
      m_date=new Date();
      String temp1=new String("����:"+String.valueOf(1+m_date.getMonth())+"/"+String.valueOf(m_date.getDate())+"/"+String.valueOf(1900+m_date.getYear()));
      String temp2=new String("ʱ��:"+String.valueOf(m_date.getHours())+":"+String.valueOf(m_date.getMinutes())+":"+String.valueOf(m_date.getSeconds()));
      gr.setText(temp1);
      gt.setText(temp2);
      try{
         Thread.sleep(1000);
         }
      catch(InterruptedException e){}
     }
  }
}
     




