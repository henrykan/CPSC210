package ca.ubc.cpsc210.tpd.figures;

import javax.json.JsonObject;
import java.awt.Graphics;
import java.awt.Point;
import java.io.File;

public class Oval extends AbstractFigure {
	// Constructs a oval
	// EFFECTS: oval has zero width and height and is at position start
	public Oval(Point start) {
		super(start);
	}

    @Override
    public boolean contains(Point p) {
        final double TOL = 1.0e-6;
        double halfWidth = width / 2.0;
        double halfHeight = height / 2.0;

        //[NOTE TO CPSC 210 STUDENTS: don't spend ANY time worrying about
        // why this implementation looks the way it does.  The mathematical
        // details of how we determine if an oval contains a point are
        // not important in the context of this course!]

        double diff = sqrDiff(x + halfWidth, p.x) / (halfWidth * halfWidth);
        diff = diff + sqrDiff(y + halfHeight, p.y) / (halfHeight * halfHeight);
        return  diff <= 1.0 + TOL;
    }

	@Override
	public void draw(Graphics g) {
		g.drawOval(x, y, x + width, y + height);
	}

    @Override
    public void read(File f) {
        // stub
    }

    @Override
    public void write(File f) {
        // stub
    }

    // Compute square of difference
	// EFFECTS: returns the square of the difference of num1 and num2
	private double sqrDiff(double num1, double num2) {
		return (num1 - num2) * (num1 - num2);
	}
}
