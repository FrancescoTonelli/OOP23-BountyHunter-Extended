package buontyhunter.Graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Stroke;
import buontyhunter.Core.GameEngine;
import buontyhunter.Common.Point2d;
import buontyhunter.model.GameObject;
import buontyhunter.model.HidableObject;
import buontyhunter.model.RectBoundingBox;
import buontyhunter.model.TileManager;
import buontyhunter.model.World;

public class SwingGraphics implements Graphics {

	private Graphics2D g2;
	private static final Stroke strokeBall = new BasicStroke(4f);
	private static final Stroke strokePick = new BasicStroke(8f);

	private int centerX;
	private int centerY;
	private double ratioX;
	private double ratioY;
	private SceneCamera camera;

	public SwingGraphics(Graphics2D g2, int centerX, int centerY, double ratioX, double ratioY, SceneCamera camera) {
		this.g2 = g2;
		this.centerX = centerX;
		this.centerY = centerY;
		this.ratioX = ratioX;
		this.ratioY = ratioY;
		this.camera = camera;
	}

	private int getXinPixel(Point2d p) {
		return (int) Math.round(p.x * ratioX);
	}

	private int getYinPixel(Point2d p) {
		return (int) Math.round(p.y * ratioY);
	}

	private int getDeltaXinPixel(double dx) {
		return (int) Math.round(dx * ratioX);
	}

	private int getDeltaYinPixel(double dy) {
		return (int) Math.round(dy * ratioY);
	}

	private double getHalfWidth() {
		return GameEngine.WORLD_WIDTH / 2;
	}

	private double getHalfHeight() {
		return GameEngine.WORLD_HEIGHT / 2;
	}

	@Override
	public void drawPlayer(GameObject obj, World w) {
		var x = getXinPixel(camera.getPlayerPoint());
		var y = getYinPixel(camera.getPlayerPoint());
		var height = getDeltaYinPixel(((RectBoundingBox) obj.getBBox()).getHeight());
		var width = getDeltaXinPixel(((RectBoundingBox) obj.getBBox()).getWidth());

		g2.setColor(Color.RED);
		g2.fillRect(x, y, width, height);
	}

	@Override
	public void drawMap(TileManager tileManager, World w) {
		var tiles = tileManager.getTiles();

		var firstX = camera.getTileFirstX();
		var firstY = camera.getTileFirstY();
		var offsetX = camera.getTileOffsetX();
		var offsetY = camera.getTileOffsetY();
		var lastX = camera.getTileLastX();
		var lastY = camera.getTileLastY();

		int i = 0, j = 0;
		for (int y = firstY; y < lastY; y++) {
			i = 0;
			for (int x = firstX; x < lastX; x++) {
				Point2d tilePos = new Point2d(i - offsetX, j - offsetY);
				try {
					var image = tiles.get(y).get(x).getImage().getScaledInstance(getDeltaXinPixel(1),
							getDeltaYinPixel(1), Image.SCALE_DEFAULT);
					g2.drawImage(image, getXinPixel(tilePos),
							getYinPixel(tilePos), null);
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
					System.out.println("Error");
				}

				i++;
			}
			j++;
		}
	}

	public void drawMiniMap(HidableObject miniMap, World w) {
		if (!miniMap.isShow())
			return;
		var tileManager = w.getTileManager();
		var tiles = tileManager.getTiles();

		var firstX = 0;
		var firstY = 0;
		var offsetX = 0;
		var offsetY = 0;
		var lastX = tiles.size();
		var lastY = tiles.get(0).size();

		int i = 0, j = 0;
		for (int y = firstY; y < lastY; y++) {
			i = 0;
			for (int x = firstX; x < lastX; x++) {
				Point2d tilePos = new Point2d(1, 1);
				try {
					g2.setColor(getTileColor(tiles.get(y).get(x).getNumber()));
					g2.fillRect(getXinPixel(tilePos) + x,
							getYinPixel(tilePos) + y, 1, 1);
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
					System.out.println("we're fucked up");
				}

				i++;
			}
			j++;
		}

		var p = w.getPlayer();
		Point2d tilePos = new Point2d(1, 1);
		g2.setColor(Color.RED);
		g2.fillRect(getXinPixel(tilePos) + (int) Math.floor(p.getPos().x),
				getYinPixel(tilePos) + (int) Math.floor(p.getPos().y), 2, 2);
	}

	private Color getTileColor(int number) {
		switch (number) {
			case 0:
				return Color.MAGENTA;
			case 1:
				return Color.GREEN;
			case 2:
				return Color.YELLOW;
			case 3:
				return Color.GRAY;
			case 4:
				return Color.DARK_GRAY;
			case 5:
				return Color.BLUE;
			default:
				return Color.RED;

		}
	}
}
