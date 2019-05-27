package dev.aksarok.rpgGame;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import dev.aksarok.rpgGame.display.Display;
import dev.aksarok.rpgGame.gfx.Assets;
import dev.aksarok.rpgGame.gfx.GameCamera;
import dev.aksarok.rpgGame.gfx.ImageLoader;
import dev.aksarok.rpgGame.gfx.SpriteSheet;
import dev.aksarok.rpgGame.input.KeyManager;
import dev.aksarok.rpgGame.input.MouseManager;
import dev.aksarok.rpgGame.states.GameState;
import dev.aksarok.rpgGame.states.MenuState;
import dev.aksarok.rpgGame.states.State;

/**
 * Main class del joc Aquesta classe sera la que executi tot el codi per que el
 * joc funcioni
 *
 * @author Robert
 *
 */
public class Game implements Runnable {

    private Display display;
    private int width, height;
    public String title;

    private boolean running = false;
    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;

    //STATES
    public GameState gameState;
    public State menuState;

    //INPUT
    private KeyManager keyManager;
    private MouseManager mouseManager;

    //CAMERA
    private GameCamera gameCamera;

    //HANDLER
    private Handler handler;

    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
    }

    /**
     * Inicia el grafics, etc...
     */
    private void init() {
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        Assets.init();

        handler = new Handler(this);
        gameCamera = new GameCamera(handler, 0, 0);

        gameState = new GameState(handler, "res/worlds/world1.wlvl");
        menuState = new MenuState(handler);
        State.setState(menuState); //STATE inicial

    }

    /**
     * Va actualitzant tot lo necessari en el nostre joc. Variables, etc...
     */
    private void tick() {
        keyManager.tick();

        if (State.getState() != null) {
            State.getState().tick();
        }
    }

    /**
     * Renderitza el joc
     */
    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3); //Prepara la memoria
            return;
        }
        g = bs.getDrawGraphics(); //Dibuixa
        //CLEAR SCREEN
        g.clearRect(0, 0, width, height);

        //DRAW HERE!
        if (State.getState() != null) {
            State.getState().render(g);
        }

        //END DRAWING!
        bs.show(); //Ensenya els grafics
        g.dispose();
    }

    public static int endFps = 0;

    /**
     * Controla el joc
     */
    public void run() {
        init();

        int fps = 60;
        double timePerTick = 1000000000 / fps; //Maxim numero de temps per executar
        double delta = 0;
        long now;
        long lastTime = System.nanoTime(); //Torna en nanosegons la velocitat del process.
        long timer = 0;
        int ticks = 0;

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1) {
                tick();
                render();
                ticks++;
                delta--;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + ticks);
                endFps = ticks;
                ticks = 0;
                timer = 0;
            }
        }

        stop();
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public MouseManager getMouseManager() {
        return mouseManager;
    }

    public GameCamera getGameCamera() {
        return gameCamera;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    /**
     * Comença el thread Game
     */
    public synchronized void start() {
        if (running) {
            return;
        }

        running = true;
        thread = new Thread(this);
        thread.start(); //Al iniciar el thread executem run()
    }

    /**
     * Tanca el thread
     */
    public synchronized void stop() {
        if (!running) {
            return;
        }

        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
