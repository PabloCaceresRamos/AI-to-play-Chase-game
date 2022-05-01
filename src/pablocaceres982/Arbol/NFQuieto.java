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

public class NFQuieto extends NodoFuncion {
private int veces;
	public NFQuieto() {
		super();
		veces=0;
	}
	/**
	 *Me quedo quieto encima del huevo
	 */
	public Types.ACTIONS MovimientoSeleccionado(StateObservation Ob,Coordenadas Avatar,Mapa map,SuperArbol arbol){
		
		return ACTIONS.ACTION_NIL;
	
	}
}
