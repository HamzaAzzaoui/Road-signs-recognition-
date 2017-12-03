package controler;

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
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfInt4;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.features2d.DMatch;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.FeatureDetector;
import org.opencv.features2d.Features2d;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;


public class Test2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	/*	Mat mat = Mat.eye(3, 3, CvType.CV_8UC1);
		Mat mat2 = Mat.ones(4, 4, CvType.CV_8UC1);
		Mat mat3 = Mat.zeros(5, 5, CvType.CV_8UC1);
		System.out.println(" mat = " +  mat.dump() );
		System.out.println(" mat = " +  mat2.dump() );
		System.out.println(" mat = " +  mat3.dump() );  */
//		Mat image = LectureImage("imageopenCV.bmp");
//		//System.out.println(" mat = " + image.dump());
//		double[] pixel = image.get(0, 0);
//		afficheTab(pixel);
//		afficheAvecPoints(LectureImage("opencv.png"));
//		Mat bgr = LectureImage("bgr.png");
//		List mv = new ArrayList();
//		Core.split(bgr, (List<Mat>) mv);
//		
//		Mat b = (Mat) mv.get(0);
//		Mat b1 = Mat.zeros(b.rows(), b.cols(), CvType.CV_8UC1);
//		List bmv = new ArrayList();
//		bmv.add(b);
//		bmv.add(b1);
//		bmv.add(b1);
//		Mat b1f = Mat.zeros(b.rows(), b.cols(), CvType.CV_8UC3);
//		Core.merge(bmv, b1f);
//		
//		Mat g = (Mat) mv.get(1);
//		Mat g1 = Mat.zeros(g.rows(), g.cols(), CvType.CV_8UC1);
//		List bmv1 = new ArrayList();
//		bmv1.add(g1);
//		bmv1.add(g);
//		bmv1.add(g1);
//		Mat b2f = Mat.zeros(g.rows(), g.cols(), CvType.CV_8UC3);
//		Core.merge(bmv1, b2f);
//		
//		Mat r = (Mat) mv.get(2);
//		Mat r1 = Mat.zeros(r.rows(), r.cols(), CvType.CV_8UC1);
//		List bmv2 = new ArrayList();
//		bmv2.add(r1);
//		bmv2.add(r1);
//		bmv2.add(r);
//		Mat b3f = Mat.zeros(r.rows(), r.cols(), CvType.CV_8UC3);
//		Core.merge(bmv2, b3f);
//		
//		ImShow("b", b1f);
//		ImShow("g", b2f);
//		ImShow("r", b3f);
		
