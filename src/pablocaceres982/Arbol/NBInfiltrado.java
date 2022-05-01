package pablocaceres982.Arbol;

import java.util.ArrayList;

import core.game.Observation;
import core.game.StateObservation;
import pablocaceres982.Coordenadas;
import pablocaceres982.Mapa;
import tools.Vector2d;

public class NBInfiltrado extends NodoBoolean {

	/*
	 *Vamos a ir limpiando el mapa por zonas, por lo que si estamos en una zona  y en alguna anterior llega 
	 *un pajaro diremos que se ha infirtrado y le atacaremos para que no coja los huevos que hay cerca
	 */
	public Nodo decision(StateObservation Ob,Mapa map,Coordenadas Avatar,SuperArbol arbol) {
		
		map.ContarPajarosZona(Ob);
		int sZona=map.siguienteZonaCaza();
		if(sZona!=arbol.getZona())
			return noCumple;
		else
			return cumple;
	}
	
	
	
}
