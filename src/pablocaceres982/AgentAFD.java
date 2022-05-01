package pablocaceres982;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import core.game.Observation;
import core.game.StateObservation;
import core.player.AbstractPlayer;
import ontology.Types;
import pablocaceres982.Arbol.*;
import pablocaceres982.Automata.*;
import tools.ElapsedCpuTimer;
import tools.Vector2d;

/**
 * Created with IntelliJ IDEA.
 * User: ssamot
 * Date: 14/11/13
 * Time: 21:45
 * This is a Java port from Tom Schaul's VGDL - https://github.com/schaul/py-vgdl
 */
public class AgentAFD extends AbstractPlayer {
    /**
     * Random generator for the agent.
     */
    protected Random randomGenerator;
    /**
     * List of available actions for the agent
     */
    protected ArrayList<Types.ACTIONS> actions;
    private Automata automata;


    /**
     * Public constructor with state observation and time due.
     * @param so state observation of the current game.
     * @param elapsedTimer Timer for the controller creation.
     */
    public AgentAFD(StateObservation so, ElapsedCpuTimer elapsedTimer)
    {
        randomGenerator = new Random();
        actions = so.getAvailableActions();
        automata=new Automata();
    }


    /**
     * Picks an action. This function is called every game step to request an
     * action from the player.
     * @param stateObs Observation of the current state.
     * @param elapsedTimer Timer when the action returned is due.
     * @return An action for the current state
     */
    public Types.ACTIONS act(StateObservation stateObs, ElapsedCpuTimer elapsedTimer) {
        Mapa m=new Mapa(stateObs);
        
        /*Calculamos la posicion del avatar en celdas mediante su coordenada en pixeles*/
        int tamañoBloques=stateObs.getBlockSize();
        Vector2d v=stateObs.getAvatarPosition();
        Coordenadas Avatar=new Coordenadas((int)v.x/tamañoBloques,(int)v.y/tamañoBloques);
        /***/
       
        
        return automata.Accion(stateObs, Avatar, m);
    }

}
