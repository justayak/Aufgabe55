package aufgabe55;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;

/**
 * http://stackoverflow.com/questions/16652691/how-exactly-does-deferred-shading-work-in-lwjgl
 * -Djava.library.path=lwjgl-2.9.1/native/windows
 *
 * Created by Julian on 07.07.2014.
 */
public class Demo {

    private static Camera camera;
    private static Object object = new Object(1,1);

    public static void main(String[]args) throws LWJGLException {
        Display.setDisplayMode(new DisplayMode(800, 600));
        Display.setVSyncEnabled(true);
        Display.setTitle("Aufgabe55");
        Display.create();

        glPolygonMode(GL11.GL_FRONT_AND_BACK,GL11.GL_FILL);
        glEnable(GL11.GL_DEPTH_TEST);
        glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
        initGL();

        // camera
        camera = new Camera();

        long lastFrame = getTime();
        while (!Display.isCloseRequested()&& !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
            long time = getTime();
            int delta = (int) (time - lastFrame);
            if (delta <= 0) delta = 1;
            lastFrame = time;
            update(delta);
            camera.update(delta);
            Display.update();
            Display.sync(60); // cap fps to 60fps
        }
        Display.destroy();
    }

    static FloatBuffer matSpecular;
    static FloatBuffer lightPosition;
    static FloatBuffer whiteLight;
    static FloatBuffer lModelAmbient;

    private static void initGL(){
        //----------- Variables & method calls added for Lighting Test -----------//
        glEnable(GL_COLOR_MATERIAL);


        matSpecular = BufferUtils.createFloatBuffer(4);
        matSpecular.put(1.0f).put(1.0f).put(1.0f).put(1.0f).flip();

        lightPosition = BufferUtils.createFloatBuffer(4);
        lightPosition.put(-1.0f).put(1.0f).put(1.0f).put(0.0f).flip();

        whiteLight = BufferUtils.createFloatBuffer(4);
        whiteLight.put(.0f).put(1.0f).put(1.0f).put(1.0f).flip();

        lModelAmbient = BufferUtils.createFloatBuffer(4);
        lModelAmbient.put(0.5f).put(0.0f).put(0.1f).put(0.1f).flip();


        glShadeModel(GL_SMOOTH);
        glMaterial(GL_FRONT, GL_SPECULAR, matSpecular);				// sets specular material color
        glMaterialf(GL_FRONT, GL_SHININESS, 110.0f);					// sets shininess

        glLight(GL_LIGHT0, GL_POSITION, lightPosition);				// sets light position
        glLight(GL_LIGHT0, GL_SPECULAR, whiteLight);				// sets specular light to white
        //glLight(GL_LIGHT0, GL_DIFFUSE, whiteLight);					// sets diffuse light to white
        glLightModel(GL_LIGHT_MODEL_AMBIENT, lModelAmbient);		// global ambient light

        glEnable(GL_LIGHTING);										// enables lighting
        glEnable(GL_LIGHT0);										// enables light0

        glEnable(GL_COLOR_MATERIAL);								// enables opengl to use glColor3f to define material color
        glColorMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE);			// tell opengl glColor3f effects the ambient and diffuse properties of material
        //----------- END: Variables & method calls added for Lighting Test -----------//
    }

    /**
     * rendering...
     * @param delta
     */
    public static void update(int delta){
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glLoadIdentity();

        camera.apply();
        object.rotation += 1;

        object.render();
    }

    public static long getTime() {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }

}
