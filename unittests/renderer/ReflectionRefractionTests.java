/**
 * 
 */
package unittests.renderer;

import org.junit.jupiter.api.Test;

import static java.awt.Color.*;

import renderer.ImageWriter;
import lighting.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;

import java.lang.reflect.Array;
import java.util.Random;

/**
 * Tests for reflection and transparency functionality, test for partial shadows
 * (with transparency)
 * 
 * @author dzilb
 */
public class ReflectionRefractionTests {
	private Scene scene = new Scene("Test scene");

	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */
	@Test
	public void twoSpheres() {
		Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPSize(150d, 150).setVPDistance(1000);

		scene.geometries.add( //
				new Sphere(new Point(0, 0, -50), 50d).setEmission(new Color(BLUE)) //
						.setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3)),
				new Sphere(new Point(0, 0, -50), 25d).setEmission(new Color(RED)) //
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)));
		scene.lights.add( //
				new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2)) //
						.setKl(0.0004).setKq(0.0000006));

		camera.setImageWriter(new ImageWriter("refractionTwoSpheres", 500, 500)) //
				.setRayTracer(new RayTracerBasic(scene)) //
				.renderImage() //
				.writeToImage();
	}

	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */
	@Test
	public void twoSpheresOnMirrors() {
		Camera camera = new Camera(new Point(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPSize(2500d, 2500).setVPDistance(10000); //

		scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), new Double3(0.1)));

		scene.geometries.add( //
				new Sphere(new Point(-950, -900, -1000), 400d).setEmission(new Color(0, 0, 100)) //
						.setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20).setKt(0.5)),
				new Sphere(new Point(-950, -900, -1000), 200d).setEmission(new Color(100, 20, 20)) //
						.setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)),
				new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500), new Point(670, 670, 3000)) //
						.setEmission(new Color(20, 20, 20)) //
						.setMaterial(new Material().setKr(1)),
				new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
						new Point(-1500, -1500, -2000)) //
						.setEmission(new Color(20, 20, 20)) //
						.setMaterial(new Material().setKr(0.5)));

		scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point(-750, -750, -150), new Vector(-1, -1, -4)) //
				.setKl(0.00001).setKq(0.000005));

		ImageWriter imageWriter = new ImageWriter("reflectionTwoSpheresMirrored", 500, 500);
		camera.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(scene)) //
				.renderImage() //
				.writeToImage();
	}

	/**
	 * Produce a picture of a two triangles lighted by a spot light with a partially
	 * transparent Sphere producing partial shadow
	 */
	@Test
	public void trianglesTransparentSphere() {
		Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPSize(200d, 200).setVPDistance(1000);

		scene.setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15)));

		scene.geometries.add( //
				new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135), new Point(75, 75, -150)) //
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)), //
				new Triangle(new Point(-150, -150, -115), new Point(-70, 70, -140), new Point(75, 75, -150)) //
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)), //
				new Sphere(new Point(60, 50, -50), 30d).setEmission(new Color(BLUE)) //
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)));
		scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0), new Vector(0, 0, -1)) //
				.setKl(4E-5).setKq(2E-7));

		ImageWriter imageWriter = new ImageWriter("refractionShadow", 600, 600);
		camera.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(scene)) //
				.renderImage() //
				.writeToImage();
	}

	@Test
	public void myShape() {
		Camera camera = new Camera(new Point(0, -150, 0), new Vector(0, 1, 0), new Vector(0, 0, 1))
				.setVPSize(200d, 200).setVPDistance(1000);
		;

		scene.setAmbientLight(new AmbientLight(new Color(gray).reduce(2), new Double3(0.15)));
		scene.geometries.add(

				new Triangle(new Point(1, -0.2, 1.4), new Point(-1, -0.2, 1.4), new Point(0, -0.2, -0.5))
						.setEmission(new Color(red).scale(10))
						.setMaterial(new Material().setKd(0).setKs(0).setShininess(1)),
				new Triangle(new Point(1, 0, 0), new Point(0, 0, 2), new Point(-1, 0, 0))
						.setEmission(new Color(red).scale(10)),
				new Sphere(new Point(0, 0, 0.5), 3).setEmission(new Color(yellow).reduce(4))
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(80).setKt(0.8)),
				new Plane(new Point(-10, 0, 0), new Point(0, 20, 0), new Point(-14.85844, 20.19227, 12))
						.setMaterial(new Material().setKd(0.4).setKs(0.2).setShininess(100).setKr(1)).setEmission(new Color(gray).reduce(2))
		);

		scene.lights.add(new PointLight(new Color(100, 100, 150), new Point(0, 6, 0)));
		scene.lights.add(new DirectionalLight(new Color(white), new Vector(1, 0, 0)));

		scene.lights.add(new SpotLight(new Color(white).reduce(2), new Point(20.43303, -7.37104, 13.77329), new Vector(-20.43, 7.37, -13.77)));
		ImageWriter imageWriter = new ImageWriter("myShape", 1000, 1000);
		camera.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(scene)) //
				.renderImage() //
				.writeToImage();

	}


	@Test
	public void myShape2() {
		Camera camera = new Camera(new Point(-330, 0, 5), new Vector(1, 0, 0), new Vector(0, 0, 1))
				.setVPSize(200d, 200).setVPDistance(1000);
		;

		scene.setAmbientLight(new AmbientLight(new Color(white).reduce(6), new Double3(0.15)));

		double angle = 0;
		double heigh = 0;


		Random generator = new Random();
		scene.geometries.add(
				new Polygon(new Vector(-4, 4, 0), new Vector(-4, -4, 0), new Point(-4, -4, 25), new Point(-4, 4, 25))

						.setMaterial(new Material().setKd(0.2).setKs(0.5).setShininess(80).setKt(0.6).setKr(0))


		);


		java.awt.Color[] colors = {yellow, red, orange, blue};
		for (int i = 25; i < 200; ++i) {
			int colorIndex = i % colors.length;//generator.nextInt(colors.length);

			scene.geometries.add(
					new Sphere(new Point(i / 25 * Math.cos(angle), i / 25 * Math.sin(angle), heigh), 0.5)
							.setEmission(new Color(colors[colorIndex]).reduce(2.2))
							.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(80).setKt(0.3))


			);

			//scene.lights.add(new PointLight(new Color( colors[colorIndex]).reduce(2),new Point(3*Math.cos(angle),  3*Math.sin(angle), heigh) ));

			angle += 3.14 / 15;

			heigh += 0.15;


		}

		//scene.lights.add(new PointLight(new Color(100,100,150),new Point(0,6,0) ));
		scene.lights.add(new SpotLight(new Color(white).reduce(2), new Point(-150, 0, 5), new Vector(1, 0, 0)));
		//scene.lights.add(new SpotLight(new Color(white).scale(40), new Point(-30,0, 5) ,new Vector(1,0,0))
		//		.setKc(0.5).setKq(0.1));

		scene.setBackground(new Color(gray));
		//scene.lights.add(new SpotLight(new Color(white).reduce(2), new Point(20.43303,-7.37104,13.77329), new Vector(-20.43,7.37,-13.77)));
		ImageWriter imageWriter = new ImageWriter("Shape", 1000, 1000);
		camera.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(scene)) //
				.renderImage() //
				.writeToImage();

	}

	@Test
	public void myShape3() {
		Camera camera = new Camera(new Point(-300, 0, 5), new Vector(1, 0, 0), new Vector(0, 0, 1))
				.setVPSize(200d, 200).setVPDistance(1000);
		;

		scene.setAmbientLight(new AmbientLight(new Color(white).reduce(6), new Double3(0.15)));

		double angle = 0;
		double heigh = 0.8;

		Random generator = new Random();
		scene.geometries.add(
				new Plane(new Point(1, 0, -0.5), new Point(0, 1, -0.5), new Point(0, 0, -0.5))
						.setEmission(new Color(black).reduce(1.3))
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(100).setKt(0).setKr(0.3))


		);

		scene.geometries.add(
				new Sphere(new Point(0, 0, 4), 3.4)
						.setEmission(new Color(yellow).reduce(10))
						.setMaterial(new Material().setKd(0.2).setKs(1).setShininess(80).setKt(0.7))


		);
		scene.geometries.add(
				new Sphere(new Point(0, 0, 10.2), 3.4)
						.setEmission(new Color(red).reduce(10))
						.setMaterial(new Material().setKd(0.2).setKs(1).setShininess(80).setKt(0.7))


		);
		scene.geometries.add(
				new Sphere(new Point(0, 0, 16.4), 3.4)
						.setEmission(new Color(green).reduce(10))
						.setMaterial(new Material().setKd(0.2).setKs(1).setShininess(80).setKt(0.7).setKr(0))


		);
		scene.geometries.add(
				new Sphere(new Point(0, 0, 16.4 + 6.2), 3.4)
						.setEmission(new Color(yellow).reduce(10))
						.setMaterial(new Material().setKd(0.2).setKs(1).setShininess(80).setKt(0.7))


		);
		scene.geometries.add(
				new Sphere(new Point(0, 0, 16.4 + 2 * 6.2), 3.4)
						.setEmission(new Color(red).reduce(10))
						.setMaterial(new Material().setKd(0.2).setKs(1).setShininess(80).setKt(0.7))


		);
		scene.geometries.add(
				new Sphere(new Point(0, 0, 16.4 + 3 * 6.2), 3.4)
						.setEmission(new Color(green).reduce(10))
						.setMaterial(new Material().setKd(0.2).setKs(1).setShininess(80).setKt(0.7).setKr(0))


		);


		scene.geometries.add(
				new Plane(new Point(10, 1, -1), new Point(10, -1, -1), new Point(10, 0, 3))
						.setEmission(new Color(darkGray).reduce(15))
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(80).setKt(0).setKr(0))
		);


		java.awt.Color[] colors = {new java.awt.Color(255, 0, 0), new java.awt.Color(255, 255 / 2, 0), new java.awt.Color(255, 255, 0),
				new java.awt.Color(0, 255, 0), new java.awt.Color(0, 255, 255 / 2), new java.awt.Color(0, 255, 255),
				new java.awt.Color(0, 0, 255), new java.awt.Color(255 / 2, 0, 255), new java.awt.Color(255, 0, 255)};

		for (int i = 0; i < 200; ++i) {
			int colorIndex = i % colors.length;//generator.nextInt(colors.length);

			scene.geometries.add(
					new Sphere(new Point(4 * Math.cos(angle), 4 * Math.sin(angle), heigh), 0.5)
							.setEmission(new Color(colors[colorIndex]).reduce(2.2))
							.setMaterial(new Material().setKd(0.2).setKs(1).setShininess(80).setKt(0.3))


			);

			//scene.lights.add(new PointLight(new Color( colors[colorIndex]).reduce(2),new Point(3*Math.cos(angle),  3*Math.sin(angle), heigh) ));

			angle += 3.14 / 13;

			heigh += 0.13;


		}

		//scene.lights.add(new PointLight(new Color(100,100,150),new Point(0,6,0) ));
		scene.lights.add(new DirectionalLight(new Color(white).scale(1.6), new Vector(1, -0.3, 0)));
		//scene.lights.add(new SpotLight(new Color(white).scale(40), new Point(-30,0, 5) ,new Vector(1,0,0))
		//		.setKc(0.5).setKq(0.1));


		//scene.lights.add(new SpotLight(new Color(white).reduce(2), new Point(20.43303,-7.37104,13.77329), new Vector(-20.43,7.37,-13.77)));
		ImageWriter imageWriter = new ImageWriter("myShape3", 1000, 1000);
		camera.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(scene)) //
				.renderImage() //
				.writeToImage();

	}








	@Test
	public void testBlurryGlass() {

	 Vector vTo =  new Vector(0, 1, 0);
		Camera camera = new Camera(new Point(0, -230, 0).add(vTo.scale(-13)), vTo, new Vector(0, 0, 1))
				.setVPSize(200d, 200).setVPDistance(1000);
		;

		scene.setAmbientLight(new AmbientLight(new Color(gray).reduce(2), new Double3(0.15)));


		for (int i = -4; i < 6; i+=2) {
			scene.geometries.add(
					new Sphere(new Point(5*i, -1.90, -3), 3).setEmission(new Color( red).reduce(4).reduce(2.2))
							.setMaterial(new Material().setKd(0.2).setKs(1).setShininess(80).setKt(0)) ,

					new Sphere(new Point(5*i, 5, 3), 3).setEmission(new Color( green).reduce(2.2))
							.setMaterial(new Material().setKd(0.2).setKs(1).setShininess(80).setKt(0))
					,
					new Sphere(new Point(5*i, -8, -8), 3).setEmission(new Color( yellow).reduce(2.2))
							.setMaterial(new Material().setKd(0.2).setKs(1).setShininess(80).setKt(0)),

					new Polygon(new Point( 5*i-4,-5,-11),new Point( 5*i-4,-5,5),new Point(5*i+4,-5,5), new Point( 5*i+4,-5,-11))
							.setEmission(new Color(250,235,215).reduce(2.5))
							.setMaterial(new Material().setKd(0.001).setKs(0.002).setShininess(1).setKt(0.95)
									.setBlurGlass(i==4?1:20,0.3*(i+5),1))

					);
		}



		scene.geometries.add(
		new Plane(new Point( 1,10,1),new Point(2,10,1), new Point( 5,10,0))
				.setEmission(new Color(white).reduce(3))
				.setMaterial(new Material().setKd(0.2).setKs(0).setShininess(0).setKt(0))

		);

		//scene.lights.add(new PointLight(new Color(100, 100, 150), new Point(0, 6, 0)));
		scene.lights.add(new DirectionalLight(new Color(white).reduce(1.3), new Vector(-0.4, 1, 0)));
		scene.lights.add(new SpotLight(new Color(white).reduce(2), new Point(20.43303, -7.37104, 13.77329), new Vector(-20.43, 7.37, -13.77)).setKl(0.6));

		ImageWriter imageWriter = new ImageWriter("check", 10000, 10000);
		camera.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(scene)) //
				.renderImage() //
				.writeToImage();

	}



	@Test
	public void checkColor2() {
		Camera camera = new Camera(new Point(0, -750, 0), new Vector(0, 1, 0), new Vector(0, 0, 1))
				.setVPSize(200d, 200).setVPDistance(1000);
		;

		scene.setAmbientLight(new AmbientLight(new Color(gray).reduce(2), new Double3(0.15)));


		java.awt.Color[] colors = {new java.awt.Color(255, 0, 0), new java.awt.Color(255, 255 / 2, 0), new java.awt.Color(255, 255, 0),
				new java.awt.Color(0, 255, 0), new java.awt.Color(0, 255, 255 / 2), new java.awt.Color(0, 255, 255),
				new java.awt.Color(0, 0, 255), new java.awt.Color(255 / 2, 0, 255), new java.awt.Color(255, 0, 255)};


		double angle = 0;

		int index =0;
		for (int i = -400; i < 400; i+=4) {
			int colorIndex = (index++) % colors.length;//generator.nextInt(colors.length);

			scene.geometries.add(
					new Sphere(new Point(i, 0,10* Math.cos(angle)), 2)
							.setEmission(new Color(colors[colorIndex]).reduce(2.2))
							.setMaterial(new Material().setKd(0.2).setKs(1).setShininess(80).setKt(0))



			);

			scene.geometries.add(
					new Sphere(new Point(i, -100,-10* Math.cos(angle)), 2)
							.setEmission(new Color(colors[colorIndex]).reduce(2.2))
							.setMaterial(new Material().setKd(0.2).setKs(1).setShininess(80).setKt(0))



					);

			//scene.lights.add(new PointLight(new Color( colors[colorIndex]).reduce(2),new Point(3*Math.cos(angle),  3*Math.sin(angle), heigh) ));

			angle += 3.14 / 7;




		}

		//scene.lights.add(new PointLight(new Color(100, 100, 150), new Point(0, 6, 0)));
		scene.lights.add(new DirectionalLight(new Color(white).reduce(2), new Vector(-0.4, 1, 0)));

		//scene.lights.add(new SpotLight(new Color(white).reduce(2), new Point(20.43303, -7.37104, 13.77329), new Vector(-20.43, 7.37, -13.77)));
		ImageWriter imageWriter = new ImageWriter("check", 500, 500);
		camera.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(scene)) //
				.renderImage() //
				.writeToImage();

	}











	@Test
	public void checkColor3() {

		Vector vTo = new Vector(50, 4, -50) ;
		Vector vUp = new Vector(50,  0, 50) ;

		Point camera_point = new Point(-11, 2, 5).add(vTo.scale(-0.001));
		Camera camera = new Camera(camera_point, vTo,  vUp)
				.setVPSize(200d, 200).setVPDistance(100);
		;

		scene.setAmbientLight(new AmbientLight(new Color(white).reduce(1.2) , new Double3(0.2)));

		//floor
		scene.geometries.add( new Plane( new Point(-6,0,-5.3),new Point(-6,8,-5.3),new Point(6,8,-5.3))
				.setEmission( new Color(black).scale(1)).setMaterial(new Material().setKd(0.005).setKs(0.001).setShininess(10).setKr(0.6).setBlurGlass(1,1,1)))
		;

		//wall
		scene.geometries.add( new Plane( new Point(20,0,0),new Point(20,1,2),new Point(20,4,5))
						.setEmission( new Color(250,235,215).reduce(1.9)).setMaterial(new Material().setKd(0.05).setKs(0.001).setShininess(0).setKr(0.5)));
		//table
		scene.geometries.add( new Polygon(new Point(6,0,0),new Point(-6,0,0),new Point(-6,8,0),new Point(6,8,0))
				.setEmission(new Color( 0,153,76).reduce(1.3)).setMaterial(new Material().setKd(0.01).setKs(.002).setShininess(0).setKt(0.7)))
				 ;

		Color table_color = new Color( 139,69,19).reduce(1.3);
		Material table_material = new Material().setKd(0.001).setKs(2).setShininess(10).setKt(0);
		scene.geometries.add(new Polygon(
				new Point(-6,8,0.3),new Point(-6,0,0.3),new Point(-6,0,-0.5),new Point(-6,8,-0.5))
				.setEmission(table_color)
				.setMaterial( table_material));

		scene.geometries.add(new Polygon(
				new Point(-6,8,0.3),new Point(6,8,0.3),new Point(6,8,-0.5),new Point(-6,8,-0.5))
				.setEmission( table_color)
				.setMaterial( table_material));

		scene.geometries.add(new Polygon(
				new Point(6,8,0.3),new Point(6,0,0.3),new Point(6,0,-0.5),new Point(6,8,-0.5))
				.setEmission(table_color)
				.setMaterial( table_material));

		scene.geometries.add(new Polygon(
				new Point(-6,0,0.3),new Point(6,0,0.3),new Point(6,0,-0.5),new Point(-6,0,-0.5))
				.setEmission(table_color)
				.setMaterial(new Material().setKd(0.2).setKs(0.1).setShininess(10).setKt(0)));

		//legs

		scene.geometries.add(new Polygon(
				new Point(-6,8,-0.3),new Point(-6,6.5,-0.3),new Point(-6,6.5,-5),new Point(-6,8,-5))
				.setEmission(table_color)
				.setMaterial(new Material().setKd(0.2).setKs(0.1).setShininess(10).setKt(0)));


		scene.geometries.add(new Polygon(
				new Point(-6,0,-0.3),new Point(-6,1.5,-0.3),new Point(-6,1.5,-5),new Point(-6,0,-5))
				.setEmission(table_color)
				.setMaterial(new Material().setKd(0.2).setKs(0.1).setShininess(10).setKt(0)));

		scene.geometries.add(new Polygon(
				new Point(6,8,-0.3),new Point(6,6.5,-0.3),new Point(6,6.5,-5),new Point(6,8,-5))
				.setEmission(table_color)
				.setMaterial(new Material().setKd(0.2).setKs(0.1).setShininess(10).setKt(0)));


		scene.geometries.add(new Polygon(
				new Point(6,0,-0.3),new Point(6,1.5,-0.3),new Point(6,1.5,-5),new Point(6,0,-5))
				.setEmission(table_color)
				.setMaterial(new Material().setKd(0.2).setKs(0.1).setShininess(10).setKt(0)));

		//row 1
     scene.geometries.add(new Sphere( new Point(-2,4,0.5001),0.5).setEmission(new Color(250,235,215).reduce(1.6))
			 .setMaterial(new Material().setKd(0.2).setKs(0.1).setShininess(10).setKt(0.01) )
	  );

	 //row 2
		scene.geometries.add(new Sphere( new Point(-1.12,3.5,0.5001),0.5).setEmission(new Color(red))
				.setMaterial(new Material().setKd(0.001).setKs(0.2).setShininess(20).setKt(0.5))
		);

		//purple
		scene.geometries.add(new Sphere( new Point(-1.12,4.5,0.5001),0.5).setEmission(new Color(139,0,139))
				.setMaterial(new Material().setKd(0.2).setKs(0).setShininess(10).setKt(0))
		);


		//row 3
		scene.geometries.add(new Sphere( new Point(-0.24,5,0.5001),0.5).setEmission(new Color(255,20,147))
				.setMaterial(new Material().setKd(0.002).setKs(0.002).setShininess(0).setKt(0))
		);

		scene.geometries.add(new Sphere( new Point(-0.24,4,0.5001),0.5).setEmission(new Color(black))
				.setMaterial(new Material().setKd(0.2).setKs(1).setShininess(80).setKt(0))
		);

		scene.geometries.add(new Sphere( new Point(-0.24,3,0.5001),0.5).setEmission(new Color(yellow).reduce(1.6))
				.setMaterial(new Material().setKd(0.001).setKs(0.002).setShininess(1).setKt(0.01) )
		);

		//row 4
		scene.geometries.add(new Sphere( new Point(0.64,5.5,0.5001),0.5).setEmission(new Color(blue))
				.setMaterial(new Material().setKd(0.2).setKs(0).setShininess(10).setKt(0))
		);

		scene.geometries.add(new Sphere( new Point(0.64,4.5,0.5001),0.5).setEmission(new Color(blue))
				.setMaterial(new Material().setKd(0.2).setKs(0).setShininess(10).setKt(0))
		);

		scene.geometries.add(new Sphere( new Point(0.64,3.5,0.5001),0.5).setEmission(new Color(blue).reduce(5))
				.setMaterial(new Material().setKd(0.2).setKs(0.3).setShininess(1).setKt(0.2))//.setBlurGlass(9,5,1.3))
		);
		scene.geometries.add(new Sphere( new Point(0.64,2.5,0.5001),0.5).setEmission(new Color(0,0,0))
				.setMaterial(new Material().setKd(0.01).setKs(0.2).setShininess(2).setKr(0.6))//.setBlurGlass(10,6,1))
		);

		scene.lights.add(new SpotLight(new Color( white).scale(1), new Point(-11, 4, 3.2),new Vector(1,0,-1.4)));
		//scene.lights.add(new DirectionalLight(new Color(white).reduce(1.4), new Vector(0.3, 0, -1)));

		//scene.lights.add(new SpotLight(new Color(white).reduce(1), new Point(20.43303, -7.37104, 13.77329), new Vector(-20.43, 7.37, -13.77)));
		ImageWriter imageWriter = new ImageWriter("check2", 10000, 10000);
		camera.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(scene)) //
				.renderImage() //
				.writeToImage();

	}
}



