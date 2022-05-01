package pablocaceres982.Arbol;

import java.util.ArrayList;

import core.game.Observation;
import core.game.StateObservation;
import pablocaceres982.Coordenadas;
import pablocaceres982.Mapa;
import tools.Vector2d;

public class NBAcorralado extends NodoBoolean {

	/*
	 *Miraremos los alrededores del avatar a ver si hay alguna casilla libre. Si no la hay, diremos
	 *que esta acorralado
	 */
	public Nodo decision(StateObservation Ob,Mapa map,Coordenadas Avatar,SuperArbol arbol) {
		
		int x=Avatar.getX();
		int y=Avatar.getY();
		posiblesMovimientos(map,Ob);
		Coordenadas[][] m=map.getMapa();
		
		if(!(m[x-1][y].getTipo()==-6 || m[x-1][y].getTipo()==0|| m[x-1][y].getTipo()==-3)) {
			if(!(m[x+1][y].getTipo()==-6 || m[x+1][y].getTipo()==0|| m[x+1][y].getTipo()==-3)) {
				if(!(m[x][y-1].getTipo()==-6 || m[x][y-1].getTipo()==0|| m[x][y-1].getTipo()==-3)) {
					if(!(m[x][y+1].getTipo()==-6 || m[x][y+1].getTipo()==0|| m[x][y+1].getTipo()==-3)) {
						if(!(m[x][y].getTipo()==-6 || m[x][y].getTipo()==0|| m[x][y].getTipo()==-3)) {
							return cumple;
							
						}
					}
				}
			}
		}
		return noCumple;
	}
	
	/*
	 * Pondremos todos los posibles movimientos de los pajaros negros para anticiparnos a ellos
	 */
	private void posiblesMovimientos(Mapa map,StateObservation Ob) {
		
		int tamañoBloques=Ob.getBlockSize();
		Vector2d v=Ob.getAvatarPosition();
		
        ArrayList<Observation>[] a =Ob.getNPCPositions(v);
	
		for (int i = 0; i < a[0].size(); i++) {
        	Coordenadas enemigo=new Coordenadas((int)a[0].get(i).position.x/tamañoBloques,(int)a[0].get(i).position.y/tamañoBloques);
			Coordenadas aux;
			aux= map.getCoordenada(enemigo.getX(), enemigo.getY());
		
			aux.setTipoNum(-5, 1);
			aux= map.getCoordenada(enemigo.getX()-1, enemigo.getY());
			
				aux.setTipoNum(-5, 1);	
			aux= map.getCoordenada(enemigo.getX()+1, enemigo.getY());
			
				aux.setTipoNum(-5, 1);	
			aux= map.getCoordenada(enemigo.getX(), enemigo.getY()-1);
			
				aux.setTipoNum(-5, 1);	
			aux= map.getCoordenada(enemigo.getX(), enemigo.getY()+1);
		
				aux.setTipoNum(-5, 1);	
	}
}
	
	
}
