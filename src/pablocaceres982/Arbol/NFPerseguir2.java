package pablocaceres982.Arbol;

import java.util.ArrayList;

import core.game.Observation;
import core.game.StateObservation;
import ontology.Types;
import pablocaceres982.AEstrella;
import pablocaceres982.Coordenadas;
import pablocaceres982.Mapa;
import tools.Vector2d;

public class NFPerseguir2 extends NodoFuncion {
/*
 *Este es un Nodo del arbol de decision que devuelve una accion. Tiene una funcion que es la encargada de 
 *buscar el pajaro blanco mas cercano y llama al A* para que encuentre el camino mas corto para llegar. 
 */
	public Types.ACTIONS MovimientoSeleccionado(StateObservation Ob,Coordenadas Avatar,Mapa map,SuperArbol arbol){
		
		int tamañoBloques=Ob.getBlockSize();
		Vector2d v=Ob.getAvatarPosition();
		
        ArrayList<Observation>[] a =Ob.getNPCPositions(v);//Devuelve los NPC segun su orden de cercania
        Coordenadas Objetivo=new Coordenadas((int)a[a.length-1].get(0).position.x/tamañoBloques,(int)a[a.length-1].get(0).position.y/tamañoBloques);
        AEstrella aE=new AEstrella();
        
		return aE.selectActions(Ob, Objetivo, Avatar, map);
	}
}
