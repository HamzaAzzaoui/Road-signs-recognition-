package controler;

import java.util.Vector;
import org.opencv.core.Mat;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import model.CommonsFactory;
import model.MatchingFactory;

/**
 * Class use to match an image with a panel database. 
 * It has been developed to determine whether an image match with a known panel or not
 * @author Aur�lien Bernard
 * @version 1.0
 */
public class MatchingControler {
	/* toMatchVector contain all images that are going to be test whether it match to a panel or not */
	private Vector<Mat> toMatchVector = new Vector<Mat>();
	/* refMatchVector contain all panels use as reference in matching process */
	private Vector<Mat> refMatchVector = new Vector<Mat>();
	/* matchingDecision : if a panel has been detected, it will contain at the same index the matching panel name */
	public Vector<String> matchingDecision;
	/**
	 * Method called to initialize MatchingControler. It will fill toMatchVector with the Descriptor of the given
	 * matrix image. It will import automatically the default database from images.
	 * @param toMatchVector : The matrix (bgr) to be testing
	 */
	public MatchingControler( Vector<Mat> toMatchVector ) {
		importReferencesFromImage();
		this.matchingDecision = new Vector<String>();
		Size referenceSize = CommonsFactory.readImage( "panneau_70.png" ).size();
		for( Mat image : toMatchVector ) {
//			CommonsFactory.imShow( "image", image );
			Mat goalImage = new Mat();
			Imgproc.resize(image, goalImage, referenceSize);
			Mat graySign = CommonsFactory.bgr2gray( goalImage );
			MatOfKeyPoint signKeypoints = MatchingFactory.extractKeyPointFromImage( graySign );
			Mat signDescriptor = MatchingFactory.getDescriptor( graySign, signKeypoints );
			this.toMatchVector.addElement( signDescriptor );
		}
	}

	/**
	 * Method called to get result of matching process.
	 * matchAll() function must be executed first.
	 * @return the decision string vector.
	 */
	public Vector<Mat> getToMatchVector() {
		return toMatchVector;
	}
	
	/**
	 * Method called to activate matching process. For each descriptor from toMatchVector, it will be
	 * compare to each descriptor from reference panels. The matching value is the distance between
	 * matching key points. The minimal matching distance will be considered as the best decision and
	 * the panel will be associated to the image by adding to matchingDecision the name of the panel at the
	 * image index. 
	 */
	public void matchAll() {
		Vector<Double> dist = new Vector<Double>();
		double distance; int ind, count;
		for( Mat image : toMatchVector ) {
			dist.clear();
			count = 0;
			for( Mat ref : refMatchVector ) {
				count ++;
				distance = MatchingFactory.matchProbability( image, ref );
				dist.add( distance );
			}
			ind = findMinDist( dist );
			if(ind == 0) matchingDecision.addElement( "20" );
			if(ind == 1) matchingDecision.addElement( "30" );
			if(ind == 2) matchingDecision.addElement( "50" );
			if(ind == 3) matchingDecision.addElement( "70" );
			if(ind == 4) matchingDecision.addElement( "80" );
			if(ind == 5) matchingDecision.addElement( "90" );
			if(ind == 6) matchingDecision.addElement( "110" );
			if(ind == 7) matchingDecision.addElement( "130" );
		}
	}
	
	private int findMinDist( Vector<Double> dist ) {
		double ind = dist.elementAt(0), s = dist.size();
		for( int i = 1; i < s; i++ ) {
			ind = ( ind > dist.elementAt( i )) ? dist.elementAt( i ) : ind;
		}
		return dist.indexOf( ind );
	}
	
	private void importReferencesFromImage() {
		Vector<String> allPanelName = new Vector<String>();
		allPanelName.add( "panneau_20.png" );
		allPanelName.add( "panneau_30.png" );
		allPanelName.add( "panneau_50.png" );
		allPanelName.add( "panneau_70.png" );
		allPanelName.add( "panneau_80.png" );
		allPanelName.add( "panneau_90.png" );
		allPanelName.add( "panneau_110.png" );
		allPanelName.add( "panneau_130.png" );
		Mat referenceImage;
		Mat graySign;
		MatOfKeyPoint signKeypoints;
		Mat signDescriptor;
		for( String name : allPanelName ) {
			referenceImage = CommonsFactory.readImage( name );
			graySign = CommonsFactory.bgr2gray( referenceImage );
			signKeypoints = MatchingFactory.extractKeyPointFromImage( graySign );
			signDescriptor = MatchingFactory.getDescriptor( graySign, signKeypoints );
			this.refMatchVector.addElement( signDescriptor );
		}
	}
	
	public String toString() {
		String str = "MATCHINGCONTROLER - ELEMENTS\n";
//		str += "toMatchVector\n";
//		for( Mat image : toMatchVector ) {
//			str += image.dump() + "\t";
//		}
//		str += "refMatchVector\n";
//		for( Mat image : refMatchVector ) {
//			str += image.dump() + "\t";
//		}
		str += "decision\n";
		for( String comp : matchingDecision ) {
			str += comp + "\t";
		}
		return str;
	}
}
