package a55;

import org.lwjgl.opengl.GL11;

/**
 * Created by Julian on 07.07.2014.
 */
public class Object {

    public float x;
    public float y;
    public float z;
    public float rotation;
    public float width = 0.5f;
    public float scale = 0.1f;

    public Object(float x, float y){
        this.x = x;
        this.y = y;
        this.z = 1;
    }

    public void render(){

        GL11.glColor3f(0.5f, 0.5f, 1.0f);

        // draw quad
        GL11.glPushMatrix();
        GL11.glTranslatef(x, y, 0);
        GL11.glScalef(scale,scale,scale);
        GL11.glRotatef(rotation, 1f, 0f, 1f);

        GL11.glBegin(GL11.GL_QUADS);

        GL11.glNormal3f( 0.0f, 1.0f, 0.0f);                  // Normal Pointing Up
        GL11.glColor3f(0.0f,1.0f,0.0f);          // Set The Color To Green
        GL11.glVertex3f( 1.0f, 1.0f,-1.0f);          // Top Right Of The Quad (Top)
        GL11.glVertex3f(-1.0f, 1.0f,-1.0f);          // Top Left Of The Quad (Top)
        GL11.glVertex3f(-1.0f, 1.0f, 1.0f);          // Bottom Left Of The Quad (Top)
        GL11.glVertex3f( 1.0f, 1.0f, 1.0f);          // Bottom Right Of The Quad (Top)

        GL11.glNormal3f( 0.0f,-1.0f, 0.0f);                  // Normal Pointing Down
        GL11.glColor3f(1.0f,0.5f,0.0f);          // Set The Color To Orange
        GL11.glVertex3f( 1.0f,-1.0f, 1.0f);          // Top Right Of The Quad (Bottom)
        GL11.glVertex3f(-1.0f,-1.0f, 1.0f);          // Top Left Of The Quad (Bottom)
        GL11.glVertex3f(-1.0f,-1.0f,-1.0f);          // Bottom Left Of The Quad (Bottom)
        GL11.glVertex3f( 1.0f,-1.0f,-1.0f);          // Bottom Right Of The Quad (Bottom)

        GL11.glNormal3f( 0.0f, 0.0f, 1.0f);                  // Normal Pointing Towards Viewer
        GL11.glColor3f(1.0f,0.0f,0.0f);          // Set The Color To Red
        GL11.glVertex3f( 1.0f, 1.0f, 1.0f);          // Top Right Of The Quad (Front)
        GL11.glVertex3f(-1.0f, 1.0f, 1.0f);          // Top Left Of The Quad (Front)
        GL11.glVertex3f(-1.0f,-1.0f, 1.0f);          // Bottom Left Of The Quad (Front)
        GL11.glVertex3f( 1.0f,-1.0f, 1.0f);          // Bottom Right Of The Quad (Front)

        GL11.glNormal3f( 0.0f, 0.0f,-1.0f);                  // Normal Pointing Away From Viewer
        GL11.glColor3f(1.0f,1.0f,0.0f);          // Set The Color To Yellow
        GL11.glVertex3f( 1.0f,-1.0f,-1.0f);          // Bottom Left Of The Quad (Back)
        GL11.glVertex3f(-1.0f,-1.0f,-1.0f);          // Bottom Right Of The Quad (Back)
        GL11.glVertex3f(-1.0f, 1.0f,-1.0f);          // Top Right Of The Quad (Back)
        GL11.glVertex3f( 1.0f, 1.0f,-1.0f);          // Top Left Of The Quad (Back)

        GL11.glNormal3f(-1.0f, 0.0f, 0.0f);                  // Normal Pointing Left
        GL11.glColor3f(0.0f,0.0f,1.0f);          // Set The Color To Blue
        GL11.glVertex3f(-1.0f, 1.0f, 1.0f);          // Top Right Of The Quad (Left)
        GL11.glVertex3f(-1.0f, 1.0f,-1.0f);          // Top Left Of The Quad (Left)
        GL11.glVertex3f(-1.0f,-1.0f,-1.0f);          // Bottom Left Of The Quad (Left)
        GL11.glVertex3f(-1.0f,-1.0f, 1.0f);          // Bottom Right Of The Quad (Left)

        GL11.glNormal3f( 1.0f, 0.0f, 0.0f);                  // Normal Pointing Right
        GL11.glColor3f(1.0f,0.0f,1.0f);          // Set The Color To Violet
        GL11.glVertex3f( 1.0f, 1.0f,-1.0f);          // Top Right Of The Quad (Right)
        GL11.glVertex3f( 1.0f, 1.0f, 1.0f);          // Top Left Of The Quad (Right)
        GL11.glVertex3f( 1.0f,-1.0f, 1.0f);          // Bottom Left Of The Quad (Right)
        GL11.glVertex3f( 1.0f,-1.0f,-1.0f);          // Bottom Right Of The Quad (Right)


        GL11.glTranslatef(-x, -y, 0);
        GL11.glEnd();
        GL11.glPopMatrix();
    }

}
