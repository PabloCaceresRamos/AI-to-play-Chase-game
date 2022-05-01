package pablocaceres982.Arbol;

import core.game.StateObservation;
import ontology.Types;
import ontology.Types.ACTIONS;
import pablocaceres982.Coordenadas;
import pablocaceres982.Mapa;

public class Arbol extends SuperArbol {

	private NFPerseguir nFP;
	/**/private NFPerseguir2 nFP2;
	private NFPerseguirSeguro nFPs;
	private NFEscaparAcorralamiento nFEa;
	private NFPerseguirInfiltrado nFPi;
	private NFQuieto nFQ;
	private NBAcechado nBA;
	private NBAcorralado nBAc;
	private NBInfiltrado nBI;
	private NBDefenderHuevo nBD;
	private NBSigoDefendiendo nBSD;
	
	
	
	
	public Arbol() {
		super();
		nFP=new NFPerseguir();
		/**/nFP2=new NFPerseguir2();
		nFPs=new NFPerseguirSeguro();
		nFQ=new NFQuieto();
		nFPi=new NFPerseguirInfiltrado();
		nFEa=new NFEscaparAcorralamiento();
		nBD=new NBDefenderHuevo();
		nBA=new NBAcechado();
		nBAc=new NBAcorralado();
		nBI=new NBInfiltrado();
		nBSD=new NBSigoDefendiendo();
		
		
		
		
		Raiz=nBA;//¿Me siguen?
		
		nBA.noCumple=nBD;//¿Defender?
		nBA.cumple=nBAc;//¿Me Acorralan?
		
		
		nBAc.noCumple=nFPs;//Perseguir con cuidado
		nBAc.cumple=nFEa;//Escapar
		
		
		nBI.cumple=nFP;
		nBI.noCumple=nFPi;
		
		nBD.cumple=nBSD;//¿guardo huevo?
		nBD.noCumple=nBI;//¿Pajaro infiltrado?
		nBSD.cumple=nFQ;
		nBSD.noCumple=nFP;
		
		zona=0;
		
	}
	
	public Types.ACTIONS Accion(StateObservation Ob,Coordenadas Avatar,Mapa map){
		//Esta funcion solo la va a hacer el nodo Raiz
		
		return this.Accion(Ob, Avatar, map, this.Raiz.decision(Ob,map, Avatar,this));
	}
	
	public Types.ACTIONS Accion(StateObservation Ob,Coordenadas Avatar,Mapa map,Nodo nodoAct){
		
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
	
	
}
