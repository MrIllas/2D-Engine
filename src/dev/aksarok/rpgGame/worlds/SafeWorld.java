/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.aksarok.rpgGame.worlds;

import dev.aksarok.rpgGame.Handler;
import dev.aksarok.rpgGame.entities.EntityManager;
import dev.aksarok.rpgGame.tiles.Tile;
import java.awt.Graphics;

/**
 *
 * @author Robert
 */
public class SafeWorld extends World{

    public SafeWorld(Handler handler, String worldName, String path, EntityManager entityManager) {
        super(handler, worldName, path, entityManager);
    }

}
