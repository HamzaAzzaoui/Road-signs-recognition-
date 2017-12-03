package vue;

import java.awt.List;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Vector;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfInt;
import org.opencv.core.MatOfInt4;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.FeatureDetector;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

import model.CommonsFactory;
import model.DetectingFactory;



public class PrincipalDetect {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		int nb_filter=3;
		Vector<Scalar> filter_list=new Vector<Scalar>();
		filter_list.add(new Scalar(0,105,20));
		filter_list.add(new Scalar(10,255,255));
		filter_list.add(new Scalar(170,105,20));
		filter_list.add(new Scalar(179,255,255));
		filter_list.add(new Scalar(105,105,20));
		filter_list.add(new Scalar(125,255,255));
		
		
		Mat matinit=new Mat();
		matinit=CommonsFactory.readImage("s_p10.jpg.png");
//		CommonsFactory.imShow("image",mat);
		Mat mathsv=CommonsFactory.bgr2hsv(matinit);
		Mat matseuil=DetectingFactory.filterImageByColor(mathsv , nb_filter, filter_list );
		CommonsFactory.imShow("seuillage",matseuil);
		
		Vector<MatOfPoint> matcontour=DetectingFactory.findBoundaries(matseuil);
		Vector<MatOfPoint> boncontour=DetectingFactory.eliminateBoundaries(matcontour, matinit);
		
		//CommonsFactory.imShow("reco",extraire_panneau(matcontour));
		Vector<Mat> list_panneau=DetectingFactory.extractPanel(boncontour,matinit);
		
	}


		
		
		
		
		
		
}
