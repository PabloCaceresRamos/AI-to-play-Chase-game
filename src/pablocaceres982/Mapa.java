package pablocaceres982;

import java.util.ArrayList;

import core.game.Observation;
import core.game.StateObservation;
import tools.Vector2d;

public class Mapa {
	private Coordenadas[][] mapa;
	private int  xmedio;//lo utilizaremos para dividir el mapa en zonas
	private int ymedio;
	private int zonasB[];//pajaros blanco por zonas
	private int zonasN[];//pajaros negros por zona
	
	public Mapa(StateObservation Ob) {
		
		
		xmedio=11;
		ymedio=5;
		zonasB=null;
		
		ArrayList<Observation>[][] M = Ob.getObservationGrid();
		mapa = new Coordenadas[M.length][M[1].length];

		for (int i = 0; i < M.length; i++) {
			for (int j = 0; j < M[1].length; j++) {
				 if (M[i][j].size() == 0) {

					mapa[i][j] = new Coordenadas(i, j, 0,0);
				} else {
					if(M[i][j].get(0).itype==0)
						mapa[i][j] = new Coordenadas(i, j,-2,M[i][j].size()); 
						//Los arboles tiene tipo 0, pero como para nosotros 0 es libre le pondremos el 2
					else
					mapa[i][j] = new Coordenadas(i, j, 0 - M[i][j].get(0).itype,M[i][j].size());// esto es para que salga 0 los
																					// espacios vacios y -tipo los
																					// objetos
				}
			}

		}
	}
	
	public void verMapa() {

		for (int j= 0; j < mapa[1].length; j++) {
			System.out.print("\n");
			for (int i = 0; i < mapa.length; i++) {
				System.out.print((-1)*mapa[i][j].getTipo()+"  ");
			}

		}
			System.out.print("\n Fin \n\n");
	}
	
	public void CambiarMapa(StateObservation Ob) {
		
		ArrayList<Observation>[][] M = Ob.getObservationGrid();
		mapa = new Coordenadas[M.length][M[1].length];

		for (int i = 0; i < M.length; i++) {
			for (int j = 0; j < M[1].length; j++) {
				 if (M[i][j].size() == 0) {

					mapa[i][j] = new Coordenadas(i, j, 0,0);
				} else {
					if(M[i][j].get(0).itype==0)
						mapa[i][j] = new Coordenadas(i, j,-2,M[i][j].size()); 
						//Los arboles tiene tipo 0, pero como para nosotros 0 es libre le pondremos el 2
					else
					mapa[i][j] = new Coordenadas(i, j, 0 - M[i][j].get(0).itype,M[i][j].size());// esto es para que salga 0 los
																					// espacios vacios y -tipo los
																					// objetos
				}
			}

		}
	}

	public int getNumFilas() {
		return mapa[1].length;
	}

	public int getNumColumnas() {
		return mapa.length;
	}
	public Coordenadas getCoordenada(int x,int y) {
		return mapa[x][y];
	}
	public Coordenadas[][] getMapa() {
		return mapa;
	}
	
	public void ContarPajarosZona(StateObservation Ob) {
		/*
		 * Contamos cuantos pajaros hay en cada zona, mirando la posicon de cada pajaro
		 */
		int cont[]= {0,0,0,0};
		int tamañoBloques=Ob.getBlockSize();
		Vector2d v=Ob.getAvatarPosition();
		
        ArrayList<Observation>[] a =Ob.getNPCPositions(v);
        
		for (int i = 0; i < a[a.length-1].size(); i++) {
			Coordenadas pajaro=new Coordenadas((int)a[a.length-1].get(i).position.x/tamañoBloques,(int)a[a.length-1].get(i).position.y/tamañoBloques);
			int x=pajaro.getX();
			int y=pajaro.getY();
			
			if(x<=this.xmedio) {
				if(y<=this.ymedio)
					cont[0]++;
				else
					cont[1]++;
			}
			else {
				if(y<=this.ymedio)
					cont[2]++;
				else
					cont[3]++;
			}
		}
		zonasB=new int[4];
		for (int i = 0; i < 4; i++) {
			zonasB[i]=cont[i];
		}
	}
	
	public void ContarPajarosNegroZona(StateObservation Ob) {
		/*
		 * Contamos cuantos pajaros hay en cada zona, mirando la posicon de cada pajaro
		 */
		int cont[]= {0,0,0,0};
		int tamañoBloques=Ob.getBlockSize();
		Vector2d v=Ob.getAvatarPosition();
		
        ArrayList<Observation>[] a =Ob.getNPCPositions(v);
		if (a.length > 1) {
			for (int i = 0; i < a[a.length - 1].size(); i++) {
				Coordenadas pajaro = new Coordenadas((int) a[a.length - 1].get(i).position.x / tamañoBloques,
						(int) a[a.length - 1].get(i).position.y / tamañoBloques);
				int x = pajaro.getX();
				int y = pajaro.getY();

				if (x <= this.xmedio) {
					if (y <= this.ymedio)
						cont[0]++;
					else
						cont[1]++;
				} else {
					if (y <= this.ymedio)
						cont[2]++;
					else
						cont[3]++;
				}
			}
		}
		zonasN=cont;
	}
	
