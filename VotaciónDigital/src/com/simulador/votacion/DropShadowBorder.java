package com.simulador.votacion;

import javax.swing.border.AbstractBorder;
import java.awt.*;

public class DropShadowBorder extends AbstractBorder {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int SHADOW_SIZE = 5;

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g.create();
        int shadowAlpha = 60;

        Color shadowColor = new Color(0, 0, 0, shadowAlpha);

        // Dibujar sombras
        g2d.setColor(shadowColor);
        g2d.fillRect(x + SHADOW_SIZE, y + height - SHADOW_SIZE, width - SHADOW_SIZE, SHADOW_SIZE); // Inferior
        g2d.fillRect(x + width - SHADOW_SIZE, y + SHADOW_SIZE, SHADOW_SIZE, height - SHADOW_SIZE); // Derecha

        g2d.dispose();
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(1, 1, SHADOW_SIZE, SHADOW_SIZE);
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        insets.set(1, 1, SHADOW_SIZE, SHADOW_SIZE);
        return insets;
    }
}
