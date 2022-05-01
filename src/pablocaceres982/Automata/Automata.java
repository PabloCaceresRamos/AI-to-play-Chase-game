package pablocaceres982.Automata;

import core.game.StateObservation;
import ontology.Types;
import pablocaceres982.Coordenadas;
import pablocaceres982.Mapa;
import pablocaceres982.Arbol.SuperArbol;

public class Automata {

	private SuperArbol [] Nodos;
	private int [][] Transiciones;
	private int NodoActual;

	
	public Automata() {
		int [][]T= {{0/*C*/,1/*E*/,2/*D*/},{1/*E*/,0/*C*/,-1},{2/*D*/,1/*E*/,0/*C*/}};//Cada numero corresponde a la poscion en la tabla Nodos
		this.Transiciones=T;
		Nodos=new SuperArbol[3];
		Nodos[0]=new Cazar();
		Nodos[1]=new Escapar();
		Nodos[2]=new Defender();
		NodoActual=0;
	}
	
	public Types.ACTIONS Accion(StateObservation Ob,Coordenadas Avatar,Mapa map){
		int pos=Nodos[NodoActual].EstadoSiguiente(Ob, Avatar, map);
		NodoActual=Transiciones[NodoActual][pos];// Aqui sacamos a que nodo pasamos
		if(NodoActual==-1) System.out.println("Error********************************************");
		return Nodos[NodoActual].Accion(Ob, Avatar, map);
	}
}
