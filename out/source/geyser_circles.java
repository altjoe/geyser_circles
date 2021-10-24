import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class geyser_circles extends PApplet {

int num_orbs = 1000;
Orb[] orbs = new Orb[num_orbs];
int hole_size;
public void setup() {
   hole_size = 15;
   // size(512, 512);
   
   background(255);
   for (int i = 0; i < num_orbs; i++){
      float theta = 2*PI*i/num_orbs;
      Orb orb = new Orb(theta);
      orb.display();
      orbs[i] = orb;
   }
   fill(0);
   ellipse(width/2, height/2, prop(hole_size), prop(hole_size));
}
public void draw() {
   background(255);
   fill(0);
   ellipse(width/2, height/2, prop(hole_size), prop(hole_size));
   for (int i = 0; i < num_orbs; i++){
      orbs[i].move();
      orbs[i].display();
   }
}

class ring {
   
}

class Orb {
   float size;
   float x;
   float y;
   float startx;
   float starty;
   float xs;
   float ys;
   float startxs;
   float startys;
   float radius = pythagorean(width/2, height/2); // edit if needed
   int count = 500;

   public Orb(float theta) {
      size = prop(1 * random(1, 10));
      radius += size / prop(1 / random(1, 50));
      x = width/2 + (radius * cos(theta));
      y = height/2 + (radius * sin(theta));
      startx = x;
      starty = y;
      xs = prop(x - width/2);
      ys = prop(y - height/2);
      startxs = xs;
      startys = ys;
      float random_speed = random(100, 300);
      xs = xs / prop(random_speed);
      ys = ys / prop(random_speed);
   }

   public void display(){
      float dist = distance(x, y, startx, starty);
      // float ratio = (dist + prop(5)) / pythagorean(width/2, height/2);
      float temp_size = size * dist/prop(300);
      noStroke();
      fill(0);
      ellipse(x, y, temp_size, temp_size);
   }
   public void move(){
      x -= xs;
      y -= ys;
      float dist = distance(x, y, startx, starty);
      if (dist > radius) {
         x = startx;
         y = starty;

         if (frameCount > count) {
            xs = 0;
            ys = 0;
         } 
      }
   }
   
}

public float prop(float value){
   return value * width / 512;
}

public float distance(float x1, float y1, float x2, float y2){
   return (float)sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
}

public float pythagorean(float x, float y){
   return sqrt(x*x+y*y);
}

// void fractal(){
//       x -= xs;
//       y -= ys;
//       float distx = x - width/2;
//       float disty = y - height/2;
//       if (abs(distx) > radius) {
//          xs = -xs;
//       }
//       if (abs(disty) > radius) {
//          ys = -ys;
//       }
//    }
  public void settings() {  size(1080, 1080); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "geyser_circles" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
