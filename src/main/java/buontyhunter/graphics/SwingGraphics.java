package buontyhunter.graphics;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.List;
import javax.swing.JButton;
import buontyhunter.core.GameEngine;
import buontyhunter.common.ImageType;
import buontyhunter.common.Point2d;
import buontyhunter.model.*;

import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import buontyhunter.model.FighterEntity.MovementState;
import buontyhunter.model.consumables.Consumable;
import buontyhunter.model.merchantClasses.MerchantEntity;
import buontyhunter.model.merchantClasses.MerchantMenu;
import buontyhunter.model.slotMachineClasses.SlotMachineBoard;
import buontyhunter.model.slotMachineClasses.SlotMachineEntity;
import buontyhunter.model.slotMachineClasses.WinCategories;
import buontyhunter.model.weaponClasses.MeleeWeapon;
import buontyhunter.model.weaponClasses.RangedWeapon;
import buontyhunter.model.weaponClasses.Weapon;
import buontyhunter.model.weaponClasses.WeaponType;

import java.util.stream.Collectors;

public class SwingGraphics implements Graphics {

	private Graphics2D g2;
	private double ratioX;
	private double ratioY;
	private SceneCamera camera;
	private SwingAssetProvider assetManager;
	final private ConsumableGraphics consumableGraphics;

	private Font titleFont = new Font("Arial", Font.BOLD, 20);
	private Font paragraphFont = new Font("Arial", Font.PLAIN, 15);

	public SwingGraphics(Graphics2D g2, double ratioX, double ratioY, SceneCamera camera,
			SwingAssetProvider assetManager) {
		this.g2 = g2;
		this.ratioX = ratioX;
		this.ratioY = ratioY;
		this.camera = camera;
		this.assetManager = assetManager;
		consumableGraphics = new ConsumableGraphicsImpl(g2, camera, assetManager);
	}

	private int getXinPixel(Point2d p) {
		return (int) Math.round(p.x * ratioX);
	}

	private int getValueInPixel(double value) {
		return (int) Math.round(value * ratioX);
	}

	private int getYinPixel(Point2d p) {
		return (int) Math.round(p.y * ratioY);
	}

	private Point2d getTilePosInPixel(Point2d p) {
		return new Point2d(getXinPixel(p), getYinPixel(p));
	}

