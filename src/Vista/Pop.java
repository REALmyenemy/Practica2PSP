package Vista;

import Modelo.*;
import java.awt.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class Pop extends javax.swing.JDialog {

	public Pop(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
		this.setSize(140,200);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
	}
	
	
	private Image fondo;
	private boolean hasFinished=false;
	private boolean limiteBajo=false;

	
	
	
	
	
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
		/* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Pop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Pop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Pop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Pop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the dialog */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				Pop dialog = new Pop(new javax.swing.JFrame(), true);
				dialog.addWindowListener(new java.awt.event.WindowAdapter() {
					@Override
					public void windowClosing(java.awt.event.WindowEvent e) {
						System.exit(0);
					}
				});
				dialog.setVisible(true);
			}
		});
	}

	
	private Elemento ganador[]=new Elemento[3];
	private int i=0;
	
	public synchronized void insertar_ganador(Elemento nuevo)
	{
		if ((limiteBajo && ganador[1]==null)||(ganador[2]==null&&!limiteBajo))
		{
			ganador[i++]=nuevo;
			if ((limiteBajo && i==2) || (i==3&&!limiteBajo))
			{
				hasFinished=true;
			}
		}
		
		
		
	}

	@Override
	public void paint(Graphics g) //Da problemas el doble buffer si no hay actualización
	{
		
		try {
			fondo= ImageIO.read(new File("podio.png"));
		} catch (IOException ex) {		}
		
		g.drawImage(fondo, 0, 0, null);
		
		g.drawImage(ganador[0].getImag(), 60, 64, null);
		g.drawString(ganador[0].getName(),52, 144);
		
		g.drawImage(ganador[1].getImag(), 29, 72, null);
		g.drawString(ganador[1].getName(),22, 139);
		
		if (!limiteBajo)
		{
			g.drawImage(ganador[2].getImag(), 86, 79, null);
			g.drawString(ganador[2].getName(), 82, 149);
		}
		
		
	}

	@Override
	public void update(Graphics g)
	{
		
		paint(g);
		
	}
	
	
	
	public void ganadores()
	{
		
		this.repaint();
		
	}

	public boolean isHasFinished() {
		return hasFinished;
	}

	public void setLimiteBajo(boolean limiteBajo) {
		this.limiteBajo = limiteBajo;
	}
	
	
	
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
