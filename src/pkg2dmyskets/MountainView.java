/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2dmyskets;
import processing.core.PApplet;
import java.awt.Color;
/**
 *
 * @author USER
 */
public class MountainView extends PApplet{
    int numStars = 100;
    float[] starX = new float[numStars];
    float[] starY = new float[numStars];
    float[] speed = new float[numStars];
    float[] starBrightness; // Menyimpan tingkat kecerahan bintang
    int blinkInterval = 10000; // Interval kedipan (semakin besar nilainya, semakin lambat)
    int lastBlink = 0; 
    int kanan = (int)random(-200,500), kanan1 = kanan - (int) random(-100,500);
    int bawah = (int)random(-300,100), bawah1 = bawah - (int) random(-200,200);
    int shipX = 0; // posisi awal objek di sumbu x
    int shipSpeed = 1;
    float brightness = 255; // Nilai awal kecerahan
    float blinkSpeed = 5; 
    float[] xPositions; // Array untuk menyimpan posisi x bintang
float[] yPositions; // Array untuk menyimpan posisi y bintang
float[] starSizes; // Array untuk menyimpan ukuran bintang
float[] brightnessValues; //
    public void settings() {
        size(1080, 720);
    }

    public void setup() {
        smooth();
        for (int i = 0; i < numStars; i++) {
            starX[i] = random(width); // Posisi acak bintang pada sumbu x
            starY[i] = random(-height, 0); // Posisi awal acak bintang pada sumbu y di luar layar
            speed[i] = random(3, 5); // Kecepatan acak bintang
        }
        starBrightness = new float[10]; // Jumlah bintang
        for (int i = 0; i < starBrightness.length; i++) {
        starBrightness[i] = random(50, 255); // Set kecerahan awal acak untuk setiap bintang
        
  }
  xPositions = new float[numStars];
  yPositions = new float[numStars];
  starSizes = new float[numStars];
  brightnessValues = new float[numStars];

  for (int i = 0; i < numStars; i++) {
    xPositions[i] = random(width);
    yPositions[i] = random(height);
    starSizes[i] = random(1, 3);
    brightnessValues[i] = random(100, 255);
  }
    }

    public void draw() {
        Color myColor = new Color(123, 133, 117);
        drawGradientBackground();
        fill(255);
        drawStars(500);
        drawStarsdrop(5);
       

        drawRandomStars(100);

        drawMountain(-30, 400, 400, 400, myColor);
        
        drawMountain(800, 400, 400, 300, myColor);
        drawMountain(300, 400, 600, 500, myColor);
        drawRandomStars(10);

   
        
        drawMoon(540, 50, 150); // Menambahkan bulan di atas gunung pojok kiri atas

        drawlake(400);
//        drawship(shipX + 90, 400, 400, 75, shipX + 200, 400, shipX + 90, 477, shipX + 90, 400);
        drawship(shipX + 100, 600, 400, 75, shipX + 200, 400, shipX + 90, 477, shipX + 90, 400);
    shipX -= shipSpeed;

    if (shipX < -500) {
        shipX = width; // Reset posisi kapal jika melewati batas layar ke arah kiri
    }
    }
    public void drawGradientBackground() {
        int from = color(0, 0, 0); // Warna hitam
        int to = color(75, 0, 130); // Warna ungu tua

        for (int y = 0; y < height / 2; y++) {
            float inter = map(y, 0, height / 2, 0, 1);
            int gradientColor = lerpColor(from, to, inter);
            stroke(gradientColor);
            line(0, y, width, y);
        }
    }

public void drawMountain(float x, float y, float baseWidth, float height, Color mountainColor) {
  float halfWidth = baseWidth / 2;
  float peakX = x + halfWidth;
  float peakY = y - height+100;

  stroke(0); // Warna stroke hitam
  strokeWeight(2); // Ketebalan stroke 2 piksel
  fill(mountainColor.getRGB());

  beginShape();
  vertex(x, y);
  quadraticVertex(peakX, peakY, x + baseWidth, y);
  endShape(CLOSE);
}
        public void drawMoon(float x, float y, float size) {
        fill(255, 255, 0);
        ellipse(x, y, size, size); // Menggambar bulan
    }

