package cz.muni.fi.raytracer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import cz.muni.fi.raytracer.materials.IMaterial;
import cz.muni.fi.raytracer.sceneobjects.Triangle;

public class FileImporter {
	// http://en.wikipedia.org/wiki/Obj
	// http://people.sc.fsu.edu/~burkardt/data/obj/obj.html
	// TODO MTl importer
	// TODO make it possible to add offset to the object
	// TODO add scale

	private List<Vector3D> vertices;

	private Vector3D getVerticeWithNumber(final String string) {
		return this.vertices.get(Integer.parseInt(string.split("/")[0]) - 1);
	}

	public void loadObj(final Scene scene, final File objFile, final IMaterial surface) throws FileNotFoundException {

		this.vertices = new LinkedList<Vector3D>();

		final BufferedReader iss = new BufferedReader(new FileReader(objFile));

		try {
			String line;
			while ((line = iss.readLine()) != null) {

				final String[] words = line.split("\\s+");

				if (words.length != 0) {
					if ("v".equals(words[0])) {
						this.vertices.add(new Vector3D(Float.parseFloat(words[1]), Float.parseFloat(words[2]), Float.parseFloat(words[3])));
					}

					if ("f".equals(words[0])) {
						for (int i = 3; i < words.length; i++) {
							scene.getObjects().add(new Triangle(this.getVerticeWithNumber(words[1]), this.getVerticeWithNumber(words[i - 1]), this.getVerticeWithNumber(words[i]), surface));
						}
					}
				}
			}
		} catch (final IOException ex) {
			Logger.getLogger(FileImporter.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void loadPly(final Scene scene, final File plyFile) {
		// TODO http://local.wasp.uwa.edu.au/~pbourke/dataformats/ply/
	}
}
