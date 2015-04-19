package io.github.phantamanta44.emotifier;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;


public class Emotifier {
	public static void main(String[] args) {
		if (args.length != 1)
			System.out.println("Usage: emote <image file>");
		else {
			File inFile = new File(args[0]);
			if (!inFile.exists())
				System.out.println("File does not exist!");
			else {
				try {
					BufferedImage img = ImageIO.read(inFile);
					List<List<Pixel>> imgMap = breakDownImage(img);
					List<List<String>> emoteMap = convertToEmotes(imgMap);
					printMap(emoteMap);
				}
				catch (Throwable th) {
					th.printStackTrace();
				}
			}
		}
	}
	
	public static List<List<Pixel>> breakDownImage(BufferedImage img) {
		List<List<Pixel>> ret = new ArrayList<>();
		int width = img.getWidth();
		int height = img.getHeight();
		for (int y = 0; y < height; y++) {
			List<Pixel> emb = new ArrayList<>();
			for (int x = 0; x < width; x++) {
				int c = img.getRGB(x, y);
				int red = (c & 0x00ff0000) >> 16;
				int green = (c & 0x0000ff00) >> 8;
				int blue = c & 0x000000ff;
				int alpha = (c >> 24) & 0xff;
				emb.add(new Pixel(red, green, blue, x, y, alpha));
			}
			ret.add(emb);
		}
		return ret;
	}
	
	public static List<List<String>> convertToEmotes(List<List<Pixel>> map) {
		List<List<String>> ret = new ArrayList<>();
		for (List<Pixel> row : map) {
			List<String> emb = new ArrayList<>();
			for (Pixel p : row) {
				if (p.a <= 60)
					emb.add("{d3rgps31}");
				else
					emb.add(Emote.closestEmote(p.toColor()));
			}
			ret.add(emb);
		}
		return ret;
	}
	
	public static void printMap(List<List<String>> map) {
		for (List<String> l : map) {
			String line = "";
			for (String s : l) {
				line += s;
			}
			System.out.println(line);
		}
	}
	
	static class Pixel {
		public final int r, g, b, x, y, a;
		public Pixel(int red, int green, int blue, int xLoc, int yLoc, int alpha) {
			r = red;
			g = green;
			b = blue;
			x = xLoc;
			y = yLoc;
			a = alpha;
		}
		public Color toColor() {
			return new Color(r, g, b);
		}
	}
	
	static class Emote {
		public static final Color CHAP = new Color(66, 142, 255);
		public static final Color MARIO = new Color(230, 0, 0);
		public static final Color SCOAT = new Color(122, 195, 250);
		public static final Color CHRIS = new Color(250, 0, 72);
		public static final Color PISTOLPARROT = new Color(255, 0, 4);
		public static final Color FUTURE = new Color(255, 145, 0);
		public static final Color AIDAN = new Color(91, 15, 12);
		public static final Color PNSPI = new Color(54, 0, 8);
		public static final Color SIG = new Color(255, 180, 163);
		public static final Color ROKE = new Color(128, 92, 59);
		public static final Color SHAYNE = new Color(196, 79, 8);
		public static final Color PEAS = new Color(194, 172, 143);
		public static final Color BVO = new Color(0, 0, 0);
		public static final Color WAY2 = new Color(63, 63, 63);
		public static final Color YINSCAPE = new Color(82, 82, 82);
		public static final Color PUTTY = new Color(203, 203, 203);
		public static final Color BLAMED3 = new Color(255, 255, 255);
		
		public final Color col;
		public final String em;
		
		private Emote(Color c, String e) {
			col = c;
			em = e;
		}
		
		public static String closestEmote(Color c) {
			
			Emote curE;
			
			curE = compareColors(c, CHAP, "{chap}", MARIO, "{mario}");
			curE = compareColors(c, curE.col, curE.em, SCOAT, "{scoat}");
			curE = compareColors(c, curE.col, curE.em, CHRIS, "{colonel_chris}");
			curE = compareColors(c, curE.col, curE.em, PISTOLPARROT, "{pistolparrot}");
			curE = compareColors(c, curE.col, curE.em, FUTURE, "{future5}");
			curE = compareColors(c, curE.col, curE.em, AIDAN, "{aidanmcc}");
			curE = compareColors(c, curE.col, curE.em, PNSPI, "{pnspi}");
			curE = compareColors(c, curE.col, curE.em, SIG, "{signatured}");
			curE = compareColors(c, curE.col, curE.em, ROKE, "{roke}");
			curE = compareColors(c, curE.col, curE.em, SHAYNE, "{shayne}");
			curE = compareColors(c, curE.col, curE.em, PEAS, "{greenpeas}");
			curE = compareColors(c, curE.col, curE.em, BVO, "{bvo}");
			curE = compareColors(c, curE.col, curE.em, WAY2, "{way2}");
			curE = compareColors(c, curE.col, curE.em, YINSCAPE, "{yinscape}");
			curE = compareColors(c, curE.col, curE.em, PUTTY, "{putty}");
			curE = compareColors(c, curE.col, curE.em, BLAMED3, "{d3rgps31}");
			
			return curE.em;
			
		}
		
		private static Emote compareColors(Color in, Color a, String aMap, Color b, String bMap) {
			double distA = ColoursJs.distanceTo(in, a);
			double distB = ColoursJs.distanceTo(in, b); 
			if (distA < distB)
				return new Emote(a, aMap);
			else if (distA > distB)
				return new Emote(b, bMap);
			else {
				Random rand = new Random();
				if (rand.nextBoolean())
					return new Emote(a, aMap);
				return new Emote(b, bMap);
			}
		}
	}
}