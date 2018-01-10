package Vista;

import Modelo.Elemento;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.JFrame;

public class Principal extends JFrame implements Runnable, MouseListener
{

	private Thread principal;
	
	public int getTamX() {
		return tamX;
	}

	public int getTamY() {
		return tamY;
	}

	
	private static Elemento elementos[];
	private int tamX;
	private int tamY;
	private static BufferedImage fondo; //final me daba problemas
	private static BufferedImage linea;
	private Dimension dim;
	private Image offscreen;
	private Graphics bufferGraphics;
	private Pop pod;
	private static Principal p;

	public Principal()
	{
		initComponents();
		
	}

	public Principal(int pj) {
		initComponents();
		tamX = 1000;
		tamY = 680;
		NumJ numJ = new NumJ(this, true);
		numJ.setAlwaysOnTop(true);
		numJ.setVisible(true);
		pod=new Pop(this,false);
		
		try //Otra comprobación de errores, por si no consigo que la ventana sea incerrable
		{
			pj = NumJ.getNumJ();

			if (pj < 2 || pj > 9) {
				pj = 2;
			}
		} catch (NumberFormatException e) {
			pj = 9;
		}
		
		if (pj==2)
			pod.setLimiteBajo(true);
			
		elementos = new Elemento[pj];
		
		
		try {
			fondo= ImageIO.read(new File("Fondo.png"));
			linea= ImageIO.read(new File("bar.png"));
		} catch (IOException ex) {		}
		addMouseListener(this);
		principal = new Thread(this);
		principal.start();
		generar_elementos();

	}

	private void generar_elementos() {
		
		int distancia = (int) (getTamY() / (elementos.length + 2));

		for (int i = 0; i < elementos.length; i++)
		{
			//System.out.println((int)(distancia*(i+1)+getTamY()/8));
			elementos[i] = new Elemento(i, (int) (distancia * (i + 1) + getTamY() / 15));
			elementos[i].setP(pod);
			elementos[i].start();
		}

	}
	
	@Override
	public void update(Graphics g)
	{
		paint(g);
		

	}

	@Override
	public void paint(Graphics g)
	{
		bufferGraphics.clearRect(0,0,dim.width,dim.width);
		bufferGraphics.setColor(Color.white);
		bufferGraphics.drawImage(fondo,0,0,null);
		for (int i = 0; i < elementos.length; i++)
		{
			bufferGraphics.drawImage(linea,14,elementos[i].getPosY()+5,null);
			bufferGraphics.drawImage(elementos[i].getImag(), elementos[i].getPosX(), elementos[i].getPosY(), null);
		}
		g.drawImage(offscreen,0,0,null);
	}

	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 680, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

	public static void main(String args[]) {
		

		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		p = new Principal(NumJ.getNumJ());
		p.setSize(new Dimension(p.getTamX(), p.getTamY()));
		p.setResizable(false);
		p.setVisible(true);
		p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	@Override
	public void run()
	{
		dim = new Dimension(tamX,tamY);
		offscreen = createImage(dim.width,dim.height);
		bufferGraphics = offscreen.getGraphics();
		
		while (true)
		{
			try {
				Thread.sleep(60);
				repaint();
				
				
				
			} catch (InterruptedException e) {}
			if (pod.isHasFinished())
			{
				break;
			}
		}
		for (int j=0;j<20;j++)
		{
			try {
				Thread.sleep(30);
				repaint();
				Thread.sleep(30);
			} catch (InterruptedException e) {}
		}
		
		
		
	
		
		pod.setVisible(true);
//		principal.stop();
		
        pack();
		
		pod.ganadores();
		
	}

	

	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		//Usaré este para aclararme mejor
		int posX=e.getX();
		int posY=e.getY();
		int i;
		Elemento aux;
		for (i=0;i<elementos.length;i++)
		{
			aux=elementos[i];
			if ((posY>=aux.getPosY()&&posY<=(aux.getPosY()+aux.getRectY()))&&(posX>=aux.getPosX()&&posX<=(aux.getPosX()+aux.getRectX())))
				break;
		}
		if (i<elementos.length) 
			elementos[i].actuar();
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
