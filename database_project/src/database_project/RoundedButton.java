package database_project;

import javax.swing.*;   
import java.awt.*;

public class RoundedButton extends JButton {
      public RoundedButton() { super(); decorate(); } 
      public RoundedButton(String text) { super(text); decorate(); } 
      public RoundedButton(Action action) { super(action); decorate(); } 
      public RoundedButton(Icon icon) { super(icon); decorate(); } 
      public RoundedButton(String text, Icon icon) { super(text, icon); decorate(); } 
      protected void decorate() { setBorderPainted(false); setOpaque(false); }
      @Override 
      protected void paintComponent(Graphics g) {
         Color c=new Color(0,0,0); 
         Color o=new Color(255,255,255); 
         Font f1 = new Font("����ũ������", Font.PLAIN, 13); 
         
         int width = getWidth(); 
         int height = getHeight(); 
         Graphics2D graphics = (Graphics2D) g; 
         graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
         if (getModel().isArmed()) { graphics.setColor(c.darker()); } 
         else if (getModel().isRollover()) { graphics.setColor(c.brighter()); } 
         else { graphics.setColor(c); } 
         graphics.fillRoundRect(0, 0, width, height, 10, 10); 
         FontMetrics fontMetrics = graphics.getFontMetrics(); 
         Rectangle stringBounds = fontMetrics.getStringBounds(this.getText(), graphics).getBounds(); 
         int textX = (width - stringBounds.width) / 2; 
         int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent(); 
         graphics.setColor(o); 
         graphics.setFont(f1); 
         graphics.drawString(getText(), textX, textY); 
         graphics.dispose(); 
         super.paintComponent(g); 
         }
      }
