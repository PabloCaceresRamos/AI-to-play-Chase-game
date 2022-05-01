package pablocaceres982;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import core.game.Observation;
import core.game.StateObservation;
import ontology.Types;
import ontology.Types.ACTIONS;

public class AEstrella {

	private ArrayList<Coordenadas> ListaAbierta;
	private ArrayList<Coordenadas> ListaCerrada;
	private String[] Act = { "Izquierda", "Derecha", "Arriba", "Abajo" };

	private Coordenadas[][] Mapa;

	public Types.ACTIONS selectActions(StateObservation Ob, Coordenadas meta, Coordenadas Avatar,Mapa map) {
		ListaAbierta = new ArrayList<Coordenadas>();
		ListaCerrada = new ArrayList<Coordenadas>();

		this.Mapa=map.getMapa();

		Mapa[Avatar.getX()][Avatar.getY()].SetDist(meta);
		Coordenadas NodoCerrado = Mapa[Avatar.getX()][Avatar.getY()];
		NodoCerrado.setMovimiento("Nil");
		boolean finAnalisis = false;// lo usaremos para saber cuando la lista esta vacia

		do {// Mientras que no lleguemos a un nodo que sea el meta o la lista cerrada este
			// vacia

			AñadirNodosCerrados(NodoCerrado, meta);
			if (!ListaCerrada.isEmpty()) {
				OrdenarArray(ListaCerrada);
				NodoCerrado = ListaCerrada.get(0);
				ListaCerrada.remove(0);
				ListaAbierta.add(NodoCerrado);
			} else
				finAnalisis = true;

		} while (!NodoCerrado.equal(meta) && !finAnalisis);

		if (ListaAbierta.isEmpty())// Si no se ha podido hacer ninguna accion, no se hace nada
			return ACTIONS.ACTION_NIL;
		else if (!NodoCerrado.equal(meta)) {
			// Si el nodo cerrado no es la meta buscamos el de menor costeTotal ordenando de
			// menor a mayor el "ArrayAbierto"
			OrdenarArray(ListaAbierta);
			NodoCerrado = ListaAbierta.get(0);
		}
		/*
		 * Cogemos el String que indica el camino inicial para hacer el camino, y lo
		 * pasamos a una Accion para el avatar
		 */
		
		String paso = NodoCerrado.getMovimiento();
		if (paso == "Arriba")
			return ACTIONS.ACTION_UP;
		if (paso == "Abajo")
			return ACTIONS.ACTION_DOWN;
		if (paso == "Derecha")
			return ACTIONS.ACTION_RIGHT;
		if (paso == "Izquierda")
			return ACTIONS.ACTION_LEFT;
		else
			return ACTIONS.ACTION_NIL;

	}

	public void AñadirNodosCerrados(Coordenadas NodoActual, Coordenadas meta) {
		/*
		 * En Esta funcion vamos a ver todos los movimientos disponibles para el
		 * "NodoActual" y lo va incorporando a la "ListaCerrada" Para ello, se mira los
		 * tipos de las celdas para saber si esta disponible, y una funcion
		 * "meAtropellaran" que indica si el movimiento es seguro o no.
		 */

		for (int i = 0; i < this.Act.length; i++) {

			Coordenadas aux = NodoActual.posicionFutura(Act[i]);// En "Act" va guardado las acciones posibles

			Coordenadas auxMapa = Mapa[aux.getX()][aux.getY()];

			if ((auxMapa.getTipo() == 0 || auxMapa.getTipo() == -6 || auxMapa.getTipo() == -3)
					&& ListaAbierta.indexOf(auxMapa) == -1 ) {

				int pasosAux = auxMapa.getPasosDados();

				if (pasosAux < 0 || pasosAux > NodoActual.getPasosDados() + 1) {
					auxMapa.SetDist(meta);
					auxMapa.setNumPasos(NodoActual.getPasosDados() + 1);
					auxMapa.setAnterior(NodoActual);

					// Se guarda que se hace en el primer movimiento del camino
					if (auxMapa.getAnterior().getMovimiento() == "Nil")
						// Esto lo cumple todo nodo que su antecesor sea la posicion del Avatar
						auxMapa.setMovimiento(Act[i]);
					else
						auxMapa.setMovimiento(NodoActual.getMovimiento());
				}

				if (ListaCerrada.indexOf(auxMapa) == -1) {
					ListaCerrada.add(auxMapa);// Si no esta en la lista cerrada se añade
				}
			}

		}
	}
	
	

	/*public boolean meAtropellaran(Coordenadas posicion, Coordenadas Avatar) {

		if (Math.abs(posicion.getX() - Avatar.getX()) < 2 && Math.abs(posicion.getY() - Avatar.getY()) < 2) {

			if (Mapa[posicion.getX() - 1][posicion.getY()].getTipo() == -8
					|| Mapa[posicion.getX() - 1][posicion.getY()].getTipo() == -7)
				return true;
			else if (Mapa[posicion.getX() + 1][posicion.getY()].getTipo() == -10
					|| Mapa[posicion.getX() + 1][posicion.getY()].getTipo() == -11)
				return true;
			else if (posicion.getX() == 1 && posicion.getTipo() != -4)
				return true;
			else if (posicion.getX() == Mapa.length - 2 && posicion.getTipo() != -4)
				return true;
			else
				return false;

		} else
			return false;
	}*/
/*
 * Este metodo ordena un ArrayList por su costeTotal
 */
	public void OrdenarArray(ArrayList<Coordenadas> a) {

		Collections.sort(a, new Comparator() {
			public int compare(Object o1, Object o2) {
				Coordenadas c1 = (Coordenadas) o1;
				Coordenadas c2 = (Coordenadas) o2;
				return new Integer(c1.getCosteTotal()).compareTo(new Integer(c2.getCosteTotal()));
			}
		});
	}

}