//		Mat imagepng = LectureImage("hsv.png");
//		ImShow("png",imagepng);
//		Mat imagehsv = Mat.zeros(imagepng.rows(), imagepng.cols(), CvType.CV_8UC3);
//		Imgproc.cvtColor(imagepng, imagehsv, Imgproc.COLOR_BGR2HSV);
//		ImShow("hsv",imagehsv);
//		
//		List mv = new ArrayList();
//		Core.split(imagehsv, (List<Mat>) mv);
//		
//		Mat b = (Mat) mv.get(0);
//		Mat b1 = Mat.zeros(b.rows(), b.cols(), CvType.CV_8UC1);
//		List bmv = new ArrayList();
//		bmv.add(b);
//		bmv.add(b1);
//		bmv.add(b1);
//		Mat b1f = Mat.zeros(b.rows(), b.cols(), CvType.CV_8UC3);
//		Core.merge(bmv, b1f);
//		
//		Mat g = (Mat) mv.get(1);
//		Mat g1 = Mat.zeros(g.rows(), g.cols(), CvType.CV_8UC1);
//		List bmv1 = new ArrayList();
//		bmv1.add(g1);
//		bmv1.add(g);
//		bmv1.add(g1);
//		Mat b2f = Mat.zeros(g.rows(), g.cols(), CvType.CV_8UC3);
//		Core.merge(bmv1, b2f);
//		
//		Mat r = (Mat) mv.get(2);
//		Mat r1 = Mat.zeros(r.rows(), r.cols(), CvType.CV_8UC1);
//		List bmv2 = new ArrayList();
//		bmv2.add(r1);
//		bmv2.add(r1);
//		bmv2.add(r);
//		Mat b3f = Mat.zeros(r.rows(), r.cols(), CvType.CV_8UC3);
//		Core.merge(bmv2, b3f);
//		
//		Mat m1 = Mat.zeros(b.rows(), b.cols(), CvType.CV_8UC3);
//		Imgproc.cvtColor(b3f, m1, Imgproc.COLOR_HLS2BGR);
//		
//		
//		ImShow("b", m1);
//		ImShow("g", b2f);
//		ImShow("r", b3f);
//		
//		Mat m = LectureImage("circles_rectangles.jpg");
//		Mat th = DetecterCercles(m);
//		ImShow("cercles rouges",th);
//		List p = DetecterContours(m);
		
		ExtractionBalleRouge("trente_alvin(1).png");
		//saturation(LectureImage("trente_alvin.png"));
		
	}
	
	public static Mat LectureImage(String fichier){
		File f = new File(fichier);
		Mat m = Highgui.imread(f.getAbsolutePath());
		return m;
	}
	
	public static void afficheTab (double [] a){
		for(int i=0; i<a.length; i++){
			System.out.println(a[i]);
		}
	}
	
	public static void afficheAvecPoints(Mat m){
		for(int i=0; i<m.rows(); i++)
		{
			for(int j=0; j<m.cols(); j++)
			{
				double[] pixel = m.get(i,j);
				if(pixel[0]==255.0 && pixel[1]==255.0 && pixel[2]==255.0){
					System.out.print(".");
				}
				else{
					System.out.print("+");
				}
				
			}
			System.out.println();
		}
	}
	
	public static void ImShow(String title, Mat img){
		MatOfByte matOfByte = new MatOfByte();
		Highgui.imencode(".png", img, matOfByte);
		byte[] byteArray = matOfByte.toArray();
		BufferedImage bufImage = null;
		try{
			InputStream in = new ByteArrayInputStream(byteArray);
			bufImage = ImageIO.read(in);
			JFrame frame = new JFrame();
			frame.setTitle(title);
			frame.getContentPane().add(new JLabel(new ImageIcon(bufImage)));
			frame.pack();
			frame.setVisible(true);
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static Mat DetecterRouge( Mat m ){
		ImShow("png",m);
		Mat hsv_image = Mat.zeros(m.size(), m.type());
		Imgproc.cvtColor(m, hsv_image, Imgproc.COLOR_BGR2HSV);
		Mat th = new Mat();
		Mat th1 = new Mat();
		Mat th2 = new Mat();
		Core.inRange(hsv_image, new Scalar(160,100,100), new Scalar(180,255,255), th2);
		Core.inRange(hsv_image, new Scalar(0,100,100), new Scalar(10,255,255), th1);
		Core.bitwise_or(th1, th2, th);
		Imgproc.GaussianBlur(th, th, new Size(9,9), 2 ,2);
		ImShow("cercles rouges",th);
		return th;
	}
	
	public static void saturation(Mat m){
		Mat hsv_image = Mat.zeros(m.size(), m.type());
		Imgproc.cvtColor(m, hsv_image, Imgproc.COLOR_BGR2HSV);
		Mat r = Mat.zeros(m.size(), m.type());
		Mat s = Mat.ones(m.size(), 0);
		System.out.println(s.dump());
		
	}
	
//	public static Mat multiplicationScalaire(Mat m, int f){
//		for(int i=0; i<m.size(); i++){
//			m.get(, col)
//		}
//	}
	
	public static List<MatOfPoint> DetecterContoursRouge( Mat m){
		ImShow("png",m);
		Mat hsv_image = Mat.zeros(m.size(), m.type());
		Imgproc.cvtColor(m, hsv_image, Imgproc.COLOR_BGR2HSV);
		Mat th = new Mat();
		Mat th1 = new Mat();
		Mat th2 = new Mat();
		Core.inRange(hsv_image, new Scalar(170,50,50), new Scalar(179,255,255), th2);
		Core.inRange(hsv_image, new Scalar(0,30,30), new Scalar(10,255,255), th1);
		Core.bitwise_or(th1, th2, th);
		Imgproc.GaussianBlur(th, th, new Size(9,9), 2 ,2);
		int thresh = 100;
		Mat canny_out = new Mat();
		List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
		Mat hierarchy = new Mat();
		Imgproc.Canny(th, canny_out, thresh, thresh*2);
		Imgproc.findContours(canny_out, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
		Mat drawing = Mat.zeros(canny_out.size(), CvType.CV_8UC3);
		Random rand = new Random();
		
		for(int i=0; i<contours.size(); i++){
			Scalar color = new Scalar(rand.nextInt(255-0+1), rand.nextInt(255-0+1), rand.nextInt(255-0+1));
			Imgproc.drawContours(drawing, contours, i, color);
		}
		ImShow("contour rouge",drawing);
		return contours;
		
	}
	public static List<MatOfPoint> DetecterContours2( Mat th){
		int thresh = 100;
		Mat canny_out = new Mat();
		List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
		Mat hierarchy = new Mat();
		Imgproc.Canny(th, canny_out, thresh, thresh*2);
		Imgproc.findContours(canny_out, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
		Mat drawing = Mat.zeros(canny_out.size(), CvType.CV_8UC3);
		Random rand = new Random();
		for(int i=0; i<contours.size(); i++){
			Scalar color = new Scalar(rand.nextInt(255-0+1), rand.nextInt(255-0+1), rand.nextInt(255-0+1));
			Imgproc.drawContours(drawing, contours, i, color);
		}
		ImShow("contour",drawing);
		return contours;
		
	}
	
	public static void Cercles(String fichier){
		Mat m = LectureImage(fichier);
		ImShow("cercles", m);
		List contours = DetecterContoursRouge(m);
		
		MatOfPoint2f mat2f = new MatOfPoint2f();
		float[] radius = new float[1];
		Point center = new Point();
		
		for(int i=0; i<contours.size(); i++){
			MatOfPoint contour = (MatOfPoint) contours.get(i);
			double contourArea = Imgproc.contourArea(contour);
			mat2f.fromList(contour.toList());
			Imgproc.minEnclosingCircle(mat2f, center, radius);
			if((contourArea/(Math.PI*radius[0]*radius[0])) >= 0.8){
				Core.circle(m, center, (int)radius[0], new Scalar(0,255,0),2);
			}
		}
		
		ImShow("detection",m);	
	}
	
	public static void ExtractionBalleRouge(String fichier){
		Mat m = LectureImage(fichier);
		ImShow("cercles", m);
		List contours = DetecterContoursRouge(m);
		
		MatOfPoint2f mat2f = new MatOfPoint2f();
		float[] radius = new float[1];
		Point center = new Point();
		for(int i=0; i<contours.size(); i++){
			MatOfPoint contour = (MatOfPoint) contours.get(i);
			double contourArea = Imgproc.contourArea(contour);
			mat2f.fromList(contour.toList());
			Imgproc.minEnclosingCircle(mat2f, center, radius);
			if((contourArea/(Math.PI*radius[0]*radius[0])) >= 0.8){
				Rect rect = Imgproc.boundingRect(contour);
				Core.rectangle(m, new Point(rect.x,rect.y), new Point(rect.x+rect.width,rect.y+rect.height), new Scalar(0,255,0));
				Mat tmp = m.submat(rect.y,rect.y+rect.height,rect.x,rect.x+rect.width);
				Mat ball = Mat.zeros(tmp.size(), tmp.type());
				tmp.copyTo(ball);
				///////////////////////////////////////////////////////////////////////////////////////////////////////
				Mat sroadSign = LectureImage("panneau_30.jpg");
				Mat sObject = new Mat();
				
				Imgproc.resize(ball, sObject, sroadSign.size());
				Mat grayObject = new Mat(sObject.rows(),sObject.cols(),sObject.type());
				Imgproc.cvtColor(sObject, grayObject, Imgproc.COLOR_BGRA2GRAY);
				Core.normalize(grayObject, grayObject,0,255, Core.NORM_MINMAX);
				
				Mat graySign = new Mat(sroadSign.rows(),sroadSign.cols(),sroadSign.type());
				Imgproc.cvtColor(sroadSign, graySign, Imgproc.COLOR_BGRA2GRAY);
				Core.normalize(graySign, graySign,0,255, Core.NORM_MINMAX);
				
				FeatureDetector orbDetector = FeatureDetector.create(FeatureDetector.ORB);
				DescriptorExtractor orbExtractor = DescriptorExtractor.create(DescriptorExtractor.ORB);
				
				MatOfKeyPoint objectKeypoints = new MatOfKeyPoint();
				orbDetector.detect(grayObject, objectKeypoints);
				
				MatOfKeyPoint signKeypoints = new MatOfKeyPoint();
				orbDetector.detect(graySign, signKeypoints);
				
				Mat objectDescriptor = new Mat(ball.rows(),ball.cols(),ball.type());
				orbExtractor.compute(grayObject, objectKeypoints, objectDescriptor);
				
				Mat signDescriptor = new Mat(sroadSign.rows(), sroadSign.cols(), sroadSign.type());
				orbExtractor.compute(graySign, signKeypoints, signDescriptor);
				
				MatOfDMatch matchs = new MatOfDMatch();
				DescriptorMatcher matcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE_L1);
				matcher.match(objectDescriptor,  signDescriptor, matchs);
				
				System.out.println(matchs.dump());
				DMatch[] d=matchs.toArray();
				double nombre=0;
				for(int j=0; j<matchs.rows(); j++){
					nombre=nombre+d[j].distance;
					
				}
				double moyenne=(nombre);
				System.out.println(moyenne);
				Mat matchedImage = new Mat(sroadSign.rows(),sroadSign.cols()*2, sroadSign.type());
				Features2d.drawMatches(sObject,  objectKeypoints,  sroadSign,  signKeypoints, matchs, matchedImage);
				
				ImShow("Ball",graySign);
				ImShow("Match",matchedImage);
			}
		}
		
	}
	

}
