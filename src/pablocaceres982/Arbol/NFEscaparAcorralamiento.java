package pablocaceres982.Arbol;

import java.util.ArrayList;

import core.game.Observation;
import core.game.StateObservation;
import ontology.Types;
import ontology.Types.ACTIONS;
import pablocaceres982.Coordenadas;
import pablocaceres982.Mapa;
import tools.Vector2d;

public class NFEscaparAcorralamiento extends NodoFuncion{

public Types.ACTIONS MovimientoSeleccionado(StateObservation Ob,Coordenadas Avatar,Mapa map,SuperArbol arbol){
    
        int tamañoBloques=Ob.getBlockSize();
		Vector2d v=Ob.getAvatarPosition();
		
        ArrayList<Observation>[] a =Ob.getNPCPositions(v);//Devuelve los NPC segun su orden de cercania
        
        Coordenadas pos=new Coordenadas((int)a[0].get(0).position.x/tamañoBloques,(int)a[0].get(0).position.y/tamañoBloques);
  
        
		for (int i = 0; i < a[0].size(); i++) {
			
		
        if(pos.getDist(Avatar)==1) {
        	Ob.advance(Avatar.getMovimiento(pos));
     		Mapa futuro=new Mapa(Ob);
        	if(futuro.getCoordenada(pos.getX(), pos.getY()).getTipo()!=-5) {
        	return Avatar.getMovimiento(pos);
        	}
        }
		}
        	//si no esta justamente al lado el pajaro mas cercano me quedo quieto
        	return ACTIONS.ACTION_NIL;
        }

	

}
