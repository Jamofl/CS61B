public class NBody {
    public static double readRadius(String path){
        In in = new In(path);
        int num = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String path){
        In in = new In(path);
		int num = in.readInt();
        double radius = in.readDouble();
        Planet[] planets = new Planet[5];
		/* Keep looking until the file is empty. */
		for(int i = 0; i <= 4; i++) {
            double xp = in.readDouble();
            double yp = in.readDouble();
            double xv = in.readDouble();
            double yv = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            planets[i] = new Planet(xp, yp, xv, yv, m, img);
                        	
        }
        return planets;
    }    
 

    public static void main(String args[]){
        
        // 1
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        
        // 2
        StdDraw.setScale(-radius, radius);
		StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");

        // 3

        // 4
        Planet[] planets = readPlanets(filename);
        for (Planet p : planets){
            p.draw();
        }

        // create animation
        StdDraw.enableDoubleBuffering();

        double[] xForces = new double[5];
        double[] yForces = new double[5];

        //StdAudio.play("audio/2001.mid");
        for(int time = 0; time < T; time++){

            // calculate net x and y for each planet
            for (int i = 0; i < planets.length; i++){
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            // update planets 
            for (int i = 0; i < planets.length; i++){
                planets[i].update(dt, xForces[i], yForces[i]);
            }
            // draw background image
            StdDraw.picture(0, 0, "images/starfield.jpg");
            // draw planets
            for (Planet p : planets){
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);

            time += dt;
        }

        // print final universe
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
}
    }
}