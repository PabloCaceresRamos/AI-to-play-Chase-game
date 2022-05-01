package pablocaceres982.Arbol;

import java.util.ArrayList;

import com.sun.prism.PhongMaterial.MapType;

import core.game.Observation;
import core.game.StateObservation;
import ontology.Types;
import ontology.Types.ACTIONS;
import pablocaceres982.Coordenadas;
import pablocaceres982.Mapa;
import pablocaceres982.AEstrella;
import tools.Vector2d;

public class NBSigoDefendiendo extends NodoBoolean {
private int veces;
	public NBSigoDefendiendo() {
		super();
		veces=0;
	}
	/**
	 * Si estamos encima de un huevo y hay algun pajaro al lado nos quedaremos quieto para defenderlo y el 
	 * pajaro se vaya. si en dos turnos el pajaro no se va, le atacaremos.
	 */
	public Nodo decision(StateObservation Ob,Mapa map,Coordenadas Avatar,SuperArbol arbol){
		
		if(veces<2) {
			veces++;
		System.out.println("Quieto");
		return cumple;
		}
		else {
			veces=0;
			int x = Avatar.getX();
			int y = Avatar.getY();
			int tamañoBloques = Ob.getBlockSize();
			Vector2d v = Ob.getAvatarPosition();

			ArrayList<Observation>[] a = Ob.getNPCPositions(v);
			for (int i = 0; i < a[a.length - 1].size(); i++) {
			
					Coordenadas pajaro = new Coordenadas((int) a[a.length - 1].get(i).position.x / tamañoBloques,
							(int) a[a.length - 1].get(i).position.y / tamañoBloques);
					
					if(Avatar.getDist(pajaro)==1) {
						System.out.println("Ataco");
						return noCumple;
					}
				}
		}
		System.out.println("Quieto");
		return cumple;
	
	}
}
