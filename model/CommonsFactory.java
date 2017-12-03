package model;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfInt4;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

public class CommonsFactory {
	/**
	 * Methode utilisee pour construire la matrice descriptif d'une image
	 * @param file : name of the file to import
	 * @return Multiple dimension matrix element that contain the information of the image
	 */
	public static Mat readImage( String file ) {
		File f = new File( file );
		Mat m = Highgui.imread( f.getAbsolutePath() );
		return m;
	}
	/**
	 * Method called to print into a new window the content of the image
	 * @param window_title : This string will be printed as the name of the window
	 * @param img : Matrix that contain image information
	 */
	public static void imShow( String window_title, Mat img ) {
		MatOfByte mat_of_byte = new MatOfByte();
		Highgui.imencode( ".png", img, mat_of_byte );
		byte[] byte_array = mat_of_byte.toArray();
		BufferedImage buf_img = null;
		try {
			InputStream in = new ByteArrayInputStream( byte_array );
			buf_img = ImageIO.read( in );
			JFrame frame = new JFrame();
			frame.setTitle( window_title );
			frame.getContentPane().add( new JLabel( new ImageIcon( buf_img ) ) );
			frame.pack();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	
	/**
	 * 
	 * @param image (BGRbase color)
	 * @return image (Gray Scale base color)
	 */
	public static Mat bgr2gray( Mat image ) {
		Mat grayImage = new Mat( image.rows(), image.cols(), image.type() );
		Imgproc.cvtColor(image, grayImage, Imgproc.COLOR_BGRA2GRAY);
		Core.normalize( grayImage, grayImage, 0, 255, Core.NORM_MINMAX );
		return grayImage;
	}

	/**
	 * 
	 * @param image (BGRbase color)
	 * @return image (HSVbase color)
	 */

	public static Mat bgr2hsv( Mat image ) {
		Mat hsv=Mat.zeros(image.size(), image.type());
		Imgproc.cvtColor(image, hsv, Imgproc.COLOR_BGR2HSV);
		return hsv;
	}

	public static List<MatOfPoint> findCircle( List<MatOfPoint> contours ) {
		List<MatOfPoint> circle_contours = new ArrayList<MatOfPoint>();
		double form_area = Imgproc.contourArea( contours.get(0) );

		return circle_contours;
	}
}
