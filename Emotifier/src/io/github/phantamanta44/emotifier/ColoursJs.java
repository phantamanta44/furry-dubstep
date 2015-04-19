package io.github.phantamanta44.emotifier;

import java.awt.Color;

public class ColoursJs {
	public static double distanceTo(Color a, Color b) {
		return Math.pow( 
                ( a.getRed() - b.getRed() ) *
                ( a.getRed() - b.getRed() ) +
                ( a.getBlue() - b.getBlue() ) *
                ( a.getBlue() - b.getBlue() ) +
                ( a.getGreen() - b.getGreen() ) *
                ( a.getGreen() - b.getGreen() ), 0.5 );
	}
}
