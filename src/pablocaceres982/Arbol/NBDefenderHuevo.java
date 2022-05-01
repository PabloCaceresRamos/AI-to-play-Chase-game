package pablocaceres982.Arbol;

import java.util.ArrayList;

import core.game.Observation;
import core.game.StateObservation;
import pablocaceres982.Coordenadas;
import pablocaceres982.Mapa;
import tools.Vector2d;

public class NBDefenderHuevo extends NodoBoolean {

	/*
	 *Si en la posicion que estamos hay mas de un elemento, es que estamos encima de huevos, si
	 *un pajaro esta cerca puede cogerlos, asi que llamo a NFQuieto.
	 */
	public Nodo decision(StateObservation Ob, Mapa map, Coordenadas Avatar,SuperArbol arbol) {

		int x = Avatar.getX();
		int y = Avatar.getY();
		if (map.getCoordenada(x, y).getNumElem() > 1) {
		int tamañoBloques = Ob.getBlockSize();
		Vector2d v = Ob.getAvatarPosition();

		ArrayList<Observation>[] a = Ob.getNPCPositions(v);
		for (int i = 0; i < a[a.length - 1].size(); i++) {
		
				Coordenadas pajaro = new Coordenadas((int) a[a.length - 1].get(i).position.x / tamañoBloques,
						(int) a[a.length - 1].get(i).position.y / tamañoBloques);
				
				if(Avatar.getDist(pajaro)==1) {
					return cumple;
				}
			}
		}
		return noCumple;
	}

}
