import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.net.URL;
import javax.swing.*;  
import java.awt.Graphics;  
import java.io.*;
import java.nio.*;
import java.awt.image.*;


class ss extends Canvas 
{  String z;
   BufferedImage nhg;
  public void paint(Graphics g) {  
  
        Toolkit t=Toolkit.getDefaultToolkit();  
        Image i=t.getImage(z);  
        g.drawImage(i, 120,100,this);  
        g.drawImage(nhg,120,400,this);
 }
}
 class jframes
{
JFrame f;
jframes()
{
f=new JFrame();
f.setSize(700,700);
f.setVisible(true);
}

void displayimage(String z,String fs) throws IOException 
{
BufferedImage bi;

File k = new File(z);
bi = ImageIO.read(k);


int h=bi.getHeight();
int w=bi.getWidth();

System.out.println("height is"+h);System.out.println("Width is"+w);
//System.out.println(fs.length());

fs=fs.replaceAll(" ","");
String tsr=btob(fs.length());
System.out.println(tsr);

//System.out.println(btob());


fs=tsr+fs;
System.out.println(fs);
int count=0;

 for(int i=0;i<h && count<fs.length();i++)
 {
	for(int j=0;j<w && count<fs.length();j++)
    { 
         int pixel= bi.getRGB(j,i);
       int blue = (pixel) & 0xff;
	   System.out.print(blue+ " after  ");
        if(fs.charAt(count)=='1')
		{blue=blue|0x1;	
	      pixel=pixel | 0x1;
		}
		else if(fs.charAt(count)=='0')
		{
		  blue=blue & ~(0x1);	
	      pixel=pixel & ~(0x1);
		 
		}
	   System.out.println(blue);
	   count++;
     /*  //pixel=pixel & blue;
	 Color c=new Color(bi.getRGB(j,i));
     //System.out.print("sno:"+j+"  ");
	 //System.out.print(c.getRed()+" ");
	 //System.out.print(c.getBlue()+" ");
	 //System.out.println(c.getGreen());
	 int tx=c.getRed(),ty=c.getBlue(),tz=c.getGreen();
	 //System.out.println(bi.getRGB(j,i));
	 
	 if(count<fs.length())
	 { 
         if(i==0 && j<3)
         	System.out.println(tx);	
      if(fs.charAt(count++)=='1')
		if(tx%2==0)
		 tx+=1;
       
	 } 
   
   if(count<fs.length() )
	 {  
        //   if(i==0 && j<3)
         //	System.out.println(tz);	
      if(fs.charAt(count++)=='1')
		if(ty%2==0)
		 ty+=1;		 
	 }
	 
    if(count<fs.length())
	 {  if(fs.charAt(count++)=='1')
		if(tz%2==0)
		 tz+=1;		 
	 }
	//System.out.println();
    Color tc=new Color(tx,ty,tz);	
   */ 
     bi.setRGB(j,i,pixel);
 }
 
 }
ImageIO.write(bi,"png",new File("c:/users/kondragunta/desktop/newimg.png"));


ss e=new ss();
e.z=z;
e.nhg=bi;
f.add(e);


}

static String btob(int n)
{
String temp="";

for(;n>0;n=n/2)
{
 temp+=Integer.toString(n%2);
}
StringBuilder s=new StringBuilder(temp);
if(s.length()<8)
{
	for(int i=s.length();i<8;i++)
		s.append(new StringBuilder("0"));
}
s.reverse();
//s.append(" ");
return new String(s);
} 



}

