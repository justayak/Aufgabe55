package aufgabe55;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;

/**
 * http://stackoverflow.com/questions/16652691/how-exactly-does-deferred-shading-work-in-lwjgl
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
        //camera = new EulerCamera.Builder().setAspectRatio((float) Display.getWidth() / Display.getHeight())
        //        .setRotation(-1.12f, 0.16f, 0f).setPosition(-1.38f, 1.36f, 7.95f).setFieldOfView(60).build();
        camera = new Camera();
        camera.applyOptimalStates();
        camera.applyPerspectiveMatrix();

        long lastFrame = getTime();
        while (!Display.isCloseRequested()&&
                !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
            long time = getTime();
            int delta = (int) (time - lastFrame);
            lastFrame = delta;
            update(delta);

            camera.processMouse(1,80,-80);
            camera.processKeyboard(56,1,1,1);
            if (Mouse.isButtonDown(0)) {
                Mouse.setGrabbed(true);
            } else if (Mouse.isButtonDown(1)) {
                Mouse.setGrabbed(false);
            }

            Display.update();
            Display.sync(60); // cap fps to 60fps
        }
        Display.destroy();
    }

    static FloatBuffer matSpecular;
    static FloatBuffer lightPosition;
    static FloatBuffer whiteLight;
    static FloatBuffer lModelAmbient;
    static FloatBuffer spotDirection;

    private static void initGL(){
        //----------- Variables & method calls added for Lighting Test -----------//
        glEnable(GL_COLOR_MATERIAL);
        initLightArrays();
        glShadeModel(GL_SMOOTH);
        glMaterial(GL_FRONT, GL_SPECULAR, matSpecular);				// sets specular material color
        glMaterialf(GL_FRONT, GL_SHININESS, 90.0f);					// sets shininess

        glLight(GL_LIGHT0, GL_POSITION, lightPosition);				// sets light position
        glLight(GL_LIGHT0, GL_SPECULAR, whiteLight);				// sets specular light to white
        glLight(GL_LIGHT0, GL_DIFFUSE, whiteLight);					// sets diffuse light to white
        glLightModel(GL_LIGHT_MODEL_AMBIENT, lModelAmbient);		// global ambient light

        glLight(GL_LIGHT1, GL_POSITION, lightPosition);
        glLight(GL_LIGHT1, GL_SPOT_DIRECTION,spotDirection);
        glLight(GL_LIGHT1, GL_SPECULAR,whiteLight);

        glEnable(GL_LIGHTING);										// enables lighting
        glEnable(GL_LIGHT0);										// enables light0
        glEnable(GL_LIGHT1);										// enables light0

        glEnable(GL_COLOR_MATERIAL);								// enables opengl to use glColor3f to define material color
        glColorMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE);			// tell opengl glColor3f effects the ambient and diffuse properties of material
        //----------- END: Variables & method calls added for Lighting Test -----------//
    }

    private static void initLightArrays(){

        spotDirection = BufferUtils.createFloatBuffer(4);
        spotDirection.put(-1.0f).put(1.0f).put(0).put(0).flip();

        matSpecular = BufferUtils.createFloatBuffer(4);
        matSpecular.put(1.0f).put(0.0f).put(.0f).put(1.0f).flip();

        lightPosition = BufferUtils.createFloatBuffer(4);
        lightPosition.put(1.0f).put(1.0f).put(1.0f).put(0.0f).flip();

        whiteLight = BufferUtils.createFloatBuffer(4);
        whiteLight.put(.4f).put(1.0f).put(.4f).put(1.0f).flip();

        lModelAmbient = BufferUtils.createFloatBuffer(4);
        lModelAmbient.put(0.5f).put(0.5f).put(0.5f).put(1.0f).flip();
    }

    /**
     * rendering...
     * @param delta
     */
    public static void update(int delta){
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glLoadIdentity();

        camera.applyTranslations();
        object.rotation += 1;

        object.render();
    }

    public static long getTime() {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }

}