	public int zonaMenosPajaros() {
		if(zonasB==null)
			System.out.println("Error, Cuenta los pajaros por zona antes");
			int min=99;
			int minNum=99;
		for (int j = 0; j < zonasB.length; j++) {
			System.out.println("zona: "+j+"  "+zonasB[j]);
			if(zonasB[j]>0 && zonasB[j]<minNum) {
				min=j;
				minNum=zonasB[j];
			}
		}
		if(zonasB[min]==0 || min>3)
			System.out.println("Error menos pajaros");
		return min;
		
	}
	
	public int zonaMenosPajarosNegros() {
		if(zonasN==null)
			System.out.println("Error, Cuenta los pajaros Negros por zona antes");
			int min=99;
			int minNum=99;
		for (int j = 0; j < zonasN.length; j++) {
			System.out.println("zona: "+j+"  "+zonasN[j]);
			if(zonasN[j]>0 && zonasN[j]<minNum) {
				min=j;
				minNum=zonasN[j];
			}
		}
		if(zonasN[min]==0 || min>3)
			System.out.println("Error menos pajaros");
		return min;
		
	}
	
	public int zonaMasPajaros() {
		if(zonasB==null)
			System.out.println("Error, Cuenta los pajaros por zona antes");
			int max=99;
			int maxNum=0;
		for (int j = 0; j < zonasB.length; j++) {
			if(zonasB[j]>0 && zonasB[j]>maxNum) {
				max=j;
				maxNum=zonasB[j];
			}
		}
		if(zonasB[max]==0 || max>3)
			System.out.println("Error menos pajaros");
		return max;
		
	}
	public int siguienteZonaCaza() {
		if(zonasB==null)
			System.out.println("Error, Cuenta los pajaros por zona antes");
		int i=0;
		while( i<4 && zonasB[i]==0) {
			i++;
		}
		return i;
		}
	
	public boolean zonaLimpia(int z) {
		return zonasB[z]==0;
	}
	public Coordenadas pajaroACazar(int zone,StateObservation Ob) {
		//nos mandan una zona y buscamos un pajaro de esa zona
		
		int tamañoBloques=Ob.getBlockSize();
		Vector2d v=Ob.getAvatarPosition();
		
        ArrayList<Observation>[] a =Ob.getNPCPositions(v);
		
		for (int i = 0; i < a[a.length-1].size(); i++) {
			Coordenadas pajaro=new Coordenadas((int)a[a.length-1].get(i).position.x/tamañoBloques,(int)a[a.length-1].get(i).position.y/tamañoBloques);
			int x=pajaro.getX();
			int y=pajaro.getY();
			
			if(x<=this.xmedio &&y<=this.ymedio && zone==0)
					return pajaro;
			if(x>this.xmedio &&y<=this.ymedio && zone==2)
					return pajaro;
			if(x<=this.xmedio &&y>this.ymedio && zone==1)
					return pajaro;
			if(x>this.xmedio &&y>this.ymedio && zone==3)
					return pajaro;
					
				
			}
		return null;
		}
	
	
	public Coordenadas pajaroACazarSeguro(StateObservation Ob) {
		//vamos a contar cuantos obstaculos tiene alrededor y devolvemos el que tenga menos o el primero que no tenga ninguno
		
		int tamañoBloques=Ob.getBlockSize();
		Vector2d v=Ob.getAvatarPosition();
		
        ArrayList<Observation>[] a =Ob.getNPCPositions(v);
        Coordenadas noSeguro=null;
        int menorObstaculo=6;
		
		for (int i = 0; i < a[a.length-1].size(); i++) {
			Coordenadas pajaro=new Coordenadas((int)a[a.length-1].get(i).position.x/tamañoBloques,(int)a[a.length-1].get(i).position.y/tamañoBloques);
			int x=pajaro.getX();
			int y=pajaro.getY();
			int obstaculosAlrededor=0;
			
					if(this.getCoordenada(x-1, y).getTipo()==-2 ||this.getCoordenada(x-1, y).getTipo()==-5)
							obstaculosAlrededor++;
					if (this.getCoordenada(x+1, y).getTipo()==-2 ||this.getCoordenada(x+1, y).getTipo()==-5) 
							obstaculosAlrededor++;
					if(this.getCoordenada(x, y-1).getTipo()==-2 ||this.getCoordenada(x, y-1).getTipo()==-5)
							obstaculosAlrededor++;
					if (this.getCoordenada(x, y+1).getTipo()==-2 ||this.getCoordenada(x, y+1).getTipo()==-5)
							obstaculosAlrededor++;
					
				if(noSeguro==null || obstaculosAlrededor<menorObstaculo) {
					noSeguro=pajaro;
					menorObstaculo=obstaculosAlrededor;
				}
				
			if(obstaculosAlrededor<2) return pajaro;
				
			}
		
		return noSeguro;
		}
	
	
	public int zonaActual(Coordenadas Avatar) {
		int x=Avatar.getX();
		int y=Avatar.getY();
		
		if(x<=this.xmedio &&y<=this.ymedio )
				return 0;
		if(x>this.xmedio &&y<=this.ymedio )
				return 2;
		if(x<=this.xmedio &&y>this.ymedio )
				return 1;
	
		
		
				return 3;
	}
	
	
	}






