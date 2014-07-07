package a55;

import org.lwjgl.opengl.GL11;

/**
 * Created by Julian on 07.07.2014.
 */
public class Scene {

    Object object = new Object(0,0);

    public void init(){

    }

    public void update(int delta){

        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

        object.rotation += 1;
        object.render();



    }

}
