package cz.muni.fi.raytracer;

import cz.muni.fi.raytracer.materials.*;
import cz.muni.fi.raytracer.sceneobjects.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

class JPanelRenderer extends JPanel {

    //TODO fake depth of field - vypocitam z buffer a ten pouzijem ako masku ku blurnutemu imageu
    BufferedImage image;

    public JPanelRenderer(int width, int height) {

        super();

        //TODO napis pocet polygonov, pocet svetiel, rozlisenie, cas renderu

        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // TODO USE createImage(width, height)

        Scene scene = new Scene(new Camera(new Vector3D(10, 10, 10), new Vector3D(1, 1, 1), 38.5, new Vector3D(0, 1, 0)));
        //Scene scene = new Scene(new Camera(new Vector3D(0, 2, 5.0), new Vector3D(0,0, 0), 38.5, new Vector3D(0, 1, 0)));
        //Scene scene = new Scene(new Camera(new Vector3D(2, 2, 2), new Vector3D(0,0, 0), 38.5, new Vector3D(0, 1, 0)));

        System.out.println("Camera:" + scene.getCamera().getPosition());
        System.out.println("Begin:" + new Date());

        //simple cornells box
        scene.setAmbientLight(new Color(0.03, .03, .03));
        scene.getLights().add(new Light(new Vector3D(250, 400, 250), new Color(1, 1, 0.43), new Color(1, 0.85, 0.43)));
        //scene.getLights().add(new Light(scene.getCamera().getPosition(), new Color(1, 1, 1), new Color(1, 1, 1)));
        scene.getLights().add(new Light(new Vector3D(17, 8, 8), new Color(1, 1, 1), new Color(1, 1, 1)));
        scene.getLights().add(new Light(new Vector3D(5, 0.1, 0.1), new Color(1, 1, 1), new Color(1, 1, 1)));
        //scene.getLights().add(new Light(new Vector3D(250, 5, 250), new Color(0.5, 0.1, 0.1), new Color(0.2, 0.2, 0.1)));

        //scene.lights.Add(new Light(new Vector3D(450, 250, 250), new Color(1, 1, 1), new Color(1, 1, 1)));

        //scene.lights.Add(new Light(new Vector3D(10, 5, 5), new Color(1, 1, 1), new Color(1, 1, 1)));
        //scene.lights.add(new Light(new Vector3D(15, 15, 26), new Color(1, 1, 1), new Color(1, 1, 1)));

        SimpleColor white = new SimpleColor(new Color(1, 1, 1), 0, 0, 1, 0, 1);
        SimpleColor red = new SimpleColor(new Color(0.63, 0.06, 0.04), 0, 0, 1, 0, 1);
        SimpleColor green = new SimpleColor(new Color(0.15, 0.48, 0.09), 0, 0, 1, 0, 1);
        SimpleColor blue = new SimpleColor(new Color(0, 0, 1), 20, 0.01, 1, 1, 1);
        SimpleColor redbb = new SimpleColor(new Color(0.63, 0.06, 0.04), 0, 1, 1, 1, 1);

        IMaterial checkerBoard = new CheckerBoard(1, 1, 0);
        IMaterial perlinNoise = new PerlinNoise(0.7, 7, 0.01, 0, 1, 0, 1);
        IMaterial perlinNoise2 = new PerlinNoise1(0.7, 4, 0.01, 0);

        Sphere sp = new Sphere( new Vector3D(0, 0, 0), 1, perlinNoise2);
        scene.getObjects().add(sp);

        Plane floor = new Plane(new Vector3D(0, 1, 0), 0, perlinNoise);
        scene.getObjects().add(floor);
        Plane ceiling = new Plane(new Vector3D(0, -1, 0), -5.0, white);
      //  scene.getObjects().add(ceiling);
        Plane leftWall = new Plane(new Vector3D(1, 0, 0), -3.0, blue);
        //scene.getObjects().add(leftWall);
        Plane rightWall = new Plane(new Vector3D(-1, 0, 0), -3.0, red);
        //scene.getObjects().add(rightWall);
        Plane backWall = new Plane(new Vector3D(0, 0, -1), -3.0, white);
        //scene.getObjects().add(backWall);


        RayTracer rayTracer = new RayTracer(width, height, new Color(0, 0, 0), scene);
        rayTracer.render(image);

        System.out.println("Finished:" + new Date());
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }
}
