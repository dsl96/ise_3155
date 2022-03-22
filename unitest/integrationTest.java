
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import renderer.Camera;
import geometries.*;
import primitives.*;

import java.util.List;

public class integrationTest {
    Camera camera;
    Intersectable shape;

    /**
     * get camera and shape and return the sum of the Intersections
     * of the ray from the camera and the shape (vp 3*3)
     *
     * @param camera
     * @param shape
     * @return
     */
    int sumIntersections(Camera camera, Intersectable shape) {
        int sum = 0;
        Ray ray;
        List<Point> Intersections;

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                //get the ray from the camera
                ray = camera.constructRay(3, 3, j, i);

                //get the Intersections of this ray
                Intersections = shape.findIntersections(ray);

                //sum the Intersections
                if (Intersections != null)
                    sum += Intersections.size();
            }
        }
        return sum;
    }

    /**
     *cases of camera intersection with sphere
     */
    @Test
    void sphereCameraTest() {

        //sphere in front of the camera   2 intersection
        camera = new Camera(new Point(0, 0, 0), new Vector(0, 0, -1), new Vector(0, 1, 0)).setVPSize(3d, 3d).setVPDistance(1);
        shape = new Sphere(new Point(0, 0, -3), 1d);
        assertEquals(2, sumIntersections(camera, shape), "ERROR sphere in front of the camera  and radius smaller then vp");

        //sphere in front of the camera   18 Intersections
        camera = new Camera(new Point(0, 0, 0.5), new Vector(0, 0, -1), new Vector(0, 1, 0)).setVPSize(3d, 3d).setVPDistance(1);
        shape = new Sphere(new Point(0, 0, -2.5), 2.5);
        assertEquals(18, sumIntersections(camera, shape), "ERROR First test case Sphere r=2.5 ");

       // sphere in front of the camera 10 Intersections
        shape = new Sphere(new Point(0, 0, -2), 2);
        assertEquals(10, sumIntersections(camera, shape), "ERROR First test case Sphere r=2 ");

        //camera inside the sphere 9 Intersections
        camera = new Camera(new Point(0, 0, 0), new Vector(0, 0, -1), new Vector(0, 1, 0)).setVPSize(3d, 3d).setVPDistance(1);
        shape = new Sphere(new Point(0, 0, -1), 2.3);
        assertEquals(9, sumIntersections(camera, shape), "ERROR First test case Sphere r=2.3  ");

        // sphere Behind  the camera 0 Intersections
        shape = new Sphere(new Point(0, 0, 3), 1);
        assertEquals(0, sumIntersections(camera, shape), "ERROR sphere Behind  the camera");
    }

    /**
     *cases of camera intersection with plane
     */
    @Test
    void planeCameraTest() {

        //plan standing in front of vp (9 points)
        camera = new Camera(new Point(0, 0, 0), new Vector(0, 0, -1), new Vector(0, 1, 0)).setVPSize(3d, 3d).setVPDistance(1);
        shape = new Plane(new Point(1,-3.2,-3.5),new Point(1.12,2.3,-3.5),new Point(3.4,12.5,-3.5));
        assertEquals(9, sumIntersections(camera, shape), "ERROR plane standing in front the vp ");

        //plane standing diagonally in front of camera (9 points)
        shape = new Plane(new Point(1,2,-3.5),new Point(1,3,-3),new Point(4,2,-3.5));
        assertEquals(9, sumIntersections(camera, shape), "ERROR plane standing diagonally in front the vp ");

        //plane standing diagonally in front of camera (6 points)
        shape = new Plane(new Point(1,2,-3.5),new Point(1,3,-2.5),new Point(4,2,-3.5));
        assertEquals(6, sumIntersections(camera, shape), "ERROR plane standing diagonally in front the vp (6 points) ");
    }

    /**
     *cases of camera intersection with triangle
     */
    @Test
    void triangleCameraTest() {

        //triangle standing in front of vp (1 point)
        camera = new Camera(new Point(0, 0, 0), new Vector(0, 0, -1), new Vector(0, 1, 0)).setVPSize(3d, 3d).setVPDistance(1);
        shape = new Triangle(new Point(0,1,-2),new Point(1,-1,-2),new Point(-1,-1,-2));
        assertEquals(1, sumIntersections(camera, shape), "ERROR triangle standing in front the vp (1 point) ");

        //triangle standing in front of vp (2 points)
        shape = new Triangle(new Point(0,20,-2),new Point(1,-1,-2),new Point(-1,-1,-2));
        assertEquals(2, sumIntersections(camera, shape), "ERROR triangle standing in front the vp (2 point) ");
    }






}