	@Override
	public void drawPlayer(GameObject obj, World w) {
		var x = getXinPixel(camera.getPlayerPoint());
		var y = getYinPixel(camera.getPlayerPoint());

		if (obj instanceof PlayerEntity) {
			switch (((PlayerEntity) obj).getDirection()) {
				case STAND_DOWN:
					g2.drawImage(assetManager.getImage(ImageType.hunterFront), x, y, null);
					break;
				case STAND_UP:
					g2.drawImage(assetManager.getImage(ImageType.hunterBack), x, y, null);
					break;
				case STAND_LEFT:
					g2.drawImage(assetManager.getImage(ImageType.hunterLeft), x, y, null);
					break;
				case STAND_RIGHT:
					g2.drawImage(assetManager.getImage(ImageType.hunterRight), x, y, null);
					break;
				case MOVE_UP:
					if (((PlayerEntity) obj).getMovementState() == MovementState.FIRST) {
						g2.drawImage(assetManager.getImage(ImageType.hunterBack1), x, y, null);
					} else {
						g2.drawImage(assetManager.getImage(ImageType.hunterBack2), x, y, null);
					}
					break;
				case MOVE_DOWN:
					if (((PlayerEntity) obj).getMovementState() == MovementState.FIRST) {
						g2.drawImage(assetManager.getImage(ImageType.hunterFront1), x, y, null);
					} else {
						g2.drawImage(assetManager.getImage(ImageType.hunterFront2), x, y, null);
					}
					break;
				case MOVE_LEFT:
					if (((PlayerEntity) obj).getMovementState() == MovementState.FIRST) {
						g2.drawImage(assetManager.getImage(ImageType.hunterLeft1), x, y, null);
					} else {
						g2.drawImage(assetManager.getImage(ImageType.hunterLeft2), x, y, null);
					}
					break;
				case MOVE_RIGHT:
					if (((PlayerEntity) obj).getMovementState() == MovementState.FIRST) {
						g2.drawImage(assetManager.getImage(ImageType.hunterRight1), x, y, null);
					} else {
						g2.drawImage(assetManager.getImage(ImageType.hunterRight2), x, y, null);
					}
					break;
			}
		}

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
					var image = tiles.get(y).get(x).getImage();
					g2.drawImage(assetManager.getImage(image), getXinPixel(tilePos),
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

	private int getMaxY(List<List<Tile>> tiles) {
		return tiles.stream().mapToInt((list) -> list.size()).max().getAsInt();
	}

	public void drawMiniMap(HidableObject miniMap, World w) {
		if (!miniMap.isShow())
			return;
		var playerSize = 3;
		var tileManager = w.getTileManager();
		var tiles = tileManager.getTiles();

		int mapShowOffSetX = 15;
		int mapShowOffSetY = 15;

		var firstX = 0;
		var firstY = 0;
		final var lastX = tiles.size();
		final var lastY = getMaxY(tiles);

		Point2d tilePos = new Point2d(1, 1);

		int propsX = GameEngine.RESIZATOR.getWINDOW_WIDTH() / (lastX);
		int propsY = GameEngine.RESIZATOR.getWINDOW_HEIGHT() / (lastY);

		g2.drawImage(assetManager.getImage(ImageType.MAPBG), firstX + mapShowOffSetX * 2, firstY + mapShowOffSetY * 2,
				getXinPixel(tilePos) + lastX * propsX + mapShowOffSetX * 2,
				getYinPixel(tilePos) + lastY * propsY + mapShowOffSetY * 2, null);
		for (int x = firstX; x < lastX; x++) {
			for (int y = firstY; y < lastY; y++) {

				try {
					g2.setColor(getTileColor(tiles.get(x).get(y).getType()));
					g2.fillRect((getXinPixel(tilePos) + mapShowOffSetY + y * propsX),
							(getYinPixel(tilePos) + mapShowOffSetX + x * propsY), propsX, propsY);
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
					System.out.println("minimap error");
				}

			}
		}

		var p = w.getPlayer();

		g2.setColor(Color.RED);
		g2.fillRect(getXinPixel(tilePos) + mapShowOffSetX + (int) Math.floor(p.getPos().x) * propsX,
				getYinPixel(tilePos) + mapShowOffSetY + (int) Math.floor(p.getPos().y) * propsY, propsX * playerSize,
				propsY * playerSize);

		for (var enemy : w.getEnemies()) {

			g2.setColor(Color.YELLOW);
			g2.fillRect(getXinPixel(tilePos) + (int) Math.floor(enemy.getPos().x) * propsX + mapShowOffSetX,
					getYinPixel(tilePos) + mapShowOffSetY + (int) Math.floor(enemy.getPos().y) * propsY,
					propsX * playerSize,
					propsY * playerSize);
		}

		var b = w.getWizardBoss();
		if (b.isGpsActive()) {
			g2.setColor(Color.MAGENTA);
			g2.fillRect(getXinPixel(tilePos) + mapShowOffSetX + (int) Math.floor(b.getPos().x) * propsX,
					getYinPixel(tilePos) + mapShowOffSetY + (int) Math.floor(b.getPos().y) * propsY,
					propsX * playerSize,
					propsY * playerSize);
		}

		// g2.setColor(Color.ORANGE);
		// pathStream.forEach((Point2d np) -> g2.fillOval(getXinPixel(tilePos) +
		// mapShowOffSetY + (int) np.x - 2,
		// getYinPixel(tilePos) + mapShowOffSetX + (int) np.y - 2, 5, 5));
	}

	private Color getTileColor(TileType type) {
		switch (type) {
			case earth:
				return new Color(160, 82, 45); // brown
			case grass:
				return Color.GREEN;
			case sand:
				return Color.YELLOW;
			case tree:
				return new Color(1, 50, 32);
			case wall:
				return Color.DARK_GRAY;
			case water:
				return Color.cyan;
			default:
				return Color.RED;

		}
	}

	@Override
	public void drawNavigatorLine(NavigatorLine navigatorLine, World w) {
		// var pathStream = navigatorLine.getPath().stream();

		// g2.setColor(Color.YELLOW);
		// pathStream.filter((Point2d p) -> camera.inScene(p))
		// .forEach((Point2d p) ->
		// g2.fillRect(getXinPixel(camera.getObjectPointInScene(p).get()),
		// getYinPixel(camera.getObjectPointInScene(p).get()), getDeltaXinPixel(0.5),
		// getDeltaYinPixel(0.5)));
	}

	@Override
	public void drawHealthBar(HealthBar healthBar, World w) {

		int healthBarLenght = 200;
		int healthBarWidth = 20;

		g2.setColor(Color.BLACK);
		g2.fillRect((int) healthBar.getPos().x, (int) healthBar.getPos().y, healthBarLenght, healthBarWidth);
		g2.setColor(Color.RED);
		g2.fillRect((int) healthBar.getPos().x + HealthBar.margin / 2,
				(int) healthBar.getPos().y + HealthBar.margin / 2,
				((healthBarLenght - HealthBar.margin) * ((FighterEntity) w.getPlayer()).getHealth())
						/ ((FighterEntity) w.getPlayer()).getMaxHealth(),
				healthBarWidth / 2);
	}

	@Override
	public void drawTeleporter(Teleporter tp, World w) {

		var tpPosInPixel = getTilePosInPixel(camera.getObjectPointInScene(tp.getPos()).get());
		g2.drawImage(assetManager.getImage(ImageType.teleporter), (int) tpPosInPixel.x, (int) tpPosInPixel.y, null);
	}

	@Override
	public void drawQuestPannel(QuestPannel questPannel, World w) {
		if (!questPannel.isShow())
			return;

		g2.setColor(new Color(0, 0, 0, 0.6f));
		g2.fillRect(0, 0, GameEngine.RESIZATOR.getWINDOW_WIDTH(), GameEngine.RESIZATOR.getWINDOW_HEIGHT());

		int minDim = GameEngine.RESIZATOR.getWINDOW_WIDTH() < GameEngine.RESIZATOR.getWINDOW_HEIGHT()
				? GameEngine.RESIZATOR.getWINDOW_WIDTH()
				: GameEngine.RESIZATOR.getWINDOW_HEIGHT();

		int boardDimension = minDim / 5 * 4;
		int x = GameEngine.RESIZATOR.getWINDOW_WIDTH() / 2 - (boardDimension / 2);
		int y = GameEngine.RESIZATOR.getWINDOW_HEIGHT() / 2 - (boardDimension / 2);

		g2.drawImage(assetManager.getImage(ImageType.noticeBoard), x, y, boardDimension, boardDimension, null);

	}

	public void drawQuest(QuestEntity quest, int x, int y, int unit, JButton btn) {
		if (btn.getBounds().isEmpty() || !btn.getBounds().equals(new Rectangle(x, y, unit, unit))) {
			btn.removeAll();

			btn.setOpaque(true);
			btn.setBorderPainted(false);
			btn.setBounds(x, y, unit, unit);
			btn.setLayout(new BorderLayout());

			Image scaled = assetManager.getImage(ImageType.paper).getScaledInstance(btn.getWidth(), btn.getHeight(),
					Image.SCALE_SMOOTH);
			btn.setIcon(new ImageIcon(scaled));

			String content = "<html><b>" + quest.getName() +
					"</b><br><br>" + quest.getDescription() +
					"<br><br>" + quest.getDoblonsReward() + " dobloni</html>";

			JLabel label = new JLabel(content);

			label.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 11));

			btn.add(label, BorderLayout.CENTER);
		}
	}

