package dev.aksarok.rpgGame.states;

import java.awt.Graphics;

import dev.aksarok.rpgGame.Handler;

public abstract class State {
	
	private static State currentState = null;
	/**
	 * Cambia el state
	 * @param state
	 */
	public static void setState(State state) {
		currentState = state;
	}
	
	/**
	 * Torna el state
	 * @return
	 */
	public static State getState() {
		return currentState;
	}
	
	//CLASS
	
	protected Handler handler;
	
	public State(Handler handler) {
		this.handler = handler;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	
	
}
