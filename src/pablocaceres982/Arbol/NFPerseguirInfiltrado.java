package pablocaceres982.Arbol;

import java.util.ArrayList;

import core.game.Observation;
import core.game.StateObservation;
import ontology.Types;
import pablocaceres982.Coordenadas;
import pablocaceres982.Mapa;
import pablocaceres982.AEstrella;
import tools.Vector2d;

public class NFPerseguirInfiltrado extends NodoFuncion {
/*
 *Este es un Nodo del arbol de decision que devuelve una accion. Tiene una funcion que es la encargada de 
 *buscar el pajaro blanco infiltrado mas cercano y llama al A* para que encuentre el camino mas
 * corto para llegar. 
 */
	public Types.ACTIONS MovimientoSeleccionado(StateObservation Ob,Coordenadas Avatar,Mapa map,SuperArbol arbol){
		
		arbol.setZona(map.siguienteZonaCaza());
		
		Coordenadas Objetivo=map.pajaroACazar(arbol.getZona(), Ob);
        AEstrella aE=new AEstrella();
        
		return aE.selectActions(Ob, Objetivo, Avatar, map);
	}
}
