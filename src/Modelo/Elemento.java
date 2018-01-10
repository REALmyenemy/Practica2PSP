package Modelo;

import Vista.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;

public class Elemento extends Thread {

	public BufferedImage getImag() {
		return imag;
	}

	public boolean isEnCarrera() {
		return enCarrera;
	}

	public void setEnCarrera(boolean enCarrera) {
		this.enCarrera = enCarrera;
	}

	public boolean isEnMovimiento() {
		return enMovimiento;
	}

	public void setEnMovimiento(boolean enMovimiento) {
		this.enMovimiento = enMovimiento;
	}

	public boolean isFinalizado() {
		return finalizado;
	}

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}

	public int getPosX() {
		return posX;
	}

	private void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}


	private static int velocidad_base = 3;
	private boolean enCarrera = false;
	private boolean enMovimiento = false;
	private boolean finalizado = false;
	private int posX = 40;
	private int posY;
	private int curImage;
	private Pop p;
	private int rectX;
	private int rectY;

	private BufferedImage imag;
	private BufferedImage imagenes[];

	public Elemento()
	{
		super();
	}

	public Elemento(int num, int Y) {
		this();
		this.setName("Can"+(num+1));
		this.posY = Y;
		try {
			imagenes=new BufferedImage[6];
			for (int i=0;i<imagenes.length;i++)
			{
				imagenes[i]=ImageIO.read(new File("c"+num+"/c"+(i+1)+".png"));
				
			}
			imag = imagenes[0];
		} catch (IOException e) {
		}

	}

	@Override
	public void run() {
		enCarrera = enMovimiento = true;
		while (isEnCarrera()) {
			try {
				Thread.sleep(70);
				avanzar();
			}
			catch (InterruptedException ex){	}
			
		}
	}
	
	public void actuar()
	{
		if (!isFinalizado() && isEnCarrera())
			if(new Random().nextInt(2)==0 && isEnMovimiento())
			{
				enMovimiento=false;
				try {
					Thread.sleep(100);
				} catch (InterruptedException ex) {			}
				
			}
			else
			{
				if (!enMovimiento)
					enMovimiento=true;
				else
					posX+=50;
			}
	}

	private void avanzar() {
		if (isEnMovimiento() && !isFinalizado()) {
			setPosX(getPosX() + new Random().nextInt(9) + velocidad_base);
			curImage++;
			if (curImage>2)
				curImage=0;
			if (getPosX()>=883)
			{
				setFinalizado(true);
				
				p.insertar_ganador(this);
			}
		}
		else
		{
			curImage++;
			if (curImage>5)
				curImage=3;
		}
		imag=imagenes[curImage];
		
	}

	/**
	 * @param p the p to set
	 */
	public void setP(Pop p) {
		this.p = p;
	}

	/**
	 * @return the rectX
	 */
	public int getRectX() {
		rectX=imag.getWidth();
				
		return rectX;
	}

	/**
	 * @return the rectY
	 */
	public int getRectY() {
		rectY=imag.getHeight();
		return rectY;
	}

}
