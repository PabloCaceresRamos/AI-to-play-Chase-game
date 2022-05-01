package pablocaceres982.Automata;

import java.util.ArrayList;

import core.game.Observation;
import core.game.StateObservation;
import ontology.Types;
import ontology.Types.ACTIONS;
import pablocaceres982.Coordenadas;
import pablocaceres982.Mapa;
import pablocaceres982.Arbol.*;
import tools.Vector2d;

public class Escapar extends SuperArbol {

	private NFPerseguirSeguro nFPs;
	private NFEscaparAcorralamiento nFEa;
	
	private NBAcorralado nBAc;
	
	
	
	
	
	public Escapar() {
		super();
	
		nFPs=new NFPerseguirSeguro();
		
		nFEa=new NFEscaparAcorralamiento();
	
		nBAc=new NBAcorralado();
		
		Raiz=nBAc;
		
		nBAc.noCumple=nFPs;
		nBAc.cumple=nFEa;
		
	}
	
	public Types.ACTIONS Accion(StateObservation Ob,Coordenadas Avatar,Mapa map){
		//Esta funcion solo la va a hacer el nodo Raiz
		
		return this.Accion(Ob, Avatar, map, this.Raiz.decision(Ob,map, Avatar,this));
	}
	
	public Types.ACTIONS Accion(StateObservation Ob,Coordenadas Avatar,Mapa map,Nodo nodoAct){
		System.out.println("Escapa");
		if(nodoAct instanceof NodoFuncion)
			return ((NodoFuncion)nodoAct).MovimientoSeleccionado(Ob,Avatar,map,this);
		else if(nodoAct==null) {
			System.out.println("Nodo null");
			return ACTIONS.ACTION_NIL;
		}
		else {
			Nodo nodosig= ((NodoBoolean)nodoAct).decision(Ob,map, Avatar,this) ;
			return Accion(Ob,Avatar,map,nodosig);
		}
	}
	
	public int EstadoSiguiente(StateObservation Ob,Coordenadas Avatar,Mapa map) {
		
		int tamañoBloques=Ob.getBlockSize();
		Vector2d v=Ob.getAvatarPosition();
		
        ArrayList<Observation>[] a =Ob.getNPCPositions(v);//Devuelve los NPC segun su orden de cercania
        if(a.length>=2) {
        	Coordenadas Acechador=new Coordenadas((int)a[0].get(0).position.x/tamañoBloques,(int)a[0].get(0).position.y/tamañoBloques);
        	if(Acechador.getDist(Avatar)<=2){
        		return 0;//nos quedamos escapando
        	}
        }
		return 1;//vamos a Cazar
		 /*Hemos mirado, si estamos en peligro. Si no lo estamos pasamos a Cazar. Si lo estamos, pasamos a defender*/
	}
	
	
}
