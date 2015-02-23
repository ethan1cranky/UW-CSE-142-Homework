import java.awt.*;
public class Illusion {
	public static void main(String[] args) {
		DrawingPanel panel = new DrawingPanel(500, 400);
		panel.setBackground(Color.GRAY);
		Graphics g = panel.getGraphics();
		drawSubfigure(0, 0, 90, 3, g);
		drawSubfigure(120, 10, 90, 3, g);
		drawSubfigure(250, 50, 80, 5, g);
		drawGrid(10, 120, 100, 10, 2, g);
		drawGrid(350, 20, 40, 5, 3, g);
		drawGrid(230, 160, 50, 5, 4, g);
	}	
	public static void drawSubfigure(int x, int y, int size, int subfigures, Graphics g) {
		g.setColor(Color.RED);
		g.fillOval(x, y, size, size);
		g.setColor(Color.BLACK);
		
		for (int i = 0; i < subfigures; i++) {
			g.drawOval(x + i * (size / subfigures / 2),
					y + i * (size / subfigures / 2), size - i * (size / subfigures), size - i * (size / subfigures));
		}
		g.drawLine(x + size / 2, y, x, y + size / 2);
		g.drawLine(x, y + size / 2, x + size / 2, y + size);
		g.drawLine(x + size / 2, y + size, x + size, y + size / 2);
		g.drawLine(x + size, y + size / 2, x + size / 2, y);
	}	
	public static void drawGrid(int x, int y, int size, int subfigures, int dimensions, Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(x, y, size * dimensions, size * dimensions);
		g.setColor(Color.RED);
		g.drawRect(x, y, size * dimensions, size * dimensions);
		for (int i = 0; i < dimensions; i++) {
			for (int j = 0; j < dimensions; j++) {
				drawSubfigure(x + j * size, y, size, subfigures, g);
			}
			y += size;
		}
	}
}