    public void drawStarsdrop(int numStars) {
        fill(255); // Warna putih untuk bintang
        noStroke();

        for (int i = 0; i <= numStars; i++) {
            ellipse(starX[i], starY[i], 2, 2); // Menggambar bintang

            // Menambahkan kecepatan ke posisi vertikal bintang
            starX[i] += speed[i]*0.7;
            starY[i] += speed[i];
            

            // Jika bintang mencapai bawah layar
        // Jika bintang mencapai batas atas gunung
        if (starY[i] <= 300 && starY[i] - speed[i] > 300) {
            starY[i] = random(-100, -10); // Reset posisi bintang di atas layar
            starX[i] = random(width); // Posisi acak bintang pada sumbu x
            speed[i] = random(3, 5); // Kecepatan acak bintang
        }
            // Jika bintang berada di bawah gunung, kembalikan ke atas
            if (starY[i] > 300 && starX[i] <= 300) { // Gunung pertama
                starY[i] = random(-100, -10);
                starX[i] = random(width);
            }
            if (starY[i] > 300 && starX[i] > 300 && starX[i] < 900) { // Gunung kedua
                starY[i] = random(-100, -10);
                starX[i] = random(width);
            }
        }
    }
        public void drawStars(int numStars){
        if (bawah >= 500){
            kanan = (int) random(-500,500);
            bawah = (int) random(-500,-100);
        }
        if (bawah1 >= 400){
            kanan1 = (int) random(-300,500);
            bawah1 = (int) random(-500,-100);
        }
        line(kanan, bawah, kanan - 50, bawah - 50);
        line(kanan + 100, bawah + 25, kanan + 50, bawah - 25);

        line(kanan + 500, bawah - 20, kanan + 450, bawah - 70);
        line(kanan + 600, bawah + 30, kanan + 550, bawah - 20);
        line(kanan + 700, bawah - 10, kanan + 650, bawah - 60);

        line(kanan1 - 250, bawah1 - 70, kanan1 - 350, bawah1 - 170);
        line(kanan1 + 150, bawah1 + 55, kanan1 + 50, bawah1 - 45);
        line(kanan1 + 250, bawah1 - 95, kanan1 + 150, bawah1 - 195);
        line(kanan1 + 450, bawah1 - 150, kanan1 + 350, bawah1 - 250);
        line(kanan1 + 750, bawah1 - 130, kanan1 + 650, bawah1 - 230);
        kanan+=5;kanan1+=5;
        bawah+=5;bawah1+=5;
    }
//        public void BlinkStar(){
//    if (millis() - lastBlink > blinkInterval && random(1) < 0.5) {
//    float x = random(width);
//    float y = random(height);
//    float size = random(1, 3);
//    drawBlinkingStar(x, y, size, 255); // Gunakan nilai maksimal kecerahan untuk bintang baru
//    lastBlink = millis(); // Perbarui waktu terakhir bintang berkedip
//  }
//  
//  // Gambar bintang
//  for (int i = 0; i < starBrightness.length; i++) {
//    float x = random(width);
//    float y = random(height);
//    float size = random(1, 3);
//    drawBlinkingStar(x, y, size, starBrightness[i]);
//    // Mengubah kecerahan secara acak untuk efek berkedip
//    if (millis() - lastBlink > blinkInterval) {
//      if (random(1) < 0.2) {
//        starBrightness[i] = starBrightness[i] == 255 ? 50 : 255;
//      }
//    }
//  }
//        }
void drawBlinkingStar(float x, float y, float size, float brightness) {
  fill(255, brightness); // Kombinasi warna putih dan kecerahan
  noStroke();
  ellipse(x, y, size, size); // Menggambar bintang
}
void drawRandomStars(int numStars) {
for (int i = 0; i < numStars; i++) {
    drawBlinkingStar(xPositions[i], yPositions[i], starSizes[i], brightnessValues[i]);

    brightnessValues[i] += blinkSpeed; // Mengubah kecerahan pada kecepatan yang lebih lambat

    if (brightnessValues[i] > 255 || brightnessValues[i] < 100) {
      blinkSpeed *= -1; // Membalik arah perubahan kecerahan saat mencapai batas atas atau bawah
    }
  }
}
    public void drawlake(int p) {
        int from = color(75, 0, 130); // Warna ungu tua
        int to = color(2, 36, 87); // Warna hitam

        for (int y = p; y < height; y++) {
            float inter = map(y, 0, height + (height / 2), 0, 1);
            int gradientColor = lerpColor(from, to, inter);
            stroke(gradientColor);
            line(0, y, width, y);
        }
    }
    

public void drawship(int x, int y, int panjang, int lebar, int x1, int y1, int x2, int y2, int x3, int y3) {
    fill(20);
    ellipse(x + 200, y + 77, 425, 25); // Lingkaran atas kapal
    fill(176, 176, 176);
    rect(x1, y - 30, panjang / 2, lebar / 2); // Segiempat atas kapal
    fill(117, 115, 115);
    rect(x, y, panjang, lebar); // Segiempat bawah kapal
    noStroke();
    triangle(x + 5, y, x + 5, y + 77, x - 80, y); // Segitiga kapal
}

    public static void main(String[] args) {
        MountainView mySketch = new MountainView();
        PApplet.runSketch(new String[]{"Mountain Lake"}, mySketch);
    }
    
}
