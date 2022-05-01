package pablocaceres982.Arbol;

import java.util.ArrayList;


import core.game.Observation;
import core.game.StateObservation;
import ontology.Types;
import pablocaceres982.Coordenadas;
import pablocaceres982.Mapa;
import sun.security.util.PendingException;
import pablocaceres982.AEstrella;
import tools.Vector2d;


public class NFPerseguirSeguro extends NodoFuncion {
/*
 *Este nodo intenta que siempore vayamos por el pajaro que menos probabilidades tiene de dejarnos acorralado. Tambien tiene un sistema que cuando un aguila
 *hace que nos bloqueemos en un espacio del mapa y no nos deja avanzar vamos hacia la porpia aguila negra, para solucionar el bug 
 */
	
	private  Types.ACTIONS ultimoMovimiento;
	private  Types.ACTIONS penUltimoMovimiento;//lo usaremos para que el muñeco no se quede atrapado en algunas ocasiones
	private int repeticionesMovimientos;
	
	public NFPerseguirSeguro() {
		super();
		ultimoMovimiento=null;
		penUltimoMovimiento=null;
		repeticionesMovimientos=0;
	}
	public Types.ACTIONS MovimientoSeleccionado(StateObservation Ob,Coordenadas Avatar,Mapa map,SuperArbol arbol){
		
		
		Coordenadas Objetivo=map.pajaroACazarSeguro(Ob);
        AEstrella aE=new AEstrella();
        
        Types.ACTIONS movimiento=aE.selectActions(Ob, Objetivo, Avatar, map);
        if(movimiento==penUltimoMovimiento) repeticionesMovimientos++;
        else repeticionesMovimientos=0;
        
        penUltimoMovimiento=ultimoMovimiento;
        
        if(repeticionesMovimientos>10) {//Si me quedo bloqueado intento ir hacia el pajaro negro que me bloquea
        	Vector2d v=Ob.getAvatarPosition();
        	ArrayList<Observation>[] a =Ob.getNPCPositions(v);
        	int tamañoBloques=Ob.getBlockSize();
        	Coordenadas pajaro=new Coordenadas((int)a[0].get(0).position.x/tamañoBloques,(int)a[0].get(0).position.y/tamañoBloques);
        	movimiento=aE.selectActions(Ob, pajaro, Avatar, map);
        }
        ultimoMovimiento=movimiento;
        return movimiento;
        
	}
}
