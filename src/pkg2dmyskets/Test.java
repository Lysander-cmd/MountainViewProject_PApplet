/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2dmyskets;

import processing.core.PApplet;

/**
 *
 * @author USER
 */
public class Test extends PApplet {
   
    int[] x = new int[100];
    int[] y = new int[100];
    int[] diameter = new int[100];
    int[] speed = new int[100];
    float[] brightness = new float[100];
    boolean[] fade = new boolean[100];

    public void settings() {
        size(800, 600);
    }

    public void setup() {
        for (int i = 0; i < 100; i++) {
            x[i] = (int) random(width);
            y[i] = (int) random(height);
            diameter[i] = (int) random(5, 20);
            speed[i] = (int) random(1, 5);
            brightness[i] = random(100, 255);
            fade[i] = random(1) > 0.5;
        }
    }

    public void draw() {
        background(0);

        for (int i = 0; i < 100; i++) {
            drawStar(x[i], y[i], diameter[i], brightness[i]);
            y[i] += speed[i];

            if (y[i] > height) {
                y[i] = 0;
            }

            if (fade[i]) {
                brightness[i] += 1;
                if (brightness[i] >= 255) {
                    fade[i] = false;
                }
            } else {
                brightness[i] -= 1;
                if (brightness[i] <= 100) {
                    fade[i] = true;
                }
            }
        }
    }

    void drawStar(int x, int y, int d, float brightness) {
        stroke(255, brightness);
        float radius = d / 2;

        float angleStep = TWO_PI / 5;
        float angle = -HALF_PI;

        beginShape();
        for (int i = 0; i < 5; i++) {
            float outerX = x + cos(angle) * radius;
            float outerY = y + sin(angle) * radius;
            vertex(outerX, outerY);

            angle += angleStep;

            float innerX = x + cos(angle) * (radius * 0.5f);
            float innerY = y + sin(angle) * (radius * 0.5f);
            vertex(innerX, innerY);

            angle += angleStep;
        }
        endShape(CLOSE);
    }

  public static void main(String[] args) {
        Test mySketch = new Test();
        PApplet.runSketch(new String[]{"Mountain Lake"}, mySketch);
    }
    
}
