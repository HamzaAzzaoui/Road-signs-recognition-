package vue;

import java.util.Vector;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import controler.MatchingControler;
import model.CommonsFactory;

public class PrincipalMatching {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Vector<Mat> toMatchVector = new Vector<Mat>();
		Vector<String> toMatchName = new Vector<String>();
		toMatchName.add( "trente_alvin.png" );
		toMatchName.add( "trente_alvin(1).png" );
		toMatchName.add( "panneau_70.png" );
		toMatchName.add( "panneau_30.jpg" );
		toMatchName.add( "panneau_50.jpg" );
		toMatchName.add( "panneau_20.jpg" );
		toMatchName.add( "panneau_80.png" );
		toMatchName.add( "panneau_90.jpg" );
		toMatchName.add( "panneau_130.png" );
		toMatchName.add( "panneau_110.png" );
		for( String name : toMatchName ) {
			Mat image = CommonsFactory.readImage( name );
			toMatchVector.add( image );
		}
		MatchingControler test = new MatchingControler( toMatchVector );
		test.matchAll();
		System.out.println( test );
	}

}
