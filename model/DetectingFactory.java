package model;

import java.util.ArrayList;
import java.util.Vector;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt4;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class DetectingFactory {

	/**
	 * 
	 * @param Vector of contours (obtained via "eliminateBoundaries")
	 * @param Reference image matrix(rgb)
	 * @return Vector of the little images to use for matching
	 */

	public static Vector<Mat> extractPanel(Vector<MatOfPoint> all_contours, Mat matbgr){
		
		Vector<Mat> panneauxdetectes = new Vector<Mat>();
		MatOfPoint2f matOfPoint2f=new MatOfPoint2f();

		float[] radius=new float[1];
		Point center=new Point();
		for(int c=0;c<all_contours.size();c++){
			MatOfPoint contour=all_contours.get(c);
			matOfPoint2f.fromList(contour.toList());
			Imgproc.minEnclosingCircle(matOfPoint2f, center, radius);
			Core.circle(matOfPoint2f, center, (int)radius[0],new Scalar(0,255,0),2);
			Rect rect=Imgproc.boundingRect(contour);

			Mat tmp=matbgr.submat(rect.y-(rect.height/20),rect.y+rect.height+(rect.height/20),rect.x-(rect.width/20),rect.x+rect.width+(rect.width/20));
			Mat panneau=Mat.zeros(tmp.size(), tmp.type());
			tmp.copyTo(panneau);
			panneauxdetectes.add(panneau);
			// pour dessiner la zone extraite (en vert)
			Core.rectangle(matbgr,new Point(rect.x,rect.y),new Point(rect.x+rect.width,rect.y+rect.height),new Scalar(0,255,0),2);
			//CommonsFactory.imShow("panneau",panneau);				


		}
		//CommonsFactory.imShow("Image avec les carres",matbgr);
		return panneauxdetectes;

	}
	
	public static Vector<Mat> extractPanel22222(Vector<MatOfPoint> all_contours, Mat matbgr){
		
		Vector<Mat> panneauxdetectes=new Vector<Mat>();
		ArrayList<MatOfPoint> contours=new ArrayList<MatOfPoint>();
		
		// permet de virer les contours trop petits
		for(int c=0;c<all_contours.size();c++){
			MatOfPoint contour=all_contours.get(c);
			double contourArea=Imgproc.contourArea(contour);
			if(contourArea>matbgr.size().height/10){
				contours.add(contour);
			}
		}
		MatOfPoint2f matOfPoint2f=new MatOfPoint2f();
		float[] radius=new float[1];
		Point center=new Point();
		for(int c=0;c<contours.size();c++){
			MatOfPoint contour=contours.get(c);
			double contourArea=Imgproc.contourArea(contour);
			
			
			matOfPoint2f.fromList(contour.toList());
			Imgproc.minEnclosingCircle(matOfPoint2f, center, radius);
			Rect rectinit=Imgproc.boundingRect(contour);
			boolean est1triangle=((contourArea/(rectinit.height*rectinit.width))>0.48)&&((contourArea/(rectinit.height*rectinit.width))<0.57);
			boolean est1cercle=((contourArea/(Math.PI*radius[0]*radius[0]))>=0.7);
			boolean est1carre=((contourArea/(rectinit.height*rectinit.width))>0.9);
			if(est1triangle||est1carre||est1cercle){
				Core.circle(matOfPoint2f, center, (int)radius[0],new Scalar(0,255,0),2);
				Rect rect=Imgproc.boundingRect(contour);
				
				Mat tmp=matbgr.submat(rect.y-(rect.height/20),rect.y+rect.height+(rect.height/20),rect.x-(rect.width/20),rect.x+rect.width+(rect.width/20));
				Mat panneau=Mat.zeros(tmp.size(), tmp.type());
				tmp.copyTo(panneau);
				panneauxdetectes.add(panneau);
				// pour dessiner la zone extraite (en vert)
				Core.rectangle(matbgr,new Point(rect.x,rect.y),new Point(rect.x+rect.width,rect.y+rect.height),new Scalar(0,255,0),2);
				//commonsFactory.imShow("panneau",panneau);				
			}
		
		}
		//commonsFactory.imShow("Image avec les carres",matbgr);
		return panneauxdetectes;

	}
	
	public static Mat extractPanelForGraphics(Vector<MatOfPoint> all_contours, Mat matbgr){
		
		Vector<Mat> panneauxdetectes = new Vector<Mat>();
		MatOfPoint2f matOfPoint2f=new MatOfPoint2f();

		float[] radius=new float[1];
		Point center=new Point();
		for(int c=0;c<all_contours.size();c++){
			MatOfPoint contour=all_contours.get(c);
			matOfPoint2f.fromList(contour.toList());
			Imgproc.minEnclosingCircle(matOfPoint2f, center, radius);
			Core.circle(matOfPoint2f, center, (int)radius[0],new Scalar(0,255,0),2);
			Rect rect=Imgproc.boundingRect(contour);

			Mat tmp=matbgr.submat(rect.y-(rect.height/20),rect.y+rect.height+(rect.height/20),rect.x-(rect.width/20),rect.x+rect.width+(rect.width/20));
			Mat panneau=Mat.zeros(tmp.size(), tmp.type());
			tmp.copyTo(panneau);
			panneauxdetectes.add(panneau);
			// pour dessiner la zone extraite (en vert)
			Core.rectangle(matbgr,new Point(rect.x,rect.y),new Point(rect.x+rect.width,rect.y+rect.height),new Scalar(0,255,0),2);
			//CommonsFactory.imShow("panneau",panneau);				


		}
		//commonsFactory.imShow("Image avec les carres",matbgr);
		return matbgr;
	
	}

	public static Vector<MatOfPoint> eliminateBoundaries(Vector<MatOfPoint> all_contours, Mat matbgr){
		
		Vector<MatOfPoint> contours=new Vector();
		MatOfPoint2f matOfPoint2f=new MatOfPoint2f();
		float[] radius=new float[1];
		Point center=new Point();

		// permet de virer les contours trop petits
		for(int c=0;c<all_contours.size();c++){
			MatOfPoint contourcurrent=all_contours.get(c);
			double contourArea=Imgproc.contourArea(contourcurrent);

			matOfPoint2f.fromList(contourcurrent.toList());
			Imgproc.minEnclosingCircle(matOfPoint2f, center, radius);
			Rect rectinit=Imgproc.boundingRect(contourcurrent);
			boolean est1triangle=((contourArea/(rectinit.height*rectinit.width))>0.48)&&((contourArea/(rectinit.height*rectinit.width))<0.57);
			boolean est1cercle=((contourArea/(Math.PI*radius[0]*radius[0]))>=0.7);
			boolean est1carre=((contourArea/(rectinit.height*rectinit.width))>0.9);
			//si le contour est de taille correcte et qu'il a une forme de panneau
			if((contourArea>matbgr.size().height/10)&((est1cercle))){
				contours.add(contourcurrent);
			}
		}
		System.out.println(contours.size());
		return contours;
	}
	
	/**
	 * Method called to apply "Canny" filter on image to detect boundaries inside a binary image
	 * @param image : The image matrix source
	 * @return The same image with the detected boundaries
	 */


	//s'applique sur une image deja seuill�e

	public static Vector<MatOfPoint> findBoundaries( Mat image ) {
		int threshold = 100;
		Mat canny_out = new Mat();
		Vector<MatOfPoint> contours = new Vector<MatOfPoint>();
		MatOfInt4 hierarchy = new MatOfInt4();
		Imgproc.Canny( image, canny_out, threshold, threshold*2 );
		Imgproc.findContours( canny_out, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE );
		Mat drawing = Mat.zeros( canny_out.size(), CvType.CV_8UC3 );
		Random rand = new Random();
		Scalar color;
		for ( int i = 0; i < contours.size(); i++ ) {
			color = new Scalar( rand.nextInt(255 - 0 + 1), rand.nextInt(255 - 0 + 1), rand.nextInt(255 - 0 + 1) );
			Imgproc.drawContours( drawing, contours, i, color );
		}
		return contours; //drawing; CHANGEMENT
	}

	/**
	 * Method called to filter an image and get the filtered image between 2 Scalars HSVbase color. for each filter
	 * 2 scalar variables are required in the specified list. 
	 * @param image : Original image source (HSVbase color)
	 * @param nb_filter : number of filter wanted
	 * @param filter_list : Scalar list that defined the different filter (HSVbase color)
	 * @return The filtered image (HSVbase color)
	 * @throws Exception 
	 */
	public static Mat filterImageByColor( Mat image , int nb_filter, List<Scalar> filter_list ) throws Exception {
		if ( nb_filter*2 > filter_list.size() ) 
			throw new Exception( "filter_list must contain enough Scalar to proceed filter" );
		if ( nb_filter == 0 ) 
			return image;
		// Working
		Mat threshold_image = new Mat();
		// Initialize threshold_image with 0
		Core.inRange( image, new Scalar( 179, 255, 255 ), new Scalar( 179, 255, 255 ), threshold_image);
		Mat threshold_image1 = new Mat();
		Scalar scalar1, scalar2;
		for (int i = 0; i < nb_filter; i++) {
			// For each filter, use two scalar value from the specific list to get pixel in range
			scalar1 = filter_list.get(i*2);
			scalar2 = filter_list.get(i*2+1);
			Core.inRange( image, scalar1, scalar2, threshold_image1 );
			// add the selected pixel to the filter
			Core.bitwise_or( threshold_image1, threshold_image, threshold_image );
		}
		// filtering the image with the constructed matrix
		Mat res = new Mat( image.size(), image.type() );
		Imgproc.GaussianBlur( threshold_image, res, new Size( 3, 3 ), 2, 2 );
		//CommonsFactory.imShow( "testet", res);
		return res;
	}






}
