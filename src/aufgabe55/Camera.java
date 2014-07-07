package aufgabe55;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.util.glu.GLU;

import static java.lang.Math.*;
import static org.lwjgl.opengl.ARBDepthClamp.GL_DEPTH_CLAMP;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glPopAttrib;

/**
 * Created by Julian on 07.07.2014.
 */
public class Camera {

    private float x = 0;
    private float y = 0;
    private float z = 0;
    private float pitch = 0;
    private float yaw = 0;
    private float roll = 0;
    private float fov = 90;
    private float aspectRatio = 1;
    private final float zNear;
    private final float zFar;
    private float mouseSpeed, maxLookUp, maxLookDown, speed;

    public Camera() {
        this.zNear = 0.3f;
        this.zFar = 100;
        aspectRatio = 800/600;
        mouseSpeed = 1;
        maxLookUp = 160;
        maxLookDown = -160;
        speed = .1f;
        if (GLContext.getCapabilities().GL_ARB_depth_clamp) {
            glEnable(GL_DEPTH_CLAMP);
        }
        glPushAttrib(GL_TRANSFORM_BIT);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        GLU.gluPerspective(fov, aspectRatio, zNear, zFar);
        glPopAttrib();
    }

    public void update(int delta){

        // -- MOUSE
        float mdx = Mouse.getDX() * mouseSpeed * 0.16f;
        float mdy = Mouse.getDY() * mouseSpeed * 0.16f;
        yaw += mdx;
        pitch += -mdy;

        // -- KEYBOARD
        float dx = 0, dy = 0, dz = 0;
        if(Keyboard.isKeyDown(Keyboard.KEY_W)){
            dz = -speed * delta;
        }else if (Keyboard.isKeyDown(Keyboard.KEY_S)){
            dz = speed * delta;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_A)){
            dx = -speed * delta;
        }else if (Keyboard.isKeyDown(Keyboard.KEY_D)){
            dx = speed * delta;
        }

        //moveFromLook(dx,dy,dz);
        this.z += dx * (float) cos(toRadians(yaw - 90)) + dz * cos(toRadians(yaw));
        this.x -= dx * (float) sin(toRadians(yaw - 90)) + dz * sin(toRadians(yaw));
        this.y += dy * (float) sin(toRadians(pitch - 90)) + dz * sin(toRadians(pitch));
    }

    /** Applies the camera translations and rotations to GL_MODELVIEW. */
    public void apply() {
        glPushAttrib(GL_TRANSFORM_BIT);
        glMatrixMode(GL_MODELVIEW);
        glRotatef(pitch, 1, 0, 0);
        glRotatef(yaw, 0, 1, 0);
        glTranslatef(-x, -y, -z);
        glPopAttrib();
    }

    @Override
    public String toString() {
        return "{" + x + "," + y + "," + z + "}";
    }

}
