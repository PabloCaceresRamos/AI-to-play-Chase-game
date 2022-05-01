package pablocaceres982.Arbol;

import java.util.ArrayList;
import core.game.Observation;
import core.game.StateObservation;
import pablocaceres982.Coordenadas;
import pablocaceres982.Mapa;
import tools.Vector2d;

public class NBAcechado extends NodoBoolean {

	/*
	 * Esta clase de tipo NodoBoolean tien un metodo que mira a que distancia esta el pajaro negro mas
	 * cercano. Si esta a menos de 2 casillas de distancia devolvemos el Nodo "cumple". Si no hay pajaros
	 * negros o no estan cerca, devolvemos el Nodo "No cumple"
	 */
	public Nodo decision(StateObservation Ob,Mapa map,Coordenadas Avatar,SuperArbol arbol) {
		
		int tamañoBloques=Ob.getBlockSize();
		Vector2d v=Ob.getAvatarPosition();
		
        ArrayList<Observation>[] a =Ob.getNPCPositions(v);//Devuelve los NPC segun su orden de cercania
        if(a.length>=2) {
        	Coordenadas Acechador=new Coordenadas((int)a[0].get(0).position.x/tamañoBloques,(int)a[0].get(0).position.y/tamañoBloques);
        	if(Acechador.getDist(Avatar)<=2){
        		return this.cumple;
        	}
        }
        
        return this.noCumple;
	}
	
	
}