class encode extends Frame implements ActionListener
{ String s,z;
  TextArea ta;
  FileDialog fd;
  Label l1;
encode()
{
ta=new TextArea();
Label l=new Label("Write your secret message");
l1=new Label();
l.setBounds(10,40,150,50);
ta.setBounds(10,100,170,200);
//ta.setText("encrypt");
fd=new FileDialog(this,"SELECT AN IMAGE");
Button b=new Button("Choose image");
Button b1=new Button("submit");
b.setBounds(10,400,100,50);
b1.setBounds(10,575,100,50);
l1.setBounds(10,475,500,50);
b.addActionListener(this);
b1.addActionListener(this);
add(l);
add(ta);
add(b);
add(b1);
add(l1);
//ta.addTextListener(this);
setSize(700,700);
setLayout(null);
setVisible(true);
}
/*public void textValueChanged(TextEvent e)
{
s=ta.getText();
msgtobin(s);
}*/

public void actionPerformed(ActionEvent e)
{
 if(e.getActionCommand()=="Choose image")
{
 fd.setVisible(true);	
 z=fd.getDirectory() + fd.getFile();
 z=z.replace("\\","\\\\");
 l1.setText(z);
 fd.dispose();
}
if(e.getActionCommand()=="submit")
{
 s=ta.getText();
 String fs=msgtobin(s);
	
 jframes f=new jframes();
 try{
 f.displayimage(z,fs);
 }
 catch(IOException ie){}
 dispose();
 } 
}

static String  msgtobin(String s)
{
StringBuilder sb=new StringBuilder();
byte b[]=s.getBytes();
for(int i=0;i<b.length;i++)
{
 sb.append(btob(b[i]));
}	
 //System.out.println(sb);
String fs=new String(sb);
return fs;
}

static StringBuilder btob(int n)
{
String temp="";
for(;n>0;n=n/2)
{
 temp+=Integer.toString(n%2);
}
StringBuilder s=new StringBuilder(temp);
if(s.length()<8)
{
	for(int i=s.length();i<8;i++)
		s.append(new StringBuilder("0"));
}
s.reverse();
s.append(" ");
return s;
} 

}

class decode extends Frame implements ActionListener 
{
FileDialog fd;
String z;
Label l1;
Panel p;
Label l2;
decode()
{
//Panel p=new Panel();
//p.setBounds(10,350,170,200);
//ta.setText("decrypt");
//add(p);
l1=new Label();
fd=new FileDialog(this,"SELECT AN IMAGE");
l2=new Label();
Button b=new Button("Choose image");
Button b1=new Button("submit");
b.setBounds(10,50,100,50);
b1.setBounds(10,250,100,50);
l1.setBounds(10,150,700,50);
add(l1);
l2.setBounds(10,350,700,50);
add(l2);
add(b);
b.addActionListener(this);
add(b1);
b1.addActionListener(this);
setSize(700,700);
setLayout(null);
setVisible(true);
}

public void actionPerformed(ActionEvent e)
{
 if(e.getActionCommand()=="Choose image")
{
 fd.setVisible(true);	
 z=fd.getDirectory() + fd.getFile();
 z=z.replace("\\","\\\\");
 l1.setText(z);
 fd.dispose();
}
else if(e.getActionCommand()=="submit")
{

File k = new File(z);
BufferedImage bi=null;
try{
bi = ImageIO.read(k);
}
catch(Exception ioe)
{}

int h=bi.getHeight();
int w=bi.getWidth();

String s="";
int count=0;
//for(int i=0;i<h;i++)
for(int j=0;j<w && count<8;j++,count++)
{ 
int pixel=bi.getRGB(j,0); 
int blue=pixel & 0xff;
int bitl=blue & 0x1;
System.out.println(blue+"   bit  "+bitl);
//System.out.print(bitl);
s+=Integer.toString(bitl);
}

int len=Integer.parseInt(s,2);
System.out.println("len "+len);
count=1;
String msg="";
s="";
for(int i=0;i<h && count<=len;i++)
{
for(int j=8;j<w && count<=len;j++,count++)	
{
int pixel=bi.getRGB(j,0); 
int blue=pixel & 0xff;
int bitl=blue & 0x1;
s+=Integer.toString(bitl);
if(count%8==0)
{ 
int temp=Integer.parseInt(s,2);
//System.out.println((char) temp);
msg+=(char) temp;
s="";
}
}	

}
System.out.println(msg);
l2.setText(msg);
//p.add(l2);
//if(msg!=null)
//ta.setText(msg);


}
}
}


class fr extends Frame implements ActionListener
{
 String s;
fr()
{
Button b1=new Button("ENCODE");
Button b2=new Button("DECODE");
b1.setBounds(100,100,70,50);
b2.setBounds(200,100,70,50);
add(b1);
add(b2);
b1.addActionListener(this);
b2.addActionListener(this);
setSize(500,500);
setLayout(null);
setVisible(true);
}


public void actionPerformed(ActionEvent e)
{
encode en;
decode de;
if(e.getActionCommand()=="ENCODE")
{
 en=new encode();
 this.s=en.s;
}
else
de=new decode();
setVisible(false);
dispose();	
}


public static void main(String args[])
{
fr f= new fr();
}

}

