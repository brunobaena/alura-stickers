import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradorSticker {

	public void criar(InputStream imagem, String filename) throws IOException {
		BufferedImage imagemOriginal = ImageIO.read( imagem );

		int largura = imagemOriginal.getWidth();
		int altura = imagemOriginal.getHeight();
		int novaAltura = altura + 200;

		var novaImagem = new BufferedImage( largura, novaAltura, Transparency.TRANSLUCENT );
		var caneta = (Graphics2D) novaImagem.getGraphics();

		caneta.drawImage( imagemOriginal, 0, 0, null );

		var font = new Font( Font.SANS_SERIF, Font.BOLD, 72 );
		escreverTexto( caneta, "Legal!!", largura, altura, font, Color.YELLOW );

		ImageIO.write( novaImagem, "png", new File( filename ) );
	}

	public void escreverTexto(Graphics caneta, String text, int largura, int altura, Font font, Color color) {
		FontMetrics metrics = caneta.getFontMetrics( font );

		int x = ( largura - metrics.stringWidth( text ) ) / 2;
		int y = altura + ( ( 200 - metrics.getHeight() ) / 2 ) + metrics.getAscent();

		caneta.setFont( font );

		caneta.setColor( Color.BLACK );
		caneta.drawString( text, x, y );

		caneta.setColor( color );
		caneta.drawString( text, x - 3, y - 3 );
	}
}
