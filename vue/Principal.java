package vue;

import java.util.ArrayList;
import java.util.Vector;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;

import controler.MatchingControler;
import model.CommonsFactory;
import model.DetectingFactory;

public class Principal {

	public static void main(String[] args) throws Exception {
		// TODO Detection part
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		int nb_filter=3;
		ArrayList<Scalar> filter_list=new ArrayList<Scalar>();
		filter_list.add(new Scalar(0,30,30));
		filter_list.add(new Scalar(20,255,255));
		filter_list.add(new Scalar(160,50,50));
		filter_list.add(new Scalar(179,255,255));
		filter_list.add(new Scalar(105,150,40));
		filter_list.add(new Scalar(125,255,255));
		
		Mat matinit=new Mat();
		matinit=CommonsFactory.readImage("Panneau30.jpg");
//		CommonsFactory.imShow("image",mat);
		Mat mathsv=CommonsFactory.bgr2hsv(matinit);
		Mat matseuil=DetectingFactory.filterImageByColor(mathsv , nb_filter, filter_list );
		CommonsFactory.imShow("seuillage",matseuil);
		
		Vector<MatOfPoint> matcontour=DetectingFactory.findBoundaries(matseuil);
		
		//CommonsFactory.imShow("reco",extraire_panneau(matcontour));
		Vector<Mat> list_panneau = DetectingFactory.extractPanel(matcontour,matinit);
		
		// TODO Matching part
		Vector<Mat> toMatchVector = new Vector<Mat>();
		for( Mat image : list_panneau ) {
			toMatchVector.add( image );
		}
		MatchingControler test = new MatchingControler( toMatchVector );
		test.matchAll();
		System.out.println( test );
	}
}
