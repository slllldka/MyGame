package maplestory;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class UI_MiniMap extends JLabel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected int offset = 7;
	
	protected Graphics2D g2 = null;
	
	protected ImageIcon icon_user = Maplestory.images.minimap_user;
	protected ImageIcon icon_shop = Maplestory.images.minimap_shop;
	protected ImageIcon icon_portal = Maplestory.images.minimap_portal;
	
	protected boolean isOpen = false;
	
	public UI_MiniMap() {
		setVisible(false);
	}
	
	//Draw Shrinked Image
	public void DSI(ImageIcon imageicon, int x, int y) {
		g2.drawImage(imageicon.getImage(), x / offset, y / offset, imageicon.getIconWidth() / offset, imageicon.getIconHeight() / offset, this);
	}
	public void DSI(ImageIcon imageicon, int x, int y, int w, int h) {
		g2.drawImage(imageicon.getImage(), x / offset, y / offset, w / offset, h / offset, this);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g2 = (Graphics2D)g;
		
		//BackGround
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
		g2.drawImage(new ImageIcon(getClass().getClassLoader().getResource("BlackOut.0.png")).getImage(), 0, 0, this);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		
		// Draw Footholds
		for (Foothold foothold : Maplestory.current_stage.Foothold_List) {
			foothold.dsiFoothold(g2, offset, this);
		}

		// Draw Fake Footholds
		for (Foothold foothold : Maplestory.current_stage.FakeFoothold_List) {
			foothold.dsiFoothold(g2, offset, this);
		}

		// Draw Walls
		int i = 0;
		for (Wall wall : Maplestory.current_stage.Wall_List) {
			if(i > 1) {
				wall.dsiWall(g2, offset, this);
			}
			i++;
		}
		
		// Draw Ladders
		for (Ladder ladder : Maplestory.current_stage.Ladder_List) {
			ladder.DSILadder(g2, ladder.getX() - 27, ladder.getYstart(), offset, this);
		}
		
		// Draw Portal
		for(Portal portal : Maplestory.current_stage.Portal_List) {
			g2.drawImage(icon_portal.getImage(), portal.xcenter / offset - icon_portal.getIconWidth() / 2
					, portal.y / offset - icon_portal.getIconHeight(), this);
		}
		
		// Draw NPCs
		for(NPC npc : Maplestory.current_stage.NPC_List) {
			if(npc.getType().equals("Shop")) {
				g2.drawImage(icon_shop.getImage(), npc.xcenter / offset - icon_shop.getIconWidth() / 2
						, npc.ybottom / offset - icon_shop.getIconHeight(), this);
			}
		}

		// Draw User
		int player_x = (Maplestory.player.PlayerX + Player.PlayerWidth/2) / offset;
		int player_y = (Maplestory.player.PlayerY - Player.PlayerHeight/2) / offset;
		g2.drawImage(icon_user.getImage(), player_x - icon_user.getIconWidth() / 2, player_y - icon_user.getIconHeight() / 2, this);
		
		paintComponents(g);
	}
	
	public void open() {
		setBounds(0, 0, Maplestory.current_stage.X_Size / offset, Maplestory.current_stage.Y_Size / offset);
		setVisible(true);
		isOpen = true;
	}
	
	public void close() {
		setVisible(false);
		isOpen = false;
	}
}
