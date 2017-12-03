package model;

import java.awt.Point;
import java.util.Arrays;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.features2d.DMatch;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.FeatureDetector;
import org.opencv.features2d.Features2d;
import org.opencv.imgproc.Imgproc;

public class MatchingFactory {
	/**
	 * Method called to evaluate matching distance between two image descriptors. The distance is use to decide 
	 * if the two descriptors are the same panel or not. The minimal distance is the best matching.
	 * @param evaluatedImage : image descriptor matrix to be evaluated 
	 * @param referenceImage : reference image descriptor matrix
	 * @return the matching distance between the two descriptors
	 */
	public static double matchProbability( Mat imageDescriptor, Mat referenceDescriptor ) {
		MatOfDMatch matchs = getMatchDataFromDescriptor( imageDescriptor, referenceDescriptor );
		double tabDistanceKeyPoints[] = getDistanceFromMatOfDMatch( matchs ), total = 0;
		int nbKeyPoints = tabDistanceKeyPoints.length;
		for(int j=0; j<nbKeyPoints; j++){
			total += tabDistanceKeyPoints[j];
		}
		System.out.println("hey " + total );
		return total;
	}
	
	/**
	 * 
	 * @param toEvaluate
	 * @return
	 */
	public static int comparisonRatio( Mat testImage, Mat refImage ) {
//		CommonsFactory.imShow( "test" , testImage );
//		CommonsFactory.imShow( "ref" , refImage );
		testImage  = CommonsFactory.bgr2gray( testImage );
		refImage = CommonsFactory.bgr2gray( refImage );
		Size testS = testImage.size(), refS = refImage.size();
		Size s = ( testS.area() > refS.area() ) ? testS : refS;
		Imgproc.resize( testImage, testImage, s);
		Imgproc.resize( refImage, refImage, s);
//		CommonsFactory.imShow( "test n" , testImage );
//		CommonsFactory.imShow( "ref n" , refImage );
		Core.inRange( testImage, new Scalar( 127 ), new Scalar( 255 ), testImage);
		Core.inRange( refImage, new Scalar( 127 ), new Scalar( 255 ), refImage);
		Mat res = new Mat( s, testImage.type() );
		testImage = extractCircle( testImage );
		refImage = extractCircle( refImage );
//		CommonsFactory.imShow( "test n" , testImage );
//		CommonsFactory.imShow( "ref n" , refImage );
		Core.bitwise_not( refImage, refImage );
		Core.bitwise_or( testImage, refImage, res );
		//CommonsFactory.imShow( "res" , res );
		int iMax = (int) s.width, jMax = (int) s.height;
		int count = 0;
		double[] interm;
		for( int i = 0; i < iMax; i++ )
			for( int j = 0; j < jMax; j++ ) {
					interm = res.get( j, i );
					if( interm[0] < 75 ) {
						count++;
					}
				}
		return count;
	}
	
	private static Mat extractCircle( Mat grayImage) {
		Size s = grayImage.size();
		Mat res = Mat.zeros( grayImage.size(), grayImage.type() );
		int iMax = (int) s.width, jMax = (int) s.height;
		Point c = new Point( jMax/2, iMax/2 );
		int r = (iMax/2)*2/3;
		double[] interm;
		for( int i = 0; i < iMax; i++ )
			for( int j = 0; j < jMax; j++ ) {
				if ( (i-c.y)*(i-c.y) + (j-c.x)*(j-c.x) < r*r ) {
					res.put( j, i, grayImage.get( j, i) );
				}
					
			}
		return res;
	}

	/**
	 * 
	 * @param image
	 * @return
	 */
	public static MatOfKeyPoint extractKeyPointFromImage( Mat image ) {
		MatOfKeyPoint imageKeypoints = new MatOfKeyPoint();
		FeatureDetector orbDetector = FeatureDetector.create( FeatureDetector.ORB );
		orbDetector.detect( image, imageKeypoints );
		return imageKeypoints;
	}

	public static Mat getDescriptor( Mat image, MatOfKeyPoint imageKeyPoints ) {
		DescriptorExtractor orbExtractor = DescriptorExtractor.create(DescriptorExtractor.ORB);
		Mat objectDescriptor = new Mat( image.rows(), image.cols(), image.type() );
		orbExtractor.compute( image, imageKeyPoints, objectDescriptor );
		return objectDescriptor;
	}

	/**
	 * 
	 * @param evaluateDescriptor
	 * @param referenceDescriptor
	 * @return
	 */
	private static MatOfDMatch getMatchDataFromDescriptor( Mat evaluateDescriptor, Mat referenceDescriptor ) {
		// Match : comparaison des images
		MatOfDMatch matchs = new MatOfDMatch();
		DescriptorMatcher matcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE_L1);
		matcher.match( evaluateDescriptor,  referenceDescriptor, matchs);
		// Affichage du contenu de la matrice
		System.out.println(matchs.dump());
		// R�cup�ration de la matrice sous forme d'un tableau -> r�cup�rer les distances
		// Calcul du total des distances / nb points
		return matchs;
	}

	/**
	 * 
	 * @param matchs
	 * @return
	 */
	private static double[] getDistanceFromMatOfDMatch( MatOfDMatch matchs ) {
		DMatch[] d=matchs.toArray();
		int nbKeyPoints = d.length;
		double tabDistanceKeyPoints[] = new double[nbKeyPoints];
		for(int j=0; j<nbKeyPoints; j++){
			tabDistanceKeyPoints[j] = d[j].distance;
		}
		return tabDistanceKeyPoints;
	}

	/**
	 * Method called 
	 * @param tabDistanceKeyPoints
	 * @param ratio
	 * @param referenceImage
	 * @param referenceKeyPoints
	 * @param evaluatedImage
	 * @param evaluateKeyPoints
	 * @param matchs
	 */
	public static void printLogInformation( double[] tabDistanceKeyPoints, double ratio,
			Mat referenceImage, MatOfKeyPoint referenceKeyPoints, 
			Mat evaluatedImage, MatOfKeyPoint evaluateKeyPoints,
			MatOfDMatch matchs ) {
		System.out.println( "distance table = " + Arrays.toString( tabDistanceKeyPoints ) );
		System.out.println( "ratio = " + ratio );
		Mat matchedImage = new Mat(referenceImage.rows(),referenceImage.cols()*2, referenceImage.type());
		Features2d.drawMatches(evaluatedImage,  evaluateKeyPoints,  referenceImage, referenceKeyPoints, matchs, matchedImage);
		CommonsFactory.imShow( "Matched Image" , matchedImage );
	}
}
