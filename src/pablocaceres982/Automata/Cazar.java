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

public class Cazar extends SuperArbol {

	private NFPerseguir nFP;

	private NFPerseguirInfiltrado nFPi;

	private NBInfiltrado nBI;
	
	
	
	public Cazar() {
		super();
		nFP=new NFPerseguir();
		
		nFPi=new NFPerseguirInfiltrado();
		
		nBI=new NBInfiltrado();
		
		
		
		Raiz=nBI;
		
		
		
		
		nBI.cumple=nFP;
		nBI.noCumple=nFPi;
		
		
		
		zona=0;
		
	}
	
	public Types.ACTIONS Accion(StateObservation Ob,Coordenadas Avatar,Mapa map){
		//Esta funcion solo la va a hacer el nodo Raiz
		
		return this.Accion(Ob, Avatar, map, this.Raiz.decision(Ob,map, Avatar,this));
	}
	
	public Types.ACTIONS Accion(StateObservation Ob,Coordenadas Avatar,Mapa map,Nodo nodoAct){
		System.out.println("Caza");
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
        		return 1; //pasamos a escapar
        	}
        }
        /*Hasta ahora hemos mirado si nos estan siguiendo, para saber si tenemos que pasar al estado de Escapar*/
        
        int x = Avatar.getX();
		int y = Avatar.getY();
		if (map.getCoordenada(x, y).getNumElem() > 1) {
		/*Ahora vamos a mirar si pasamos al estado de Defensa, o nos quedamos en el estado actual*/
			
		for (int i = 0; i < a[a.length - 1].size(); i++) {
		
				Coordenadas pajaro = new Coordenadas((int) a[a.length - 1].get(i).position.x / tamañoBloques,
						(int) a[a.length - 1].get(i).position.y / tamañoBloques);
				
				if(Avatar.getDist(pajaro)==1) {
					return 2;//pasamos a defender
				}
			}
		}
		return 0; //nosquedamos cazando
       
	}
	
	
}
