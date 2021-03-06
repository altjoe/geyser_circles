int num_orbs = 1000;
Orb[] orbs = new Orb[num_orbs];
int hole_size;
void setup() {
   hole_size = 15;
   // size(512, 512);
   size(1080, 1080);
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
void draw() {
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

   void display(){
      float dist = distance(x, y, startx, starty);
      // float ratio = (dist + prop(5)) / pythagorean(width/2, height/2);
      float temp_size = size * dist/prop(300);
      noStroke();
      fill(0);
      ellipse(x, y, temp_size, temp_size);
   }
   void move(){
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

float prop(float value){
   return value * width / 512;
}

float distance(float x1, float y1, float x2, float y2){
   return (float)sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
}

float pythagorean(float x, float y){
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