	public void drawBlacksmithButtons(int index, int x, int y, int unit, JButton btn) {
		btn.setOpaque(true);
		btn.setBackground(new Color(197, 145, 84));
		btn.setBorderPainted(true);
		btn.setBorder(new LineBorder(new Color(130, 91, 49), unit / 10));
		btn.setBounds(x, y, unit, unit);
		btn.setLayout(new BorderLayout());

		Image scaled;

		if (index == 0) {
			scaled = assetManager.getImage(ImageType.hammer).getScaledInstance((int) (btn.getWidth() / (1.5)),
					(int) (btn.getHeight() / (1.5)), Image.SCALE_SMOOTH);
		} else {
			scaled = assetManager.getImage(ImageType.arrow).getScaledInstance((int) (btn.getWidth() / (1.5)),
					(int) (btn.getHeight() / (1.5)), Image.SCALE_SMOOTH);
		}

		btn.setIcon(new ImageIcon(scaled));
	}
	
	public void drawSlotMachineButtons(int x, int y, int unit, JButton btn) {
		btn.setOpaque(false);
		btn.setBackground(new Color(197, 145, 84));
		btn.setBorderPainted(false);
		btn.setBorder(new LineBorder(new Color(130, 91, 49), unit / 10));
		btn.setBounds(x, y, unit, unit);
		btn.setLayout(new BorderLayout());

		Image scaled;

		
		scaled = assetManager.getImage(ImageType.playButton).getScaledInstance((int) (btn.getWidth()),
				(int) (btn.getHeight() / (1.5)), Image.SCALE_SMOOTH);
		

		btn.setIcon(new ImageIcon(scaled));
	}

	public void drawMerchantButtons(int index, int x, int y, int unit, JButton btn){
		btn.setOpaque(true);
		btn.setBackground(new Color(197, 145, 84));
		btn.setBorderPainted(true);
		btn.setBorder(new LineBorder(new Color(130, 91, 49), unit / 10));
		btn.setBounds(x, y, unit, unit);
		btn.setLayout(new BorderLayout());

		Image icon;


		switch (index) {
			case 0:
				icon = assetManager.getImage(ImageType.armourUpgrade).getScaledInstance((int) (btn.getWidth() / (1.5)),
						(int) (btn.getHeight() / (1.5)), Image.SCALE_SMOOTH);
				break;
			case 1:
				icon = assetManager.getImage(ImageType.damageUp).getScaledInstance((int) (btn.getWidth() / (1.5)),
						(int) (btn.getHeight() / (1.5)), Image.SCALE_SMOOTH);
				break;
			case 2:
				icon = assetManager.getImage(ImageType.shurikenBundle).getScaledInstance((int) (btn.getWidth() / (1.5)),
						(int) (btn.getHeight() / (1.5)), Image.SCALE_SMOOTH);
				break;
			default:
				icon = assetManager.getImage(ImageType.hammer).getScaledInstance((int) (btn.getWidth() / (1.5)),
						(int) (btn.getHeight() / (1.5)), Image.SCALE_SMOOTH);
				break;
		}

		btn.setIcon(new ImageIcon(icon));

	}

