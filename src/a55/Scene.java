package a55;

import org.lwjgl.opengl.GL11;

/**
 * Created by Julian on 07.07.2014.
 */
public class Scene {

    float x = 400,y=300;
    float rotation = 0;

    public void init(){

    }

    public void update(int delta){

        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

        GL11.glColor3f(0.5f, 0.5f, 1.0f);

        // draw quad
        GL11.glPushMatrix();
        GL11.glTranslatef(x, y, 0);
        GL11.glRotatef(rotation, 0f, 0f, 1f);
        GL11.glTranslatef(-x, -y, 0);

        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2f(x - 50, y - 50);
        GL11.glVertex2f(x + 50, y - 50);
        GL11.glVertex2f(x + 50, y + 50);
        GL11.glVertex2f(x - 50, y + 50);
        GL11.glEnd();
        GL11.glPopMatrix();

    }

}
