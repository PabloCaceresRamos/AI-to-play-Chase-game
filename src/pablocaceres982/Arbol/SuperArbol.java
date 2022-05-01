package pablocaceres982.Arbol;

import core.game.StateObservation;
import ontology.Types;
import ontology.Types.ACTIONS;
import pablocaceres982.Coordenadas;
import pablocaceres982.Mapa;

public class SuperArbol {
/*
 * Este va a ser el padre de los tipos arbol que vamos a tener.
 */
	
public NodoBoolean Raiz;
	
public int zona;//El mapa se divide en 4 zonas, guarda a la zona de caza actual

public SuperArbol() {
	zona=0;
}

public Types.ACTIONS Accion(StateObservation Ob,Coordenadas Avatar,Mapa map){
	//Esta funcion solo la va a hacer el nodo Raiz
	System.out.println("Metodo de SuperArbol");
	return null;
}

public Types.ACTIONS Accion(StateObservation Ob,Coordenadas Avatar,Mapa map,Nodo nodoAct){
	
	System.out.println("Metodo de SuperArbol");
	return null;
}

public int EstadoSiguiente(StateObservation Ob,Coordenadas Avatar,Mapa map) {
	System.out.println("Metodo de SuperArbol");
	return -1;
}

public void setZona(int z) {
	this.zona=z;
}
public int getZona() {
	return zona;
}
}
