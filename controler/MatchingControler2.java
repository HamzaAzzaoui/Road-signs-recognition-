package controler;

import java.util.Vector;
import org.opencv.core.Mat;
import model.CommonsFactory;
import model.MatchingFactory;

/**
 * Class use to match an image with a panel database. 
 * It has been developed to determine whether an image match with a known panel or not
 * @author Aur�lien Bernard
 * @version 1.0
 */
public class MatchingControler2 {
	/* toMatchVector contain all images that are going to be test whether it match to a panel or not */
	/* refMatchVector contain all panels use as reference in matching process */
	/* matchingDecision : if a panel has been detected, it will contain at the same index the matching panel name */
	private Vector<Mat> toMatchVector = new Vector<Mat>();
	private Vector<Mat> refMatchVector = new Vector<Mat>();
	public Vector<String> matchingDecision;
	
	/**
	 * Method called to initialize MatchingControler. It will fill toMatchVector with the Descriptor of the given
	 * matrix image. It will import automatically the default database from images.
	 * @param toMatchVector : The matrix (bgr) to be testing
	 */
	public MatchingControler2( Vector<Mat> toMatchVector ) {
		importReferencesFromImage();
		this.matchingDecision = new Vector<String>();
		this.toMatchVector = toMatchVector;
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
				distance = MatchingFactory.comparisonRatio( image, ref );
				System.out.println("d " + distance);
				dist.add( distance );
			}
			System.out.println("-------------------");
			ind = findMinDist( dist );
			if(ind == 0) matchingDecision.addElement( "30" );
			if(ind == 1) matchingDecision.addElement( "50" );
			if(ind == 2) matchingDecision.addElement( "70" );
			if(ind == 3) matchingDecision.addElement( "90" );
			if(ind == 4) matchingDecision.addElement( "110" );
			if(ind == 5) matchingDecision.addElement( "double" );
			if(ind == 6) matchingDecision.addElement( "inconnu" );
		}
	}
	
	/**
	 * Method called inside matchAll() function to search witch distance is the minimum
	 * @param dist : Vector of double element
	 * @return the minimum double contained by dist vector
	 */
	private int findMinDist( Vector<Double> dist ) {
		double ind = dist.elementAt(0), s = dist.size();
		for( int i = 1; i < s; i++ ) {
			ind = ( ind > dist.elementAt( i )) ? dist.elementAt( i ) : ind;
		}
		if( ind < 5000 )
			return dist.indexOf( ind );
		else 
			return 6;
	}
	
	/**
	 * Method called inside constructor to import the reference panels from all panel images.
	 * Complete this method to add further panel as reference panel.
	 */
	private void importReferencesFromImage() {
		/* simply add the name of the ref image in allPanelName vector to add the image to reference */
		Vector<String> allPanelName = new Vector<String>();
		allPanelName.add( "ref30.jpg" );
		allPanelName.add( "ref50.jpg" );
		allPanelName.add( "ref70.jpg" );
		allPanelName.add( "ref90.jpg" );
		allPanelName.add( "ref110.jpg" );
		allPanelName.add( "refdouble.jpg" );
		Mat referenceImage;
		for( String name : allPanelName ) {
			referenceImage = CommonsFactory.readImage( name );
			this.refMatchVector.addElement( referenceImage );
		}
	}
	
	/**
	 * Method called to print on console the decision vector.
	 */
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
