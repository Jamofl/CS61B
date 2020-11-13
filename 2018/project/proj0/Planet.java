import sun.misc.Contended;

public class Planet{

    // all member should be public and non-static

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    static final double G = 6.67e-11; // constant value G

    public Planet(double xP, double yP, double xV, double yV, double m, String img){
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }
    
    public Planet(Planet p){
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    /** return the distance between two planet */
    public double calcDistance(Planet p){
        return Math.sqrt(Math.pow((this.xxPos - p.xxPos), 2) + Math.pow((this.yyPos - p.yyPos), 2));
        }

    public double calcForceExertedBy(Planet p){
        return (G * this.mass * p.mass) / Math.pow(this.calcDistance(p), 2);
    }

    public double calcForceExertedByX(Planet p){
        double dx = p.xxPos - this.xxPos;
        return (this.calcForceExertedBy(p) * dx) / this.calcDistance(p);
    }

    public double calcForceExertedByY(Planet p){
        double dy = p.yyPos - this.yyPos;
        return (this.calcForceExertedBy(p) * dy) / this.calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] planets){
        double forceX = 0.0;
        for(Planet p : planets){
            if (p.equals(this)){
                continue;
            }
            else{
                forceX += this.calcForceExertedByX(p);
            }
        }
        return forceX;
    }

    public double calcNetForceExertedByY(Planet[] planets){
        double forceY = 0.0;
        for(Planet p : planets){// same as::: for(int i = 0; i <= planets.length; i++)
            if (p.equals(this)){
                continue;
            }
            else{
                forceY += this.calcForceExertedByY(p);
            }
        }
        return forceY;
    }

    public void update(double dt, double fx, double fy){
        double ax = fx / this.mass;
        double ay = fy / this.mass;
        this.xxVel += dt * ax;
        this.yyVel += dt * ay;
        this.xxPos += dt * this.xxVel;
        this.yyPos += dt * this.yyVel;
    }

    public void draw(){
        StdDraw.picture(xxPos, yyPos, "images/" + this.imgFileName);
    }


    





}