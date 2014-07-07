package a55;


import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

/**
 * Created by Julian on 07.07.2014.
 */
public class Main{

    static long lastFrame; // Die Zeit während des letzten Frames
    static long lastFPS;

    // test
    public static void main(String[] args) throws LWJGLException {
        Display.setDisplayMode(new DisplayMode(800, 600));
        Display.create();

        initGL();
        getDelta(); // call once before loop to initialise lastFrame
        lastFPS = getTime(); // call before loop to initialise fps timer

        Scene scene = new Scene();

        while (!Display.isCloseRequested()){
            int delta = getDelta();
            scene.update(delta);
            Display.update();
            Display.sync(60); // cap fps to 60fps
        }
        Display.destroy();
    }

    /**
     * Calculate how many milliseconds have passed
     * since last frame.
     *
     * @return milliseconds passed since last frame
     */
    public static int getDelta() {
        long time = getTime();
        int delta = (int) (time - lastFrame);
        lastFrame = time;

        return delta;
    }

    /**
     * Get the accurate system time
     *
     * @return The system time in milliseconds
     */
    public static long getTime() {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }

    public static void initGL(){
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        //GL11.glOrtho(0,800,0,600,1,-1);
        GL11.glFrustum(-400,400,-300,300,-1,1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
    }

}
