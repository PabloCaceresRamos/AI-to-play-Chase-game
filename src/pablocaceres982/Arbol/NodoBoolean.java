package pablocaceres982.Arbol;

import core.game.StateObservation;
import pablocaceres982.Coordenadas;
import pablocaceres982.Mapa;

public class NodoBoolean extends Nodo {

	
	public Nodo cumple;
	public Nodo noCumple;
	
	NodoBoolean(){
		cumple=null;
		noCumple=null;
	}
	
	public void setNodoCumple(Nodo c) {
		this.cumple=c;
	}
	public void setNodoNoCumple(Nodo c) {
		this.noCumple=c;
	}
	
	public Nodo getNodoCumple() {
		return this.cumple;
	}
	public Nodo getNodoNoCumple() {
		return this.noCumple;
	}
	
	public Nodo decision(StateObservation Ob,Mapa map,Coordenadas Avatar,SuperArbol a) {
		System.out.println("padre");
		return this.noCumple;
	}
	
	public Nodo decision() {
		return this.noCumple;
	}
	
	
}