	public void drawStringUnderPlayer(String s) {
		var playerPosition = camera.getPlayerPoint();
		playerPosition.setY(playerPosition.y + 1.5);
		playerPosition.setX(playerPosition.x - 2);
		var x = getXinPixel(playerPosition);
		var y = getYinPixel(playerPosition);
		g2.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 20));
		g2.setColor(Color.BLACK);
		g2.drawString(s, x, y);
	}

	@Override
	public void drawBlacksmithPanel(BlacksmithPanel blacksmithPanel, World w) {
		if (!blacksmithPanel.isShow())
			return;

		g2.setColor(new Color(0, 0, 0, 0.6f));
		g2.fillRect(0, 0, GameEngine.RESIZATOR.getWINDOW_WIDTH(), GameEngine.RESIZATOR.getWINDOW_HEIGHT());

		int minDim = GameEngine.RESIZATOR.getWINDOW_WIDTH() < GameEngine.RESIZATOR.getWINDOW_HEIGHT()
				? GameEngine.RESIZATOR.getWINDOW_WIDTH()
				: GameEngine.RESIZATOR.getWINDOW_HEIGHT();

		int boardDimension = minDim / 5 * 3;
		int x = GameEngine.RESIZATOR.getWINDOW_WIDTH() / 2 - (boardDimension / 2);
		int y = GameEngine.RESIZATOR.getWINDOW_HEIGHT() / 2 - (boardDimension / 2);

		g2.drawImage(assetManager.getImage(ImageType.blacksmith), x, y, boardDimension, boardDimension, null);

	}

	@Override
	public void drawQuestJournal(World w) {
		int width;
		int height;
		width = GameEngine.RESIZATOR.getWINDOW_WIDTH();
		height = GameEngine.RESIZATOR.getWINDOW_HEIGHT();

		g2.setColor(new Color(0, 0, 0, 0.6f));
		g2.fillRect(0, 0, width, height);
		g2.setColor(Color.WHITE);
		g2.drawRoundRect(width / 12, height / 12, 5 * width / 6, 5 * height / 6, 36, 36);

		var quests = ((PlayerEntity) w.getPlayer()).getQuests();

		int singleQuestHeight = 150;

		g2.setFont(titleFont);
		g2.drawString("Registro Missioni", width / 2 - 75, height / 24);

		quests.forEach((Quest q) -> {
			int indexOfQuest = quests.indexOf(q);
			g2.setFont(titleFont);
			g2.drawString(q.getName(), width / 12 + 10, height / 12 + 20 + singleQuestHeight * indexOfQuest);
			g2.setFont(paragraphFont);
			g2.drawString(q.getDescription(), width / 12 + 10, height / 12 + 50 + singleQuestHeight * indexOfQuest);
			g2.setFont(titleFont);
			g2.drawString(q.getDoblonsReward() + " dobloni", width / 12 + 10,
					height / 12 + 80 + singleQuestHeight * indexOfQuest);
			g2.setFont(paragraphFont);
			g2.setFont(titleFont);
			g2.drawString("Nemici uccisi: " + q.getnTargetActuallyKilled(), width / 12 + 10,
					height / 12 + 110 + singleQuestHeight * indexOfQuest);
		});
	}

	private void outWeapon(Point2d pos, int width, int height) {
		g2.setColor(Color.blue);
		g2.fillRect(getXinPixel(pos), getYinPixel(pos), width, height);
	}

	@Override
	public void drawWeapon(FighterEntity fe) {

		if (fe.getWeapon() instanceof RangedWeapon) {
			drawBullet(((RangedWeapon) fe.getWeapon()));
		} else {
			var point = camera.getObjectPointInScene(((RectBoundingBox) fe.getDamagingArea().getBBox()).getPoint2d());

			if (point.isPresent()) {
				outWeapon(point.get(), getValueInPixel(((RectBoundingBox) fe.getWeapon().getHitbox()).getWidth()),
						getValueInPixel(((RectBoundingBox) fe.getWeapon().getHitbox()).getHeight()));
			}

		}
	}

	@Override
	public void drawBullet(RangedWeapon w) {
		if (w.getHitbox() != null) {
			var point = camera.getObjectPointInScene(w.getHitbox().getPoint2d());

			if(w.getBullet()!=null){

				if (point.isPresent()) {
	
					if(w.getWeaponType()==WeaponType.BOW){
						
						switch (w.getBullet().getDirection()) {
							case STAND_UP: {
								g2.drawImage(assetManager.getImage(ImageType.arrowUp), getXinPixel(point.get()), getYinPixel(point.get()), null);
								
								break;
							}
							case STAND_DOWN: {
								g2.drawImage(assetManager.getImage(ImageType.arrowDown), getXinPixel(point.get()), getYinPixel(point.get()), null);
		
								break;
							}
							case STAND_LEFT: {
								g2.drawImage(assetManager.getImage(ImageType.arrowLeft), getXinPixel(point.get()), getYinPixel(point.get()), null);
		
								break;
							}
							case STAND_RIGHT: {
								g2.drawImage(assetManager.getImage(ImageType.arrowRight), getXinPixel(point.get()), getYinPixel(point.get()), null);
		
								break;
							}
							default:
								break;
						}
					}else if(w.getWeaponType()==WeaponType.SHURIKENS){
	
						if(w.getBullet().getZigZag()==0){
							g2.drawImage(assetManager.getImage(ImageType.shuriken), getXinPixel(point.get()), getYinPixel(point.get()), null);
						}else{
							g2.drawImage(assetManager.getImage(ImageType.shurikenRotated), getXinPixel(point.get()), getYinPixel(point.get()), null);
	
						}
	
					}else{
						g2.setColor(Color.RED);
						g2.fillRect(getXinPixel(point.get()), getYinPixel(point.get()),
								getValueInPixel(w.getHitbox().getWidth()),
								getValueInPixel(w.getHitbox().getHeight()));
	
					}
				}
			}
		}
	}

	public void drawEnemy(GameObject obj, World w) {
		var point = camera.getObjectPointInScene(obj.getPos());
		if (point.isPresent()) {
			if (obj instanceof EnemyEntity) {
				g2.drawString(((EnemyEntity) obj).getEnemyIdentifier() + "", getXinPixel(point.get()),
						getYinPixel(point.get()));
				switch (((EnemyEntity) obj).getEnemyType()) {
					case BOW:
						this.drawSkelly((EnemyEntity) obj, w, getXinPixel(point.get()), getYinPixel(point.get()));
						break;
					case THROW_PUNCHES:
						this.drawZombie((EnemyEntity) obj, w, getXinPixel(point.get()), getYinPixel(point.get()));
						break;
					case SWORD:
						this.drawKnight((EnemyEntity) obj, w, getXinPixel(point.get()), getYinPixel(point.get()));
						break;
					default:

						break;
				}
			}
		}
	}

	private void drawZombie(EnemyEntity zombie, World w, int x, int y) {
		switch (zombie.getDirection()) {
			case STAND_DOWN:
				g2.drawImage(assetManager.getImage(ImageType.zombieFront), x, y, null);
				break;
			case STAND_UP:
				g2.drawImage(assetManager.getImage(ImageType.zombieBack), x, y, null);
				break;
			case STAND_LEFT:
				g2.drawImage(assetManager.getImage(ImageType.zombieLeft), x, y, null);
				break;
			case STAND_RIGHT:
				g2.drawImage(assetManager.getImage(ImageType.zombieRight), x, y, null);
				break;
			case MOVE_UP:
				if (zombie.getMovementState() == MovementState.FIRST) {
					g2.drawImage(assetManager.getImage(ImageType.zombieBack1), x, y, null);
				} else {
					g2.drawImage(assetManager.getImage(ImageType.zombieBack2), x, y, null);
				}
				break;
			case MOVE_DOWN:
				if (zombie.getMovementState() == MovementState.FIRST) {
					g2.drawImage(assetManager.getImage(ImageType.zombieFront1), x, y, null);
				} else {
					g2.drawImage(assetManager.getImage(ImageType.zombieFront2), x, y, null);
				}
				break;
			case MOVE_LEFT:
				if (zombie.getMovementState() == MovementState.FIRST) {
					g2.drawImage(assetManager.getImage(ImageType.zombieLeft1), x, y, null);
				} else {
					g2.drawImage(assetManager.getImage(ImageType.zombieLeft2), x, y, null);
				}
				break;
			case MOVE_RIGHT:
				if (zombie.getMovementState() == MovementState.FIRST) {
					g2.drawImage(assetManager.getImage(ImageType.zombieRight1), x, y, null);
				} else {
					g2.drawImage(assetManager.getImage(ImageType.zombieRight2), x, y, null);
				}
				break;
		}
	}

	private void drawKnight(EnemyEntity knight, World w, int x, int y) {
		switch (knight.getDirection()) {
			case STAND_DOWN:
				g2.drawImage(assetManager.getImage(ImageType.knightFront), x, y, null);
				break;
			case STAND_UP:
				g2.drawImage(assetManager.getImage(ImageType.knightBack), x, y, null);
				break;
			case STAND_LEFT:
				g2.drawImage(assetManager.getImage(ImageType.knightLeft), x, y, null);
				break;
			case STAND_RIGHT:
				g2.drawImage(assetManager.getImage(ImageType.knightRight), x, y, null);
				break;
			case MOVE_UP:
				if (knight.getMovementState() == MovementState.FIRST) {
					g2.drawImage(assetManager.getImage(ImageType.knightBack1), x, y, null);
				} else {
					g2.drawImage(assetManager.getImage(ImageType.knightBack2), x, y, null);
				}
				break;
			case MOVE_DOWN:
				if (knight.getMovementState() == MovementState.FIRST) {
					g2.drawImage(assetManager.getImage(ImageType.knightFront1), x, y, null);
				} else {
					g2.drawImage(assetManager.getImage(ImageType.knightFront2), x, y, null);
				}
				break;
			case MOVE_LEFT:
				if (knight.getMovementState() == MovementState.FIRST) {
					g2.drawImage(assetManager.getImage(ImageType.knightLeft1), x, y, null);
				} else {
					g2.drawImage(assetManager.getImage(ImageType.knightLeft2), x, y, null);
				}
				break;
			case MOVE_RIGHT:
				if (knight.getMovementState() == MovementState.FIRST) {
					g2.drawImage(assetManager.getImage(ImageType.knightRight1), x, y, null);
				} else {
					g2.drawImage(assetManager.getImage(ImageType.knightRight2), x, y, null);
				}
				break;
		}
	}

	private void drawSkelly(EnemyEntity skelly, World w, int x, int y) {
		switch (skelly.getDirection()) {
			case STAND_DOWN:
				g2.drawImage(assetManager.getImage(ImageType.skellyFront), x, y, null);
				break;
			case STAND_UP:
				g2.drawImage(assetManager.getImage(ImageType.skellyBack), x, y, null);
				break;
			case STAND_LEFT:
				g2.drawImage(assetManager.getImage(ImageType.skellyLeft), x, y, null);
				break;
			case STAND_RIGHT:
				g2.drawImage(assetManager.getImage(ImageType.skellyRight), x, y, null);
				break;
			case MOVE_UP:
				if (skelly.getMovementState() == MovementState.FIRST) {
					g2.drawImage(assetManager.getImage(ImageType.skellyBack1), x, y, null);
				} else {
					g2.drawImage(assetManager.getImage(ImageType.skellyBack2), x, y, null);
				}
				break;
			case MOVE_DOWN:
				if (skelly.getMovementState() == MovementState.FIRST) {
					g2.drawImage(assetManager.getImage(ImageType.skellyFront1), x, y, null);
				} else {
					g2.drawImage(assetManager.getImage(ImageType.skellyFront2), x, y, null);
				}
				break;
			case MOVE_LEFT:
				if (skelly.getMovementState() == MovementState.FIRST) {
					g2.drawImage(assetManager.getImage(ImageType.skellyLeft1), x, y, null);
				} else {
					g2.drawImage(assetManager.getImage(ImageType.skellyLeft2), x, y, null);
				}
				break;
			case MOVE_RIGHT:
				if (skelly.getMovementState() == MovementState.FIRST) {
					g2.drawImage(assetManager.getImage(ImageType.skellyRight1), x, y, null);
				} else {
					g2.drawImage(assetManager.getImage(ImageType.skellyRight2), x, y, null);
				}
				break;
		}
	}

	@Override
	public void drawProgressBar(LoadingBar loadingBar, World w) {
		if (loadingBar.loadingIsStarted()) {
			var currentLoaded = loadingBar.getCurrentLoaded();
			var loadingTime = loadingBar.getLoadingTime();
			var frame = GameEngine.RESIZATOR.getWINDOW_WIDTH();
			var g2 = this.g2;
			if (currentLoaded < loadingTime) {
				g2.setColor(Color.BLACK);
				g2.fillRect(0, frame - 120, frame, 40);
				g2.setColor(Color.WHITE);
				g2.fillRect(10, frame - 110, currentLoaded * frame / loadingTime - 30, 20);
			} else {
				g2.setColor(Color.BLACK);
				g2.fillRect(0, frame - 120, frame, 40);
				g2.setColor(Color.WHITE);
				g2.fillRect(10, frame - 110, frame - 100, 20);
			}
		}
	}

	public void drawWizardBoss(WizardBossEntity boss, World w) {
		if (boss.getHealth() <= 0)
			return;
		var point = camera.getObjectPointInScene(boss.getPos());

		if (point.isPresent()) {

			int x = getXinPixel(point.get());
			int y = getYinPixel(point.get());

			switch (boss.getDirection()) {
				case STAND_DOWN:
					g2.drawImage(assetManager.getImage(ImageType.wizardFront), x, y, null);
					break;
				case STAND_UP:
					g2.drawImage(assetManager.getImage(ImageType.wizardBack), x, y, null);
					break;
				case STAND_LEFT:
					g2.drawImage(assetManager.getImage(ImageType.wizardLeft), x, y, null);
					break;
				case STAND_RIGHT:
					g2.drawImage(assetManager.getImage(ImageType.wizardRight), x, y, null);
					break;
				case MOVE_UP:
					if (boss.getMovementState() == MovementState.FIRST) {
						g2.drawImage(assetManager.getImage(ImageType.wizardBack1), x, y, null);
					} else {
						g2.drawImage(assetManager.getImage(ImageType.wizardBack2), x, y, null);
					}
					break;
				case MOVE_DOWN:
					if (boss.getMovementState() == MovementState.FIRST) {
						g2.drawImage(assetManager.getImage(ImageType.wizardFront1), x, y, null);
					} else {
						g2.drawImage(assetManager.getImage(ImageType.wizardFront2), x, y, null);
					}
					break;
				case MOVE_LEFT:
					if (boss.getMovementState() == MovementState.FIRST) {
						g2.drawImage(assetManager.getImage(ImageType.wizardLeft1), x, y, null);
					} else {
						g2.drawImage(assetManager.getImage(ImageType.wizardLeft2), x, y, null);
					}
					break;
				case MOVE_RIGHT:
					if (boss.getMovementState() == MovementState.FIRST) {
						g2.drawImage(assetManager.getImage(ImageType.wizardRight1), x, y, null);
					} else {
						g2.drawImage(assetManager.getImage(ImageType.wizardRight2), x, y, null);
					}
					break;
			}
		}
	}

	@Override
	public void drawInventory(InventoryObject inventory, World w) {
		g2.setColor(new Color(0, 0, 0, 0.6f));
		g2.fillRect(0, 0, GameEngine.RESIZATOR.getWINDOW_WIDTH(), GameEngine.RESIZATOR.getWINDOW_HEIGHT());

		var player = (PlayerEntity) w.getPlayer();

		player.getWeapons().forEach((weapon) -> {
			drawBlacksmithButtons(1, 100, 100, 100, new JButton());
		});
	}

	public void drawInventoryWeapon(Weapon w, int x, int y, JButton btn) {

		int btnDimension = 100;

		btn.setOpaque(true);
		btn.setBackground(new Color(197, 145, 84));
		btn.setBorderPainted(true);
		btn.setBorder(new LineBorder(new Color(130, 91, 49), btnDimension / 10));
		btn.setBounds(x, y, btnDimension, btnDimension);
		btn.setLayout(new BorderLayout());

		Image scaled;

		switch (w.getWeaponType()) {
			case SWORD:
				scaled = assetManager.getImage(ImageType.sword).getScaledInstance((int) (btn.getWidth() / (1.5)),
						(int) (btn.getHeight() / (1.5)), Image.SCALE_SMOOTH);
				break;
			case BRASSKNUCKLES:
				scaled = assetManager.getImage(ImageType.brassKnucles).getScaledInstance((int) (btn.getWidth() / (1.5)),
						(int) (btn.getHeight() / (1.5)), Image.SCALE_SMOOTH);
				break;
			case BOW:
				scaled = assetManager.getImage(ImageType.bow).getScaledInstance((int) (btn.getWidth() / (1.5)),
						(int) (btn.getHeight() / (1.5)), Image.SCALE_SMOOTH);
				break;
			case SHURIKENS:
				scaled = assetManager.getImage(ImageType.shurikenBundle).getScaledInstance((int) (btn.getWidth() / (1.5)),
						(int) (btn.getHeight() / (1.5)), Image.SCALE_SMOOTH);
				break;
			default:
				scaled = assetManager.getImage(ImageType.sword).getScaledInstance((int) (btn.getWidth() / (1.5)),
						(int) (btn.getHeight() / (1.5)), Image.SCALE_SMOOTH);
				break;
		}

		btn.setIcon(new ImageIcon(scaled));
	}

	public void drawWeaponIcon(Weapon weapon, int x, int y, int dimension) {
		if (weapon.getWeaponType() == WeaponType.SWORD) {
			g2.drawImage(assetManager.getImage(ImageType.sword), x, y, dimension, dimension, null);
		} else if (weapon.getWeaponType() == WeaponType.BRASSKNUCKLES) {
			g2.drawImage(assetManager.getImage(ImageType.brassKnucles), x, y, dimension, dimension, null);
		} else if (weapon.getWeaponType() == WeaponType.BOW) {
			g2.drawImage(assetManager.getImage(ImageType.bow), x, y, dimension, dimension, null);
		}else if (weapon.getWeaponType() == WeaponType.SHURIKENS) {
			g2.drawImage(assetManager.getImage(ImageType.shurikenBundle), x, y, dimension, dimension, null);
		}
	}

	@Override
	public void drawDurabilityBar(Weapon weapon, int x, int y) {
		if (weapon instanceof MeleeWeapon) {
			int barLenght = 150;
			int barWidth = 20;

			g2.setColor(Color.BLACK);
			g2.fillRect(x, y, barLenght, barWidth);
			g2.setColor(Color.GREEN);
			g2.fillRect(x + 5, y + 5,
					((MeleeWeapon) weapon).getDurability() * (barLenght - 10)
							/ ((MeleeWeapon) weapon).getMaxDurability(),
					barWidth - 10);
		}
	}

	@Override
	public void drawConsumable(Consumable consumable) {
		if (camera.getObjectPointInScene(consumable.getPos()).isPresent()) {
			consumableGraphics.drawConsumable(consumable,
					getXinPixel(camera.getObjectPointInScene(consumable.getPos()).get()),
					getYinPixel(camera.getObjectPointInScene(consumable.getPos()).get()));
		}
	}

	@Override
	public void drawPongIcon(PongEntity pong, World w) {
		if (w.getInterractableAreas().stream().filter(i -> i.getPanel().isShow()).collect(Collectors.toList()).isEmpty()
				&&
				!w.getInventory().isShow() && !w.getQuestJournal().isShow()) {
			g2.drawImage(assetManager.getImage(ImageType.pongIcon), getXinPixel(pong.getPos()) + 20,
					getYinPixel(pong.getPos()) + 20, null);
		}
	}

	@Override
	public void drawPongPanel(PongPanel panel) {
		if (!panel.isShow())
			return;

		g2.setColor(new Color(0, 0, 0, 0.6f));
		g2.fillRect(0, 0, GameEngine.RESIZATOR.getWINDOW_WIDTH(), GameEngine.RESIZATOR.getWINDOW_HEIGHT());

		double minDim = GameEngine.RESIZATOR.getWINDOW_WIDTH() < GameEngine.RESIZATOR.getWINDOW_HEIGHT()
				? GameEngine.RESIZATOR.getWINDOW_WIDTH()
				: GameEngine.RESIZATOR.getWINDOW_HEIGHT();

		double boardWidth = minDim / 5 * 4;
		double boardHeight = boardWidth / 4 * 3; // 4:3 dimension, like old TVs

		double widthUnit = boardWidth / (double) panel.getBoardWidthSegments();
		double heightUnit = boardHeight / (double) panel.getBoardHeightSegments();

		double x = GameEngine.RESIZATOR.getWINDOW_WIDTH() / 2 - (boardWidth / 2);
		double y = GameEngine.RESIZATOR.getWINDOW_HEIGHT() / 2 - (boardHeight / 2);

		g2.setColor(Color.BLACK);
		g2.fill(new Rectangle2D.Double(x, y, boardWidth, boardHeight));

		g2.setColor(Color.GRAY);
		g2.fill(new Rectangle2D.Double(pongConvertCoordinate((double) panel.getBoardWidthSegments() / 2, x, widthUnit),
				pongConvertCoordinate(0, y, heightUnit),
				widthUnit, boardHeight));
		g2.fill(new Rectangle2D.Double(pongConvertCoordinate(0, x, widthUnit),
				pongConvertCoordinate(0, y, heightUnit),
				widthUnit, boardHeight));
		g2.fill(new Rectangle2D.Double(pongConvertCoordinate((double) panel.getBoardWidthSegments() - 1, x, widthUnit),
				pongConvertCoordinate(0, y, heightUnit),
				widthUnit, boardHeight));

		g2.setColor(Color.WHITE);
		g2.fill(new Rectangle2D.Double(pongConvertCoordinate(panel.getBall().getPosition().x, x, widthUnit),
				pongConvertCoordinate(panel.getBall().getPosition().y, y, heightUnit),
				panel.getBall().getWidth() * widthUnit, panel.getBall().getHeight() * heightUnit));
		g2.fill(new Rectangle2D.Double(pongConvertCoordinate(panel.getPlayerPaddle().getPosition().x, x, widthUnit),
				pongConvertCoordinate(panel.getPlayerPaddle().getPosition().y, y, heightUnit),
				panel.getPlayerPaddle().getWidth() * widthUnit, panel.getPlayerPaddle().getHeight() * heightUnit));
		g2.fill(new Rectangle2D.Double(pongConvertCoordinate(panel.getEnemyPaddle().getPosition().x, x, widthUnit),
				pongConvertCoordinate(panel.getEnemyPaddle().getPosition().y, y, heightUnit),
				panel.getEnemyPaddle().getWidth() * widthUnit, panel.getEnemyPaddle().getHeight() * heightUnit));

	}

	/**
	 * Converts PongPanel coordinates to fit the game panel
	 * 
	 * @param x      the coordinate to adjust
	 * @param boardX the coordinate of the pong board
	 * @param unitX  the unit of a cell on the pong grid
	 * @return the adjusted coordinate
	 */
	private double pongConvertCoordinate(double x, double boardX, double unitX) {
		return boardX + (x * unitX);
	}

	public void drawSlotMachine(SlotMachineEntity slot, World w) {
		if (w.getInterractableAreas().stream().filter(i -> i.getPanel().isShow()).collect(Collectors.toList()).isEmpty() &&
				!w.getInventory().isShow() && !w.getQuestJournal().isShow()) {

			g2.drawImage(assetManager.getImage(ImageType.slotMachine), getXinPixel(slot.getPos()) + 20,
					getYinPixel(slot.getPos()) + 20, null);
		}
	}

	public void drawSlotMachineBoard(SlotMachineBoard board,  World w){
		if (!board.isShow()){
			return;
		}

		ImageType[][] imgList = board.getTileImages();
		
		int minDim = GameEngine.RESIZATOR.getWINDOW_WIDTH() < GameEngine.RESIZATOR.getWINDOW_HEIGHT()
		? GameEngine.RESIZATOR.getWINDOW_WIDTH()
		: GameEngine.RESIZATOR.getWINDOW_HEIGHT();
		
		int boardDimension = minDim / 5 * 3;
		int x = GameEngine.RESIZATOR.getWINDOW_WIDTH() / 2 - (boardDimension / 2);
		int y = GameEngine.RESIZATOR.getWINDOW_HEIGHT() / 2 - (boardDimension / 2);
		int offset = (int)(boardDimension/7.5);
		
		g2.setColor(new Color(0, 0, 0, 0.6f));
		g2.fillRect(0, 0, GameEngine.RESIZATOR.getWINDOW_WIDTH(), GameEngine.RESIZATOR.getWINDOW_HEIGHT());


		g2.drawImage(assetManager.getImage(ImageType.slotMachineBackground), x, y, boardDimension, boardDimension, null);

		for(int i=0; i < imgList.length;i++){
            for(int j=0; j < imgList[i].length;j++){
				g2.drawImage(assetManager.getImage(imgList[i][j]),(int)(x+offset+j*boardDimension*0.23),(int)(y+offset+i*boardDimension*0.28),90,90, null);
		
			}
		}

		if(board.isjackpotWon()){
			g2.drawImage(assetManager.getImage(ImageType.jackpot), x, 0, boardDimension, boardDimension/3, null);
		}
		else if(board.isResultDisplaying()&&board.currentWinCategories()!=WinCategories.Lose){
			g2.drawImage(assetManager.getImage(ImageType.win), x, 0, boardDimension, boardDimension/3, null);
		}


	}

	public void drawMerchant(MerchantEntity merchant, World w) {
		if (w.getInterractableAreas().stream().filter(i -> i.getPanel().isShow()).collect(Collectors.toList()).isEmpty() &&
				!w.getInventory().isShow() && !w.getQuestJournal().isShow()) {

			g2.drawImage(assetManager.getImage(ImageType.merchant), getXinPixel(merchant.getPos()) + 20,
					getYinPixel(merchant.getPos()) + 20, null);
		}
	}

	public void drawMerchantMenu(MerchantMenu menu, World w){
		if (!menu.isShow()){
			return;
		}

		g2.setColor(new Color(0, 0, 0, 0.6f));
		g2.fillRect(0, 0, GameEngine.RESIZATOR.getWINDOW_WIDTH(), GameEngine.RESIZATOR.getWINDOW_HEIGHT());

		int minDim = GameEngine.RESIZATOR.getWINDOW_WIDTH() < GameEngine.RESIZATOR.getWINDOW_HEIGHT()
				? GameEngine.RESIZATOR.getWINDOW_WIDTH()
				: GameEngine.RESIZATOR.getWINDOW_HEIGHT();

		int boardDimension = (int)(minDim*0.8);
		int x = GameEngine.RESIZATOR.getWINDOW_WIDTH() / 2 - (boardDimension / 2);
		int y = GameEngine.RESIZATOR.getWINDOW_HEIGHT() / 2 - (boardDimension / 2);

		g2.drawImage(assetManager.getImage(ImageType.merchantBackground), x, y, boardDimension, boardDimension, null);

	}

}
